package com.example.wishlist.Classesapp;

import java.util.List;

public class Group {
    public Integer ID; //fonction setter?
    public String name;
    public List<User> members;

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

    /**
     * @return les membres du groupes
     */
    public List<User> getMembers() {
        return members;
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

    /**
     * @param user nouveau membre du groupe
     */
    public void addMember(User user) {
        this.members.add(user);
    }


    /**
     * @param user membres à retirer
     */
    public void deleteMember(User user){
        this.members.remove(user);
    }

}
