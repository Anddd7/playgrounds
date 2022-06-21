package com.github.anddd7.utils;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command Tool for
 * - javap
 * - java
 */
public final class JavaCommandLineTool {

  private static final Logger log = LoggerFactory.getLogger(JavaCommandLineTool.class);

  private static final String DEFAULT_SOURCECODE_DIR = "machine-code";
  private static final String JAVAP_COMMAND = "javap -p -s -c %s %s";
  private static final String JAVA_GC_COMMAND = "java -verbose:gc %s";

  /**
   * -----------------
   * |  javap utils  |
   * -----------------
   */
  public static void javap(Class clazz) throws IOException {
    javap(clazz, false);
  }

  public static void javap(Class clazz, boolean printStack) throws IOException {
    javapToDir(DEFAULT_SOURCECODE_DIR, clazz, printStack);
  }

  public static void javapToDir(String dir, Class clazz, boolean printStack) throws IOException {
    String classPath = convertTestPathToMain(clazz.getResource("/").getPath());
    String relatedClassPath = clazz.getName().replace(".", File.separator);
    String absoluteClassPath = format("%s%s.class", classPath, relatedClassPath);

    log.debug("ClassPath:{}", classPath);
    log.debug("RelatedClassPath:{}", relatedClassPath);
    log.debug("AbsoluteClassPath:{}", absoluteClassPath);

    Path path = Paths.get(dir, relatedClassPath);
    if (Files.notExists(path.getParent())) {
      Files.createDirectories(path.getParent());
    }
    Files.write(path, javapToLines(absoluteClassPath, printStack));
  }

  private static String convertTestPathToMain(String path) {
    return path.replace("test-classes", "classes");
  }

  private static List<String> javapToLines(String classFilePath, boolean printStack) {
    return executeCommand(format(JAVAP_COMMAND, printStack ? "-verbose" : "", classFilePath));
  }

  /**
   * -------------------
   * |  java gc utils  |
   * -------------------
   */
  public static void printGCMessage(Class classType) {
    executeCommand(format(JAVA_GC_COMMAND, classType.getSimpleName()))
        .stream()
        .reduce((s, s2) -> s + "/n" + s2)
        .ifPresent(s -> log.debug("\n" + s));
  }

  /**
   * ----------------------
   * |  command executor  |
   * ----------------------
   */
  private static List<String> executeCommand(String command) {
    log.debug("Execute Command:\n{}", command);
    try {
      Process p = Runtime.getRuntime().exec(command);

      LineReaderThread msgThread = new LineReaderThread(p.getInputStream());
      LineReaderThread errThread = new LineReaderThread(p.getErrorStream());
      msgThread.start();
      errThread.start();

      if (p.waitFor() != 0) {
        errThread.lines.forEach(log::error);
      }
      return msgThread.lines;
    } catch (InterruptedException | IOException e) {
      log.error("", e);
    }
    return Collections.emptyList();
  }

  static class LineReaderThread extends Thread {

    private List<String> lines;
    private InputStream inputStream;

    LineReaderThread(InputStream inputStream) {
      this.inputStream = inputStream;
      this.lines = new ArrayList<>();
    }

    @Override
    public void run() {
      try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          lines.add(line);
        }
      } catch (IOException e) {
        log.error("", e);
      }
    }
  }
}
