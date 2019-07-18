
const executor = (value) => {
    console.log("exceuting: " + value);
    return Promise.resolve(value)
}
const errorHandling = (error) => {
    console.log(error)
    return Promise.reject(error)
};

const validator1 = (value) => value > 1 ? value : Promise.reject("should > 1")
const validator2 = (value) => value > 2 ? value : Promise.reject("should > 2")
const validator3 = (value) => value > 3 ? value : Promise.reject("should > 3")

const validators = (value) =>
    Promise.resolve(value)
        .then(value => value % 2 == 0 ? value : Promise.reject("should even"))
        .catch(error => Promise.reject("sub validator: " + error))


const promise = Promise.resolve(8)
    .then(validator1)
    .then(validator2)
    .then(validator3)
    .then(validators)
    .then(executor)
    .catch(errorHandling)
    .then(value => console.log("extension: " + value))
    .catch(error => console.error("rethrow error: " + error))
