
package com.affiliateLink.common.exception ;

public class InvalidStatusTransitionException extends  RuntimeException{

    public InvalidStatusTransitionException(String message){
        super(message);
    }
}