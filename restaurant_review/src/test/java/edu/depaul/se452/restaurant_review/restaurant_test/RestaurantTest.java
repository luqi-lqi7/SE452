package edu.depaul.se452.restaurant_review.restaurant_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import edu.depaul.se452.restaurant_review.restaurant_nisarg.relational.Restaurant;

@DataJpaTest
@ActiveProfiles("test")
public class RestaurantTest {
    @Test
    public void testToString() {
        var restaurant = new Restaurant();
        restaurant.setRestaurantName("Restaurant 1");
        restaurant.setRestaurantDescription("This is Restaurant 1");
        restaurant.setRestaurantLocation("Chicago");
        var expectedNoError = "Restaurant(id=0, restaurant_name=Restaurant 1, restaurant_description=This is Restaurant 1, restaurant_location=Chicago)";
        assertEquals(expectedNoError, review.toString());
    } 
}
