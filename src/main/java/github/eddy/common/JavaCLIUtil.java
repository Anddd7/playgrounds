package github.eddy.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCLIUtil {

  /**
   * execute javap
   */
  public static Stream<String> javap(String filePath, String options) {
    String command = "javap " + options + " " + filePath;
    try {
      Process p = Runtime.getRuntime().exec(command);
      return new BufferedReader(new InputStreamReader(p.getInputStream())).lines();
    } catch (IOException e) {
      e.printStackTrace();
      return Stream.empty();
    }
  }

  public static Stream<String> javap(String filePath) {
    return javap(filePath, "-c");
  }

  /**
   * get target class's machine code
   */
  public static Stream<String> javap(Class classType) {
    return javap(
        classType.getClassLoader().getResource("").getPath() + classType.getName().replace(".", "/")
            + ".class");
  }

  /**
   * get target class's machine code
   */
  public static void javapToDir(Class classType, String dir) {
    try {
      Path path = Paths.get(dir + "/" + classType.getName().replace(".", "/"));
      if (Files.notExists(path.getParent())) {
        Files.createDirectories(path.getParent());
      }
      Files.write(path, javap(classType).collect(Collectors.toList()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void printGCMessage(Class classType) {
    String command = "java -verbose:gc " + classType.getSimpleName();
    try {
      Process p = Runtime.getRuntime().exec(command);
      new BufferedReader(
          new InputStreamReader(p.getInputStream()))
          .lines()
          .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
