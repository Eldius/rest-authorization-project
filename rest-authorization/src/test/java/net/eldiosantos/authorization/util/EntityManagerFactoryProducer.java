package net.eldiosantos.authorization.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by esjunior on 22/07/2015.
 */
@ApplicationScoped
public class EntityManagerFactoryProducer {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("service");

    @Produces
    public EntityManager entityManager() {
        return factory.createEntityManager();
    }
}
