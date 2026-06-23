# Agent Notes — LearningScala

## What this repo is
Personal Scala learning workspace. Subdirectories correspond to different books/courses; the actively maintained one is `Programming_in_Scala/`.

## `Programming_in_Scala` layout
- Each `chapterN/` is a **standalone sbt project** with its own `build.sbt` and `project/build.properties`.
- There is **no root `build.sbt`**; do not run sbt from `Programming_in_Scala/`.
- Toolchain per chapter: Scala **3.8.4**, MUnit **1.3.2** (test), sbt **1.12.x**.
  - `chapter1` pins sbt **1.12.11**.
  - `chapter2`/`chapter3` pin sbt **1.12.12**.

## Running code
Always `cd` into the chapter first:

```bash
cd Programming_in_Scala/chapter1
sbt compile
sbt test          # runs MUnit in src/test/scala/MySuite.scala
sbt run           # runs the default @main def in Main.scala
```

If a chapter defines multiple `@main` defs, `sbt run` may prompt; use `sbt runMain <qualified-name>` to target a specific one.

## Entry points and worksheets
- `src/main/scala/Main.scala` contains the chapter's `@main def ...` entry points.
- `*.worksheet.sc` files in `src/main/scala/` are Metals/IntelliJ Scala worksheets for interactive exploration. They are **not compiled by sbt** (`sbt compile` ignores `.sc` files).

## Notes and learning material
- `notes/dayN_chN.md` are Japanese-language lesson plans and exercise prompts. Treat them as reference material, not buildable code.

## Generated files
- `.metals/`, `.bloop/`, `.bsp/`, `.idea/`, and `target/` are IDE/build artifacts and are gitignored. Do not commit them.

## Other subdirectories
- `Scala_for_the_Impatient/` and `Get_Programming_with_Scala/` exist at repo root but were not inspected for this version.
