package ru.job4j.generics.stores;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        var result = false;
        if (findById(id) != null) {
            mem.replace(id, model);
            result =  true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        var model = findById(id);
        return mem.remove(id, model);
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }

}