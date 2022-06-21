const groupId = process.argv[2]
const caseId = process.argv[3]

if (groupId == 0) {
  /**
   * new Promise(resolve, reject) 只会创建一个promise对象, 并不会立即执行
   */
  console.log(new Promise(resolve => resolve(1))) // Promise { 1 }
}

if (groupId == 1) {
  /**
   * 同case0
   */
  console.log(Promise.resolve(1)) // Promise { 1 }
}

if (groupId == 2) {
  /**
   * .then 过后会promise的状态会变为pending(需要后续操作)
   */
  const pending = Promise.resolve(1)
  .then(x => console.log(x))

  if (caseId == 2) {
    console.log(pending) // Promise { <pending> }
  }
}

if (groupId == 3) {
  if (caseId == 1) { /**
   * promise的数据可以链式传递, 只要promise到达了fulfill状态, 就会继续传递下去
   * 在then状态中返回一个数据, promise会直接进入fulfill状态
   */
  Promise.resolve(1)
  .then(x => {
    console.log(x) // 1
    return x + 1
  })
  .then(x => console.log(x)) // 2
  }

  if (caseId == 2) {
    console.log(
        Promise.resolve(1)
        .then(x => x + 1)
    ) // Promise { <pending> }
  }

  /**
   * 可以使用async await将一个promise同步化
   */
  if (caseId == 3) {
    async function asyncTest() {
      console.log(
          await Promise.resolve(1)
          .then(x => x + 1)
      )
    }
    asyncTest()
  }
}

if (groupId == 3) {
  if (caseId == 1) {
    /**
     * promise可以继续传递promise
     */
    Promise.resolve(1)
    .then(x => Promise.resolve(x + 1))
    .then(x => console.log(x)) // 2
  }

  if (caseId == 2) {
    Promise.resolve(1)
    .then(x => {
      return Promise.resolve(x + 1).then(y => console.log(y)) // 2
    })
    .then(x => console.log(x)) // undefined
  }

  if (caseId == 3) {
    Promise.resolve(1)
    .then(x =>
        Promise.all([
          Promise.resolve(11),
          Promise.resolve(12),
          Promise.resolve(13),
          Promise.resolve(14),
        ])
    )
    .then(x => console.log(x)) // [ 11, 12, 13, 14 ]
  }

  if (caseId == 4) {
    Promise.resolve(1).then(x =>
        Promise.all([
          Promise.resolve(11),
          Promise.resolve(12),
          Promise.resolve(13),
          Promise.reject(14),
        ])
    )
    .then(x => console.log(x))
    .catch(x => console.log(x)) // [ 14 ]
  }

  if (caseId == 5) {
    Promise.reject(1)
    .then(x => console.log(x))
    .catch(x => console.log(x)) // 1
  }

  if (caseId == 6) {
    Promise.reject(1)
    .then(x => Promise.all([
      Promise.resolve(11),
      Promise.resolve(12),
      Promise.resolve(13),
      Promise.resolve(14),
    ]))
    .then(x => console.log(x))
    .catch(x => console.log(x)) // 1
  }

  if (caseId == 7) {
    Promise.resolve(1)
    .then(x => Promise.all([
      Promise.resolve(11),
      Promise.resolve(12),
      Promise.resolve(13),
      Promise.reject(14),
    ]))
    .then(x => console.log(x))
    .catch(x => console.log(x)) // 14
  }
}
