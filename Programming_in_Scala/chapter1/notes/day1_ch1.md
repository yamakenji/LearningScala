# 🎯 Day 1 — Ch.1「A Scalable Language」

---

## [1] ウォームアップ (5分)

初日なので前回の確認クイズはありません。代わりに一つだけ聞かせてください。

> **初日チェック**: Javaを使っていて「もっとこう書けたらいいのに」と感じたことはありますか？（例: ループが冗長、型宣言が多すぎる、など）

答えなくても大丈夫です。頭の片隅に置いてセッションを進めましょう。

---

## [2] 概念説明 (15分)

### Ch.1の核心概念、3つに絞ります

---

### 概念① — Scalaとは「スケーラブルな言語」

**Scala = Scalable Language** の略です。「スケーラブル」とは、*小さなスクリプト*から*大規模システム*まで、同じ言語で対応できるという意味です。

```
小スクリプト ←────────────────────→ 大規模システム
  Perl / Python                   Java / C++
           ↑
          Scala（両方をカバー）
```

Javaでいうと、「Javaは大規模向けだが小さなものを書くには冗長すぎる」という不満がありますよね。Scalaはその両端をカバーしようとしています。

---

### 概念② — OO（オブジェクト指向）＋ FP（関数型）の融合

これがScalaの最大の特徴です。

| | Java | Scala |
|---|---|---|
| オブジェクト指向 | ✅ | ✅ |
| 関数型プログラミング | △ (Java8から部分対応) | ✅ (言語の根幹) |
| 関数はオブジェクトか？ | ❌ (メソッドは第一級ではない) | ✅ **関数もオブジェクト** |

Scalaでは「関数もオブジェクト」という事実が、今後学ぶ高階関数・クロージャ・型クラスすべての基盤になります。今は「へえ」くらいで大丈夫です。

```
// Java: 関数を変数に入れられない（ラムダはある程度可能だが本質的に別物）
// Scala: 関数をそのまま変数に入れられる
val double = (x: Int) => x * 2   // 関数を変数に代入！
println(double(5))                // → 10
```

---

### 概念③ — ライブラリが「言語のように見える」設計

書籍の面白い例として `BigInt` があります。

```scala
// Javaでは BigInteger を使うと…（書籍より）
import java.math.BigInteger
def factorial(x: BigInteger): BigInteger =
  if x == BigInteger.ZERO then BigInteger.ONE
  else x.multiply(factorial(x.subtract(BigInteger.ONE)))

// Scalaでは BigInt を使うと…まるで普通の整数のように書ける！
def factorial(x: BigInt): BigInt =
  if x == 0 then 1 else x * factorial(x - 1)
```

`BigInt` はScalaに組み込まれた型ではなく、**ライブラリ**です。でも普通の `Int` と同じ演算子（`*`、`-`、`==`）が使えます。これがScalaの「拡張性」の本質です。

---

## [3] Worksheet演習 (30分)

### 演習 Lv1 — まず動かしてみよう

```scala
// ============================================
// Day 1 | Ch.1: A Scalable Language
// 演習 Lv1: Scalaの「らしさ」を体感する
// ============================================

// ---- 実行してみよう ----
@main def exercise1(): Unit =

  // --- OO + FP の融合を感じるコード ---

  // 1) 関数を変数に入れる（Javaではこうはいかない）
  val double = (x: Int) => x * 2
  val triple = (x: Int) => x * 3
  println(double(5))   // → 10
  println(triple(5))   // → 15

  // 2) 関数を別の関数に渡す（高階関数の入口）
  val numbers = List(1, 2, 3, 4, 5)
  val doubled = numbers.map(double)
  println(doubled)     // → List(2, 4, 6, 8, 10)

  // 3) BigInt で巨大な数を普通に計算
  def factorial(x: BigInt): BigInt =
    if x == 0 then 1 else x * factorial(x - 1)
  println(factorial(30))  // → 265252859812191058636308480000000

// ---- 確認ポイント ----
// Q: doubled の型は何でしょう？ List[???] — IntelliJにカーソルを当てて確認してみよう
```

---

### 演習 Lv2 — 改造してみよう

```scala
// ============================================
// Day 1 | Ch.1: A Scalable Language
// 演習 Lv2: 関数を自分で作って渡す
// ============================================

@main def exercise2(): Unit =

  val numbers = List(1, 2, 3, 4, 5)

  // TODO: (1) numbers の各要素を「2乗」する関数 square を定義してください
  // val square = ???

  // TODO: (2) square を map に渡して、2乗のリストを作ってください
  // val squared = numbers.map(???)
  // println(squared)  // → List(1, 4, 9, 16, 25) になるはず

  // TODO: (3) 偶数だけを取り出してみよう（filter を使う）
  // val evens = numbers.filter(???)
  // println(evens)    // → List(2, 4) になるはず

// NOTE: filter の中には「Int を受け取り Boolean を返す関数」を渡します
// (x: Int) => x % 2 == 0  ← これが「偶数判定関数」です
```

---

### 演習 Lv3 — 自力チャレンジ

```scala
// ============================================
// Day 1 | Ch.1: A Scalable Language
// 演習 Lv3: 関数の合成（応用）
// ============================================

// 【チャレンジ】
// 以下の3ステップを1つのパイプラインで書いてください
// 1. List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) から
// 2. 偶数だけを取り出し
// 3. それぞれを2乗して
// 4. 合計を出す
//
// 期待する答え: 220  （4 + 16 + 36 + 64 + 100 = 220）
//
// ヒントが必要なら /hint と入力してください

@main def exercise3(): Unit =
  val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  // ここに書いてみよう
  // val result = numbers. ???
  // println(result)  // → 220

// NOTE: map, filter, sum の3つのメソッドチェーンで書けます
```

---

## [4] 振り返り (10分)

### 今日の3行まとめを書いてみよう

セッション終了後、以下のテンプレートに記入して `/summary` で送ってください。採点します。

```markdown
## Day 1 — [今日の日付]
**章**: Ch.1 A Scalable Language
**今日の3行まとめ**:
1. 
2. 
3. 
**できた演習**: Lv□ □ □
**詰まったポイント**: 
**明日の予習キーワード**: val / var / def
```

---

### 💡 今日のAIツール活用ヒント

| 今やること | おすすめツール |
|---|---|
| Lv2・Lv3の演習コードをIntelliJで実行 | **JetBrains AI**: エラーが出たらその場で「このエラーを説明して」 |
| `map` / `filter` って何者？もっと知りたい | **ChatGPT Plus**: Scala公式ドキュメントの英文を貼って「日本語で要約して」 |
| Lv3を別の書き方でも試したい | **Gemini Pro**: 「この Scala コードの別の書き方を3つ教えて」 |

---

まずは **Lv1からコードをコピーしてWorksheetで実行**してみてください。動いたら教えてもらえると次のステップに進めます！