package com.itametis.sandbox.drools;

public class Action {

    Message message;


    public void performAction(Message message) {
        message.printMessage();
        this.message = message;
    }


    @Override
    public String toString() {
        return "Action{" + "message=" + message.toString() + '}';
    }

}
