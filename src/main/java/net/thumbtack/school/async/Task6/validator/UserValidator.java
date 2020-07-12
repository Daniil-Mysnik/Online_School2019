package net.thumbtack.school.async.Task6.validator;

import net.thumbtack.school.async.Task6.exceptions.HospitalErrorCode;
import net.thumbtack.school.async.Task6.exceptions.HospitalException;
import net.thumbtack.school.async.Task6.model.User;
import net.thumbtack.school.async.Task6.model.UserType;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {

    public void checkUserExist(User user) throws HospitalException {
        if (user == null) {
            throw new HospitalException(HospitalErrorCode.USER_NOT_EXISTS);
        }
    }

    public void checkLoginFree(User user) throws HospitalException {
        if (user != null) {
            throw new HospitalException(HospitalErrorCode.BUSY_LOGIN);
        }
    }

    public void checkPasswordMatch(String firstPass, String secondPass) throws HospitalException {
        if (!firstPass.equals(secondPass)) {
            throw new HospitalException(HospitalErrorCode.WRONG_PASSWORD);
        }
    }

    public void checkUserIsDoctor(User user) throws HospitalException {
        if (!user.getUserType().equals(UserType.DOCTOR)) {
            throw new HospitalException(HospitalErrorCode.USER_IS_NOT_DOCTOR);
        }
    }

    public void checkUserIsPatient(User user) throws HospitalException {
        if (!user.getUserType().equals(UserType.PATIENT)) {
            throw new HospitalException(HospitalErrorCode.USER_IS_NOT_PATIENT);
        }
    }

    public void checkUserNotPatient(User user) throws HospitalException {
        if (user.getUserType().equals(UserType.PATIENT)) {
            throw new HospitalException(HospitalErrorCode.NOT_ENOUGH_RIGHTS);
        }
    }

}
