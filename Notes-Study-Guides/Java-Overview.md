# Java Overview

## Table of Contents
- [1. High-Level Overview](#1-high-level-overview)
- [2. Advantages of Java](#2-advantages-of-java)
- [3. Java Ecosystem](#3-java-ecosystem-jvm-jre-jdk)
    - [3.1 JRE vs JDK](#31-jre-vs-jdk)
- [4. Getting Started](#4-getting-started)
    - [4.1 Hello World](#41-hello-world)
    - [4.1 Execution Workflow](#42-execution-workflow)

## 1. High-Level Overview
**Java** at its core, is a high-level, class-based, object-oriented (OOP) programming language. Java is also compiled - meaning Java code can run on all platforms that support Java without needing to be recompiled. This is achieved through abstraction using the **Java Virtual Machine (JVM)**. Java is therefore a **Write Once, Run Anywhere** language.

**More Java Info**
- **High-Level Language** - No Need for manual memory management
    - Java contains automated garbage collection processes
- **Statically Typed Language** - You must provide the type for your data when writing your code
    - Declare type explicitly (Normal)
        - `String myStr = "Hello World";`
    - Use special type *var* and have the compiler infer the type based on the value
        - `var age = 27;`
- **Strongly Typed Language** - Once a reference has its type set, Java cannot implicitly change the type of that reference

[&uarr; Back to Table of Contents](#table-of-contents)

## 2. Advantages of Java
- Java is platform dependent
- Java's syntax is inspired by **C**
- Java provides automatic memory management
- Java has an extensive built-in runtime library
- Java is supported by Oracle
- Java has a rich open source community

[&uarr; Back to Table of Contents](#table-of-contents)

## 3. Java Ecosystem (JVM, JRE, JDK)
These are the three core components for how Java code is developed, compiled, and executed. The relationship between these three components follows a hierarchy.

**Java Development Kit (JDK)** &rarr; **Java Runtime Environment (JRE)** &rarr; **Java Virtual Machine (JVM)**

The **JDK** contains the **JRE** which contains the **JVM**.

| Component | Full Name | Purpose | Contents |
| :--- | :--- | :--- | :--- |
| **JDK** | Java Development Kit | Contains the tools required to *develop* and *run* Java applications. | `javac` compiler<br>`jdb` debugger
| **JRE** | Java Runtime Environment | Contains libraries needed to *run* Java applications. | `java.lang`<br>`java.util`
| **JVM** | Java Virtual Machine | Execution engine that runs the bytecode.<br>The JVM compiles `.class` files into machine code. | Just-In-Time (JIT) Compiler<br>Interpreter<br>Garbage Collector

### 3.1 JRE vs JDK
Fundamental difference between these two is what you **intend** to do
- **JRE Usage** - If you only want to play a Java-based game or run an existing Java application
- **JDK Usage** - If you intend to write code, compile it into bytecode, and develop new software

[&uarr; Back to Table of Contents](#table-of-contents)

## 4. Getting Started

### 4.1 Hello World
**"Hello World"** is one of the most basic applications in any programming language. Below is the Java implementation

```java
// Filenames in Java must match the class name: HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

> [!TIP]
> The Java **public class** name must match the filename. `HelloWorld` class **must** match a file named `HelloWorld.Java`.

### 4.2 Execution Workflow
The transformation of source to a running program involves two distinct steps
- **Compile** - `javac HelloWorld.java` &rarr; Produces `HelloWorld.class` (**Bytecode**)
- **Run** - `javac HelloWorld` &rarr; **JVM** executes the bytecode

[&uarr; Back to Table of Contents](#table-of-contents)