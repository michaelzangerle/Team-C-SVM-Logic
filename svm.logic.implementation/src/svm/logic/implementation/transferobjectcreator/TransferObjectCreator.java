package svm.logic.implementation.transferobjectcreator;

import svm.logic.abstraction.exception.IllegalGetInstanceException;

import java.lang.reflect.InvocationTargetException;

/**
 * Projectteam
 * Date: 24.10.12
 * This Class This class is responsible for the generation of the transfer objects
 */
public class TransferObjectCreator {

    /**
     * private Constructor
     */
    private TransferObjectCreator() {
    }

    /**
     * Method is responsible for the generation of the transfer objects
     * As a function parameter, the object type (x) specified to be created and which object (obj) should be transferred
     *
     * @param x   Transferperson.class
     * @param obj Person
     * @return Transferobject
     * @throws IllegalGetInstanceException
     */
    public static Object getInstance(Class x, Object obj) throws IllegalGetInstanceException {

        Object c = null;
        try {
            //Create Instance
            c = x.newInstance();
            //Create parameter types
            Class[] parameterTypes = new Class[]{Object.class};
            //get Methods
            java.lang.reflect.Method method = x.getMethod("setObject", parameterTypes);
            //Do Invoke
            method.invoke(c, obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalGetInstanceException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalGetInstanceException(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalGetInstanceException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalGetInstanceException(e);
        }
        //return Tranfer Object
        return c;
    }
}
