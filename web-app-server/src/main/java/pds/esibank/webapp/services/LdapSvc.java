package pds.esibank.webapp.services;


import pds.esibank.models.LdapUser;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author BOURGEOIS Thibault
 * Date     25/10/2017
 * Time     17:24
 */
public class LdapSvc {

    //Adresse du serveur sur lequel se trouve l'annuaire LDAP
    private static String serverIP = "192.154.88.145";
    //Pourt du serveur sur lequel se trouve l'annuaire LDAP
    private static String serverPort = "389";
    //Login de connexion à l'annuaire LDAP : Le login dois être sous forme de "distinguished name"
    //ce qui signifie qu'il dois être affiché sous la forme de son arborescence LDAP
    private static String serverLogin = "cn=admin,dc=esibank,dc=inside,dc=esiag,dc=info";
    //Mot de passe de connexion à l'annuaire LDAP
    private static String serverPass = "esibankpds";

    private static DirContext myLdapContext;

    private static void connectToLdap() {

        //On remplis un tableau avec les parametres d'environement et de connexion au LDAP
        Hashtable environnement = new Hashtable();
        environnement.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environnement.put(Context.PROVIDER_URL, "ldap://"+serverIP+":"+serverPort+"/");
        environnement.put(Context.SECURITY_AUTHENTICATION, "simple");
        environnement.put(Context.SECURITY_PRINCIPAL, serverLogin);
        environnement.put(Context.SECURITY_CREDENTIALS, serverPass);

        try {
            //On appelle le contexte à partir de l'environnement
            myLdapContext = new InitialDirContext(environnement);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public static byte[] getUserImage(String uid, String OU) {

        connectToLdap();

        try {
            Attributes attrs = myLdapContext.getAttributes("uid=" + uid + ",OU=" + OU + ",DC=esibank,DC=inside,DC=esiag,DC=info");

            myLdapContext.close();

            byte[] photo = (byte[])attrs.get("jpegPhoto").get();

            return photo;
        } catch (NamingException e) {

        }
        return null;
    }

    public static List<LdapUser> getAllLdapUser(String OU) {

        connectToLdap();

        List<LdapUser> listReturn = new ArrayList<LdapUser>();

        //Parcourir tous les Utilisateurs
        try {

            //Filtre du LDAP
            NamingEnumeration userAnswer = myLdapContext.search("OU=" + OU + ",dc=esibank,dc=inside,dc=esiag,dc=info", null);

            myLdapContext.close();

            while (userAnswer.hasMore()) {
                SearchResult sr = (SearchResult) userAnswer.next();

                listReturn.add(new LdapUser(sr.getAttributes().get("uid").get(0).toString(),
                        sr.getAttributes().get("givenName").get(0).toString(),
                        sr.getAttributes().get("sn").get(0).toString(),
                        sr.getAttributes().get("title").get(0).toString()));

            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return listReturn;
    }

    public static LdapUser getOneLdapUser(String uid,String OU) {

        connectToLdap();

        LdapUser userReturn = new LdapUser();

        try {
            //Filtre du LDAP
            Attributes userAnswer = myLdapContext.getAttributes("uid=" + uid + ",OU=" + OU + ",dc=esibank,dc=inside,dc=esiag,dc=info");

            myLdapContext.close();

            userReturn.setUid(uid);
            userReturn.setFirstname(userAnswer.get("givenname").get(0).toString());
            userReturn.setLastname(userAnswer.get("sn").get(0).toString());
            userReturn.setMail(userAnswer.get("mail").get(0).toString());
            userReturn.setDescription(userAnswer.get("description").get(0).toString());

            userReturn.setCompany(userAnswer.get("o").get(0).toString());
            userReturn.setPosition(userAnswer.get("title").get(0).toString());
            userReturn.setPhone(userAnswer.get("telephoneNumber").get(0).toString());

        }
        catch (NamingException e) {
            e.printStackTrace();
        }
        return userReturn;
    }

}
