var double = (x: Int) => x * 2
var triple = (x: Int) => x * 3

val numbers = List(1, 2, 3, 4, 5)
val doubled = numbers.map(double)
println(doubled)
