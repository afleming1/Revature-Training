package com.revature.modifiers;

import com.revature.modifiers.access.Kid;

public class Playground {
    public static void main(String[] args) {
        Kid sally = new Kid("Sally", 8);

        System.out.println(sally.name);
        System.out.println(sally.age);

        sally.shareSecretPassword();

        // The communityPassword cannot be directly called here because it is protected
        //sally.communityPassword();

        //sally.socialSecurityNumber;
    }
}
