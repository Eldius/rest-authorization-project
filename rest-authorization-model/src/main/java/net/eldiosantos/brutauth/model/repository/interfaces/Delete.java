package net.eldiosantos.brutauth.model.repository.interfaces;

import java.io.Serializable;

public interface Delete<T, K extends Serializable> {
    void delete(T element);
}
