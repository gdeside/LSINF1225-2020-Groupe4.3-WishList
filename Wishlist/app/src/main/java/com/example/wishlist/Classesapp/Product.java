package com.example.wishlist.Classesapp;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Nom_Product")
    private String name;

    @ColumnInfo(name = "Prix")
    private int prix;

    @ColumnInfo(name = "Catégorie")
    private String categorie;

    @ColumnInfo(name = "Note")
    private int note;

    @ColumnInfo(name = "Lien")
    private String lien;

    public Product(@NonNull String name, int prix, String categorie) {
        this.name = name;
        this.prix = prix;
        this.categorie = categorie;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        categorie = categorie;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
}
