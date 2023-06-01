package edu.depaul.se452.restaurant_review.review_lu.relational;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // find reviews by userID
    List<Review> findByUserID(long userID);

    // find reviews by restaurantID
    List<Review> findByRestaurantID(long restaurantID);

    // find reviews by date
    List<Review> findByDate(LocalDate date);

    // find reviews by content
    List<Review> findByContent(String content);

    // find reviews by star rating
    List<Review> findByStar(int star);

    // find reviews by userID and restaurantID
    List<Review> findByUserIDAndRestaurantID(long userID, long restaurantID);
}
