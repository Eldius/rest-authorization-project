package net.eldiosantos.authorization.repository.interfaces;

import java.io.Serializable;

public interface Delete<T, K extends Serializable> {
    void delete(T element);
}
