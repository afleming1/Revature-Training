package com.revature.modifiers.access.sub;

import com.revature.modifiers.access.Person;

public class Adult extends Person {
    public Adult(String name, int age) {
        super(name, age);
    }

    public void giveCommunityPlaygroundGreeting() {
        // communityPlaygroundMeeting cannot be accessed here even through Adult inherits the method from Person
        //communityPlaygroundMeeting();
    }
}
