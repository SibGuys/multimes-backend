package org.multimes.backend.core.web.repository.interfaces;

import org.multimes.backend.core.web.model.Dialog;

import java.util.Set;

public interface IDialogRepository {
    public Set<Dialog> getAll();

    public void add(Dialog dialog);
}
