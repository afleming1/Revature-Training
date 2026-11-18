# Java Methods

## Table Of Contents
- [1. Java Methods](#1-java-methods)
    [1.1 Method Declaration & Syntax](#11-method-declaration--syntax)

## 1. Java Methods
Java **methods** are the mechanism in which you define repeatable actions within your code. Methods are attached to classes an can be instanced or class scope. Call a method using the **`.`** operator and reference the method you want to invoke.

### 1.1 Method Declaration & Syntax
A **method** is a block of code that only runs when it is called. To use a method, you must first define it's structure.

**Method Syntax**
`[Modifer] [Return Type] [Method Name] ([Parameters]) { // Body }`

```java
public int addNumbers(int a, int b) {
    return a + b;
}
```

- **Method Parameters** - Variables listed in the method declaration
- **Return Type** - Type of value the method sends back to the caller. Use `void` if the method returns nothing.
- **Method Invocation** - Act of "calling" or executing the method

[&uarr; Back to Table of Contents](#table-of-contents)