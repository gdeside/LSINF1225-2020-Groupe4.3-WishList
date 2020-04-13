package com.example.wishlist.Classesapp;

public class Group {
    public Integer ID; //fonction setter?
    public String name;

    /*
    Getter
     */

    /**
     * @return L'ID du groupe
     */
    public Integer getID() {
        return ID;
    }


    /**
     * @return le nom du groupe
     */
    public String getName() {
        return name;
    }



    /*
    Setter
     */

    /**
     * @param name nouveau nom du groupe
     */
    public void setName(String name) {
        this.name = name;
    }



    /*
    Autres fonctions
     */



    public void addMember(){

    }

    public void deleteMember(){
    }

}
