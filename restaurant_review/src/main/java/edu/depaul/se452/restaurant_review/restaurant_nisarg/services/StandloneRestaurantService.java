package edu.depaul.se452.restaurant_review.restaurant_nisarg.services;

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

import edu.depaul.se452.restaurant_review.restaurant_nisarg.relational.Restaurant;

@RestController
@RequestMapping("/api/nondb/restaurants")
public class StandaloneRestaurantService {
    private static Map<Long, Restaurant> allRestaurants = new HashMap<>();

    static {
        var r1 = new Restaurant();
        r1.setId(getNextKeyValue());
        r1.setRestaurantName("Restaurant 1");
        r1.setRestaurantDescription("This is Restaurant 1");
        r1.setRestaurantLocation("Chicago");
        allRestaurants.put(r1.getId(), r1);

        var r2 = new Review();
        r2.setId(getNextKeyValue());
        r1.setRestaurantName("Restaurant 2");
        r1.setRestaurantDescription("This is Restaurant 2");
        r1.setRestaurantLocation("Cleveland");
        allRestaurants.put(r2.getId(), r2);
    }

    @GetMapping
    public List<Restaurant> list() {
        return allRestaurants.values().stream().toList();
    }

    @GetMapping("byName/{restaurant_name}")
    public List<Restaurant> getByName(@PathVariable("restaurant_name") String restaurant_name) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant r : allRestaurants.values()) {
            if (r.getRestaurantName() == restaurant_name) {
                result.add(r);
            }
        }
        return result;
    }

    @GetMapping("byDescription/{restaurant_description}")
    public List<Restaurant> getByName(@PathVariable("restaurant_description") String restaurant_description) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant r : allRestaurants.values()) {
            if (r.getRestaurantDescription() == restaurant_description) {
                result.add(r);
            }
        }
        return result;
    }

    @GetMapping("byLocation/{restaurant_location}")
    public List<Restaurant> getByName(@PathVariable("restaurant_location") String restaurant_location) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant r : allRestaurants.values()) {
            if (r.getRestaurantLocation() == restaurant_location) {
                result.add(r);
            }
        }
        return result;
    }

    @PostMapping
    public long add(@RequestBody Restaurant restaurant) {
        restaurant.setId(getNextKeyValue());
        allRestaurants.put(restaurant.getId(), restaurant);
        return restaurant.getId();
    }

    @PutMapping
    public void update(Restaurant restaurant) {
        allRestaurants.put(restaurant.getId(), restaurant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        allRestaurants.remove(id);
    }

    private static int getNextKeyValue() {
        return ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
    }
}