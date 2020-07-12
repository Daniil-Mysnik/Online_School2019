package net.thumbtack.school.async.Task6;

import net.thumbtack.school.async.Task6.dao.DataBase;
import net.thumbtack.school.async.Task6.model.User;
import net.thumbtack.school.async.Task6.model.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HospitalServer extends SpringBootServletInitializer {

    public static void main(String[] args)  {
        SpringApplication.run(HospitalServer.class, args);
        DataBase dataBase = DataBase.getInstance();
        dataBase.getUsers().add(new User(1, "Daniil", "Mysnik", "Leonidovich", "admin12345", "admin12345", UserType.PATIENT));
    }

}
