package main

import (
	"os"
	"path"

	"k8s-crd-tutorial/pkg/signals"

	"flag"
	"time"

	kubeinformers "k8s.io/client-go/informers"
	"k8s.io/client-go/kubernetes"
	"k8s.io/client-go/tools/clientcmd"
	"k8s.io/klog/v2"

	crdclientset "k8s-crd-tutorial/pkg/generated/clientset/versioned"

	informers "k8s-crd-tutorial/pkg/generated/informers/externalversions"
)

func main() {
	klog.InitFlags(nil)
	flag.Parse()
	// set up signals so we handle the first shutdown signal gracefully
	stopCh := signals.SetupSignalHandler()

	home, _ := os.UserHomeDir()
	config, _ := clientcmd.BuildConfigFromFlags("", path.Join(home, ".kube/config"))
	kubeClient, err := kubernetes.NewForConfig(config)
	clientset, _ := crdclientset.NewForConfig(config)

	kubeInformerFactory := kubeinformers.NewSharedInformerFactory(kubeClient, time.Second*30)
	crdInformerFactory := informers.NewSharedInformerFactory(clientset, time.Second*30)

	controller := NewController(kubeClient, clientset,
		crdInformerFactory.K8scrdtutorial().V1alpha1().Buyers(),
		crdInformerFactory.K8scrdtutorial().V1alpha1().Sellers(),
	)

	// notice that there is no need to run Start methods in a separate goroutine. (i.e. go kubeInformerFactory.Start(stopCh)
	// Start method is non-blocking and runs all registered informers in a dedicated goroutine.
	kubeInformerFactory.Start(stopCh)
	crdInformerFactory.Start(stopCh)

	if err = controller.Run(2, stopCh); err != nil {
		klog.Fatalf("Error running controller: %s", err.Error())
	}
}
