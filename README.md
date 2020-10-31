# Note (Java Programming)

> Course of Duke University - Coursera</br>
> Author: Seokhyun Wie (Brandon) &copy;

## Week1 ) Arrays, Lists, and Structured Data

## A. Caesar Cipher Algorithm

### 1. Encrypt

1. assign all alphabets to a _**String**_
1. shift the string using `substring` with the key as pivot
1. use **StringBuilder** to assign intput value
1. loop through each letter of input, find indice from shifted alphabet that matches indice from alphabet string
1. replace **StringBuilder** letters with new letter found

### 2. Decrypt

1. same as Encrypt, but the key is now `26-key`

### 3. Count

1. assign all alphabets to a ***String***
1. create an **Array** with a length of 26 that matches with ***String***
1. loop through each letter of input and compare with the ***String***
1. increase elements in the **Array** that has the same index in ***String***

### 4. Breaking Caesar Cipher

> using letter frequency in English

- the most frequent letter will be most likely `E`
- use the **Decrypt** method, but what matters the most is the **KEY** value

#### how to find key value

1. use **Count** method to count which letter occurs the most and return `index` of it
1. assume the `index` is the letter `E`'s index and now find an index of `A` to decrypt; `key = maxIndex -4`
1. if the `maxIndex` which is `E`'s location is less than 4, then find the distance between current `E`'s index(maxIndex) and initial `E`'s index(which is 4) and calculate the key from the end because if it's the `E` is located at `index < 4` the amount which is the same as the distance is shifted to the end (pushed back); `key = 26 - ( 4 - maxIndex )`

### 5. Summary

#### - Arrays: Indexed Collections

- String[], int[], StorageResource[] or
  - One variable represents ...many values
  - Indexed collection of elements

#### - Using Arrays in Java

- the size can't change, but vaules in array cells can change

```java
String[] names = new String[100]; //set size
int[] counters = new int[2048];
names[5] = "Zeus";
counters[123] = counters[123] + 1;
```

### - Indexing Array Elements

- Typically use for-loops with indexes
  - Sometimes build methods to solve problems

### - Cracking Codes

- Use arrays to crack a Caesar cipher
  - Counting frequencies, using indexing
  - Both encrypt and decrypt used indexing

## B. Object Oriented Programming

- Java: Object Oriented Language
- Encapsulate code and data
  > **Object = Code(methods) + Data(fields)**
- Familiar example: **String**
  - Data: sequence of char
  - Code:

    ```java
    s.length(); // int
    s.indexOf("program"); // int
    s.indexOf("q", 8);
    s.startsWith("duke"); // bool
    s.endsWith("king");
    s.subString(4,7); // String
    s.toUpperCase();
    ```

- Class = Type, Object = Instance
  - can make many Object(instances)
  - Classes define what's in object of that type
- What's to learn [here](##B.-Object-Oriented-Programming)
  - facilitate large programs
  - fields, constructors, visibility
  - principles of Software Design

### - Obeject Oriented Caesar Cipher

- Same functionality, easier to think of Cipher as logical unit w/ key
- [Old approach](course2/lecture/src/CaesarCipherOld.java)
- [OO approach](course2/lecture/src/CaesarCipher.java) - using `private`

#### Class Concepts

- Class = Noun
- Method = verb
- Field = noun
  - Noun: Things that a class has
  - Adjectives describe properties

#### Why Private

- Abstraction: separation of **interface** + **implementation**
  - public - interface: What it does
  - private - implementation: How it does it (fields, helpers)

---

## Week2 ) Arrays, Lists, and Structured Data

### GladLibs

#### 1. Thinking About Data: Types

> Benefits and Drawbacks

- **StorageResource**
  - No need to know size, add as needed
  - Must iterate over all elements, but random choice?
- **String[] array**
  - Easy to choose at random, pick an index
  - Must know capacity of array when creating
- **New Concept**: ArrayList

#### 2. **ArrayList** as a Solution

- Class **ArrayList** in package `java.util`
- Expand as needed using `.add` method
- Provides access via index to any element in list
- Essential in implementing StorageResource
- Basic syntax

  ```java
  ArrayList<String> words = new ArrayList<String>();
  words.add("hello");
  words.add("hello");
  String s = words.get(1);
  words.set(0, "goodbye");
  ```

#### 3. Arrays and ArrayList

- `String[] a` and `ArrayList<String> b`
  - `a[k]` compared `b.get()` and `b.set()`
- `int[] ac` and `ArrayList<Integer> bc`
  - Concerns with int/Integer conversions
  - `ac[index]++` works
  - `bc.get(index)++` DOES NOT WORK
- But, arrays don't grow, that's a concern!

#### Sum1. ArrayList

- Indexable collection, like array, but growable
  - Access via integer index, start with zero
  - import java.util.ArrayList or java.util.*;
  - Create with generic: **ArrayList\<Integer\>**
- Common methods for ArrayList
  - `.add(elt)` - added to end of ArrayList
  - `.size()` - returns number of elements in ArrayList

#### Sum2. ArrayList with Indexing Loops

- Access elements via indexing
  - Start with zero, loop to less than `.size()`
  - Access via `.get(index)`
  - Do not call `.remove()` during iteration

  ```java
  ArrayList<String> a = new ArrayList<String>();
  // add elements

  for (int k=0; k< a.size(); k++) {
    String s = a.get(k);
    // process s
  }
  ```

### Extending Programs and Classes

- Good design or good judgment comes from experience
  - Experience comes from bad judgment
- Software can be brittle
- Open/Closed: open for extension, closed for modification
