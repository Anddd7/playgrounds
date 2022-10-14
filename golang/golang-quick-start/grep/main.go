package main

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
	"regexp"
)

const (
	basepath = "xxx"
	folder   = "xxx"
)

func main() {
	grepUnderFolder(fmt.Sprintf("%s/%s", basepath, folder), folder)
}

func grepUnderFolder(dir string, name string) {
	r, _ := regexp.Compile("variable \"(.*)\"")

	files, _ := ioutil.ReadDir(dir)
	for _, file := range files {
		if file.IsDir() {
			grepUnderFolder(fmt.Sprintf("%s/%s", dir, file.Name()), file.Name())
		} else if file.Name() == "variables.tf" || file.Name() == "variable.tf" {
			f, _ := os.Open(fmt.Sprintf("%s/%s", dir, file.Name()))
			scanner := bufio.NewScanner(f)
			fmt.Println(fmt.Sprintf("module \"%s\"{", name))
			fmt.Println(fmt.Sprintf("source = \"./%s/%s\"", folder, name))
			for scanner.Scan() {
				results := r.FindStringSubmatch(scanner.Text())
				if len(results) > 0 {
					fmt.Printf("%s = var.%s\n", results[1], results[1])
				}
			}
			fmt.Println("}")
		}
	}
}
