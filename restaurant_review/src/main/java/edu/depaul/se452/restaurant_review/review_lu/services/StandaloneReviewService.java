package edu.depaul.se452.restaurant_review.review_lu.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.se452.restaurant_review.review_lu.relational.Review;

@RestController
@RequestMapping("/api/nondb/reviews")
public class StandaloneReviewService {
    private static Map<Long, Review> allReviews = new HashMap<>();

    static {
        var r1 = new Review();
        r1.setId(getNextKeyValue());
        r1.setUserID(1);
        r1.setRestaurantID(1);
        r1.setDate(LocalDate.of(2023, 4, 1));
        r1.setContent("very good");
        r1.setStar(5);
        allReviews.put(r1.getId(), r1);

        var r2 = new Review();
        r2.setId(getNextKeyValue());
        r2.setUserID(2);
        r2.setRestaurantID(2);
        r1.setDate(LocalDate.of(2023, 4, 10));
        r2.setContent("just so so");
        r2.setStar(3);
        allReviews.put(r2.getId(), r2);
    }

    @GetMapping
    public List<Review> list() {
        return allReviews.values().stream().toList();
    }

    @GetMapping("byUser/{userID}")
    public List<Review> getByUser(@PathVariable("userID") int userID) {
        List<Review> result = new ArrayList<>();
        for (Review r : allReviews.values()) {
            if (r.getUserID() == userID) {
                result.add(r);
            }
        }
        return result;
    }

    @GetMapping("byRestaurant/{restaurantID}")
    public List<Review> getByRestaurant(@PathVariable("restaurantID") int restaurantID) {
        List<Review> result = new ArrayList<>();
        for (Review r : allReviews.values()) {
            if (r.getRestaurantID() == restaurantID) {
                result.add(r);
            }
        }
        return result;
    }

    @PostMapping
    public long add(@RequestBody Review review) {
        review.setId(getNextKeyValue());
        allReviews.put(review.getId(), review);
        return review.getId();
    }

    @PutMapping
    public void update(Review review) {
        allReviews.put(review.getId(), review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        allReviews.remove(id);
    }

    private static int getNextKeyValue() {
        return ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
    }
}
