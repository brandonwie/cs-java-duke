# Object Oriented Programming in Java

## What to learn in this course

1. Author a class in Java and explain how objects are constructured, how they store data, and how to define their actions
2. Trace the execution of code using memory-models
3. Define the scope of variables and methods
4. Extend existing libraries to build a medium-sized project
5. Implement user interface features
6. Build and work with a class hierarchy that has multiple levels
7. Explain "is-a" and "has-a" relationship between objects
8. Author code which implements an Interface
9. Explain the difference between compile-time and run-time decisions when working with polymorphism
10. Explain the difference between event-driven programming and imperative programming
11. Use searching and sorting to design algorithms for analyzing data
12. Search for an element in a sorted and unsorted list and explain the differences
13. Explain multiple sorting techniques and performance tradeoffs

## Week 1 ) Object design: constructors, instance variables, methods

### Java program execution
>
> ```java
> public static void main(String[] args){}
> ```

- Java begins execution with the first line of a "main" method which must have the signature above
- command line: `java MyClass`
  - the class must be compiled, and contain `main` method

#### `static` in `main`'s signature

- the method(or member variable) is defined for the class, but not for particular objects in the class.

#### Computer Science is

- The science of using and processing `large amount of information` `to automate useful tasks` and `learn about the world around us` (using a computer)

#### A **`class`** is a `type` of data

#### An **`object`** is one such`piece of data` (*with associated functionality)

### Overloading methods in Java

```java
  // OVERLOAD CONSTRUCTOR
  public SimpleLocation() {
    this.latitude = 32.9;
    this.longitude = -117.2;
  }

  public SimpleLocation(double lat, double lon) {
    this.latitude = lat;
    this.longitude = lon;
  }
```

```java
  // OVERLOAD METHOD
  public double distance(double otherLat, double otherLon) {}

  public double distance(SimpleLocation other) {}
```

- **Return type** is NOT part of the method signature
- Overloaded methods CAN have different return types
- Changing the **return type** is **NOT ENOUGH** for **overloading**
- **Overloading with different return type is only possible when it has different parameter set**

>In the Java programming language, a method signature is the method name and the number, type and order of its parameters. Return types and thrown exceptions are not considered to be a part of the method signature. -Wikipedia

```java
  public double distance(SimpleLocation other) {}

  public int distance(SimpleLocation other) {} // THIS WILL THROW ERROR!!! - PARAMETER MUST BE DIFFERENT
```

## Week 2 ) Memory Models, Scope, and Project

- primitive types don't need arrows (means no reference to a heap because it's just a value)


