package main.java;

import main.java.enums.AccountStatus;

public class Account {

    String id;
    String password;
    AccountStatus status;


    boolean resetPassword(){
        return true;
    }
}
