package org.multimes.backend.core.web.repository;

import java.util.HashSet;
import java.util.Set;

import org.multimes.backend.core.web.repository.interfaces.IInterRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InterRepository implements IInterRepository {

    private Set<Long> interIdSet = new HashSet<>();

    @Override
    public Set<Long> getAll() {
        return interIdSet;
    }

    @Override
    public void addId(long id) {
        interIdSet.add(id);
    }

}
