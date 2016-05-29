package common.enums;

/**
 * Result enumeration used as a result codes that the server generates in some cases.
 * those codes usually used for different error types and DB updates.
 * @author shays
 *
 */
public enum Result {

    OK,
    FAILED,
    WRONG_USER,
    WRONG_PASSWORD,
    ALREADY_LOGIN,
    ERROR,
    LOGGEDOUT,
    CLIENT_NOT_FOUND,
    PERSON_EXISTS,
    SPECIALIST_NOT_FOUND,
    ALL_AVAILABLE,
    NO_APPOINTMENTS_FOUND,
    NEXT_24,
    APPOINTMENT_CANCELED;

}
