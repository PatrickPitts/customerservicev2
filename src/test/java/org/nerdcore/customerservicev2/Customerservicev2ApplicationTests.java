package org.nerdcore.customerservicev2;

import org.junit.jupiter.api.Test;
import org.nerdcore.customerservicev2.models.UserModel;
import org.nerdcore.customerservicev2.services.XMLProcessorService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Customerservicev2ApplicationTests {

    @Test
    void loadUsers() {
        assertEquals(new UserModel("username", "password"),
        XMLProcessorService.getUsers().get(0), "UserModel should have same username and password fields");
    }

}
