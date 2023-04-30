package edu.depaul.se452.restaurant_review.restaurant_nisarg.relational;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
}
