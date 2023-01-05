package main

import (
	"context"
	"fmt"
	"os"
	"path"

	"anddd7.github.com/buyer-controller/pkg/generated/clientset/versioned"
	v1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/client-go/tools/clientcmd"
)

func main() {
	home, _ := os.UserHomeDir()
	config, _ := clientcmd.BuildConfigFromFlags("", path.Join(home, ".kube/config"))

	clientset, _ := versioned.NewForConfig(config)
	apple, _ := clientset.Anddd7V1beta1().Buyers("default").Get(context.Background(), "buyer-apple", v1.GetOptions{})
	fmt.Println("Name:\t", apple.Spec.Name)
	fmt.Println("Amount:\t", *apple.Spec.Amount)
	fmt.Println("Price:\t", *apple.Spec.Price)
}
