package net.thumbtack.school.junit;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserServiceTest {

    private static UserService userService;

    @BeforeClass
    public static void createUserService() {
        userService = ServiceFactory.createUserService();
    }

    @Test
    public void testCreateUserWithDefaultGroup() {
        User boris = userService.createUser("Boris");

        SoftAssertions.assertSoftly(softy -> {
            softy.assertThat(boris).isNotNull()
                    .extracting(User::getName).isEqualTo("Boris");
            softy.assertThat(boris.getGroups())
                    .containsOnly(new Group("Boris"));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testCreateUserWithoutName() {
        User userWOName = userService.createUser(null);
    }

    @Test
    public void testCreateUserWithEmptyName() { //Создается юзер с пустым именем
        User userWEName = userService.createUser("");

        assertNull(userWEName);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateUserWithoutGroup() {
        User userWOGroup = userService.createUser("Borya", null);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateGroupWithoutName() {
        Group groupWOName = userService.createGroup(null);

        assertNull(groupWOName);
    }

    @Test
    public void testCreateGroupWithEmptyName() { //Создается группа с пустым именем
        Group groupWEName = userService.createGroup("");

        Assert.assertNull(groupWEName);
    }

    @Test
    public void testAddUserToGroup() {
        User user = userService.createUser("Borya");
        Group group = userService.createGroup("NewGroup");
        userService.addUserToGroup(user, group);

        assertEquals(1, group.getUsers().size());
        assertEquals(user, group.getUsers().toArray()[0]);
        assertEquals(2, user.getGroups().size());
        assertTrue(user.getGroups().contains(group));
    }
}
