- [ ] refactor folder description into CODEOWNERS
- [ ] add k8s infra: terraform, namespace, kustomized
- [ ] ci/cd enhance: skaffold, argocd


# Mono Repository of the Golang

## background

order, product, warehouse

create order, calculate total price of products and send notification to warehouse.

## structure

### gateway

config of istio, k8s ingress,

### services

- grpc server
- handlers
- grpc client

#### orchestration

a special service reuse the service api to attach new functions without modify the service

### pkg

- common package

### infrastructure

#### container

#### shell

#### terraform
