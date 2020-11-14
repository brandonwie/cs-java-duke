# Course 4. Data Structures and Performance

## What to learn in this course

1. Describe the difference between an abstract data type (ADT) and its implementation.
2. Work with Java's built-in classes for fundamental data structures including: String, LinkedList, TreeSet, HashSet, and HashMap.
3. Implement fundamental data structures from scratch including Linked Lists and Tries (yes, we mean Tries rather than trees ... stay tuned to learn about this data structure!).
4. Analyze the performance of code, using Big-O notation as well as through benchmarking experiments.
5. Compare and contrast the performance of different data structures to select the most appropriate for a task.
6. Write JUnit tests to ensure the correctness of your code.
7. Implement several algorithms for generating and processing text.

## Pre-Course Quiz

  ```java
  public class Person {
    private String name;

    public Person(String n) {
      this.name = n;
    }

    public String toString() {
      return name;
    }
  }

  public class Student extends Person {
    private int id;

    public Student(String n, int id) {
      super(n);
      this.id = id;
    }

    public String toString() {
      return super.toString()+", "+id;
    }
  }

  public class Tester {
    public static void main (String[] args) {
      Person s = new Student("Jose",12345);
      System.out.println(s);
    }
  }
  // at runtime, the object is type Student.
  // So the method called will be toString method
  // in Student, which prints "Jose, 12345"
  ```

## Flesch reading ease

