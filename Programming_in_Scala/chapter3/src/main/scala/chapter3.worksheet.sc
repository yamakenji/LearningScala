// Programming in Scala - Chapter 3: Next Steps in Scala

val numbers = Array(1, 2, 3, 4, 5)
numbers.foreach(println)
numbers(0) = 100
numbers.foreach(println)

val numbersList = List(1, 2, 3, 4, 5)
val doubled = numbersList.map(n => n * 2)

println(numbersList)
println(doubled)
numbersList.foreach(println)
doubled.foreach(println)

// 演習Lv1
val scores = Array(90, 80, 70, 60, 50)
scores(1) = 95

val names = Array("Alice", "Bob", "Charlie", "David", "Eve")
val person = ("Kenji", 50, "Architect")

scores.foreach(println)
names.foreach(println)
println(person)
println(s"${person._1} is ${person._2} years old and works as a ${person._3}.")


// --- Arrays ---
val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"
for i <- 0 to 2 do
  print(greetStrings(i))

val numNames = Array("zero", "one", "two")
println(numNames.mkString(", "))

// --- Lists ---
val oneTwoThree = List(1, 2, 3)
val oneTwo = List(1, 2)
val threeFour = List(3, 4)
val oneTwoThreeFour = oneTwo ::: threeFour
println(oneTwoThreeFour)

val twoThree = List(2, 3)
val oneTwoThree2 = 1 :: twoThree
println(oneTwoThree2)

val oneTwoThree3 = 1 :: 2 :: 3 :: Nil
println(oneTwoThree3)

// List methods
val thrill = "Will" :: "fill" :: "until" :: Nil
println(thrill.count(s => s.length == 4))
println(thrill.drop(2))
println(thrill.dropRight(2))
println(thrill.exists(s => s == "until"))
println(thrill.filter(s => s.length == 4))
println(thrill.forall(s => s.endsWith("l")))
println(thrill.foreach(s => print(s)))
println(thrill.head)
println(thrill.init)
println(thrill.isEmpty)
println(thrill.last)
println(thrill.length)
println(thrill.map(s => s + "y"))
println(thrill.mkString(", "))
println(thrill.reverse)

// --- Tuples ---
val pair = (99, "Luftballons")
println(pair._1)
println(pair._2)

// --- Sets and Maps ---
var jetSet = Set("Boeing", "Airbus")
jetSet += "Lear"
println(jetSet.contains("Cessna"))
println(jetSet)

import scala.collection.mutable
val movieSet = mutable.Set("Hitch", "Poltergeist")
movieSet += "Shrek"
println(movieSet)

val romanNumeral = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
println(romanNumeral(4))

// --- Functional vs Imperative style ---
def printArgsImperative(args: Array[String]): Unit =
  var i = 0
  while i < args.length do
    println(args(i))
    i += 1

def printArgsFunctional(args: Array[String]): Unit =
  args.foreach(println)

printArgsImperative(Array("one", "two", "three"))
printArgsFunctional(Array("one", "two", "three"))

// --- Classes ---
class ChecksumAccumulator:
  private var sum = 0
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1

val acc = new ChecksumAccumulator
acc.add(1)
acc.add(2)
println(acc.checksum())
