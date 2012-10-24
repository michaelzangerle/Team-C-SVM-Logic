package test;

import svm.logic.abstraction.exceptions.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.interfaces.ITransferAddress;
import svm.logic.abstraction.transferobjects.interfaces.ITransferPerson;
import svm.logic.implementation.tranferobjects.TransferPerson;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

/**
 * Created with IntelliJ IDEA.
 * User: mike
 * Date: 19.10.12
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        IPerson p = new Person();
        ITransferPerson tp = null;
        try {
            tp = (ITransferPerson) TransferObjectCreator.getInstance(TransferPerson.class, p);
            System.out.println(tp.getName());
            ITransferAddress a=tp.getAddress();
            System.out.println(a.getPlz());
        } catch (IllegalGetInstanceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }
}
