package edu.depaul.se452.restaurant_review.food_arshain.services;

import edu.depaul.se452.restaurant_review.food_arshain.relational.Food;
import edu.depaul.se452.restaurant_review.food_arshain.relational.FoodRepository;
import edu.depaul.se452.restaurant_review.review_lu.relational.Review;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/db/food")
@Log4j2
public class FoodService {
    @Autowired
    private FoodRepository repo;

    @GetMapping
    public List<Food> list() {
        log.traceEntry("list");
        var retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @GetMapping("/{id}")
    public Food get(@PathVariable("id") Long id) {
        log.traceEntry("get", id);
        Food menu = repo.findById(id).orElse(new Food());
        log.traceExit("get", id);
        return menu;
    }

    @PostMapping
    public long add(Food review) {
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
    public void update(Food food) {
        log.traceEntry("update", food);

        // find the menu from database
        var repoFood = get(food.getId());

        // update the values for menu in database to value that was passed
        repoFood.setFoodID(food.getFoodID());
        repoFood.setTitle(food.getTitle());
        repoFood.setDescription(food.getDescription());

        // save the updated value
        repo.save(repoFood);
        log.traceExit("update", food);
    }
    @GetMapping("byUser/{foodID}")
    public List<Food> findFoodByFoodD(Long foodID) {
        Food re = new Food();
        re.setFoodID(foodID);

        Example<Food> example = Example.of(re);
        List<Food> foods = repo.findAll(example);

        return foods;
    }
}
