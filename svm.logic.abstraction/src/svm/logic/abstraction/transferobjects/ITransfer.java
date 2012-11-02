package svm.logic.abstraction.transferobjects;

import java.io.Serializable;

/**
 * Projectteam
 * Date: 24.10.12
 * This Class consists all methods that are needed from the Transfer Objects
 */
public interface ITransfer extends Serializable {

    /**
     * Needed to set the Internal Object (Person,Match) from the Transfer Object with the invoke Method.
     * @param o (Person,Match,etc.)
     */
    void setObject(Object o);

}
