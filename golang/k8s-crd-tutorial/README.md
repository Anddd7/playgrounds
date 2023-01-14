# Create CRD 0-1

## Handcraft

### Step 1

- Create crd and resources

    ```sh
    cd artifacts

    k apply -f crd-buyer.yaml
    k apply -f crd-seller.yaml
    k apply -f buyers.yaml
    k apply -f sellers.yaml
    ```

- Print resources

    ```sh
    k get buyers -o jsonpath='{range .items[*]}{@.metadata.name}:{@.spec.name}{"\n"}{end}'
    k get sellers -o jsonpath='{range .items[*]}{@.metadata.name}:{@.spec.name}{"\n"}{end}'
    ```

### Step 2

创建脚手架（以便代码生成）

- go mod init
  - `go mod init <project-name>`
- 创建 register.go

    ```sh
    # pkg/api/<group_name>
    mkdir -p pkg/apis/k8scrdtutorial
    touch pkg/apis/k8scrdtutorial/register.go
    ```

- 创建 doc.go

    ```sh
    # pkg/api/<group_name>/<version>
    mkdir -p pkg/apis/k8scrdtutorial/v1alpha1
    touch pkg/apis/k8scrdtutorial/v1alpha1/doc.go
    ```

  - 添加注释
  
  ```go
    // 声明为整个包下的类型定义生成DeepCopy方法；
    // +k8s:deepcopy-gen=package 
    // 声明了这个包对应的API的组名，和CRD中的组名一致；
    // +groupName=k8scrdtutorial.github.com
  ```

- 创建 types.go

    ```sh
    touch pkg/apis/k8scrdtutorial/v1alpha1/types.go
    ```

- 创建 register.go

    ```sh
    touch pkg/apis/k8scrdtutorial/v1beta1/register.go
    ```

- 目录结构
  
  ```sh
  .
  ├── README.md
  ├── artifacts
  │   ├── buyers.yaml
  │   ├── crd-buyer.yaml
  │   ├── crd-seller.yaml
  │   └── sellers.yaml
  ├── go.mod
  ├── go.sum
  └── pkg
      └── apis
          └── k8scrdtutorial
              ├── register.go
              └── v1alpha1
                  ├── doc.go
                  ├── register.go
                  └── types.go
  ```

### Step 3

- 准备 hack 文件夹
  - 下载必要的依赖
    - `go get -u k8s.io/code-generator`
    - `go get -u k8s.io/apimachinery/pkg/apis/meta/v1`
- 修改对应的名称和参数（代码生成的路径）
- 代码生成和验证

  ```sh
    go mod vendor
    chmod -R 777 vendor
    ./hack/update-codegen.sh
  ```

- 编写 main.go 测试

### Step 4

- 编写 controller.go

### Step 5

- 验证资源已被修改

  ```sh
  k get sellers -o jsonpath='{range .items[*]}{@.metadata.name}{"\t"}{@.spec.name}{"\t"}{@.spec.price}:{@.spec.amount}:{@.spec.money}{"\n"}{end}'
  k get buyers -o jsonpath='{range .items[*]}{@.metadata.name}{"\t"}{@.spec.name}{"\t"}{@.spec.price}:{@.spec.amount}{"\n"}{end}'
  ```

### Step 6

- build and package as image
- start it as k8s pod (bind kubeconfig in or process via service account)

## Operator

- `gvm use go1.19`
- <https://sdk.operatorframework.io/docs/building-operators/golang/tutorial/>
