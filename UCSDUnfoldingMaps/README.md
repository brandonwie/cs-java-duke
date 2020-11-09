# Course3-2. Object Oriented Programming in Java

## Week 4)

### Inheritance

> keyword: `extends`

1. Keep common behavior in one class
2. Split different behavior into separate classes
3. Keep all of the objects in a single data structure

#### `extends` means `inherit from`

- What is inherited?
  - Public instance variables
  - Public methods
  - Private instance variables
    - **Private vars can be accessed only through public methods**
    - Use getter and setter to access

- UML Diagrams
   |Person|
   |:-|
   |String name|
   |String getName()|

- inheritance will solve #1, #2 above

#### `is-a` relationship

- Reference - Object relationship

  ```java
  Person p = new Person();
  Student s = new Student();

  Person p = new Student(); // this should be allowed because a student is a person

  // in main
  Person[] p = new Person[3];
  p[0] = new Person();
  p[1] = new Student();
  p[2] = new Faculty();
  // A Person array CAN store Student and Faculty objects
  ```

1. Compile time decisions: reference type
2. Run time decisions: object type

#### Inheritance Example

```java
public class Person {
  private String name;
  public String getName() {return name;}
}

public class Student extends Person {
  private int id;
  public int getID() {return id;}
}

public class Faculty extends Person {
  private String id;
  public String getID() {return id;}
}
```

```java
Student s = new Student();
Person p = new Person();
Person q = new Person();
Faculty f = new Faculty();
Object o = new Faculty();
```

1. `String n = s.getName();` [o]
   1. `Student` inherits `Person`, so can access `.getName()` in `Person`
2. `p = s;` [o]
   1. simply assign `new Student()` to `Person p` object
   2. Can `Person` reference an Object of type `Student`?
   3. Is a student also a person? Yes.
3. `int m = p.getID();` [x] - compile error
   1. complie time error: compiler still think that `p` is `Person`
   2. !only at Runtime it sees `p` as `Student` object
   3. can fix this issue with CAST `int m = ((Student)p).getID();`
4. `f = q;` [x] - compile error
   1. cannot assign parent to a child
   2. Is all people faculty? No.
5. `o = s;` [o]
   1. every object inherits from the object class
   2. can assign new object to `Object` variable
   3. Everthing in Java is object

### Rule of thumb: Make member variables `private` (and methods either public or private)

- `protected`: can access from **same class**, **same package**, **any subclass**
- `package`: can access from **same class**, **same package**(loose access by any subclass)

### Compile Rules

1. No superclass? Compiler inserts: `extends Object`

   ```java
   public class Person extends Object { // implicitly
     private String name;
   }

2. No contructor? Java gives you one for you

  ```java
  public class Person extends Object {
    private String name;

    public Person(){}
  }
  ```

3. First line in the constructor must be:
   1. `this(args)` // same class constructor call
   2. OR `super(args)` // Super class constructor call
   3. OTHERWISE, Java inserts: `super();`

  ```java
  public class Person extends Object { // added by compiler
    private String name;

    public Person(){
      super(); // added by compiler
    }
  }
  ```

```java
  public class Person extends Object {
    private String name;

    public Person(String n) {
      super();
      this.name = n;
    }
  }

  public class Student extends Person {
    public Student(String n) {
      //super();
      //this.name = n;  COMPILE ERROR
      super(n); // pass variable via super()
    }

    // add NO-ARG default constructor
    public Student() {
      // super("Student"); can be, but not a good idea
      this("Student"); // Use our same class constructor
    }
  }
```

### Concept Challenge #1

```java
public class Person {
  private String name;
  public Person(String n) { // 4
    this.name = n; // 5
    System.out.print("#1 "); // 6
  }
}

public class Student extends Person {
  public Student() {
    this("Student"); // 1
    System.out.print("#2 "); // 8
  }
  public Student(String n) { // 2
    super(n); // 3
    System.out.print("#3 "); // 7
  }
}

Student s = new Student();
```

### Concept Challenge #2

```java
public class Person {
  private String name;

  public Person(String n) {
    super();
    this.name = n;
  }
  public void setName(String n) {
    this.name = n;
  }
}

public class Student extends Person {
  public Student() {
    // super(); will be inserted by compiler
    // but there's nothing with no-arg constructor in Person
    // therefore, Java throws compiler error
    this.setName("Student");
  }
}

Student s = new Student();
```

### Overloading vs Overriding

- **Overloading**: **Same class** has same method name with **different** parameters
- **Overriding**: **Subclass** has same method name with the **same parametes** as the superclass

```java
public class Person {
  private String name;

  public String toString() {
    return this.getName();
  }
}
// assume ctor
Person p  = new Person("Tim");
System.out.println(p.toString()); // calls Person's toString() (override)
System.out.println(p); // calls Object's toString() method
// if you ever pass an object as parameter to print line,
// it automatically calls toString() method

```

## Polymorphism

> Superclass reference to subclass object
>
> ```java
> Person s = new Student("Cara", 1234);
> ```

```java
// assume appropriate ctors
// in main
Person p[] = new Person[3];
p[0] = new Person( "Tim" );
p[1] = new Student( "Cara", 1234);
p[2] = new Faculty( "Mia", "ABCD" );

