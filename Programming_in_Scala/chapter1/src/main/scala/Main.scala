@main def exercise1(): Unit =
  val double = (x: Int) => x * 2
  val triple = (x: Int) => x * 3

  println(double(5))
  println(triple(5))

  val numbers = List(1, 2, 3)
  val doubled = numbers.map(double)
  println(doubled)

  def factorial(x: BigInt): BigInt =
    if (x == 0) then 1
    else x * factorial(x - 1)
  println(factorial(30))



