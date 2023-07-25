package com.AZtech_labs.joali.BDD;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserModel extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String dnaissance;
    private String lnaissance;
    private String mobile;

    private int active;
    private int age;
}
