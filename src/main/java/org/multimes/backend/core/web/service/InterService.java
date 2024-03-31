package org.multimes.backend.core.web.service;

import java.util.Set;

import org.multimes.backend.core.web.repository.interfaces.IInterRepository;
import org.multimes.backend.core.web.service.interfaces.IInterService;
import org.springframework.stereotype.Service;

@Service
public class InterService implements IInterService {

    private final IInterRepository interRepository;

    public InterService(IInterRepository interRepository) {
        this.interRepository = interRepository;
    }

    public Set<Long> getAll() {
        return interRepository.getAll();
    }

    public void addId(long id) {
        interRepository.addId(id);
    }
}
