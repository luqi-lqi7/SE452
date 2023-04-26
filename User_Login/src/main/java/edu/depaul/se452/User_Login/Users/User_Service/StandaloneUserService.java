package edu.depaul.se452.User_Login.Users.User_Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.se452.User_Login.Users.User_Relational;
import lombok.var;

@RestController
@RequestMapping("/api/nondb/users")
public class StandaloneUserService {
    private static Map<Long, User_Relational> USERS = new HashMap<Long, User_Relational>();

    static {
        var user1 = new User_Relational();
        user1.setId(getNextKeyValue());
        user1.setUsername("johndoe@example.com");
        user1.setPassword("password123");
        USERS.put(user1.getId(), johndoe@example.com);

        var user2 = new User_Relational();
        user2.setId(getNextKeyValue());
        user2.setUsername("janesmith@example.com");
        user2.setPassword("password456");
        USERS.put(user2.getId(), janesmith@example.com);
    }

    @GetMapping
    public List<User_Relational> list() {
        var retval = USERS.values().stream().collect(Collectors.toList());
        return retval;
    }

    @GetMapping("/{id}")
    public User_Relational get(@PathVariable("id") long userId) {
        User_Relational user = USERS.get(userId);
        return user;
    }

    /*
     * Parameter passed as part of request body
     */
    @PostMapping
    public long add(@RequestBody User_Relational user) {
        user.setId(getNextKeyValue());
        USERS.put(user.getId(), user);
        return user.getId();
    }

    /*
     * Parameter passed as part of request uri
     */
    @PutMapping
    public void update(User_Relational user) {
        USERS.put(user.getId(), user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        USERS.remove(id);
    }

    private static long getNextKeyValue() {
        return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE - 1);
    }
}
