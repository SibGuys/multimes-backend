package org.multimes.backend.core.web.model.dto.requests;

public class AllMessagesByDialogIdRequest {
    private final int dialogId;

    public AllMessagesByDialogIdRequest() {
        this.dialogId = -1;
    }

    public AllMessagesByDialogIdRequest(int dialogId) {
        this.dialogId = dialogId;
    }

    public int getDialogId() {
        return dialogId;
    }

    @Override
    public String toString() {
        return "AllMessagesByDialogIdRequest [dialogId=" + dialogId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dialogId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AllMessagesByDialogIdRequest other = (AllMessagesByDialogIdRequest) obj;
        if (dialogId != other.dialogId)
            return false;
        return true;
    }

}
