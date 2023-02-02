package tutorials

import "fmt"

type target struct {
	id, name string
	skills   map[string]string
}
type batch struct {
	one, two, three target
}

type query struct {
	id                   string
	targetId, targetName *string
	targetSkills         *map[string]string
}

func AssignValueViaPointer() {
	b := batch{
		two: target{
			id: "original",
		},
	}
	fmt.Printf("A: target id address for 1: %p \n", &b.one.id)
	fmt.Printf("A: target id address for 2: %p \n", &b.two.id)
	fmt.Printf("A: target id address for 3: %p \n", &b.three.id)
	fmt.Printf("-------------------------\n")

	pointers := map[int]*batch{
		0: &b,
	}
	items := map[int]batch{
		0: b,
	}

	// 传递值对象，在进入方法时会出现一次 copy，值对象内的地址和外部对象已经不一致
	// process(b)

	// 传递值地址，进入方法后操作的是原对象内存区域
	process(&b)

	// 通过源对象可直接打印出修改后的数据
	fmt.Printf("You can get the data from p: %s, %p\n", b, &b)

	// pointers 中存放的是源对象地址，通过指针寻址，也可打印数据
	fmt.Printf("You can get the data from pointers: %s, %p\n", *pointers[0], pointers[0])

	// items 中存放的数据在加入 map 时经过了 copy
	temp := items[0]
	fmt.Printf("You cannot get the data from items: %s, %p \n", items[0], &temp)

	items2 := map[int]batch{
		0: b,
	}

	// items2 中存放的数据在加入 map 已经是修改过的了，所以也可以取出数据
	temp = items2[0]
	fmt.Printf("You can get the data from items2: %s, %p \n", items2[0], &temp)
}

func process(pointer *batch) {
	queries := []query{
		{
			id:           "query1",
			targetId:     &pointer.one.id,
			targetName:   &pointer.one.name,
			targetSkills: &pointer.one.skills,
		},
		{
			id:           "query2",
			targetId:     &pointer.two.id,
			targetName:   &pointer.two.name,
			targetSkills: &pointer.two.skills,
		},
		{
			id:           "query3",
			targetId:     &pointer.three.id,
			targetName:   &pointer.three.name,
			targetSkills: &pointer.three.skills,
		},
	}

	fmt.Printf("B: target id address for 1: %p \n", &pointer.one.id)
	fmt.Printf("B: target id address for 2: %p \n", &pointer.two.id)
	fmt.Printf("B: target id address for 3: %p \n", &pointer.three.id)
	fmt.Printf("-------------------------\n")

	for i, query := range queries {

		*query.targetId = query.id
		*query.targetName = query.id + " target "
		*query.targetSkills = map[string]string{
			"a": query.id,
		}

		fmt.Printf("C: target id address for %d: %p \n", i+1, query.targetId)
		fmt.Printf("C: target id for %d: %s \n", i+1, *query.targetId)
	}
}
