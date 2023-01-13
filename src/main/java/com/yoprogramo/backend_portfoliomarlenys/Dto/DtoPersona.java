package com.yoprogramo.backend_portfoliomarlenys.Dto;

import javax.validation.constraints.NotBlank;

public class DtoPersona {

    @NotBlank
    private String backImage;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String about;
    @NotBlank
    private String photo;

    //Constructores
    public DtoPersona() {
    }

    public DtoPersona(String backImage, String name, String title, String about, String photo) {
        this.backImage = backImage;
        this.name = name;
        this.title = title;
        this.about = about;
        this.photo = photo;
    }

    //Getter y Setter
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
