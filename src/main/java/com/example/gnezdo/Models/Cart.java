package com.example.gnezdo.Models;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "iduser")
    private Long iduser;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private String price;

    public Cart (){
    }

    public Cart(Long iduser, String title, String price) {
        this.iduser = iduser;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
