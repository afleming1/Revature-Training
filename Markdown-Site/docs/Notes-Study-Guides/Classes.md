# Java Classes

## Table of Contents
- [1. Java Classes](#1-java-classes)
    - [1.1 Class vs Object](#11-class-vs-object)
- [2. Modifiers](#2-modifiers)
    - [2.1 Access Modifier](#21-access-modifiers)
    - [2.2 Non-Access Modifiers](#22-non-access-modifiers)

## 1. Java Classes
Java is an **Object-Oriented Programming (OOP)** Language - All code is faciliated through the use of **Class** blueprints and **Object** implementation

| Concept | Analagy | Definition
| :--- | :--- | :--- |
| **Class** | **Blueprint** | Template or blueprint that defines<br>**Fields** - Attributes (Data)<br>**Methods** - Behaviors (Actions) |
| **Object** | **The House** | Concrete **instance** created from the class. Occupies sapce in memory and holds specific values for the properties defined by the class. |

### 1.1 Class vs Object
A **Class** is a logical construct while an **Object** is a physical construct (entity in memory). You can have one `Car` class, but you can have thousands of unique `Car` objects.

[&uarr; Back to Table of Contents](#table-of-contents)

## 2. Modifiers
### 2.1 Access Modifiers
Control where resources in your code can be accessed. There are four different access modifiers you can use to access your resources.

- **`public`** - Resources can be accessed anywhere in your code. Use this modifier for public facing code you write.
- **`protected`** - Resources can be accessed by child classes and within the same package.
- **`default`** - If no access modifier is provided, the instance is giving "Default" access modifier. This makes it so that only the resources in the same package can access the default modified resource.
- **`private`** - Resources can only be accessd within the same class where they are defined

### 2.2 Non-Access Modifiers
There are some keywords you need to be aware of that do not control access to resources

- **`static`** - Makes fields / methods class scoped
    - Allows the method to be called without first creating and "instance" (object) of the class.
- **`abstract`** - Makes classes unable to have an object made of them directly. Methods have their implementation defined in another location
    - Abstract methods do not have immediate implementation. Code elsewhere must implement the method.
- **`final`** - Final classes can not be extended. Final fields can not have their reference changed. Final methods cannot be overriden.

[&uarr; Back to Table of Contents](#table-of-contents)