package edu.depaul.se452.User_Login.Users.User_Service;

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

import edu.depaul.se452.User_Login.Users.User_Relational.Users;
import edu.depaul.se452.User_Login.Users.User_Relational.UsersRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/db/Users")
@Log4j2
public class UserService {
   @Autowired
   private  UsersRepository repo;


   @GetMapping
   public List<Users> list() {
      log.traceEntry("list");
      var retval = repo.findAll();
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
    
   @GetMapping("byUsername/{username}")
   public List<Users> getByUsername(@PathVariable("username") String username) {
       log.traceEntry("get", username);
       List<Review> user = findUsersByUsername(username);
       log.traceExit("get", username);
       return user;
   }

   @PostMapping
   public long add(Users user) {
      log.traceEntry("add", user);        
      repo.save(user);
      log.traceExit("add", user);
      return user.getId();
}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        log.traceEntry("delete", id);
        repo.deleteById(id);
        log.traceExit("delete", id);
    }

    @PutMapping
    public void update(Users user) {
        log.traceEntry("update", user);        

        // find the User from database
        var repoUser = get(user.getId());

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
        List<Users> user = repo.findAll(exp);

        return user;
    }

    
}
