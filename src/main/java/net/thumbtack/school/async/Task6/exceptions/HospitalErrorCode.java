package net.thumbtack.school.async.Task6.exceptions;

public enum HospitalErrorCode {
    WRONG_FIRST_NAME("firstName","First name cannot be empty!"),
    WRONG_LAST_NAME("lastName", "Last name cannot be empty!"),
    WRONG_EMAIL("email","Email cannot be empty!"),
    WRONG_LOGIN("login", "Login must be between 5 and 20 characters"),
    WRONG_PASSWORD("password", "Password must be between 8 and 16 characters"),
    WRONG_ADDRESS("address", "Address cannot be empty!"),
    BUSY_LOGIN("login", "This login is busy!"),
    BUSY_EMAIL("email", "This email is busy!"),
    INCORRECT_PASSWORD("password", "The password is incorrect"),
    USER_NOT_EXISTS("login", "Client with this login does not exist"),
    PATIENT_NOT_EXIST("patient", "Patient does not exist!"),
    DATABASE_ERROR("database", "Something was wrong in database"),
    SESSION_NOT_EXIST("session", "You are not logged in"),
    UNKNOWN_USER_TYPE("userType", "Unknown user type"),
    USER_IS_NOT_ADMIN("userType", "User is not admin"),
    USER_IS_NOT_DOCTOR("userTupe", "User is not doctor"),
    INCORRECT_SPECIALITY("speciality", "There are no doctors with this specialty at this time"),
    NOT_ENOUGH_RIGHTS("sessionId", "You do not have sufficient rights for this action."),
    SCHEDULE_BUSY("schedule", "Tickets for this period have already been issued"),
    AMBIGUOUS_DOCTOR("doctor", "The request must contain either the doctor’s id or specialty"),
    USER_IS_NOT_PATIENT("session", "You are not a patient"),
    INCORRECT_TICKET_DATE("date", "The ticket for such a time does not exist"),
    BUSY_TICKET("ticket", "The ticket for this time is busy"),
    UNKNOWN_SPECIALITY("speciality", "There are no doctors with this specialty at this time."),
    TICKET_NOT_EXISTS("number", "Ticket with such number does not exist"),
    PATIENT_HAS_NO_TICKET("patient", "This patient has no tickets"),
    WRONG_ROOM("room", "The room does not match any doctor’s room"),
    BUSY_ROOM("room", "This room is busy"),
    DOCTOR_NOT_EXIST("doctorId", "Doctor with this id not exist"),
    NONWORKING_TIME("time", "Doctor is not working at this time."),
    NOT_FOUND("url", "Page not found"),
    INTERNAL_SERVER_ERROR("server", "Something wrong with server. Try later"),
    BAD_REQUEST("request", "You entered something wrong in the request"),
    DATE("date", "Start date must be later than current and end date must be later than start date"),
    ONESCHEDULE("schedule", "The doctor must have only one type of schedule"),
    WEEKDAY("weekDay", "Days of the week are set in the format “Mon”, “Tue”, “Wed”, “Thu”, “Fri”; Saturday and Sunday are weekends."),
    TIME("time", "A work shift cannot last more than 8 hours."),
    IDORSPECIALITY("doctor", "The request must be either id or specialty");

    private String field;
    private String message;

    HospitalErrorCode(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
