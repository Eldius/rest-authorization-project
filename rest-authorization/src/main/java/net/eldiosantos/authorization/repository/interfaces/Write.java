package net.eldiosantos.authorization.repository.interfaces;

import java.io.Serializable;

public interface Write<T, K extends Serializable> {
    void persist(T element);

    void update(T element);

    void saveOrUpdate(T element);
}