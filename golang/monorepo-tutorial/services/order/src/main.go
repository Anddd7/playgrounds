package main

import (
	"context"
	"fmt"
)

import (
	"flag"
	"log"
	"net"

	"google.golang.org/grpc"

	this "github.com/anddd7/monorepo/services/order"
	"github.com/anddd7/monorepo/services/product"
)

var (
	port = flag.Int("port", 50052, "The server port")
)

func prepare() {
	flag.Parse()
}

type server struct {
	this.OrderServiceServer
}

func (s server) GetOrder(ctx context.Context, req *this.GetOrderReq) (*this.Order, error) {
	var products []*product.Product
	return &this.Order{
		Id:             req.GetId(),
		Products:       products,
		TotalPriceCent: 0,
		Status:         0,
	}, nil
}
func (s server) CreateOrder(ctx context.Context, req *this.CreateOrderReq) (*this.CreateOrderResp, error) {
	return &this.CreateOrderResp{
		Id: 1,
	}, nil
}

func register(s *grpc.Server) {
	this.RegisterOrderServiceServer(s, &server{})
}

func main() {
	prepare()
	listener, err := net.Listen("tcp", fmt.Sprintf(":%d", *port))
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()
	register(s)
	log.Printf("server listening at %v", listener.Addr())
	if err := s.Serve(listener); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
