# Object-Oriented Programming (OOP)

## Table Of Contents
- [1. Four Pillars of OOP](#1-four-pillars-of-oop)
- [2. OOP Ecosystem](#2-oop-ecosystem)
- [3. Deep Dive: The Four Pillars](#3-deep-dive-the-four-pillars)
    - [3.1 Encapsulation](#31-encapsulation-data-protection)
    - [3.2 Inheritance](#32-inheritance-is-a-relationship)
    - [3.3 Abstraction](#33-abstraction-complexity-hiding)
    - [3.4 Polymorphism](#34-polymorphism-many-forms)
- [4. Overloading vs Overriding](#4-overloading-vs-overriding)

## 1. Four Pillars of OOP
There are four fundamental pillars of OOP.

- **Encapsulation**
- **Abstraction**
- **Inheritance**
- **Polymorphism**

## 2. OOP Ecosystem
| Pillar | Primary Role | Relationship to Other Pillars |
| :--- | :--- | :--- |
| **Encapsulation** | **Data Integrity** | Access control - protects the data used by the other pillars |
| **Abstraction** | **Interface** | Defines the "what" (contract) that **Inheritance** and **Polymorphism** will later implement |
| **Inheritance** | **Hierarchy** | Uses **Abstraction** to build specialized versions of general concepts |
| **Polymorphism** | **Flexiblitiy** | Uses **Inheritance** and interface implementation to allow different objects to be treated as the same general type |

[&uarr; Back to Table of Contents](#table-of-contents)

## 3. Deep Dive: The Four Pillars

### 3.1 Encapsulation (Data Protection)
**Encapsulation** is the practice of bundling data (fields) and methods into a single unit (class) and **restricting direct access** to some of the object's components.

- **Common Implementation**
    1. Declare variables as `private`
    2. Provide public **Getter** and **Setter** methods to control how the data is viewed or modified

```java
public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

### 3.2 Inheritance ("Is-A" Relationship)
**Inheritance** allows a new class (***Subclass / Child***) to acquire the properties and methods of an existing (***Superclass / Parent***).

- **Keyword** - `extends`
- **Benefit** - Code reusability. You don't have to rewrite common logic for every new object type.
- **Example** - A `Dog` **is-a** `Animal`. The `Dog` class inherits `eat()` from `Animal` but can add its own unique behavior like `bark()`.

```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}

// Usage
Dog myDog = new Dog();
myDog.eat(); // Inherited Method
myDog.bark();
```

### 3.3 Abstraction (Complexity Hiding)
**Abstraction** focuses on the **essential qualities** of an object rather than the specific implementation details. It allows you to define a "contract" (what an object can do) withou specifying "how" it does it.

- **Mechanism** - Achieved via **Abstract Classes** and **Interfaces**
- **Goal:** Reduce complexity by allowing the programmer to focus on high-level interactions.

```java
// The Contract
interface RemoteControl {
    void pressPowerButton();
}

// Implementation 1: TV Remote
class TVRemote implements RemoteControl {
    @Override
    public void pressPowerButton() {
        System.out.println("TV: Powering On / Off...");
    }
}

// Implementation 2: AC Remote
class ACRemote implements RemoteControl {
    @Override
    public void pressPowerButton() [
        System.out.println("AC: Powering On / Off...");
    ]
}
```

### 3.4 Polymorphism (Many Forms)
**Polymorphism** allows an object to take on many forms. A parent class reference can point to a child class object, and the program will automatically use the correct, specialized behavior at runtime.

```java
public class Main {
    public static void main(String[] args) {
        RemoteControl myRemote = new TVRemote();
        myRemote.pressPowerButton();

        myRemote = new ACRemote();
        myRemote.pressPowerButton();
    }
}
```

[&uarr; Back to Table of Contents](#table-of-contents)

### 4. Overloading vs Overriding
While both involve methods with the same name, they occur at different stages in the program lifecycle.

| Feature | **Method Overloading** | **Method Overriding**
| :--- | :--- | :--- |
| **Concept** | Multiple methods in the **same class** with the same name but different parameters | A method in a **subclass** that has the same name and parameters as a method in its super class |
| **Relationship** | Within a single class | Between Parent and Child classes |
| **Signature** | **Must Change** (Different Arguments) | Must have the same method signature (name and parameter list). The return type must be compatible, and the overriding method cannot reduce visibility. |
| **Binding Time** | **Compile-Time** (Static Binding) | **Runtime** (Dynamic Binding) |

```java
// Overloading - Same Name, Different Parameters (Compile-Time)
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}

// Overriding - Same Name, Same Parameters, but Different Behavior (Runtime)
class SpecialCalculator extends Calculator {
    @Override
    int add(int a, int b) {
        return (a + b) * 2;
    }
}
```

[&uarr; Back to Table of Contents](#table-of-contents)