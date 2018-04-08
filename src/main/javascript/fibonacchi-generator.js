function* fibonacchi() {
    let [a, b] = [1, 1];
    while (true) {
        yield a;
        let oldA = a;
        a = b;
        b = b + oldA;

    }
}


let generator = fibonacchi();

for (let index = 0; index < 60; index++) {
    console.log(generator.next().value)
}
