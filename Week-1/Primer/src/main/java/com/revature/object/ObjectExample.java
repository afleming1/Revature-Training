package com.revature.object;

import java.util.Objects;

public class ObjectExample {
    String myField;

    public ObjectExample(String myField) {
        this.myField = myField;
    }

    public static void main(String[] args) {
        ObjectExample myObject = new ObjectExample("My String");

        System.out.println(myObject);
    }

    /**
     * toString() is provided by the base Object class and can be overridden to provide a meaningful textual representation
     */
    @Override
    public String toString() {
        return "My Field: '" + myField + "'";
    }

    /**
     * equals() is provided by the base Object class and is used by Java to determine if two objects of your class represent the same thing
     * @param object   the reference object with which to compare
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ObjectExample that = (ObjectExample) object;
        return Objects.equals(myField, that.myField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myField);
    }
}
