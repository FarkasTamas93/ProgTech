package valuta;

import valuta.model.Login;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Farkas Tamás on 2017.05.04..
 */
public class LoginServiceJPA {
    EntityManager entityManager;

    public LoginServiceJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Login createLoginAccount(String userName, String password, String firstName, String lastName, String email) {
        Login login = new Login();
        try {
            login.setUserName(userName);
            login.setPassword(password);
            login.setFirstName(firstName);
            login.setLastName(lastName);
            login.setEmail(email);
            //Megkerdezni ballatol miert nem a rendes datumot szurja be
            login.setDate(LocalDate.now().plusDays(1));
            entityManager.persist(login);
        } catch (EntityExistsException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return login;
    }

    public List<String> getAllUser() {
        Query query = null;
        try {
            query = entityManager.createQuery("SELECT e.userName FROM Login e");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }
    public List<String> getAllEmail() {
        Query query = null;
        try {

            query = entityManager.createQuery("select e.email from Login e");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

//    public Login findUserByName(String name) {
//        Query query = null;
//        try {
//            query = entityManager.createQuery("select p from Login p where userName = :userName", Login.class);
//            query.setParameter("userName", name);
//        }catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//        return (Login) query.getSingleResult();
//    }

    public Login findUserByName(String name) {
        Query query = null;
        try {
            query = entityManager.createNativeQuery("select * from Login where userName = binary'"+ name+"'", Login.class);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return (Login)query.getSingleResult();
    }

}
