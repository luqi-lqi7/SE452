package edu.depaul.se452.restaurant_review.restaurant_nisarg.services;

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

import edu.depaul.se452.restaurant_review.restaurant_nisarg.relational.Restaurant;
import edu.depaul.se452.restaurant_review.restaurant_nisarg.relational.RestaurantRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/db/restaurants")
@Log4j2
public class RestaurantService {
    @Autowired
    private RestaurantRepository repo;
    
    @GetMapping
    public List<Restaurant> list() {
        log.traceEntry("list");
        var restval = repo.findAll();
        log.traceExit("list");
        return restval;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") Long id) {
        log.traceEntry("get", id);
        Restaurant restaurant = repo.findById(id).orElse(new Restaurant());
        log.traceExit("get", id);
        return restaurant;
    }

    // @GetMapping("byUser/{userID}")
    // public List<Review> getByUser(@PathVariable("userID") Long userID) {
    //     log.traceEntry("get", userID);
    //     List<Review> reviews = findReviewByUserID(userID);
    //     log.traceExit("get", userID);
    //     return reviews;
    // }

    @GetMapping("byName/{restaurant_name}")
    public List<Restaurant> getByName(@PathVariable("restaurant_name") Long restaurant_name) {
        log.traceEntry("get", restaurant_name);
        List<Restaurant> restaurants = findRestaurantByName(restaurant_name);
        log.traceExit("get", restaurant_name);
        return restaurants;
    }

    @GetMapping("byDescription/{restaurant_description}")
    public List<Restaurant> getByDescription(@PathVariable("restaurant_description") Long restaurant_description) {
        log.traceEntry("get", restaurant_description);
        List<Restaurant> restaurants = findRestaurantByDescription(restaurant_description);
        log.traceExit("get", restaurant_description);
        return restaurants;
    }

    @GetMapping("byLocation/{restaurant_location}")
    public List<Restaurant> getByLocation(@PathVariable("restaurant_location") Long restaurant_location) {
        log.traceEntry("get", restaurant_location);
        List<Restaurant> restaurants = findRestaurantByLocation(restaurant_location);
        log.traceExit("get", restaurant_location);
        return restaurants;
    }

    @PostMapping
    public long add(Restaurant restaurant) {
        log.traceEntry("add", restaurant);        
        repo.save(restaurant);
        log.traceExit("add", restaurant);
        return restaurant.getId();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.traceEntry("delete", id);        
        repo.deleteById(id);
        log.traceExit("delete", id);        
    }

    @PutMapping
    public void update(Restaurant restaurant) {
        log.traceEntry("update", restaurant);        

        // find the restaurant from database
        var repoRestaurant = get(restaurant.getId());

        // update the values for restaurant in database to value that was passed
        repoRestaurant.setRestaurantName(restaurant.getRestaurantName());
        repoRestaurant.setRestaurantDescription(restaurant.getRestaurantDescription());
        repoRestaurant.setRestaurantLocation(restaurant.getRestaurantLocation());

        // save the updated value
        repo.save(repoRestaurant);      
        log.traceExit("update", restaurant);        
    }


    // @GetMapping("byRestaurant/{restaurantID}")
    // public List<Review> getByRestaurant(@PathVariable("restaurantID") Long restaurantID) {
    //     log.traceEntry("get", restaurantID);
    //     List<Review> reviews = findReviewByRestaurantID(restaurantID);
    //     log.traceExit("get", restaurantID);
    //     return reviews;
    // }

    // @PostMapping
    // public long add(Review review) {
    //     log.traceEntry("add", review);        
    //     repo.save(review);
    //     log.traceExit("add", review);
    //     return review.getId();
    // }

    // @DeleteMapping("/{id}")
    // public void delete(@PathVariable("id") Long id) {
    //     log.traceEntry("delete", id);        
    //     repo.deleteById(id);
    //     log.traceExit("delete", id);        
    // }

    // @PutMapping
    // public void update(Review review) {
    //     log.traceEntry("update", review);        

    //     // find the review from database
    //     var repoReview = get(review.getId());

    //     // update the values for review in database to value that was passed
    //     repoReview.setUserID(review.getUserID());
    //     repoReview.setRestaurantID(review.getRestaurantID());
    //     repoReview.setContent(review.getContent());
    //     repoReview.setDate(review.getDate());
    //     repoReview.setStar(review.getStar());

    //     // save the updated value
    //     repo.save(repoReview);      
    //     log.traceExit("update", review);        
    // }

    private List<Restaurant> findRestaurantByName(String restaurant_name) {
        Restaurant re = new Restaurant();
        re.setRestaurantName(restaurant_name);

        Example<Restaurant> example = Example.of(re);
        List<Restaurant> restaurants = repo.findAll(example);

        return restaurants;
    } 

    private List<Restaurant> findRestaurantByDescription(String restaurant_description) {
        Restaurant re = new Restaurant();
        re.setRestaurantDescription(restaurant_description);

        Example<Restaurant> example = Example.of(re);
        List<Restaurant> restaurants = repo.findAll(example);

        return restaurants;
    }  
    
    private List<Restaurant> findRestaurantByLocation(String restaurant_location) {
        Restaurant re = new Restaurant();
        re.setRestaurantLocation(restaurant_location);

        Example<Restaurant> example = Example.of(re);
        List<Restaurant> restaurants = repo.findAll(example);

        return restaurants;
    }  

    // private List<Review> findReviewByUserID(Long userID) {
    //     Review re = new Review();
    //     re.setUserID(userID);

    //     Example<Review> example = Example.of(re);
    //     List<Review> reviews = repo.findAll(example);

    //     return reviews;
    // }

    // private List<Review> findReviewByRestaurantID(Long restaurantID) {
    //     Review re = new Review();
    //     re.setRestaurantID(restaurantID);

    //     Example<Review> example = Example.of(re);
    //     List<Review> reviews = repo.findAll(example);

    //     return reviews;
    // }
}
