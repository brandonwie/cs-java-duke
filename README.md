# Note (Java Programming)

> Course of Duke University - Coursera</br>
> Author: Seokhyun Wie (Brandon) &copy;

# Arrays, Lists, and Sturctured Data

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

1. assign all alphabets to a _**String**_
1. create an **Array** with a length of 26 that matches with _**String**_
1. loop through each letter of input and compare with the _**String**_
1. increase elements in the **Array** that has the same index in _**String**_

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
