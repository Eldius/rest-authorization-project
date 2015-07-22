package net.eldiosantos.authorization.repository.hibernate;

import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;

import javax.persistence.EntityManager;

public class UserSessionAuthRepositoryHibernateImpl extends BaseRepository<UserSessionAuth, String> implements UserSessionAuthRepository {

    @Deprecated
    public UserSessionAuthRepositoryHibernateImpl() {
    }

    public UserSessionAuthRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
