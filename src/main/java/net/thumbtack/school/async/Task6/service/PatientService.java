package net.thumbtack.school.async.Task6.service;

import net.thumbtack.school.async.Task6.converter.PatientConverter;
import net.thumbtack.school.async.Task6.dao.DataBase;
import net.thumbtack.school.async.Task6.dto.response.UserResponse;
import net.thumbtack.school.async.Task6.exceptions.HospitalException;
import net.thumbtack.school.async.Task6.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientConverter patientConverter;
    private DataBase dataBase = DataBase.getInstance();

    @Autowired
    public PatientService(PatientConverter patientConverter) {
        this.patientConverter = patientConverter;
    }

    public UserResponse get(String sessionId, int patientId) throws HospitalException {
        User user = dataBase.getById(patientId);
        return patientConverter.inflateResponse(user);
    }

}
