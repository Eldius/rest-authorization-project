package net.eldiosantos.authorization.repository.hibernate;

import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;
import net.eldiosantos.brutauth.model.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryHibernateImpl extends BaseRepository<User, Long> implements UserRepository {

    @Deprecated
    public UserRepositoryHibernateImpl() {
    }

    public UserRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User findByLogin(final String user) {
        return (User) entityManager.createQuery("select u from User u where upper(u.user) like :user ")
                .setParameter("user", user.toUpperCase())
                .getSingleResult();
    }

    @Override
    public List<User> findAll(String query) {
        return (List<User>) entityManager.createQuery("select u from User u where UPPER(u.name) like :query or UPPER(u.user) like :query or UPPER(u.email) like :query ")
                .setParameter("query", "%" + query.toUpperCase() + "%")
                .getResultList();
    }
}
