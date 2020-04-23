/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author user
 */
public enum ReqCommand {
    
    REQ_CHEESE_CHAIN("REQ_CHEESE_CHAIN"),
    REQ_TRAN_CHAIN("REQ_TRAN_CHAIN"),
    REQ_NEW_TRANS("REQ_NEW_TRANS"),
    REQ_ACK("REQ_ACK"),
    REQ_VALIDATION("REQ_VALIDATION"),
    REQ_NEW_CHEESE("REQ_NEW_CHEESE"),
    REQ_DEATH_MEMBER("REQ_DEATH_MEMBER"),
    REQ_TEST("REQ_TEST"),
    REQ_REGISTER("REQ_REGISTER")

    ;

    private final String text;

    ReqCommand(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
