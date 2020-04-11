package com.example.wishlist.Classesapp;

public class Wishlist {
    public int number; //pourait devenir privé et vraiment necessaire?
    public String name; //nom de la wishlist
    public Boolean option;
    public String description;



    //getter
    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    /**
     * @return si la liste est publique ou non (true=publique)
     */
    public boolean isPublic(){//peut-être changer en getOption

        return option;
    }


    //setter


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOption(Boolean option) {
        this.option = option;
    }
}
