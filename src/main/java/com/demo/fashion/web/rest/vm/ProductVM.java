package com.demo.fashion.web.rest.vm;

import com.demo.fashion.domain.Category;

import javax.validation.constraints.NotNull;

/**
 * @author bietdoicamtu.
 *         Created on 4/23/2018
 */
public class ProductVM {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String manufacturer;
    private String photo;
    @NotNull
    private Float price;
    private Float currentSale;
    @NotNull
    private Integer quantity;
    private String fullTextPost;
    private Category category;

    private FileDTO image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCurrentSale() {
        return currentSale;
    }

    public void setCurrentSale(Float currentSale) {
        this.currentSale = currentSale;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFullTextPost() {
        return fullTextPost;
    }

    public void setFullTextPost(String fullTextPost) {
        this.fullTextPost = fullTextPost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public FileDTO getImage() {
        return image;
    }

    public void setImage(FileDTO image) {
        this.image = image;
    }
}
