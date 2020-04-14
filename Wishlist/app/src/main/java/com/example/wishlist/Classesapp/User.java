package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity
public class User {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int ID;

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

    private List<Wishlist> wishlists;
    private List<Friendship> friendships;
    private List<Group> groups;

    public User(int ID, String password, String name, String surname, String DOB, String description) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.DOB = DOB;
        this.description = description;
    }

    /**
     * @return ID de l'utilisateur
     */
    //Getter
    public int getID(){
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



    /**
     * @return  retourne les wishlists
     */
    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    //Setter

    /**
     * @param ID nouveau ID
     */
    public void setID(int ID) {
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


    /**
     * @param wishlist wishlist à rajouter
     *
     */
    public void createWishlist(Wishlist wishlist){
        this.wishlists.add(wishlist);
    }




}




