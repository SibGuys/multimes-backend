package org.multimes.backend.core.web.service.interfaces;

import org.multimes.backend.core.web.model.Dialog;

import java.util.Set;

public interface IDialogService {
    public Set<Dialog> getAll();

    public void add(Dialog dialog);
}
