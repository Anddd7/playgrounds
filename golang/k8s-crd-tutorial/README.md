# Create CRD 0-1

## Handcraft

### Step 1

- Create crd and resources

    ```sh
    cd artifacts

    k apply -f crd-buyer.yaml
    k apply -f buyers.yaml
    ```

- Print resources

    ```sh
    k get buyers -o jsonpath='{range .items[*]}{@.metadata.name}:{@.spec.name}{"\n"}{end}'
    ```

### Step 2

创建脚手架（以便代码生成）

- go mod init
  - `go mod init <group_name>/project-name`
  - `go mod init anddd7.github.com/buyer-controller`
- 创建 register.go

    ```sh
    # pkg/api/<group_name>
    mkdir -p pkg/apis/anddd7
    touch pkg/apis/anddd7/register.go
    ```

- 创建 doc.go

    ```sh
    # pkg/api/<group_name>/<version>
    mkdir -p pkg/apis/anddd7/v1beta1
    touch pkg/apis/anddd7/v1beta1/doc.go
    ```

  - 添加注释
  
  ```go
    // 声明为整个包下的类型定义生成DeepCopy方法；
    // +k8s:deepcopy-gen=package 
    // 声明了这个包对应的API的组名，和CRD中的组名一致；
    // +groupName=anddd7.github.com
  ```

- 创建 types.go

    ```sh
    touch pkg/apis/anddd7/v1beta1/types.go
    ```

- 创建 register.go

    ```sh
    touch pkg/apis/anddd7/v1beta1/register.go
    ```

- 目录结构
  
  ```sh
  .
  ├── README.md
  ├── artifacts
  │   ├── buyers.yaml
  │   └── crd-buyer.yaml
  ├── go.mod
  ├── go.sum
  └── pkg
      └── apis
          └── anddd7
              ├── register.go
              └── v1beta1
                  ├── doc.go
                  ├── register.go
                  └── types.go
  ```

## Step 3

- 准备 hack 文件夹
  - 修改对应的名称和参数