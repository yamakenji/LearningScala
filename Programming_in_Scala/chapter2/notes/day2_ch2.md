# Day 2 | Ch.2: First Steps in Scala

## 1. 学習目的

今日は、Scala 3 の最初の実践として **変数・関数・`if`・ループ・コレクション反復** を Worksheet で動かします。
Java経験者向けに言うと、「Javaっぽく書けるScala」から入りつつ、少しずつ **式として考えるScala** に寄せていく回です。

*Programming in Scala* Ch.2 は「First Steps in Scala」として、REPL、変数、関数、スクリプト、`while` / `if`、`foreach` / `for-do` を扱う章です。

---

## 2. 背景説明

ScalaはJavaと同じJVM上で動くので、最初はかなりJavaに近い感覚で書けます。ただし大事な違いがあります。

Javaでは「変数を変更しながら処理を進める」書き方が自然ですが、Scalaではまず **`val` で値を固定する** ことを基本にします。これは関数型プログラミングの土台です。値が途中で変わらないと、コードの読み手は「この値、どこかで書き換わってない？」と疑わなくてよくなります。地味ですが、設計の安心感がかなり変わります。

また、Scalaでは `if` が「文」ではなく「式」として値を返せます。つまり、

```scala
val message = if score >= 80 then "合格" else "再挑戦"
```

のように、分岐結果をそのまま値として扱えます。これは後で学ぶ関数型ドメインモデリングにも効いてきます。業務ルールを「状態を書き換える手続き」ではなく、「入力から結果を返す関数」として表現しやすくなるからです。

---

## [1] ウォームアップ — 5分

まず確認クイズです。答えは頭の中で考えてから下に進んでください。

**Q1.** Scalaで基本的に優先したいのは `val` と `var` のどちらでしょう？なぜでしょう？

**Q2.** Javaの `if` とScalaの `if` の大きな違いは何でしょう？

答えの方向性としては、Q1は「`val`。再代入できないので安全で読みやすい」、Q2は「Scalaの `if` は値を返す式として使える」です。

---

## [2] 概念説明 — 15分

### 概念1: `val` と `var`

Scalaでは、まず `val` を使います。

```scala
val name = "Kenji"
val age = 50
```

`val` はJavaでいう `final` 付きローカル変数に近いです。再代入できません。

```scala
val x = 10
// x = 20 // コンパイルエラー
```

一方、`var` は再代入できます。

```scala
var count = 0
count = count + 1
```

Java経験者は `var` のほうが自然に見えるかもしれませんが、Scalaでは `var` は「必要なときだけ使う道具」です。関数型っぽく書くなら、値を変えるより **新しい値を作る** ほうを優先します。

---

### 概念2: `def` で関数・メソッドを定義する

Scalaでは次のように書きます。

```scala
def double(x: Int): Int = x * 2
```

Javaならこういう感覚です。

```java
int double(int x) {
    return x * 2;
}
```

Scalaでは `return` をあまり使いません。最後の式が戻り値になります。

```scala
def isAdult(age: Int): Boolean =
  age >= 18
```

この「最後の式が値になる」感覚がScalaらしさです。関数型の文脈では、関数を「入力を受け取って、値を返すもの」として素直に見られるのが大事です。

---

### 概念3: `while` より `foreach` / `for-do`

Ch.2では `while` も出てきます。Javaから来た人には馴染みやすいです。

```scala
var i = 0
while i < 3 do
  println(i)
  i += 1
```

ただ、これは `var` と状態変更を使います。Scalaでは次のように書くほうが自然です。

```scala
List("Scala", "Java", "Kotlin").foreach(name => println(name))
```

または Scala 3 の `for-do`。

```scala
for name <- List("Scala", "Java", "Kotlin") do
  println(name)
```

今日の段階では「`while` は読めればOK。自分で書くなら `foreach` や `for` を優先」くらいで大丈夫です。ここ、命令型から関数型へ橋を渡る最初の板です。ちょっとギシギシしますが、落ちません。

---

## [3] Worksheet演習 — 30分

IntelliJ IDEA の Scala Worksheet に、各コードを1つずつ貼って実行してください。

---

### 演習 Lv1: `val`・関数・`if` を動かす

```scala
// ============================================
// Day 2 | Ch.2: First Steps in Scala
// 演習 Lv1: val・def・if式
// ============================================

// ---- 実行してみよう ----
@main def exerciseLv1(): Unit =
  val name = "Kenji"
  val score = 82

  def judge(score: Int): String =
    if score >= 80 then "合格"
    else "再挑戦"

  println(s"$name さんのスコア: $score")
  println(s"判定: ${judge(score)}")

// ---- 確認ポイント ----
// Q: judge関数の戻り値型 String を省略しても動くでしょうか？
```

