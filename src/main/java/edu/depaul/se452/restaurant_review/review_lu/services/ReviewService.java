package edu.depaul.se452.restaurant_review.review_lu.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.se452.restaurant_review.review_lu.relational.Review;
import edu.depaul.se452.restaurant_review.review_lu.relational.ReviewRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/db/reviews")
@Log4j2
public class ReviewService {
    @Autowired
    private ReviewRepository repo;
    private final MeterRegistry meterRegistry;

    @Autowired
    public ReviewService(ReviewRepository repo, MeterRegistry meterRegistry) {
        this.repo = repo;
        this.meterRegistry = meterRegistry;
    }
    
    @GetMapping
    public List<Review> list() {
        log.traceEntry("list");
        var retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @GetMapping("/{id}")
    public Review get(@PathVariable("id") Long id) {
        log.traceEntry("get", id);
        Review review = repo.findById(id).orElse(new Review());
        log.traceExit("get", id);
        return review;
    }

    @GetMapping("byUser/{userID}")
    public List<Review> getByUser(@PathVariable("userID") Long userID) {
        log.traceEntry("get", userID);
        List<Review> reviews = findReviewByUserID(userID);
        log.traceExit("get", userID);
        return reviews;
    }


    @GetMapping("byRestaurant/{restaurantID}")
    public List<Review> getByRestaurant(@PathVariable("restaurantID") Long restaurantID) {
        log.traceEntry("get", restaurantID);
        List<Review> reviews = findReviewByRestaurantID(restaurantID);
        log.traceExit("get", restaurantID);
        return reviews;
    }

    @PostMapping
    public long add(Review review) {
        log.traceEntry("add", review);        
        repo.save(review);
        log.traceExit("add", review);
        return review.getId();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.traceEntry("delete", id);        
        repo.deleteById(id);
        log.traceExit("delete", id);        
    }

    @PutMapping
    public void update(Review review) {
        log.traceEntry("update", review);        

        // find the review from database
        var repoReview = get(review.getId());

        // update the values for review in database to value that was passed
        repoReview.setUserID(review.getUserID());
        repoReview.setRestaurantID(review.getRestaurantID());
        repoReview.setContent(review.getContent());
        repoReview.setDate(review.getDate());
        repoReview.setStar(review.getStar());

        // save the updated value
        repo.save(repoReview);      
        log.traceExit("update", review);        
    }

    private List<Review> findReviewByUserID(Long userID) {
        Review re = new Review();
        re.setUserID(userID);

        Example<Review> example = Example.of(re);
        List<Review> reviews = repo.findAll(example);

        return reviews;
    }

    private List<Review> findReviewByRestaurantID(Long restaurantID) {
        Review re = new Review();
        re.setRestaurantID(restaurantID);

        Example<Review> example = Example.of(re);
        List<Review> reviews = repo.findAll(example);

        return reviews;
    }

    //Additional finders
    public List<Review> findReviewsByUserID(long userID) {
        List<Review> reviews = repo.findByUserID(userID);
        meterRegistry.counter("service.review.findReviewsByUserID.count").increment();
        return reviews;
    }

    public List<Review> findReviewsByRestaurantID(long restaurantID) {
        meterRegistry.counter("service.review.findReviewsByRestaurantID.count").increment();
        return repo.findByRestaurantID(restaurantID);
    }

    public List<Review> findReviewsByDate(LocalDate date) {
        meterRegistry.counter("service.review.findReviewsByDate.count").increment();
        return repo.findByDate(date);
    }

    public List<Review> findReviewsByContent(String content) {
        meterRegistry.counter("service.review.findReviewsByContent.count").increment();
        return repo.findByContent(content);
    }

    public List<Review> findReviewsByStar(int star) {
        meterRegistry.counter("service.review.findReviewsByStar.count").increment();
        return repo.findByStar(star);
    }

    public List<Review> findReviewsByUserIDAndRestaurantID(long userID, long restaurantID) {
        meterRegistry.counter("service.review.findReviewsByUserIDAndRestaurantID.count").increment();
        return repo.findByUserIDAndRestaurantID(userID, restaurantID);
    }
}
