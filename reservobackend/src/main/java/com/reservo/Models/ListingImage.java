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


}
