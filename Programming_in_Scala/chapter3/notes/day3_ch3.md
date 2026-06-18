# Day 3 | Ch.3「Next Steps in Scala」

## 配列・リスト・タプル・Set/Map・関数型スタイルへの第一歩

今日のテーマは、Scalaで「複数の値」を扱う基本です。
*Programming in Scala 5th Edition* の Ch.3 は、Array / List / Tuple / Set / Map に加えて、`map` や `for-yield` による変換へ進む章です。

---

## 1. 学習目的

Scalaの基本コレクションである `Array`、`List`、`Tuple`、`Set`、`Map` の使い分けを理解します。
さらに、Java風の「手続き的なループ」から、Scalaらしい「値を変換して新しい値を作る」関数型スタイルへの入口を体験します。

---

## 2. 背景説明

Javaでは、配列や `ArrayList` に対して `for` ループで値を追加・更新していく書き方がよくあります。

```java
List<Integer> result = new ArrayList<>();
for (int n : numbers) {
    result.add(n * 2);
}
```

Scalaでは、もちろん似たこともできますが、よりScalaらしいのはこうです。

```scala
val result = numbers.map(n => n * 2)
```

大事なのは、**既存のコレクションを変更するのではなく、新しいコレクションを作る** という発想です。

これは関数型プログラミングでとても重要です。
データを直接変更しないので、処理の流れが読みやすくなり、バグも追いやすくなります。ドメインモデリングでも「注文一覧から有効な注文だけを取り出す」「金額を税込みに変換する」など、業務ルールを安全に表現しやすくなります。

---

# 3. ステップバイステップのチュートリアル

## [1] ウォームアップ：前回の確認クイズ

### Q1

Scalaの `val` と `var` の違いは何でしょうか？

<details>
<summary>答え</summary>

`val` は再代入できない値、`var` は再代入できる変数です。
関数型スタイルでは、基本的に `val` を使います。

</details>

### Q2

Scalaでは `if` は文ではなく「式」です。これはどういう意味でしょうか？

<details>
<summary>答え</summary>

`if` が値を返せるという意味です。

```scala
val result = if score >= 60 then "Pass" else "Fail"
```

</details>

---

## [2] 今日の核心概念 3つ

## 核心1：`Array` はJava配列に近いが、Scalaではまず `List` に慣れる

`Array` は要素を更新できます。

```scala
val numbers = Array(1, 2, 3)
numbers(0) = 100
println(numbers.mkString(", "))
```

出力：

```text
100, 2, 3
```

Java経験者にはかなり自然です。
ただし関数型Scalaでは、まず **変更しないコレクション** に慣れるのが大事です。

---

## 核心2：`List` はイミュータブルなコレクション

`List` は基本的に変更しません。

```scala
val numbers = List(1, 2, 3)
val doubled = numbers.map(n => n * 2)
```

このとき、`numbers` 自体は変わりません。

```scala
numbers  // List(1, 2, 3)
doubled  // List(2, 4, 6)
```

Javaの `ArrayList` をその場で変更する感覚とは違い、Scalaでは「変換して新しい値を作る」と考えます。

---

## 核心3：`map` と `for-yield` は「変換」を表す

この2つは似た処理を書けます。

```scala
val names = List("Alice", "Bob", "Charlie")

val upper1 = names.map(name => name.toUpperCase)

val upper2 =
  for name <- names yield name.toUpperCase
```

どちらも結果は同じです。

```scala
List("ALICE", "BOB", "CHARLIE")
```

`map` は短く書けます。
`for-yield` は、複数の処理を読みやすく書きたいときに便利です。

---

# [3] Worksheet演習

以下は IntelliJ IDEA の Scala Worksheet、または `.scala` ファイルで実行できます。

---

## 演習 Lv1：Array / List / Tuple を触る

```scala
// ============================================
// Day 3 | Ch.3: Next Steps in Scala
// 演習 Lv1: Array / List / Tuple の基本
// ============================================

// ---- 実行してみよう ----
@main def exerciseLv1(): Unit =
  val scores = Array(80, 90, 70)
  scores(1) = 95

  val names = List("Alice", "Bob", "Charlie")
  val person = ("Kenji", 50, "Architect")

  println(scores.mkString(", "))
  println(names)
  println(person)
  println(s"${person._1} is ${person._2} years old")

// ---- 確認ポイント ----
// Q: Array は変更できるのに、List は基本的に変更しない。
//    この違いは、関数型プログラミングではなぜ重要でしょうか？
```

### 期待結果

```text
80, 95, 70
List(Alice, Bob, Charlie)
(Kenji,50,Architect)
Kenji is 50 years old
```

### 意図

ここでは、Scalaの基本的な複数値の扱いを確認します。

`Array` は更新できます。
`List` はイミュータブルに扱います。
`Tuple` は、名前をつけたクラスを作るほどではない一時的な組み合わせに使えます。

ただし、ドメインモデリングでは `Tuple` を多用しすぎると意味が曖昧になります。

```scala
("Kenji", 50, "Architect")
```

よりも、本格的なモデルでは次のような `case class` の方が読みやすいです。

```scala
case class Person(name: String, age: Int, role: String)
```

これは後の章で扱います。

---

## 演習 Lv2：Listを変換する

