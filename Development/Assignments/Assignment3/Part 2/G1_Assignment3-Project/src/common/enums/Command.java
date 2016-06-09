package common.enums;

// TODO: Auto-generated Javadoc
/**
 * Command enumeration used as an op-code to classify the different requests from clients to the server.
 * @author shays
 *
 */
public enum Command {


    /** The login. */
    LOGIN,
    
    /** The logout. */
    LOGOUT,
    
    /** The find client. */
    FIND_CLIENT,
    
    /** The create client. */
    CREATE_CLIENT,
    
    /** The find specialist. */
    FIND_SPECIALIST,
    
    /** The find full dates. */
    FIND_FULL_DATES,
    
    /** The find available hours. */
    FIND_AVAILABLE_HOURS,
    
    /** The create appointment. */
    CREATE_APPOINTMENT,
    
    /** The find appointments. */
    FIND_APPOINTMENTS,
    
    /** The cancel appointment. */
    CANCEL_APPOINTMENT,
    
    /** The find reference by sid cid date. */
    FIND_REFERENCE_BY_SID_CID_DATE, 
    
    /** The find reference by refnum. */
    FIND_REFERENCE_BY_REFNUM,
    
    /** The remove client. */
    REMOVE_CLIENT,
    
    /** The return client. */
    RETURN_CLIENT,
	  
  	/** The find userid by username. */
  	FIND_USERID_BY_USERNAME,
	  
  	/** The find today appointment. */
  	FIND_TODAY_APPOINTMENT,
	  
  	/** The create examination. */
  	CREATE_EXAMINATION, 
    
    /** The create examination view. */
    CREATE_EXAMINATION_VIEW,
    
    /** The create examination update. */
    CREATE_EXAMINATION_UPDATE,
    
    /** The get client by appointmet. */
    GET_CLIENT_BY_APPOINTMET,
    
    /** The get branches. */
    GET_BRANCHES,
    
    /** The weekly report. */
    WEEKLY_REPORT,
    
    /** The monthly report. */
    MONTHLY_REPORT, 
    
    /** The get client by client id. */
    GET_CLIENT_BY_CLIENT_ID,
    
    /** The get branch by username. */
    GET_BRANCH_BY_USERNAME, 
    
    /** The get examination type. */
    GET_EXAMINATION_TYPE, 
    
    /** The insert lab refrence. */
    INSERT_LAB_REFRENCE,
    
    /** The report missing. */
    REPORT_MISSING,
    
    /** The validate reference. */
    VALIDATE_REFERENCE, 
    
    /** The insert record. */
    INSERT_RECORD, 
    
    /** The find client examination. */
    FIND_CLIENT_EXAMINATION,
    
    /** The end medical treatment. */
    END_MEDICAL_TREATMENT, 
    
    /** The get examination number. */
    GET_EXAMINATION_NUMBER,
    
    /** The get medical file. */
    GET_MEDICAL_FILE,
    
    /** The get appointment review. */
    GET_APPOINTMENT_REVIEW,
		
	/** The send notification. */
	SEND_NOTIFICATION, 
	
	/** The find medical file. */
	FIND_MEDFILE,
	
	/** The find delete client. */
	DELETE_CLIENT;

}
