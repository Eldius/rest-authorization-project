package net.eldiosantos.authorization.repository.interfaces;

import java.io.Serializable;

public interface Repository<T, K extends Serializable> extends Read<T, K>, Write<T, K>, Delete<T, K> {

}