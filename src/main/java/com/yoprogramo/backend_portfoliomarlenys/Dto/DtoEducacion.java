package com.yoprogramo.backend_portfoliomarlenys.Dto;

import javax.validation.constraints.NotBlank;

public class DtoEducacion {

    @NotBlank
    private String image;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String date;

    //Constructores
    public DtoEducacion() {
    }

    public DtoEducacion(String image, String name, String title, String description, String date) {
        this.image = image;
        this.name = name;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    //Getter y Setter
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
