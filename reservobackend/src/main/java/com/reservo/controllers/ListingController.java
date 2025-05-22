package com.reservo.controllers;

import com.reservo.Models.Listing;
import com.reservo.Models.ReservoUser;
import com.reservo.Models.Review;
import com.reservo.Repository.ReservationRepository;
import com.reservo.Repository.ReservoUserRepository;
import com.reservo.Repository.ReviewRepository;
import com.reservo.services.ListingService;
import com.reservo.services.ReservoUserService;
import com.reservo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ListingController {
    @Autowired
    ListingService listingService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReservoUserRepository reservoUserRepository;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/listing/{id}")
    public String viewListing(@PathVariable Long id, Model model) {
        Listing listing = listingService.getById(id);
        List<Review> reviews = reviewRepository.findByListingId(id);
        int reservationCount = reservationRepository.countByListingId(id);

        model.addAttribute("listing", listing);
        model.addAttribute("reviews", reviews);
        model.addAttribute("reservationCount", reservationCount);

        double averageRating = reviews.isEmpty()
                ? 0.0
                : reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);

        model.addAttribute("averageRating", averageRating);
        return "listing";
    }

    @PostMapping("/listing/{id}/review")
    public String submitReview(@PathVariable Long id,
                               @RequestParam int rating,
                               @RequestParam String comment,
                               Principal principal) {
        ReservoUser user = reservoUserRepository.findByUsername(principal.getName());
        reviewService.submitReview(id, user.getId(), rating, comment);
        return "redirect:/listing/" + id;
    }

    @PostMapping("/listing/{id}/reserve")
    public String submitReview(){

        return "redirect:/reservations/";
    }


}
