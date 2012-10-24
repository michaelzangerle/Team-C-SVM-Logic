package svm.logic.tests;

import junit.framework.TestCase;
import svm.logic.implementation.tranferobjects.TransferPerson;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import test.Person;


/**
 * Projectteam
 * Date: 24.10.12
 */
public class TransferObjectCreatorTest extends TestCase{

    public void testGetInstance() throws Exception {
        assertTrue(TransferObjectCreator.getInstance(TransferPerson.class, new Person())!=null);
    }

}
