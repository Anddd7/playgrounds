#!/bin/zsh


cd ..

protoc -I. \
    --go_out=paths=source_relative:. \
    --go-grpc_out=require_unimplemented_servers=false,paths=source_relative:. \
    ./**/*.proto