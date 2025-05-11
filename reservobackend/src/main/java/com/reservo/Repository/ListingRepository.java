package com.reservo.Repository;

import com.reservo.Models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByReservoUser_Id(long userId);
    List<Listing> findByCityId(long cityId);
    List<Listing> findByPropertyTypeId(long propertyTypeId);
}
