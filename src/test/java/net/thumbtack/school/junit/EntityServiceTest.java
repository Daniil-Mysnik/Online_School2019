package net.thumbtack.school.junit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.junit.Assert.*;


public class EntityServiceTest {

    private static UserService userService;
    private static EntityService entityService;

    @BeforeMethod
    public void createService() {
        userService = ServiceFactory.createUserService();
        entityService = ServiceFactory.createEntityService();
    }

    @Parameters
    @DataProvider(name = "entityName")
    public static Object[][] createEntity() {
        return new Object[][]{
                {null, false},
                {"", false},
                {" ", false},
                {"      ", false},
                {"abcdsdffsfsfsfsfsdfsfsfsfsdfsdfsfsdfwfwe12345678910", false},
                {"bestName", true}
        };
    }

    @Test(dataProvider = "entityName")
    public void testCreateEntityWithValidName(String name, Boolean valid) {
        User user = userService.createUser("Boris");
        Entity entity = new Entity(name, "123");

        assertEquals(valid, entityService.save(user, entity));
    }

    @Test
    public void testChangeEntityValue() {
        User user = userService.createUser("Boris");
        Entity entity = new Entity("Entity", "123");
        entityService.save(user, entity);
        entity.setValue("someValue");

        assertEquals("someValue", entity.getValue());
    }

    @Test
    public void testCreatorAccess() {
        User borya = userService.createUser("Borya");
        User dima = userService.createUser("Dima");
        Entity entity = new Entity("Entity", "123");
        entityService.save(borya, entity);

        assertNotNull(entityService.getByName(borya, "Entity"));
        assertNull(entityService.getByName(dima, "Entity"));
        assertFalse(entityService.delete(dima, entity));
        assertTrue(entityService.delete(borya, entity));
    }

    @Test
    public void testGroupAccess() {
        Group group1 = userService.createGroup("Group1");
        Group group2 = userService.createGroup("Group2");
        Group group3 = userService.createGroup("Group3");

        User user1 = userService.createUser("user1", group1);
        User user2 = userService.createUser("user2", group1);
        User user3 = userService.createUser("user3", group2);
        User user4 = userService.createUser("user4", group3);
        User user5 = userService.createUser("user5", group3);

        userService.addUserToGroup(user1, group2);

        Entity entity = new Entity("Entity", "123");

        entityService.save(user1, entity);
//        2. При создании сущности к ней привязываются все группы в которых состоит пользователь который создал сущность --- не привязываются
//        entityService.grantPermission(entity, group1, Permission.write);
//        entityService.grantPermission(entity, group2, Permission.write);

        assertNotNull(entityService.getByName(user1, "Entity"));
        assertNotNull(entityService.getByName(user2, "Entity"));
        assertNotNull(entityService.getByName(user3, "Entity"));
        assertNull(entityService.getByName(user4, "Entity"));
        assertNull(entityService.getByName(user5, "Entity"));
        assertFalse(entityService.delete(user4, entity));
        assertFalse(entityService.delete(user5, entity));
        assertTrue(entityService.delete(user1, entity));

        entityService.save(user1, entity);

        assertTrue(entityService.delete(user2, entity));

        entityService.save(user1, entity);

        assertTrue(entityService.delete(user3, entity));
    }

    @Test
    public void testJoinToGroupAccess() {
        Group group = userService.createGroup("Group");

        User user1 = userService.createUser("user1");
        User user2 = userService.createUser("user2");

        userService.addUserToGroup(user1, group);

        Entity entity = new Entity("Entity", "123");

        entityService.save(user1, entity);
        entityService.grantPermission(entity, group, Permission.write);

        assertNull(entityService.getByName(user2, "Entity"));
        assertFalse(entityService.delete(user2, entity));

        userService.addUserToGroup(user2, group);

        assertNotNull(entityService.getByName(user2, "Entity"));
        assertTrue(entityService.delete(user2, entity));
    }
}
