include ../../infrastructure/make/grpc_gen.mk

# input variables

SERVICE_NAME	:= order
SRC_PATH		:= src
BUILD_PATH		:= build

# variables

MAIN_PATH 		:= ${SRC_PATH}/main.go

# commands

build:
	go build -o ${BUILD_PATH}/${SERVICE_NAME} ${MAIN_PATH}

run:
	go run ${MAIN_PATH}
