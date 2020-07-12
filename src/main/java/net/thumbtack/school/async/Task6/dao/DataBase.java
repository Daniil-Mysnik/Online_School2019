package net.thumbtack.school.async.Task6.dao;

import net.thumbtack.school.async.Task6.exceptions.HospitalErrorCode;
import net.thumbtack.school.async.Task6.exceptions.HospitalException;
import net.thumbtack.school.async.Task6.model.Session;
import net.thumbtack.school.async.Task6.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataBase {
    private Map<Session, User> userMap = new HashMap<>();
    private List<User> users = new ArrayList<>();

    private static DataBase dataBase;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }


    public User getById(int id) throws HospitalException {
        for (Map.Entry<Session, User> entry : userMap.entrySet()) {
            if (entry.getValue().getId() == id)
                return entry.getValue();
        }
        throw new HospitalException(HospitalErrorCode.SESSION_NOT_EXIST);
    }

    public Session getByUserId(int id) {
        for (Map.Entry<Session, User> entry : userMap.entrySet()) {
            if (entry.getValue().getId() == id)
                return entry.getKey();
        }
        return null;
    }

    public User getUserBySession(String sessionId) {
        for (Map.Entry<Session, User> entry : userMap.entrySet()) {
            if (entry.getKey().getSessionId().equals(sessionId))
                return entry.getValue();
        }
        return null;
    }

    public Session putSession(Session session, User user) {
        userMap.put(session, user);
        return session;
    }

    public Session getSessionById(String id) {
        for (Map.Entry<Session, User> entry : userMap.entrySet()) {
            if (entry.getKey().getSessionId().equals(id))
                return entry.getKey();
        }
        return null;
    }

    public User getByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    public Map<Session, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Session, User> userMap) {
        this.userMap = userMap;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
