package org.multimes.backend.core.web.repository;

import org.multimes.backend.core.web.model.Dialog;
import org.multimes.backend.core.web.repository.interfaces.IDialogRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DialogRepository implements IDialogRepository {

    private Set<Dialog> dialogSet = new HashSet<>();

    @Override
    public Set<Dialog> getAll() {
        return dialogSet;
    }

    @Override
    public void add(Dialog dialog) {
        dialogSet.add(dialog);
    }

}
