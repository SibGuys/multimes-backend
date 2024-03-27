package org.multimes.backend.core.web.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class InterService {
    private Set<Long> interIdList = new HashSet<>();

    public Set<Long> getInterIdList() {
        return interIdList;
    }

    public void addId(long id) {
        interIdList.add(id);
    }
}
