/*
Copyright The Kubernetes Authors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

// Code generated by informer-gen. DO NOT EDIT.

package v1beta1

import (
	"context"
	time "time"

	anddd7v1beta1 "anddd7.github.com/buyer-controller/pkg/apis/anddd7/v1beta1"
	versioned "anddd7.github.com/buyer-controller/pkg/generated/clientset/versioned"
	internalinterfaces "anddd7.github.com/buyer-controller/pkg/generated/informers/externalversions/internalinterfaces"
	v1beta1 "anddd7.github.com/buyer-controller/pkg/generated/listers/anddd7/v1beta1"
	v1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	runtime "k8s.io/apimachinery/pkg/runtime"
	watch "k8s.io/apimachinery/pkg/watch"
	cache "k8s.io/client-go/tools/cache"
)

// BuyerInformer provides access to a shared informer and lister for
// Buyers.
type BuyerInformer interface {
	Informer() cache.SharedIndexInformer
	Lister() v1beta1.BuyerLister
}

type buyerInformer struct {
	factory          internalinterfaces.SharedInformerFactory
	tweakListOptions internalinterfaces.TweakListOptionsFunc
	namespace        string
}

// NewBuyerInformer constructs a new informer for Buyer type.
// Always prefer using an informer factory to get a shared informer instead of getting an independent
// one. This reduces memory footprint and number of connections to the server.
func NewBuyerInformer(client versioned.Interface, namespace string, resyncPeriod time.Duration, indexers cache.Indexers) cache.SharedIndexInformer {
	return NewFilteredBuyerInformer(client, namespace, resyncPeriod, indexers, nil)
}

// NewFilteredBuyerInformer constructs a new informer for Buyer type.
// Always prefer using an informer factory to get a shared informer instead of getting an independent
// one. This reduces memory footprint and number of connections to the server.
func NewFilteredBuyerInformer(client versioned.Interface, namespace string, resyncPeriod time.Duration, indexers cache.Indexers, tweakListOptions internalinterfaces.TweakListOptionsFunc) cache.SharedIndexInformer {
	return cache.NewSharedIndexInformer(
		&cache.ListWatch{
			ListFunc: func(options v1.ListOptions) (runtime.Object, error) {
				if tweakListOptions != nil {
					tweakListOptions(&options)
				}
				return client.Anddd7V1beta1().Buyers(namespace).List(context.TODO(), options)
			},
			WatchFunc: func(options v1.ListOptions) (watch.Interface, error) {
				if tweakListOptions != nil {
					tweakListOptions(&options)
				}
				return client.Anddd7V1beta1().Buyers(namespace).Watch(context.TODO(), options)
			},
		},
		&anddd7v1beta1.Buyer{},
		resyncPeriod,
		indexers,
	)
}

func (f *buyerInformer) defaultInformer(client versioned.Interface, resyncPeriod time.Duration) cache.SharedIndexInformer {
	return NewFilteredBuyerInformer(client, f.namespace, resyncPeriod, cache.Indexers{cache.NamespaceIndex: cache.MetaNamespaceIndexFunc}, f.tweakListOptions)
}

func (f *buyerInformer) Informer() cache.SharedIndexInformer {
	return f.factory.InformerFor(&anddd7v1beta1.Buyer{}, f.defaultInformer)
}

func (f *buyerInformer) Lister() v1beta1.BuyerLister {
	return v1beta1.NewBuyerLister(f.Informer().GetIndexer())
}