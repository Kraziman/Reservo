package com.reservo.Models;

import jakarta.persistence.*;

@Entity
public class ListingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_image_id")
    private long id;

    @Column(name = "image_path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
