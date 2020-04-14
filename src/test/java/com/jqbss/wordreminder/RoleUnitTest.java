package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.Role;
import com.jqbss.wordreminder.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RoleUnitTest {

    public static Role setRoleTest(){
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        Role role = new Role();
        role.setRoleId(111L);
        role.setName("Admin");
        role.setUsers(userSet);
        return  role;
    }

    @Test
    public void getRoleTest(){
        Role role = setRoleTest();

        assertEquals(111L,role.getRoleId());
        assertEquals("Admin", role.getName());
        assertEquals(1,role.getUsers().size());
    }
}
