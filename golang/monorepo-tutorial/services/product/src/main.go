package main

import (
	"context"
	"flag"
	"fmt"
	"log"
	"net"

	"google.golang.org/grpc"

	"github.com/anddd7/monorepo/pkg/envs"
	this "github.com/anddd7/monorepo/services/product"
)

var (
	port = flag.Int("port", envs.LOCAL_PRODUCT_PORT, "The server port")
)

func prepare() {
	flag.Parse()
}

type server struct {
	this.ProductServiceServer
}

func (s server) GetProduct(ctx context.Context, req *this.GetProductReq) (*this.Product, error) {
	return &this.Product{
		Id:        req.GetId(),
		Name:      "Initial Product",
		PriceCent: 99800,
		Status:    0,
	}, nil
}
func (s server) CreateProduct(ctx context.Context, req *this.CreateProductReq) (*this.CreateProductResp, error) {
	return &this.CreateProductResp{
		Id: 1,
	}, nil
}

func register(s *grpc.Server) {
	this.RegisterProductServiceServer(s, &server{})
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
