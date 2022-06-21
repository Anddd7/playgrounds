const getStore = () => {
  return {
    name: "Store",
    dispatch: (action) => {
      console.log("Receive the action: " + action)
    }
  }
};

let store = getStore();

const runTest = () => {
  console.log("----------------------");
  store.dispatch("action");
  store = getStore();
};

// root dispatch
runTest();

// 在原有dispatch上加了一层代理
let next = store.dispatch;
store.dispatch = (action) => {
  console.log("Logging: " + action);
  next(action);
};
runTest();

// 使用封装好的函数来加代理
const applyMiddleWare = (middleware) => {
  let next = store.dispatch;
  store.dispatch = middleware(store)(next)
};
const logger = (store) =>
    (next) =>
        (action) => {
          console.log("Middleware logging: " + store.name + " " + action);
          next(action);
        };
applyMiddleWare(logger);

runTest();

// 使用applyMiddleWare进行串联
const uppercase = (store) =>
    (next) =>
        (action) => {
          next(action.toLocaleUpperCase());
        };
applyMiddleWare(uppercase);

runTest();

// applyMiddleware
const applyMiddleWares = (middlewares) => {
  middlewares = middlewares.slice(); // 使用新的对象
  middlewares.reverse(); // 倒转顺序, 先进后apply先生效

  let next = store.dispatch;
  middlewares.forEach(middleware =>
      next = middleware(store)(next)
  );
  return Object.assign({}, store, {dispatch: next})
};

store = applyMiddleWares([logger, uppercase]);
runTest();

// trunk middleware
const api = (store) => (next) => (action) => {
  setTimeout(() => next("API Call: " + action), 10);
};
applyMiddleWare(api);
runTest();
