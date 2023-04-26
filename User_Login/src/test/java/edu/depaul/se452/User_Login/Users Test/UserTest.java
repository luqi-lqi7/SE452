package edu.depaul.se452.User_Login.Users Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UsersTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveAndGetUser() {
        // Create a user
        Users usr = new Users();
        usr.setUsername("bobjohnson@example.com");
        usr.setPassword("password789");

        // Save the user to the database
        entityManager.persist(usr);

        // Get the user from the database
        Users retrievedUser = entityManager.find(Users.class, usr.getId());

        // Check that the retrieved user matches the original user
        assertNotNull(retrievedUser);
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
    }
}
