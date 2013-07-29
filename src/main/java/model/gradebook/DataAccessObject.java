package gradebook.model;

import java.util.List;

/**
* This is the DAO interface. Set up to work with a list.
* Can easily be changed by modifying some of the code here.
*@param <T>, the type of the object used by the DAO.
*@author Ryan Pallarino
*/
public interface DataAccessObject<T> {

    List<T> getAll();
    void updateObject(T obj);
    void deleteObject(T obj);
    T getObject(int index);
}
