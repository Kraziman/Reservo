package com.reservo.services;

import com.reservo.Models.Listing;
import com.reservo.Repository.ListingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public List<Listing> getFilteredListings(String city,
                                             String propertyType,
                                             Integer minPrice,
                                             Integer maxPrice,
                                             Integer minGuests,
                                             Integer maxGuests,
                                             Integer minBedrooms,
                                             Integer maxBedrooms,
                                             String sort) {

        return listingRepository.findAll().stream()
                .filter(l -> city == null || l.getCity().getName().equalsIgnoreCase(city))
                .filter(l -> propertyType == null || l.getPropertyType().getName().equalsIgnoreCase(propertyType))
                .filter(l -> minPrice == null || l.getPricePerNight() >= minPrice)
                .filter(l -> maxPrice == null || l.getPricePerNight() <= maxPrice)
                .filter(l -> minGuests == null || l.getMax_guests() >= minGuests)
                .filter(l -> maxGuests == null || l.getMax_guests() <= maxGuests)
                .filter(l -> minBedrooms == null || l.getBedrooms() >= minBedrooms)
                .filter(l -> maxBedrooms == null || l.getBedrooms() <= maxBedrooms)
                .sorted(getComparator(sort))
                .collect(Collectors.toList());
    }

    private Comparator<Listing> getComparator(String sort) {
        if (sort == null) return Comparator.comparing(Listing::getId); // Default

        return switch (sort) {
            case "priceAsc" -> Comparator.comparing(Listing::getPricePerNight);
            case "priceDesc" -> Comparator.comparing(Listing::getPricePerNight).reversed();
            case "titleAsc" -> Comparator.comparing(Listing::getTitle, String.CASE_INSENSITIVE_ORDER);
            case "titleDesc" -> Comparator.comparing(Listing::getTitle, String.CASE_INSENSITIVE_ORDER).reversed();
            default -> Comparator.comparing(Listing::getId); // fallback
        };
    }

    public Listing getById(Long id){
        return
                listingRepository.findById(id).orElseThrow(()->
                        new NoSuchElementException("Listing not found with id: " + id));
    }
}
