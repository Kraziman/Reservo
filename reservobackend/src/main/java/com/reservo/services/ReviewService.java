package com.reservo.services;

import com.reservo.Models.Listing;
import com.reservo.Models.ReservoUser;
import com.reservo.Models.Review;
import com.reservo.Repository.ListingRepository;
import com.reservo.Repository.ReservoUserRepository;
import com.reservo.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ReviewService {
    @Autowired
    ReservoUserRepository reservoUserRepository;
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    ReviewRepository reviewRepository;

    public void submitReview(Long listingId, Long userId, int rating, String comment) {
        ReservoUser user = reservoUserRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new NoSuchElementException("Listing not found with ID: " + listingId));

        Review review = new Review();
        review.setUser(user);
        review.setListing(listing);
        review.setRating(rating);
        review.setComment(comment);
        review.setCreationDate(LocalDateTime.now());

        reviewRepository.save(review);
    }

}
