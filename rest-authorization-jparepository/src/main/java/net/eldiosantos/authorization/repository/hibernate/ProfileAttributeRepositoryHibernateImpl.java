package net.eldiosantos.authorization.repository.hibernate;

import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;
import net.eldiosantos.brutauth.model.auth.profile.ProfileAttribute;
import net.eldiosantos.brutauth.model.auth.profile.ProfileAttributeId;
import net.eldiosantos.brutauth.model.repository.ProfileAttributeRepository;

import javax.persistence.EntityManager;

public class ProfileAttributeRepositoryHibernateImpl extends BaseRepository<ProfileAttribute, ProfileAttributeId> implements ProfileAttributeRepository {

    @Deprecated
    public ProfileAttributeRepositoryHibernateImpl() {
    }

    public ProfileAttributeRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
