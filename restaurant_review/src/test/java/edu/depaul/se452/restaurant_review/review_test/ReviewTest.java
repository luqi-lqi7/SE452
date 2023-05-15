package edu.depaul.se452.restaurant_review.review_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import edu.depaul.se452.restaurant_review.review_lu.relational.Review;

@DataJpaTest
@ActiveProfiles("test")
public class ReviewTest {
    @Test
    public void testToString() {
        var review = new Review();
        review.setUserID(1);
        review.setRestaurantID(3);
        review.setContent("lol, this place is good");
        review.setDate(LocalDate.of(2023, 5, 1));
        review.setStar(4);
        var expectedNoError = "Review(id=0, userID=1, restaurantID=3, date=2023-05-01, content=lol, this place is good, star=4)";
        assertEquals(expectedNoError, review.toString());
    } 
}
