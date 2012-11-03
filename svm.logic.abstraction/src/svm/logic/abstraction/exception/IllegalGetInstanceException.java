package svm.logic.abstraction.exception;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * Projectteam
 * Date: 24.10.12
 * Class for Handling getInstance Errors
 */
public class IllegalGetInstanceException extends Exception implements Serializable,Remote{

    public IllegalGetInstanceException(Throwable cause) {
        super(cause);
    }


}

