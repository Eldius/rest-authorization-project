package net.eldiosantos.brutauth.model.repository.interfaces;

import java.io.Serializable;
import java.util.List;

public interface Read<T, K extends Serializable> {
    T getByPk(K pk);

    List<T> listAll();
}