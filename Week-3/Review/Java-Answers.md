# Java Review - Answers

1. The compiler produces bytecode in the form of .class files.

2. The JDK adds the build tools necessary to create Java apps.

3. An object is eligible for Garbage Collection when its memory reference is replaced. Here, `o2` is eligible because the line `o2 = o3` replaces the memory reference for it.

4. Scope modifiers make the objects (variables / classes / modifiers) scoped to the various.
    - Scope - Where and when. Only compile-time (Coding / Write) time.
    - Access - Where something is accessible. Only compile-time. Control your access to write the code.

5. The instance variable `x` will be created 100 times because instance variables are created for every instance. The `static` variable `y` will only exist once for all copies because it is scoped to the class; it belongs to the class. If we create 0 instances, we still get one `static y` variable.

6. Java classes by default get a blank, no-argument constructor. Once you started creating your own constructors, if you need a no-argument one, you must explicitly recreate it.

7. `==` compares the actual value held in the stack. `.equals` compares the primitives. We have to override the `.equals` method and pass the other object into `.equals`.

8. Collections rely on `hashCode()`.

9. Pass-by-value means that the object is copied. Copy still points to original. The dot operators re-references. copy of address is still an address.

10. The stack holds primitives and references. Objects live on the heap.

11. Since Java strings are immutable, when you call `str1.concat(" is the best!");`, you are creating a brand new string. The original string, `str` still points to it's original data in memory.

12. Identical string literals share a pooled object, while `new String("foo")` creates a separate object. Use `equals()` to compare the text.

13. Using `+=` repeatedly creates a new string and copies the text built so far. `StringBuilder` appends to a reusable buffer that grows as needed.

14. They are not. `i *= 2` assigns a new `Integer` value to the local loop variable, leaving the list element uncahnged.

15. Varargs go last so the compiler can match the fixed parameters first and gather the remaining arguments. The method receives those arguments as an array.

16. The package is part of a class's full name, therefore `java.util.Date` and `java.sql.Date` are distinct classes.

17.  Maven looks in the local PC cache repository, then it searches the Maven central repository. The second build is usually faster because Maven caches from your local PC if the dependency is downloaded there.

18.  Maven commands run the given commands and all commands before it.

19. `@Override` tells the computer to verify that the method actually overrides or implements an inherited method. It catches mistakes like a misspelled method name or wrong parameter types.

20.  `double` to `int` requires casting because a `double` can represent every `int` exactly and Java can automatically convert it. Going the other way requires casting because of data loss with the fraction part of the `double`.

21. A cast can't interpret text as a number. Use `Integer.parseInt("42")` to get the `int` value `42`.