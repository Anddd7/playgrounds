package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"path"
	"strings"
)

const (
	basepath = "/Users/adliao/Library/Mobile Documents/iCloud~com~logseq~logseq/Documents/Anddd 3.0/assets"
)

func main() {
	renameUnderFolder(basepath)
}

func renameUnderFolder(dir string) {
	files, _ := ioutil.ReadDir(dir)
	for _, file := range files {

		if file.IsDir() {
			renameUnderFolder(fmt.Sprintf("%s/%s", dir, file.Name()))
		} else {
			fullname := file.Name()
			name := path.Base(fullname)
			suffix := path.Ext(fullname)
			prefix := name[0 : len(name)-len(suffix)]

			if strings.HasSuffix(prefix, " 2") {
				// fmt.Printf("fullname: %s", fullname)
				// fmt.Printf("name: %s, suffix: %s, prefix: %s", name, suffix, prefix)

				oldpath := fmt.Sprintf("%s/%s%s", dir, prefix, suffix)
				newpath := fmt.Sprintf("%s/%s%s", dir, strings.TrimSuffix(prefix, " 2"), suffix)

				fmt.Printf("oldpath: %s -> newpath: %s\n", oldpath, newpath)
				os.Rename(oldpath, newpath)
			}
		}
	}
}
