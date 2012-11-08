package svm.logic.tests;

import svm.logic.implementation.ldap.LdapChecker;

/**
 * Projectteam : Team C
 * Date: 08.11.12
 */
public class LdapTest {

    public static void main(String[] args) {
        System.out.println(LdapChecker.authentication("tga9952","slseivit"));
    }
}
