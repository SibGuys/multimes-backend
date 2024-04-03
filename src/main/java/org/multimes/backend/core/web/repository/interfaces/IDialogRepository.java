package org.multimes.backend.core.web.repository.interfaces;

import org.multimes.backend.core.web.model.Dialog;

import java.util.List;

public interface IDialogRepository {
    public void add(Dialog dialog);

    public Dialog getById(long id);

    public boolean checkExistsWithIdInMessenger(long id);

    public List<Dialog> getAll();
}
