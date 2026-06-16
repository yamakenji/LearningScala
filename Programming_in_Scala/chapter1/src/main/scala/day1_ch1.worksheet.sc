var double = (x: Int) => x * 2
var triple = (x: Int) => x * 3

val numbers = List(1, 2, 3, 4, 5)
val doubled = numbers.map(double)
println(doubled)


val numbers2 = List(1, 2, 3, 4, 5)
val squared = numbers.map(x => x * x)
println(squared)

val evens = numbers.filter(x => x % 2 == 0)
println(evens)

val numbers3 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val result = numbers3.filter(x => x % 2 == 0)
                      .map(x => x * x)
                      .sum

println(result)