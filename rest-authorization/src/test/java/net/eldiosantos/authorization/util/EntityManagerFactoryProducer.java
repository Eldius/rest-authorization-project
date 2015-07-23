package net.eldiosantos.authorization.util;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by esjunior on 22/07/2015.
 */
public class EntityManagerFactoryProducer {
    private EntityManagerFactory factory;

    @Produces
    @Singleton
    public EntityManager entityManager() {
        if(factory == null) {
            factory = Persistence.createEntityManagerFactory("service");
        }
        return factory.createEntityManager();
    }
}
