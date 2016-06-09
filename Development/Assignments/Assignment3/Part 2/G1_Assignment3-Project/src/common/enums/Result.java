package common.enums;

// TODO: Auto-generated Javadoc
/**
 * Result enumeration used as a result codes that the server generates in some cases.
 * those codes usually used for different error types and DB updates.
 * @author shays
 *
 */
public enum Result {

    /** The ok. */
    OK,
    
    /** The failed. */
    FAILED,
    
    /** The wrong user. */
    WRONG_USER,
    
    /** The wrong password. */
    WRONG_PASSWORD,
    
    /** The already login. */
    ALREADY_LOGIN,
    
    /** The error. */
    ERROR,
    
    /** The loggedout. */
    LOGGEDOUT,
    
    /** The client not found. */
    CLIENT_NOT_FOUND,
    
    /** The person exists. */
    PERSON_EXISTS,
    
    /** The specialist not found. */
    SPECIALIST_NOT_FOUND,
    
    /** The all available. */
    ALL_AVAILABLE,
    
    /** The no appointments found. */
    NO_APPOINTMENTS_FOUND,
    
    /** The NEX t_24. */
    NEXT_24,
    
    /** The appointment canceled. */
    APPOINTMENT_CANCELED;

}
