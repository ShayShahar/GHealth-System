package common.enums;

public enum Command {

    LOGIN,
    LOGOUT,
    FIND_CLIENT,
    CREATE_CLIENT,
    FIND_SPECIALIST,
    FIND_FULL_DATES,
    FIND_AVAILABLE_HOURS,
    CREATE_APPOINTMENT,
    FIND_APPOINTMENTS,
    CANCEL_APPOINTMENT,
    FIND_REFERENCE_BY_SID_CID_DATE, 
    FIND_REFERENCE_BY_REFNUM,
    REMOVE_CLIENT,
    RETURN_CLIENT,
	  FIND_USERID_BY_USERNAME,
	  FIND_TODAY_APPOINTMENT,
	  CREATE_EXAMINATION, 
    CREATE_EXAMINATION_VIEW,
    CREATE_EXAMINATION_UPDATE,
    GET_CLIENT_BY_APPOINTMET,
    GET_BRANCHES,
    WEEKLY_REPORT,
    MONTHLY_REPORT, 
    GET_CLIENT_BY_CLIENT_ID,
    GET_BRANCH_BY_USERNAME, 
    GET_EXAMINATION_TYPE,
    END_TREATMENT,
    REPORT_MISSING;
    
}
