package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String ID;

    @ColumnInfo(name = "mot_de_passe")
    private String password;



    @ColumnInfo(name = "Prénom")
    private String name;

    @ColumnInfo(name = "Nom_de_famille")
    private String surname;

    @ColumnInfo(name = "Date_de_naissance")
    private String DOB; // date de naissance

    @ColumnInfo(name = "Description_compte")
    private String description;




    public User(String ID, String password, String name, String surname) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.DOB = "01/01/2020";
    }

    /**
     * @return ID de l'utilisateur
     */
    //Getter
    public String getID(){
        return this.ID;
    }

    /**
     * @return le Mot de Passe
     */
    public String getPassword() {
        return password;
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
    public String getDOB() {
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
     public void setPassword(String password) {
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
    public void setDOB(String DOB) {
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




