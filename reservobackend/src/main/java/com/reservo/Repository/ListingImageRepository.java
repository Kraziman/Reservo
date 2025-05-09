package com.reservo.Repository;

import com.reservo.Models.ListingImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingImageRepository extends JpaRepository<ListingImage, Integer> {
    List<ListingImage> findByListingId(Integer listingId);
}
