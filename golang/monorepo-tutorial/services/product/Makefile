# input variables

SERVICE_NAME	:= product
SRC_PATH		:= src
BUILD_PATH		:= build
GIT_ROOT_DIR    := $(shell git rev-parse --show-toplevel)
REPO_PATH 		:= /golang/monorepo-tutorial

# variables

MAIN_PATH 		:= ${SRC_PATH}/main.go
ROOT_DIR := ${GIT_ROOT_DIR}${REPO_PATH}

# commands

build:
	go build -o ${BUILD_PATH}/${SERVICE_NAME} ${MAIN_PATH}

run:
	go run ${MAIN_PATH}

gen:
	@echo ">> generating grpc"
	$(ROOT_DIR)/infrastructure/shell/grpc_gen.sh $<