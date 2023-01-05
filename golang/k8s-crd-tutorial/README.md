# Create CRD 0-1

## Handcraft

### Step 1

Create crd and resources

```sh
cd artifacts

k apply -f crd-buyer.yaml
k apply -f buyers.yaml
```

Print resources

```sh
k get buyers -o jsonpath='{range .items[*]}{@.metadata.name}:{@.spec.name}{"\n"}{end}'
```

### Step 2

