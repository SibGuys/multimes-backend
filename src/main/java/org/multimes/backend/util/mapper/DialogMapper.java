package org.multimes.backend.util.mapper;

import org.multimes.backend.core.web.model.Dialog;
import org.multimes.backend.core.web.model.response.DialogResp;

public class DialogMapper {
    public static DialogResp dialogToDialogResp(Dialog dialog) {
        return new DialogResp(dialog.getMessenger(), dialog.getUsername());
    }
}
