package net.thumbtack.school.async.Task6.exceptions;

public class HospitalException extends Exception {
    private HospitalErrorCode hospitalErrorCode;

    public HospitalException(HospitalErrorCode hospitalErrorCode) {
        this.hospitalErrorCode = hospitalErrorCode;
    }

    public HospitalErrorCode getErrorCode() {
        return hospitalErrorCode;
    }

}
