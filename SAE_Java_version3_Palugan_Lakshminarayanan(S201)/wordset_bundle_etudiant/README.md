# WordSet simple bundle

Purpose of the library
----------------------
This library provides a simple way to load and use a list of words in a Java program.

It is mainly designed for a word guessing game. The library loads words from a JSON file and gives access to them through a simple API.

It can be used to:
- get the number of available words
- get a word by its index
- choose a random word for a game
- find a word starting with a given letter

The game does not need to know how the words are stored. It only uses the WordSet interface.

## Contents

```
wordset_bundle_etudiant/
├── README.txt
├── data/
│   └── mots.json : JSON file with 1000 French 5-letter words
├── docs/
│   └── javadoc-html/ : HTML Javadoc generated from sources
├── jar/
│   └── wordset.jar : library JAR
└── tests/
    └── TestWordSet.java : Test program example
```

## Public API

```text
┌────────────────────────────────────────────┐
│                «interface»                 │
│                  WordSet                   │
├────────────────────────────────────────────┤
│ + size() : int                             │
│ + word(index : int) : String               │
│ + random() : String                        │
│ + startingWith(letter : char) : String     │
└────────────────────────────────────────────┘
                    △
                    ╎
                    ╎ «realize»
                    ╎
┌────────────────────────────────────────────┐
│                JsonWordSet                 │
├────────────────────────────────────────────┤
│ - words : List<String>                     │
│ - random : Random                          │
├────────────────────────────────────────────┤
│ + JsonWordSet(filePath : String)           │
│ + size() : int                             │
│ + word(index : int) : String               │
│ + random() : String                        │
│ + startingWith(letter : char) : String     │
└────────────────────────────────────────────┘
```

**Package:** `words`

**Classes:**
- `words.WordSet`
- `words.JsonWordSet`

## JSON format

The file must contain a simple JSON array of strings.

Example:
```json
["abces", "abdel", "abime", "aboli", "abord"]
```

All words must have 5 letters.

## Requirements

- Java SE 11 or higher is required
- The JAR is compiled for Java 11 (bytecode version 55)

## Example usage (TestWordSet.java)

```java
package words;

public class TestWordSet {
    public static void main(String[] args) throws Exception {
        WordSet words = new JsonWordSet("data/mots.json");

        System.out.println("Number of words: " + words.size());
        System.out.println("Random word: " + words.random());
        System.out.println("Word at index 1: " + words.word(1));
        System.out.println("Word starting with 'm': " + words.startingWith('m'));
    }
}
```

## Compile a test program using the JAR

Run this command from the project root directory:

### Linux / macOS
```bash
javac -cp ".:jar/wordset.jar" -d . tests/TestWordSet.java
```

### Windows
```bash
javac -cp ".;jar/wordset.jar" -d . tests/TestWordSet.java
```

## Run a program using the JAR

Run this command from the project root directory:

### Linux / macOS
```bash
java -cp ".:jar/wordset.jar" words.TestWordSet
```

### Windows
```bash
java -cp ".;jar/wordset.jar" words.TestWordSet
```

## Notes

- The option `-d .` creates the correct package directory (`words/`)
- Even if the source file is in `tests/`, the compiled class is in package `words`
- The path `data/mots.json` works because the program is executed from the project root
