package net.thumbtack.school.async.Task6.controller;

import net.thumbtack.school.async.Task6.dto.response.UserResponse;
import net.thumbtack.school.async.Task6.exceptions.HospitalException;
import net.thumbtack.school.async.Task6.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse get(@CookieValue("JAVASESSIONID") String sessionId, @PathVariable(value = "id") int patientId) throws HospitalException {
        return patientService.get(sessionId, patientId);
    }

}
