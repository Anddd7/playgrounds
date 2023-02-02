package tutorials

import "fmt"

type Greeting func(name string) string

func (g Greeting) say(n string) {
	fmt.Println(g(n))
}

func english(name string) string {
	return "Hello, " + name
}
func chinese(name string) string {
	return "你好, " + name
}

func Test() {
	// 转换成 Greeting 类型的函数对象
	greets := []Greeting{Greeting(english), Greeting(chinese)}

	for _, item := range greets {
		item.say("ni")
	}
}
