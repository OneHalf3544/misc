import static java.util.Comparator.comparing

Map<Number, List<Tuple>> sumNumbersMap = [:]
Map<Number, List<Tuple>> productNumbersMap = [:]
fillMaps(sumNumbersMap, productNumbersMap)


sumNumbersMap.values().removeIf{it.size() < 2}
productNumbersMap.values().removeIf {it.size() < 2}
println("for sum:     ${    sumNumbersMap}")
println("for product: ${productNumbersMap}")


Set<Tuple> sumNumbers = sumNumbersMap.values().inject([] as Set) { result, v -> result += v }
Set<Tuple> productNumbers = productNumbersMap.values().inject([] as Set) { result, v -> result += v }

sumNumbers.retainAll(productNumbers);
productNumbers.retainAll(sumNumbers);

println("for sum:     ${    sumNumbers.sort(true, tuples()).join(' ')}")
println("for product: ${productNumbers.sort(true, tuples()).join(' ')}")




private void fillMaps(Map<Number, List<Tuple>> sumNumbersMap, Map<Number, List<Tuple>> productNumbersMap) {
    for (sum in 4..99) {
        for (a in 2..(sum - 2)) {
            b = sum - a
            if (a > b) continue
            sumNumbersMap.computeIfAbsent(sum, { s -> [] }).add(new Tuple(a, b))
            productNumbersMap.computeIfAbsent(a * b, { s -> [] }).add(new Tuple(a, b))
        }
    }
}

private Comparator<Tuple> tuples() {
    return {a, b ->
        def compare = a[0] <=> b[0]
        if (compare == 0) {
            return a[1] <=> b[1]
        }
        return compare
    }
}