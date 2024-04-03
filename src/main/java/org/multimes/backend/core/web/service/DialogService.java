package org.multimes.backend.core.web.service;

import org.multimes.backend.core.web.model.Dialog;
import org.multimes.backend.core.web.repository.interfaces.IDialogRepository;
import org.multimes.backend.core.web.service.interfaces.IDialogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialogService implements IDialogService {

    private final IDialogRepository interRepository;

    public DialogService(IDialogRepository interRepository) {
        this.interRepository = interRepository;
    }

    public List<Dialog> getAll() {
        return interRepository.getAll();
    }

    public void add(Dialog dialog) {
        interRepository.add(dialog);
    }
}
