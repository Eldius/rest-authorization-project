package net.eldiosantos.authorization.repository.hibernate;


import net.eldiosantos.brutauth.model.auth.UserRole;
import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;
import net.eldiosantos.brutauth.model.repository.UserRoleRepository;

import javax.persistence.EntityManager;

public class UserRoleRepositoryHibernateImpl extends BaseRepository<UserRole, Long> implements UserRoleRepository {

    @Deprecated
    public UserRoleRepositoryHibernateImpl() {
    }

    public UserRoleRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
