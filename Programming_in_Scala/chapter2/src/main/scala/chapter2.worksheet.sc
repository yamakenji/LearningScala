val score = 90
val message = if score >= 80 then "Pass" else "Fail"

val name = "Kenji"

def judge(score: Int): String =
  if score >= 80 then "Pass" else "Fail"

println(s"$name さんのスコア: $score")
println(s"判定: ${judge(score)}")

var i = 1

println("while:")
while i <= 3 do {
  println(i)
  i += 1
}

println("for-do:")
for i <- 1 to 3 do
  println(i)

def shippingFee(totalAmount: Int): Int =
  if totalAmount >= 5000 then 0
  else 700

def checkoutMessage(totalAmount: Int): String =
  val fee = shippingFee(totalAmount)
  s"商品合計: ${totalAmount}円, 送料: ${fee}円"

val amounts = List(3500, 5000, 7800)
for amount <- amounts do
  println(checkoutMessage(amount))

val n = 7
val result = if n % 2 == 0 then true
else false

println(result)

def discountRate(customerRank: String): Double =
  customerRank match {
    case "Gold" => 0.20
    case "Silver" => 0.10
    case _ => 0.0
  }

val myCustomerRank = "Gold"
println(s"あなたは${myCustomerRank}会員です。割引率は${discountRate(myCustomerRank)*100}%です。")

val tasks = List("read", "code", "review")
for task <- tasks do
  println(s"task: ${task}")