- Flesch score = 206.835 - 1.015(#words/#sentences) - 84.6(#syllables/#words)

|Score|School level|Notes|
|:-|:-|:-|
|100.00–90.00|5th grade|Very easy to read. Easily understood by an average 11-year-old student.|
|90.0–80.0|6th grade|Easy to read. Conversational English for consumers.|
|80.0–70.0|7th grade|Fairly easy to read.|
|70.0–60.0|8th & 9th grade|Plain English. Easily understood by 13- to 15-year-old students.|
|60.0–50.0|10th to 12th grade|Fairly difficult to read.
|50.0–30.0|College|Difficult to read.|
|30.0–10.0|College graduate|Very difficult to read. Best understood by university graduates.|
|10.0–0.0|Professional|Extremely difficult to read. Best understood by university graduates.|

## String Basics

> `String`s are objects, not Primitive

1. Strings are immutable
2. String concat
   - creates a new heap(new object)
3. `+` operator also does concat

4. Interned Strings: One object

    ```java
    String text = new String("Hello World!"); // heap 1
    String text2 = "Hello World!"; // heap 2 (interned obj)
    String text3 = "Hello World!"; // heap 2 (interned obj)
    // it does not create a new object unless `new` is declared
    ```

5. `.equals` vs `==`

    ```java
    String text = new String("Hello World!"); // heap 1
    String text2 = new String("Hello World!"); // heap 2
    text.equals(text2) // Evaluates to TRUE (value comparison)
    text == text2 // Evaluates to FALSE (reference comparison)
    ```

   1. `.equals` compares characters (object)
   2. `==` compares variable values (reference)

```java
char[] cArray = word.toCharArray();
// creates a copy of the word object
```

```java
// with Errors
public static String replace(String word, char gone, char here) {
  char[] cArray = word.toCharArray();
  for(char c : cArray) {
    if (c == gone) {
      c = here; // it only changes copy of char (which is c),
                //not char inside cArray
    }
  }
  return word; // nothing has change to original `word`
}
```

## RegEx

- `+`: one or more
- `*`: zero or more
- `[]`: any single char inside
  - `[1-3]`, `[a-f]` can be used to set range
- `^`: exclude
  - `[^a-z123 ]`: NOT lower case a-z, 1-3, empty space

## Measureing Performances: Big O

### Asymptotic Analysis

  1. Count operations instead of time
  2. Focus on how performance scales
  3. Go beyond input size

- What counts as an operation?
  - Operation: basic unit that doesn't change as the input changes
- Think about the number of which part of the operation actually increases when input size increases (however, number of input does not affect algorithm efficiency)

### `f(n) = O(g(n))`

> (Eventually,) `f(n)` and `g(n)` grow in same way as their input grows (up to constants)

- **Big O** notation captures the **Rate of growth of a function**

1. Drop Constants
   - 100000 = O(1)
2. Keep only dominant term
   - 3n + 3 => O(3n) => O(n)

- (Formally) f(n) = O(g(n)): f(n) equals Big O of g(n)
  - there are constants N and c so that for each n > N
  - `f(n) <= Cg(n)`

```java
public static void reduce(int[] vals) {
  int minIndex = 0; // O(1)
  for (int i = 0; i < vals.length; i++) { // O(n)
    if (vals[i] < vals[minIndex]) {
      minIndex = i;
    }
  }
  int minVal = vals[minIndex]; // O(1)
  for (int i = 0; i < vals.length; i++) { // O(n)
    vals[i] = vals[i] - minVal;
  }
} // TOTAL: 2n + 2 => O(n)

int[] vals = [1,2,5,3];
// first for-loop
0 -> vals[0] = 1 / vals[minIndex] = 1 / minIndex = 0
1 -> vals[1] = 2 / vals[minIndex] = 1 / minIndex = 0
2 -> vals[2] = 5 / vals[minIndex] = 1 / minIndex = 0
3 -> vals[3] = 3 / vals[minIndex] = 1 / minIndex = 0
minVal = 1
vals[0] = vals[0] - 1 = 1 - 1 = 0
vals[1] = vals[1] - 1 = 2 - 1 = 1
vals[2] = vals[2] - 1 = 5 - 1 = 4
vals[3] = vals[3] - 1 = 3 - 1 = 2
result [0,1,4,2]
```

```java
public static int maxDifference (int[] vals) {
  int max = 0; // O(1)
  for (int i=0; i < vals.length; i++) { // O(n)
    for (int j=0; j < vals.length; j++) { // O(n)
      if (vals[i] – vals[j] > max) { // O(1)
        max = vals[i] – vals[j];
      }
    }
  }
  return max; // O(1)
} // TOTAL: n^2 + 2 => O(n^2)
```

- **Big-O only applies as n gets large**. When n is small, either A or B could use fewer operations

```console
O(n) = n^2 - 10000 = O(n^2)
O(n^2) = n + n*log(n) = O(n*log(n)) // can ignore n
O(log2(n)) = log10(n)

log10(n) = log2(n) / log2(10)
```

```java
for (int i=0; i<2*n; i++) { // O(n): runs 2n times
  for (int j=n-1000; j<n; j++) { //O(1000) bcs it starts from n-1000 and ends at n, which means
    for (int k=n/2; k<n; k++ ) { // O(n): runs n/2 times
      //sum is a variable declared and initialized elsewhere
      sum++; // O(1)
    }
  }
} // TOTAL: O(n^2)
```

- Average case: Performance of algorithm on average, consider all possible inputs of size n (too complicated)
- Therefore, think of the best case and the worst case as a big bound and see the entire ballpark as a whole

||Best case|Worst case|
|:-|:-|:-|
|Linear Search|O(1)|O(n)|
|Binary Search*|O(1)|O(log(n))|
*assuming data is sorted

||Best case|Worst case|
|:-|:-|:-|
|Selection Sort|O(n^2)|O(n^2)|
|Insertion Sort|O(n)|O(n^2)|
|Merge Sort|O(n\*log(n))|O(n\*log(n))|

### Merge Sort (recursion)

```console
if list ahs one element, return.

Divide list in half

Sort first half // RECURSION
Sort second half // RECURSION

Merge sorted lists
```

||Best case|Average case|Worst case|
|:-|:-|:-|:-|
|Selection Sor t|O(n^2)|O(n^2)|O(n^2)|
|Insertion Sort|O(n)|O(n^2)|O(n^2)|
|Merge Sort|O(n\*log(n))|O(n\*log(n))|O(n\*log(n))|
|Quick Sort|O(n\*log(n))|O(n\*log(n))|O(log(n^2))|
*Asymptotic is not the only measure of performance

### Benchmarking and Timing

```java
long startTime = System.nanoTime();
methodToDetermine();
long endTime = System.nanoTime();
System.out.println((endTime- startTime)/100000000); // millisecond
```

## Week 4 ) Abstraction, LinkedList

### Some basics

- List as interface || abstract  (ADT: Abstract Data Type): no implementation
- Abstraction Barrier (in between ADT and Data Structure): sets the rules of interaction
- ArrayList - Data Structure (Specific implementation)

### ArrayList vs LinkedList

- How long does it take to add an element to the front of an ArrayList?
  - O(n)
- How lon does it take to get a particular element from an ArrayList?
  - O(1)

- (doubled linked list)

```console
  head -> | prev - data - next | <=> | prev - data - next | <- tail
        ListNode

  MyLinkedList: has references to head and tail (pointer) to access all the data
```

- ArrayList elements has it's own positions on the memory, so you to get an element from ArrayList takes O(1) regardless of what element you choose, however, LinkedList elements is connected to each other, it takes O(n) to get an element from the List.

### Example: Parameterized typed

```java
public class RememberLast<T> {
  private T lastElement;
  private int numElement;

  public RememberLast() {
    numElements = 0;
    lastElement = null;
  }

  public T add (T element) {
    if(element == null) {
      throw new NullPointerException("RememberLast Object cannot store null pointers.")
    }
    T prevLast = lastElement;
    lastElement = element;
    numElements++;
    return prevLast;
  }
}

// Somewhere else...
RememberLast<Integer> rInt = new RememberLast<>();
RememberLast<String> rStr = new RememberLast<>();

rInt.add(3);
rStr.add("Happy");
```

### Adding to a Linked List

- Insert a new node at the very front

1. create new node
2. Link new node in
   1. n.next = head.next;
   2. n.prev = n.next.prev;
   3. n.next.prev = n;
   4. head.next = n; (more generally n.prev.next)
3. update size

- if you modify head.next first for instance, to point n.next, then the original head.next which was pointing to the first node disappears
- start with current node(n), one that you wanna insert, assign head.next(previous first node) to n.next, n.next.prev(previous first node's prev which was pointing the head node) to n.prev (because n becomes the first node)

### Testing Practices

1. Black Box Testing
   1. often more representative of user use of code (to some extent)
   2. easier to write by someone unfamiliar with the implementation
2. Clear Box Testing
   1. more knowledgeable of potential corner cases which might cause incorrect behavior

#### Testing Linked List "Get" Method

> ```java
> // removes the element at the index indicated or, if the index
> // is invalid, throw an IndexOutOfBoundsException
> public E get (int index)
> ```

1. Test get(0) from an empty list

    ```java
    // in testGet (in JUnit @Test)
    try {
      emptyList.get(0);
      fail("Check out of bounds");
    } catch(IndexOutOfBoundsException e) {}
    ```

2. Test get(-1) from a list with 1 element

    ```java
    try {
      shortList.get(-1);
      fail("Check out of bounds");
    } catch (IndexOutOfBoundsException e) {}
    ```

3. Test get(0) from a list with 1 element (maybe one more test: to ensure it works with not just only with first element)

    ```java
    assertEquals("Check first", (Integer)65, shortList.get(0));
    ```
