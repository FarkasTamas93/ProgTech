package valuta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Farkas Tamás on 2017.05.19..
 */
public class JpaService {
    private static JpaService jpaServiceInstance = new JpaService();

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private LoginServiceJPA loginServiceJPA = null;
    private ValutesServiceJPA valutesServiceJPA = null;

    public static JpaService getJpaServiceInstance() {
        return jpaServiceInstance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public LoginServiceJPA getLoginServiceJPA() {
        return loginServiceJPA;
    }

    public void setLoginServiceJPA(LoginServiceJPA loginServiceJPA) {
        this.loginServiceJPA = loginServiceJPA;
    }

    public ValutesServiceJPA getValutesServiceJPA() {
        return valutesServiceJPA;
    }

    public void setValutesServiceJPA(ValutesServiceJPA valutesServiceJPA) {
        this.valutesServiceJPA = valutesServiceJPA;
    }
}