for(int i = 0; i < p.length; i++)
{
  System.out.println( p[i] );
}
```

### Compile-time vs Runtime rules

>"Think like a compiler, act like a runtime environment"
> -Rick Ord

Step 1. Compiler interprets code
Step 2. Runtime environment executes interpreted code

1. Compile Time Rules
   - Compiler ONLY knows reference type
   - Can only look in reference type class for method
   - Outputs a method signature

2. Run Time Rules

   - Follow exact runtime type of object to find method
   - Must match compile time method signature to appropriate method in actual object's class

3. Casting
   - Automatic type promotion(like `int` to `double`)
     - `Superclass superRef = new Subclass();`
   - Explicit casting (like `double` to `int`)
     - `Subclass ref = (Subclass) superRef;

  ```java
  public class Person {
    private String name;
    public String getName(){//...}
    public String toString(){//...}
  }

  public class Student extends Person {
    private int studentID;
    public int getSID(){//...}
    public String toString(){//...}
  }

  Person s = new Student("Cara", 1234);
  s.getSID(); // Compile Time Error!!!
  ((Student)s).getSID(); // This will work!!

  Person s = new Person("Tim");
  ((Student)s).getSID(); // Runtime Error (bcs Compiler trusts you)
  // java.lang.ClassCastException: From Person to Student
  ```

4. Runtime type check

> `instanceof`

- Provides runtime check of `is-a` relationship

  ```java
  if (s instanceof Student) {
    // only executes if s is-a
    // Student at runtime
    ((Student)s).getSID();
  }
  ```

### Polymorphism Part 1

```java
public class Person
{
  private String name;

  public Person(String name)  { this.name = name; }
  public boolean isAsleep(int hr)  { return 22 < hr || 7 > hr; }
  public String toString()      { return name; }

  public void status( int hr )
  {
    if ( this.isAsleep( hr ) )
      System.out.println( "Now offline: " + this );
    else
      System.out.println( "Now online: " + this );
  }
}

public class Student extends Person
{
  public Student(String name)  {
    super(name);
  }

  public boolean isAsleep( int hr ) // override
  { return 2 < hr && 8 > hr; }
}

Person p;
p = new Student("Sally");
p.status(1);
// 1. at runtime Java will take `p` as a Student object
// 2. Student doesn't have `status` method, so Java will look for super class Person
// 3. In `this.isAsleep(hr) `this` will point Student object because it's called from Student object (Dynamic Binding)
// 4. so it will follow Student's `isAsleep()` method
```

### Polymorphism Part 2

```java
public class Person {
    public void method1() {
        System.out.print("Person 1 ");
    }
    public void method2() {
        System.out.print("Person 2 ");
    }
}

public class Student extends Person {
    public void method1() {
        System.out.print("Student 1 ");
        super.method1(); // when compile, it bind `method1()` in Person
        method2();
    }
    public void method2() {
        System.out.print("Student 2 ");
    }
}

public class Undergrad extends Student {
     public void method2() {
         System.out.print("Undergrad 2 ");
     }
}

Person u = new Undergrad();
u.method1();
// 1. it finds `method1()` in Student (extends)
// 2. `super.method1();` => lead to `method1()` in Person class
// 3. `method2()` doesn't have calling object, Java will insert `this`
```

- call to `super`: get bound on compiler time
- call to `this`: happen at runtime

### Abstract class

#### Person - Campus Accounts

- Add method "monthlyStatement"
- "Person" objects no longer make sense

How do we:

1. Force subclasses to have this method
2. Stop having actual Person objects
3. Keep having Person references
4. Retain common Person code
=> use `Abstract` classes!

- Can make any class abstract with keyword:

```java
public abstract class Person {}
// cannot create objects of this type
```

- Class **must** be abstract if any methods are:

```java
public abstract void monthlyStatement() {}
// Concrete cubclasses must override this method
// nice way of forcing subclasses to have this method
```

### Implementation vs Interface

> Abstract classes offer inheritance of both!

- **Implementation**: instance variables and methods which define common behavior
- **Interface**: methods signatures which define required behaviors

### When only need Interface

- 4. <s>Retain common Person code</s> (if there's no common behavior needed)
- then use an Interface

### Interfaces

- Interfaces only define required methods
- Classes can inherit from multiple Interfaces

```java
// Defined in java.lang.Comparable
package java.lang;

public interface Comparable<E> {
  // Compare this object's name to o's name
  // Return <0, 0, > 0 if this object compares
  // less than, equal to, greater than o.
  public abstract int compareTo(E o);
}

public class Person implements Comparable<Person> {
  private String name;
  ...

  @Override
  public int compareTo(Person o) {
    return this.getName().compareTo(o.getName());
  }
}
```

### Abstract class or Interface

- If you just want to define a required method:
  - **Interface**
- If you want to define potentially required methods AND common behavior
  - **Abstract class**

## Week5 ) Event-Driven Programming

- Procedural
  - Code execution follows predictable sequence, based on control logic and program state

    ```java
    int[] vals = new int[7];

    for (int i = 0; i < vals.length; i++) {
      vals[i] = i;
    }
    ```

- Event driven

  ```java
  public void keyPressed() {...}
  public void mousePressed() {...}
  ```
