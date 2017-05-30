package valuta;

import valuta.model.ValutesToDatabase;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.TransactionRequiredException;

/**
 * Created by Farkas Tamás on 2017.05.07..
 */

public class ValutesServiceJPA {
    EntityManager entityManager;

    public ValutesServiceJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ValutesToDatabase createValutesToLoginAccount(String userName) {
        ValutesToDatabase valutesToDatabase = new ValutesToDatabase();
        try {
            entityManager.getTransaction().begin();
            valutesToDatabase.setUserName(userName);
            entityManager.persist(valutesToDatabase);
            entityManager.getTransaction().commit();
        }catch (EntityExistsException | IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return valutesToDatabase;
    }

    public ValutesToDatabase findValutesByName(String name) {
        Query query=null;
        try {
            query = entityManager.createNativeQuery("select * from valutes where binary userName = binary'"+ name+"'", ValutesToDatabase.class);
        }catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return (ValutesToDatabase) query.getSingleResult();
    }

    public void updateValuteToDatabase(ValutesToDatabase actualUser)
    {
        //Itt csak feltoltom az adatbazisba mert a settert mar a BuyValute-ba beallitottam
        try {
            entityManager.find(ValutesToDatabase.class, actualUser.getEid());
            entityManager.getTransaction().begin();
            entityManager.getTransaction().commit();
        }catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }
}
