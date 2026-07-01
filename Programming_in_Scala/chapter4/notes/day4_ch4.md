# Day 4 セッション開始 — Ch.4「クラスとオブジェクト」

## [1] ウォームアップ (5分) — 前回の確認クイズ

Day 3 は「配列・リスト・タプル」でしたね。まず1問だけ確認させてください。

**Q1**: Scalaで `val nums = List(1, 2, 3)` としたとき、`nums`に対して `nums(0) = 99` のように書き換えようとするとどうなりますか？またその理由は？

答えてみてください（わからなければ「わからない」でもOKです、次に進みます）。

---

## [2] 概念説明 (15分) — Ch.4の核心3つ

### ① クラス、フィールド、メソッド

```scala
class ChecksumAccumulator:
  // フィールドとメソッドをここに定義（インデントベース）
```

- **フィールド**（`val`/`var`）と**メソッド**（`def`）を合わせて「メンバー」と呼びます
- フィールドが状態（データ）を保持し、メソッドがその状態を使って計算を行います

**[Java比較]** Javaの `class` とほぼ同じ概念ですが、Scalaは波括弧 `{}` の代わりに**インデント**でブロックを表現できます（Scala 3の新機能）。中括弧スタイルも使えますが、この教材ではインデントスタイルを採用します。

### ② セミコロン推論

Scalaでは文の終わりに `;` は基本不要です。1行に複数の文を書く場合のみ `;` で区切ります。

```scala
val x = 1; val y = 2  // 1行に複数文
val z = 3              // 通常は不要
```

**[Java比較]** Javaでは毎回 `;` が必須ですが、Scalaはコンパイラが行末を見て「文が終わったかどうか」を推論します。

### ③ シングルトンオブジェクト（`object`）

```scala
object ChecksumAccumulator:
  def calculate(s: String): Int =
    // ...
```

- **[Java比較]** Javaの `static` メソッドの置き場所と考えると分かりやすいです。ただし重要な違いがあります：
    - シングルトンオブジェクトは**それ自体が一級オブジェクト**（first-class object）
    - `new` でインスタンス化できない（パラメータを渡せない）
    - クラスと同名の `object` は「コンパニオンオブジェクト」と呼ばれ、そのクラスのファクトリメソッドなどを置く場所になる

**Case class（おまけ）**: `case class Person(name: String, age: Int)` と書くだけで、`equals`・`hashCode`・`toString`・アクセサ・ファクトリメソッド（`apply`）が自動生成されます。Javaで手書きしていたボイラープレートが不要になる、という点は関数型スタイルの重要な利点の一つです。

---

## [3] Worksheet演習 (30分)

### Lv1: コピペして動作確認

```scala
// ============================================
// Day 4 | Ch.4: クラスとオブジェクト
// 演習 Lv1: クラスとコンパニオンオブジェクトの基本
// ============================================

class ChecksumAccumulator:
  private var sum = 0
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1

object ChecksumAccumulator:
  def calculate(s: String): Int =
    val acc = new ChecksumAccumulator
    for c <- s do acc.add(c.toByte)
    acc.checksum()

@main def exercise(): Unit =
  val result = ChecksumAccumulator.calculate("Scala")
  println(s"checksum = $result")
  // NOTE: object と class が同名 → コンパニオン関係
  // NOTE: object 側からは new せずに直接メソッド呼び出しできる

// ---- 確認ポイント ----
// Q: なぜ ChecksumAccumulator.calculate(...) の呼び出しに `new` が要らないのか説明してみよう
```

### Lv2: 穴埋め・改造

```scala
// ============================================
// Day 4 | Ch.4: クラスとオブジェクト
// 演習 Lv2: Case classを使ってみよう
// ============================================

case class Book(title: String, /* ここに price: Int を追加 */)

@main def exercise2(): Unit =
  val b1 = Book("Programming in Scala", /* 価格を入れる */)
  val b2 = Book("Programming in Scala", /* 同じ価格を入れる */)

  println(b1)                  // toString の自動生成を確認
  println(b1 == b2)            // equals の自動生成を確認 → true になるはず
  println(b1.title)            // アクセサメソッドの自動生成を確認

// ---- 確認ポイント ----
// Q: b1 と b2 は new で別々に作られたのに、なぜ == で true になるのか？
// (JavaならObjectのequalsはデフォルトで参照比較=falseになるはず)
```

### Lv3: 自力実装チャレンジ

```scala
// ============================================
// Day 4 | Ch.4: クラスとオブジェクト
// 演習 Lv3: 自分でクラス＋コンパニオンオブジェクトを設計
// ============================================

// 課題: 「温度」を表すクラス Temperature を作ってください
// 要件:
//   1. コンストラクタで摂氏(celsius: Double)を受け取る
//   2. コンパニオンオブジェクトに、華氏から作るファクトリメソッド
//      fromFahrenheit(f: Double): Temperature を用意する
//   3. インスタンスメソッド toFahrenheit(): Double を実装する
//      (変換式: F = C * 9/5 + 32)

// ここに実装してみてください
// class Temperature(...)
// object Temperature { ... }

@main def exercise3(): Unit =
  // val t1 = new Temperature(100)
  // println(t1.toFahrenheit())  // 212.0 になるはず
  // val t2 = Temperature.fromFahrenheit(32)
  // println(t2.celsius)          // 0.0 になるはず
  ???
```

`/hint` と入力してくれれば、詰まったポイントに応じてヒントを出します。

---

## [4] 振り返り (10分)

演習が終わったら `/summary` で「今日の3行まとめ」を書いてください。採点します。

**AIツール活用ヒント**: 今日の `class` と `object` の使い分けでコンパイルエラーが出やすいです（特に `new` の付け忘れ・付けすぎ）。IntelliJで赤線が出たら、まず自分で考えてから **JetBrains AI** にエラーメッセージだけ聞いてみるのがおすすめです。概念自体で迷ったら、いつでも `/explain` で聞いてください。