**期待結果**

```text
Kenji さんのスコア: 82
判定: 合格
```

**意図**

`if` が値を返していることを確認します。Javaのように「変数を用意して、ifの中で代入する」のではなく、`if` の結果をそのまま返しています。

---

### 演習 Lv2: `while` から `for-do` へ

```scala
// ============================================
// Day 2 | Ch.2: First Steps in Scala
// 演習 Lv2: whileとfor-doの比較
// ============================================

// ---- 実行してみよう ----
@main def exerciseLv2(): Unit =
  var i = 1

  println("while:")
  while i <= 3 do
    println(i)
    i = i + 1

  println("for-do:")
  for n <- 1 to 3 do
    println(n)

// ---- 確認ポイント ----
// Q: while版では var が必要でした。for-do版ではなぜ var が不要なのでしょう？
```

**期待結果**

```text
while:
1
2
3
for-do:
1
2
3
```

**意図**

`while` はカウンタ変数を変化させます。`for-do` は `1 to 3` という範囲から値を取り出して処理します。関数型寄りの考え方では、「自分で状態を動かす」より「データの流れに処理を適用する」ほうを好みます。

---

### 演習 Lv3: 小さなドメインルールを関数にする

```scala
// ============================================
// Day 2 | Ch.2: First Steps in Scala
// 演習 Lv3: 業務ルールを関数で表す
// ============================================

// ---- 実行してみよう ----
@main def exerciseLv3(): Unit =
  def shippingFee(totalAmount: Int): Int =
    if totalAmount >= 5000 then 0
    else 700

  def checkoutMessage(totalAmount: Int): String =
    val fee = shippingFee(totalAmount)
    s"商品合計: ${totalAmount}円, 送料: ${fee}円"

  val amounts = List(3200, 5000, 7800)

  for amount <- amounts do
    println(checkoutMessage(amount))

// ---- 確認ポイント ----
// Q: shippingFeeは同じtotalAmountに対して、常に同じ結果を返すでしょうか？
```

**期待結果**

```text
商品合計: 3200円, 送料: 700円
商品合計: 5000円, 送料: 0円
商品合計: 7800円, 送料: 0円
```

**意図**

これは小さいですが、関数型ドメインモデリングの入口です。
「5000円以上なら送料無料」という業務ルールを、状態変更ではなく `Int => Int` の関数として表しています。

業務ルールをこう書くと、テストしやすいです。

```scala
shippingFee(3200) == 700
shippingFee(5000) == 0
```

この形にできるルールは、なるべくこの形に寄せる。これが後々かなり効いてきます。

---

## 4. 演習問題

### 問題1

`isEven(n: Int): Boolean` という関数を作ってください。
偶数なら `true`、奇数なら `false` を返します。

ヒント:

```scala
n % 2 == 0
```

---

### 問題2

`discountRate(customerRank: String): Double` を作ってください。

条件:

```text
"Gold"   -> 0.20
"Silver" -> 0.10
その他   -> 0.0
```

今日はまだ `match` を本格的にやっていないので、`if / else if / else` で書いてOKです。

---

### 問題3

次のリストを `for-do` で表示してください。

```scala
val tasks = List("read", "code", "review")
```

期待結果:

```text
task: read
task: code
task: review
```

---

## 5. 要点まとめ

* Scalaでは、まず `val` を使う。`var` は必要なときだけ。
* `def 関数名(引数: 型): 戻り値型 = 本体` で関数・メソッドを定義する。
* Scalaの `if` は値を返す「式」として使える。
* `while` はJavaに近いが、Scalaでは `foreach` や `for-do` のほうが自然。
* 業務ルールは、状態を書き換える処理ではなく「入力から結果を返す関数」として表すとテストしやすい。
* 今日のゴールは、Scalaの文法暗記ではなく「値を変えるより、値を返す」という感覚をつかむこと。

---

## 今日の3行まとめ用テンプレート

```markdown
## Day 2 — 2026-06-16
**章**: Ch.2 First Steps in Scala
**今日の3行まとめ**:
1. 
2. 
3. 
**できた演習**: Lv□ Lv□ Lv□
**詰まったポイント**: 
**明日の予習キーワード**: 配列 / List / Tuple / map
```

今日のAI活用ヒントは、JetBrains AIに「この `while` を `for` または `foreach` に書き換えて」と聞くのがかなり良いです。命令型からScalaらしい書き方への変換練習になります。
