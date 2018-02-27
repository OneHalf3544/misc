
class Combinations<T> {

    static def list = [1, 2, 3, 4, 5]

    static void main(String[] args) {
        def combinations = new Combinations()

        def result = combinations.combination(list).sort()
        result.each {println it}
        println "total: ${result.size()}"
    }

    def combination(List<?> list) {
        if (list.size() == 1) {
            return [[], [list.first()]]
        }
        def mediumIndex = (list.size() - 1).intdiv(2)

        list.split {}
        def lists1 = combination(list[0..mediumIndex])
        def lists2 = combination(list[mediumIndex + 1..list.size() - 1])

        def result = []
        for (def element1 : lists1) {
            for (def element2 : lists2) {
                def subResult = []
                subResult.addAll(element1)
                subResult.addAll(element2)
                result.add(subResult)
            }
        }
        result
    }
}