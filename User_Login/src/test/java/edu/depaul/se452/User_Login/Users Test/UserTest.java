package edu.depaul.se452.User_Login.Users Test

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
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        // Save the user to the database
        entityManager.persist(user);

        // Get the user from the database
        Users retrievedUser = entityManager.find(Users.class, user.getId());

        // Check that the retrieved user matches the original user
        assertNotNull(retrievedUser);
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
    }
}


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
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        // Save the user to the database
        entityManager.persist(user);

        // Get the user from the database
        Users retrievedUser = entityManager.find(Users.class, user.getId());

        // Check that the retrieved user matches the original user
        assertNotNull(retrievedUser);
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
    }
}
