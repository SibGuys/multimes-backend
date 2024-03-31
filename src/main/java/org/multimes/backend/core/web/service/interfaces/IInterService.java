package org.multimes.backend.core.web.service.interfaces;

import java.util.Set;

public interface IInterService {
    public Set<Long> getAll();

    public void addId(long id);
}
