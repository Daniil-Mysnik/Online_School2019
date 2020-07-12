package net.thumbtack.school.async.Task6.service;


import net.thumbtack.school.async.Task6.converter.PatientConverter;
import net.thumbtack.school.async.Task6.dao.*;
import net.thumbtack.school.async.Task6.dto.request.LoginRequest;
import net.thumbtack.school.async.Task6.dto.response.*;
import net.thumbtack.school.async.Task6.exceptions.HospitalErrorCode;
import net.thumbtack.school.async.Task6.exceptions.HospitalException;
import net.thumbtack.school.async.Task6.model.*;
import net.thumbtack.school.async.Task6.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {
    private final DataBase dataBase = DataBase.getInstance();
    private final PatientConverter patientConverter;
    private final UserValidator userValidator;

    public SessionService(PatientConverter patientConverter,
                          UserValidator userValidator) {
        this.patientConverter = patientConverter;
        this.userValidator = userValidator;
    }

    public LoginResponse create(LoginRequest request) throws HospitalException {
        User user = dataBase.getByLogin(request.getLogin());
        userValidator.checkUserExist(user);
        userValidator.checkPasswordMatch(user.getPassword(), request.getPassword());
        Session session = dataBase.getByUserId(user.getId());
        if (session != null) {
            dataBase.getUserMap().remove(session);
        }
        session = dataBase.putSession(new Session(UUID.randomUUID().toString(), user), user);
        return new LoginResponse(session.getSessionId(), getInfo(session.getSessionId()));
    }

    public UserResponse getInfo(String sessionId) throws HospitalException {
        Session session = dataBase.getSessionById(sessionId);
        User user = session.getUser();
        if (user.getUserType() == UserType.PATIENT) {
            User user1 = dataBase.getById(user.getId());
            return patientConverter.inflateResponse(user1);
        }
        throw new HospitalException(HospitalErrorCode.UNKNOWN_USER_TYPE);
    }

    public EmptyResponse delete(String sessionId) throws HospitalException {
        Session session = dataBase.getSessionById(sessionId);
        dataBase.getUserMap().remove(session);
        return new EmptyResponse();
    }

}
