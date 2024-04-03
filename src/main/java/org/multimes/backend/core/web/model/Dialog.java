package org.multimes.backend.core.web.model;

public class Dialog {
    private final long id;
    private final String messenger;
    private final String username;

    public Dialog() {
        id = -1;
        messenger = null;
        username = null;
    }

    public Dialog(long id, String messenger, String username) {
        this.id = id;
        this.messenger = messenger;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getMessenger() {
        return messenger;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((messenger == null) ? 0 : messenger.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        Dialog other = (Dialog) obj;
        if (id != other.id)
            return false;
        if (messenger == null) {
            if (other.messenger != null)
                return false;
        } else if (!messenger.equals(other.messenger))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
