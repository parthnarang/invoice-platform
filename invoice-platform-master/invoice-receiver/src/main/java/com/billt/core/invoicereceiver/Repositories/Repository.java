package com.billt.core.invoicereceiver.Repositories;
import java.util.List;
import com.mongodb.WriteResult;

public interface Repository<T> {

    public List<T> getAllUsers();

    public void addnewuser(T object);

    public T getUser(String id);

   // public WriteResult updateObject(String id, String name);

}