package edu.depaul.se452.User_Login.Users Test


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import edu.depaul.se452.User_Login.Users.User_Relational.Users;

@DataJpaTest
@ActiveProfiles("test")
public class UserTest {
    @Test
    public void testToString() {
        var user = new Users();
        user.setId(1);
        user.setUsername("johndoe@example.com");
        user.setPassword("password123");
        var expectedNoError = "Users(id=1, username=johndoe@example.com, password=password123)";
        assertEquals(expectedNoError, user.toString());
    } 
}
