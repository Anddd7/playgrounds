#!/bin/zsh

ROOT_DIR=$(go env GOMOD | xargs dirname)
SERVICES_PATH=services

cd "${ROOT_DIR}/${SERVICES_PATH}"

protoc -I. \
    --go_out=paths=source_relative:. \
    --go-grpc_out=require_unimplemented_servers=false,paths=source_relative:. \
    ./**/*.proto