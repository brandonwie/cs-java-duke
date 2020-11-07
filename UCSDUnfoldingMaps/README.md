# Object Oriented Java Programming

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
