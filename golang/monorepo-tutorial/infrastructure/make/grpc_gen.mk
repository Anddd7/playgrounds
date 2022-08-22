# input variables

GIT_ROOT_DIR    := $(shell git rev-parse --show-toplevel)
## set to empty if repo is same as the root of git
REPO_PATH 		:= golang/monorepo-tutorial

# variables

ROOT_DIR 		:= ${GIT_ROOT_DIR}/${REPO_PATH}

# commands

gen:
	@echo ">> generating grpc"
	$(ROOT_DIR)/infrastructure/shell/grpc_gen.sh