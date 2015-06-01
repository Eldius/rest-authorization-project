package net.eldiosantos.authorization.repository.hibernate;

import net.eldiosantos.authorization.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.repository.UserSessionAuthRepository;
import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;

import javax.persistence.EntityManager;

public class UserSessionAuthRepositoryHibernateImpl extends BaseRepository<UserSessionAuth, String> implements UserSessionAuthRepository {

    @Deprecated
    public UserSessionAuthRepositoryHibernateImpl() {
    }

    public UserSessionAuthRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
