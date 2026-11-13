package com.revature.classes;

public class Dog {
    // Field exists in the Object (Instance) scope.
    // Every Dog object that is created will have access to its own "name" field and can use this field in any of its methods.
    String name;
    boolean hungry;

    // Class (Static) scope resource
    static int dogCount = 0;

    public Dog(String name, boolean hungry) {
        dogCount++;

        this.name = name;
        this.hungry = hungry;
    }

    public void barkName() {
        System.out.println("Bark Bark! My name is " + name);
    }

    /**
     * checkDogCount() and dogCount both belong to the static scope of the Dog class.
     */
    public static void checkDogCount() {
        System.out.println("Dog Count: " + dogCount);
    }

    public void eat() {
        // notHungryMessage is Method scoped. It can be used anywhere inside the method.
        String notHungryMessage = "I am full!";

        if (hungry) {
            // hungryMessage exists in Block scope. It is only available within the code block where it was initialized
            String hungryMessage = "Nom nom nom!";

            System.out.println(hungryMessage);
        } else {
            System.out.println(notHungryMessage);
        }
    }
}
