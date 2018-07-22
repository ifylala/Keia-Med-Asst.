package com.clevmania.keia;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static DatabaseReference getRootReference(){
        return FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseAuth getAuthenticationReference() {
        return FirebaseAuth.getInstance();
    }
}
