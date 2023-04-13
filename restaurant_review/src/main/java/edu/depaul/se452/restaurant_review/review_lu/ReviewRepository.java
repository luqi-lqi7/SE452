package edu.depaul.se452.restaurant_review.review_lu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {
    
}
