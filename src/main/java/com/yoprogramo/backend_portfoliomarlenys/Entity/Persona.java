
package com.yoprogramo.backend_portfoliomarlenys.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int id; 
    private String backImage;   
    private String name;
    private String title;
    private String about;    
    private String photo;  
   
    //Constructores
    public Persona() {
    } 

    public Persona(String backImage, String name, String title, String about, String photo) {
        this.backImage = backImage;
        this.name = name;
        this.title = title;
        this.about = about;
        this.photo = photo;
    }
    
    //Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }    
    
}
