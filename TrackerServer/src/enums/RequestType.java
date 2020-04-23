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
public enum RequestType {
   GET("get"),
   POST("post"),
    put("put")
    ;

    private final String text;

    RequestType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }  
}
