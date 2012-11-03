package svm.logic.abstraction.exception;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * ProjectTeam: Team C
 * Date: 01.11.12
 */
public class LogicException extends Exception implements Serializable,Remote {
    public LogicException(String message) {
        super(message);
    }
}