```scala
// ============================================
// Day 3 | Ch.3: Next Steps in Scala
// 演習 Lv2: map と filter
// ============================================

// ---- 実行してみよう ----
@main def exerciseLv2(): Unit =
  val prices = List(1000, 2500, 800, 1200)

  val withTax = prices.map(price => (price * 1.1).toInt)
  val expensive = withTax.filter(price => price >= 1500)

  println(prices)
  println(withTax)
  println(expensive)

// ---- 確認ポイント ----
// Q: prices 自体は変更されていますか？
// Q: map と filter は、それぞれ何を返していますか？
```

### 期待結果

```text
List(1000, 2500, 800, 1200)
List(1100, 2750, 880, 1320)
List(2750)
```

### 意図

この演習は、関数型スタイルの中心です。

```scala
val withTax = prices.map(...)
```

これは「各価格を税込価格に変換する」という意味です。

```scala
val expensive = withTax.filter(...)
```

これは「条件に合うものだけを残す」という意味です。

Javaでいうと Stream API に近いです。

```java
prices.stream()
      .map(...)
      .filter(...)
      .toList();
```

Scalaではこの書き方がかなり自然に使われます。

---

## 演習 Lv3：注文データを関数型に処理する

少しドメインモデリング寄りにします。
注文一覧から「確定済みの注文」だけを取り出し、合計金額を計算します。

```scala
// ============================================
// Day 3 | Ch.3: Next Steps in Scala
// 演習 Lv3: 注文リストを関数型スタイルで処理する
// ============================================

// ---- 実行してみよう ----
@main def exerciseLv3(): Unit =
  val orders = List(
    ("O-001", "confirmed", 1200),
    ("O-002", "draft", 3000),
    ("O-003", "confirmed", 2500)
  )

  val confirmedAmounts =
    for
      order <- orders
      if order._2 == "confirmed"
    yield order._3

  val total = confirmedAmounts.sum

  println(confirmedAmounts)
  println(s"total = $total")

// ---- 確認ポイント ----
// Q: for-yield は何を作っていますか？
// Q: このコードで、元の orders は変更されていますか？
```

### 期待結果

```text
List(1200, 2500)
total = 3700
```

### 意図

ここでは `for-yield` を使って、次の処理を表しています。

```text
注文一覧
  → confirmed の注文だけに絞る
  → 金額だけ取り出す
  → 合計する
```

この考え方は、業務処理でかなり強力です。

たとえばJavaでありがちな「途中で変数を書き換えながら合計する」コードではなく、Scalaでは処理の意味をそのまま書けます。

```scala
val confirmedAmounts =
  for
    order <- orders
    if order._2 == "confirmed"
  yield order._3
```

ただし、ここでは `Tuple` を使っているため、`order._2` や `order._3` の意味がやや分かりにくいです。
これは次の章以降で `class` や `case class` を学ぶと改善できます。

たとえば将来的にはこう書きたいです。

```scala
case class Order(id: String, status: String, amount: Int)
```

こうすると、`order._2` ではなく `order.status` と書けます。
これはドメインモデリング的にはかなり大事です。業務の言葉がコードに出てくるからです。

---

# 4. 演習問題

## 問題1：点数リストの合格者だけを取り出す

次のリストから、60点以上だけを取り出してください。

```scala
val scores = List(45, 80, 59, 60, 90)
```

期待結果：

```scala
List(80, 60, 90)
```

ヒント：

```scala
filter
```

---

## 問題2：商品価格を税込価格に変換する

次の価格一覧を、消費税10%込みの価格に変換してください。

```scala
val prices = List(500, 1200, 3000)
```

期待結果：

```scala
List(550, 1320, 3300)
```

ヒント：

```scala
map
```

---

## 問題3：Mapで在庫数を表す

次のような商品在庫を `Map` で表し、`"coffee"` の在庫数を表示してください。

```text
coffee -> 10
tea    -> 5
juice  -> 0
```

さらに、在庫数が1以上の商品名だけを表示してください。

ヒント：

```scala
val stock = Map("coffee" -> 10, "tea" -> 5, "juice" -> 0)
```

---

# 5. 要点まとめ

* `Array` は更新可能で、Javaの配列に近い。
* `List` は基本的にイミュータブルに扱う。Scalaではまずこちらに慣れるのが大事。
* `Tuple` は複数の値を一時的にまとめられるが、業務的な意味を表すなら `case class` の方がよい。
* `Set` は重複なしの集合、`Map` はキーと値の対応を表す。
* `map` は「各要素を変換する」。
* `filter` は「条件に合う要素だけ残す」。
* `for-yield` は、コレクションから新しいコレクションを作る読みやすい構文。
* 関数型スタイルでは、元のデータを変更せず、新しい値を作る。
* ドメインモデリングでは、データ変換の流れを「業務ルールのパイプライン」として表現できる。

---

# 今日の3行まとめ用テンプレート

```markdown
## Day 3 — 2026-06-18
**章**: Ch.3 Next Steps in Scala

**今日の3行まとめ**:
1. 
2. 
3. 

**できた演習**: Lv□ Lv□ Lv□
**詰まったポイント**: 
**明日の予習キーワード**: class, object, case class
```

明日は Ch.4「Classes and Objects」です。
今日の `Tuple` で少し気持ち悪かった `order._2` 問題が、かなりスッキリします。
