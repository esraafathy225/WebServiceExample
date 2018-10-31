package com.esraa.hp.webserviceex;

/**
 * Created by HP on 2018-10-13.
 */

public class UserDetails {
    private String name;
    private int likes;


    public UserDetails(String name, int likes){
        this.name=name;
        this.likes=likes;
    }

    public int getLikes() {
        return likes;
    }

    public String getName() {
        return name;
    }
}
