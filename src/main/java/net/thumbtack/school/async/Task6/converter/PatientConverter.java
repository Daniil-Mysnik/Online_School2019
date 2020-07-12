package net.thumbtack.school.async.Task6.converter;

import net.thumbtack.school.async.Task6.dto.response.PatientResponse;
import net.thumbtack.school.async.Task6.dto.response.UserResponse;
import net.thumbtack.school.async.Task6.model.Patient;
import net.thumbtack.school.async.Task6.model.User;
import org.springframework.stereotype.Component;

@Component
public class PatientConverter {

    public PatientResponse inflateResponse(Patient patient) {
        return new PatientResponse(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getPatronymic(), patient.getEmail(), patient.getAddress(), patient.getPhone());
    }

    public UserResponse inflateResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getPatronymic());
    }

}
