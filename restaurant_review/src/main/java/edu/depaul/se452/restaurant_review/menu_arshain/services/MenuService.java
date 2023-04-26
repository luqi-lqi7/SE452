package edu.depaul.se452.restaurant_review.menu_arshain.services;

import edu.depaul.se452.restaurant_review.menu_arshain.entity.Menu;
import edu.depaul.se452.restaurant_review.menu_arshain.entity.MenuRepository;
import edu.depaul.se452.restaurant_review.review_lu.relational.Review;
import edu.depaul.se452.restaurant_review.review_lu.relational.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/db/menu")
@Log4j2
public class MenuService {
    @Autowired
    private MenuRepository repo;
    
    @GetMapping
    public List<Menu> list() {
        log.traceEntry("list");
        var retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable("id") Long id) {
        log.traceEntry("get", id);
        Menu menu = repo.findById(id).orElse(new Menu());
        log.traceExit("get", id);
        return menu;
    }


    @GetMapping("byRestaurant/{restaurantID}")
    public List<Menu> getByRestaurant(@PathVariable("restaurantID") Long restaurantID) {
        log.traceEntry("get", restaurantID);
        List<Menu> menus = findMenuByRestaurantID(restaurantID);
        log.traceExit("get", restaurantID);
        return menus;
    }

    @PostMapping
    public long add(Menu review) {
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
    public void update(Menu menu) {
        log.traceEntry("update", menu);

        // find the menu from database
        var repoMenu = get(menu.getId());

        // update the values for menu in database to value that was passed
        repoMenu.setRestaurantID(menu.getRestaurantID());
        repoMenu.setTitle(menu.getTitle());
        repoMenu.setDescription(menu.getDescription());
        repoMenu.setPrice(menu.getPrice());

        // save the updated value
        repo.save(repoMenu);
        log.traceExit("update", menu);
    }

    private List<Menu> findMenuByRestaurantID(Long restaurantID) {
        Menu re = new Menu();
        re.setRestaurantID(restaurantID);

        Example<Menu> example = Example.of(re);
        List<Menu> menus = repo.findAll(example);

        return menus;
    }
}
