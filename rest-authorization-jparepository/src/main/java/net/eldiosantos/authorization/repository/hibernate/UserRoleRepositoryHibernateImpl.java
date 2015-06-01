package net.eldiosantos.authorization.repository.hibernate;


import net.eldiosantos.authorization.model.auth.UserRole;
import net.eldiosantos.authorization.repository.UserRoleRepository;
import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;

import javax.persistence.EntityManager;

public class UserRoleRepositoryHibernateImpl extends BaseRepository<UserRole, Long> implements UserRoleRepository {

    @Deprecated
    public UserRoleRepositoryHibernateImpl() {
    }

    public UserRoleRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
