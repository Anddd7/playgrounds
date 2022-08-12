package main

import (
	"fmt"
)

func init() {
	fmt.Println("init1:", a)
	fmt.Println("init1:", b)
}

func init() {
	fmt.Println("init2:", a)
	fmt.Println("init2:", b)
}

var a = 10

const b = 100
