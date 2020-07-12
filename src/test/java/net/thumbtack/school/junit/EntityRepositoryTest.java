package net.thumbtack.school.junit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class EntityRepositoryTest {

    @Test
    public void testVerifySave() {
        EntityRepository entityRepository = Mockito.mock(EntityRepository.class);
        EntityService entityService = ServiceFactory.createEntityService(entityRepository);

        User user = new User("Boris", null);
        Entity entity = new Entity("entity", "123");

        entityService.save(user, entity);
        entityService.save(user, entity);
        entityService.save(user, entity);

        ArgumentCaptor<Entity> entityArgumentCaptor = ArgumentCaptor.forClass(Entity.class);
        Mockito.verify(entityRepository, Mockito.times(3)).save(entityArgumentCaptor.capture());

        Assert.assertEquals(entity, entityArgumentCaptor.getValue());
    }

    @Test
    public void testDatabase() {
        UserService userService = ServiceFactory.createUserService();
        EntityRepository entityRepository = Mockito.mock(EntityRepository.class);
        EntityService entityService = ServiceFactory.createEntityService(entityRepository);

        Group group = userService.createGroup("Group");
        User user1 = userService.createUser("user1", group);
        User user2 = userService.createUser("user2", group);
        Entity entity = new Entity("Entity", "123");
        entityService.save(user1, entity);
        entity.setValue("newEntity");

        Mockito.when(entityRepository.getByName("Entity")).thenReturn(entity);
        Mockito.when(entityRepository.delete(entity)).thenReturn(true);

        Assert.assertEquals(entity, entityService.getByName(user1, "Entity"));
        Assert.assertTrue(entityService.delete(user1, entity));

        entityService.save(user1, entity);

        Assert.assertNotEquals(entity, entityService.getByName(user2, "Entity"));
        Assert.assertFalse(entityService.delete(user2, entity));

        entityService.grantPermission(entity, group, Permission.write);

        Assert.assertEquals(entity, entityService.getByName(user2, "Entity"));
        Assert.assertTrue(entityService.delete(user1, entity));

        ArgumentCaptor<Entity> entityArgumentCaptor = ArgumentCaptor.forClass(Entity.class);
        Mockito.verify(entityRepository, Mockito.times(2)).save(entityArgumentCaptor.capture());

        Assert.assertEquals(entity, entityArgumentCaptor.getValue());
    }
}