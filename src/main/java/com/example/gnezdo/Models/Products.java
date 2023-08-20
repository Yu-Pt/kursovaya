package com.example.gnezdo.Models;


import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;
    @Column(name = "image")
    private String filename;

    public Products(){
    }
    public Products(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.author = user;
    }

    public String getAuthorName(){
        return author.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
