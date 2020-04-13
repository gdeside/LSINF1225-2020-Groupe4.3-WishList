package com.example.wishlist.Classesapp;

import java.util.Date;

public class User {
    public String ID;

    private String password;

    public User(String password) {
        this.password = password;
    }
   
    public boolean connect(String Id,String password) {
        return this.ID.equals(Id) && this.password.equals(password);
    }

    private String password; //ne peut pas être get
    public String name;
    public String surname;
    public Date DOB; // date de naissance
    public String description;


    /**
     * @return ID de l'utilisateur
     */
    //Getter
    public String getID(){
        return this.ID;
    }

    /**
     * @return le nom
     */
    public String getName() {
        return name;
    }

    /**
     * @return retourne le prénom
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return la date de naissance
     */
    public Date getDOB() {
        return DOB;
    }

    /**
     * @return retourne la description
     */
    public String getDescription() {
        return description;
    }

    //Setter

    /**
     * @param ID nouveau ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @param password nouveau mot de passe
     */
    public void setpassword(String password) {
        this.password = password;
    }

    /**
     * @param name nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param surname nouveau prénom
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @param DOB nouvelle dâte de naissance
     */
    public void setDOB(Date DOB) {
        this.DOB = DOB;

    }

    /**
     * @param description nouvelle description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    //autres fonctions

}




