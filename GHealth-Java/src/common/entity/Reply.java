package common.entity;

import common.enums.*;

public class Reply {

    private final Object result;
    private final Command command;


    public Reply(Object result, Command command) {
    	this.result = result;
    	this.command = command;
    }

    public Object getResult() {
    	return this.result;
    }

    public Command getCommand() {
    	return this.command;
    }
}
