package org.multimes.backend.core.web.service.interfaces;

import org.multimes.backend.core.web.model.Dialog;

import java.util.List;

public interface IDialogService {
    public List<Dialog> getAll();

    public void add(Dialog dialog);
}
