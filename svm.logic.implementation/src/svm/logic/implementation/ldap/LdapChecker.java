package svm.logic.implementation.ldap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

/**
 * Projectteam : Team C
 * Date: 07.11.12
 */
public class LdapChecker {

    private static final String LDAP_SERVER= "ldaps://ldap.fhv.at:636/dc=uclv,dc=net";
    private static final String FACTORY=  "com.sun.jndi.ldap.LdapCtxFactory";
    private static final String SECURITY_AUTHENTICATION= "simple";

    private LdapChecker(){}

    public static boolean authentication(String id, String pw){
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,FACTORY);
        env.put(Context.PROVIDER_URL, LDAP_SERVER );
        env.put(Context.SECURITY_AUTHENTICATION,SECURITY_AUTHENTICATION);
        try {
            env.put(Context.SECURITY_PRINCIPAL,getDNName(id)); // specify the username
        } catch (NamingException e) {
            e.printStackTrace();
            return false;
        }
        // uid=tga9952,ou=fhv,ou=People,dc=uclv,dc=net
        env.put(Context.SECURITY_CREDENTIALS,pw);

        try {
            Context ctx = new InitialContext(env);
            return true;
        } catch (NamingException e) {
            return false;
        }
    }

    private static String getDNName(String id) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,FACTORY);
        env.put(Context.PROVIDER_URL, LDAP_SERVER);
        InitialDirContext ctx = new InitialDirContext(env);
        String base="";
        if(id.equals("tf-test"))
            base="ou=apps";
        else
            base="ou=fhv,ou=People";
        String dnName="";
        Attributes match = new BasicAttributes();
        match.put(new BasicAttribute("uid", id));
        NamingEnumeration<SearchResult> namingEnum = ctx.search(base, match);
        while (namingEnum.hasMore()) {
            SearchResult result = namingEnum.next();
            dnName=result.getNameInNamespace();
            if(namingEnum.hasMore())
                throw new NamingException("More than one User found");
            else
                return dnName;
        }

        return "";
    }
}
