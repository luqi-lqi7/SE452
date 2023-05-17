package edu.depaul.se452.restaurant_review.user_kwasi.services;
import edu.depaul.se452.restaurant_review.user_kwasi.relational.Users;
import edu.depaul.se452.restaurant_review.user_kwasi.relational.UsersRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/db/Users")
@Log4j2
public class UserService {

    private final UsersRepository repo;

    @Autowired
    public UserService(UsersRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Users> list() {
        log.traceEntry("list");
        List<Users> retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @GetMapping("/{id}")
    public Users get(@PathVariable("id") Long id) {
        log.traceEntry("get", id);
        Users user = repo.findById(id).orElse(new Users());
        log.traceExit("get", id);
        return user;
    }

    @PostMapping
    public long add(@RequestBody Users user) {
        log.traceEntry("add", user);
        repo.save(user);
        log.traceExit("add", user);
        return user.getId();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.traceEntry("delete", id);
        repo.deleteById(id);
        log.traceExit("delete", id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Users user) {
        log.traceEntry("update", user);

        // find the User from database
        Users repoUser = repo.findById(id).orElse(new Users());

        // update the values for User in database to value that was passed
        repoUser.setUsername(user.getUsername());
        repoUser.setPassword(user.getPassword());

        // save the updated value
        repo.save(repoUser);
        log.traceExit("update", user);
    }

    private List<Users> findUsersByUsername(String username) {
        Users usr = new Users();
        usr.setUsername(username);

        Example<Users> exp = Example.of(usr);
        return repo.findAll(exp);
    }
}
