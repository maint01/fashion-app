package com.demo.fashion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "photo")
    private String photo;

    @NotNull
    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "current_sale")
    private Float currentSale;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "full_text_post")
    private String fullTextPost;

    @Column(name = "time_created")
    private ZonedDateTime timeCreated;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MorePhoto> morePhotos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Product id(Long name) {
        this.id = id;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Product manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPhoto() {
        return photo;
    }

    public Product photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Float getPrice() {
        return price;
    }

    public Product price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCurrentSale() {
        return currentSale;
    }

    public Product currentSale(Float currentSale) {
        this.currentSale = currentSale;
        return this;
    }

    public void setCurrentSale(Float currentSale) {
        this.currentSale = currentSale;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFullTextPost() {
        return fullTextPost;
    }

    public Product fullTextPost(String fullTextPost) {
        this.fullTextPost = fullTextPost;
        return this;
    }

    public void setFullTextPost(String fullTextPost) {
        this.fullTextPost = fullTextPost;
    }

    public ZonedDateTime getTimeCreated() {
        return timeCreated;
    }

    public Product timeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
        return this;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Category getCategory() {
        return category;
    }

    public Product category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<MorePhoto> getMorePhotos() {
        return morePhotos;
    }

    public Product morePhotos(Set<MorePhoto> morePhotos) {
        this.morePhotos = morePhotos;
        return this;
    }

    public Product addMorePhoto(MorePhoto morePhoto) {
        this.morePhotos.add(morePhoto);
        morePhoto.setProduct(this);
        return this;
    }

    public Product removeMorePhoto(MorePhoto morePhoto) {
        this.morePhotos.remove(morePhoto);
        morePhoto.setProduct(null);
        return this;
    }

    public void setMorePhotos(Set<MorePhoto> morePhotos) {
        this.morePhotos = morePhotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    public Product user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", manufacturer='" + getManufacturer() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", price='" + getPrice() + "'" +
            ", currentSale='" + getCurrentSale() + "'" +
            ", quatity='" + getQuantity() + "'" +
            ", fullTextPost='" + getFullTextPost() + "'" +
            ", timeCreated='" + getTimeCreated() + "'" +
            "}";
    }
}
