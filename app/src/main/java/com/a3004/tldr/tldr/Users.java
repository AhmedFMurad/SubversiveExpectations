package com.a3004.tldr.tldr;


import java.util.Arrays;
import java.util.Date;

import static java.util.Arrays.asList;

public class Users {
    protected String username;
    protected String[] preferedCategories;
    protected Date lastLogin;
    protected int amountOfCategories;

    public String getUserName(){
        return username;
    }

    public void addCategory(String category){
        if(!(this.preferedCategories.length < this.amountOfCategories)){
            // expand the array
        } else {
            this.preferedCategories[this.amountOfCategories] = category;
            this.amountOfCategories++;
        }
        // Update database?
    }

    public void removeCategory(String category){
        int index = Arrays.asList(this.preferedCategories).indexOf(category);
        for(int i=index; i<this.amountOfCategories;i++){
            this.preferedCategories[i] = this.preferedCategories[i+1];
        }
        this.amountOfCategories--;
    }


}
