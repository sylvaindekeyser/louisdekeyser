package com.example.jorda.jeux.model;

import java.io.Serializable;

public class Question implements Serializable {

    private int image;
    private String regex ;

    // utilisation des regex pour valider plusieurs reponses.
    public Question(int image, String regex){
        this.image=image;
        this.regex=regex;
    }

    public int getImage() {
        return image;
    }

    public String getRegex(){ return regex; }

}
