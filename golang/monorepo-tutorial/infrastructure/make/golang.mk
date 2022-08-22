############## REQUIRED ##############
#
# SERVICE_NAME	:= <service_name>
#
#
#
############## REQUIRED ##############

# input variables

RELATIVE_PATH	:= .
SRC_PATH		:= src
BUILD_PATH		:= build

# variables

MAIN_PATH 		:= ${SRC_PATH}/main.go

# commands

build:
	go build -o ${RELATIVE_PATH}/${BUILD_PATH}/${SERVICE_NAME} ${RELATIVE_PATH}/${MAIN_PATH}

run:
	go run ${RELATIVE_PATH}/${MAIN_PATH}
