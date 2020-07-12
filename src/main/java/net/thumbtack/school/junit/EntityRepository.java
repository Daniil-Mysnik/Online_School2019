package net.thumbtack.school.junit;

public interface EntityRepository {
    boolean save(Entity entity);

    Entity getByName(String name);

    boolean delete(Entity entity);
}
