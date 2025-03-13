package dao;


import lombok.Getter;

import java.util.List;
import java.util.Optional;

public interface Dao<O> {

    Optional<O> findById(int id);

    List<O> findAll();

    void save(O o);

    int update(O o);



}
