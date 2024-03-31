package org.multimes.backend.core.web.repository.interfaces;

import java.util.Set;

public interface IInterRepository {
    public Set<Long> getAll();

    public void addId(long id);
}
