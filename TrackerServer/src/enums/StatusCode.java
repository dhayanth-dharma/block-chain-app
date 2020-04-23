/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public enum StatusCode {
      
    OK(200),
    ERROR(400),
    ACCEPTED(203),
    INTERNAL_ERROR(500),
    NOT_FOUND(404),
    UNSUPPORTED_MEDIA_FORMAT(415),
    INVALID_REQUEST(411),
    ACKNOWLEDGE(205),
    CREATED(201);

    private int value;
    
    private StatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
