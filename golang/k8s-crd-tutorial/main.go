package main

import (
	"context"
	"fmt"
	"os"
	"path"

	crdclientset "k8s-crd-tutorial/pkg/generated/clientset/versioned"

	matav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/client-go/tools/clientcmd"
)

func main() {
	home, _ := os.UserHomeDir()
	config, _ := clientcmd.BuildConfigFromFlags("", path.Join(home, ".kube/config"))

	clientset, _ := crdclientset.NewForConfig(config)
	appleBuyer, _ := clientset.K8scrdtutorialV1alpha1().Buyers("default").Get(context.Background(), "buyer-apple", matav1.GetOptions{})
	fmt.Println("Name:\t", appleBuyer.Spec.Name)
	fmt.Println("Amount:\t", *appleBuyer.Spec.Amount)
	fmt.Println("Price:\t", *appleBuyer.Spec.Price)

	appleSeller, _ := clientset.K8scrdtutorialV1alpha1().Sellers("default").Get(context.Background(), "seller-apple", matav1.GetOptions{})
	fmt.Println("Name:\t", appleSeller.Spec.Name)
	fmt.Println("Amount:\t", *appleSeller.Spec.Amount)
	fmt.Println("Price:\t", *appleSeller.Spec.Price)
	fmt.Println("Money:\t", *appleSeller.Spec.Money)
}
