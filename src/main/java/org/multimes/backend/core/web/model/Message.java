package org.multimes.backend.core.web.model;

public class Message {
    private final String text;
    private final int interId;

    public Message() {
        text = null;
        interId = -1;
    }

    public Message(String text, int interId) {
        this.text = text;
        this.interId = interId;
    }

    public String getText() {
        return text;
    }

    public int getInterId() {
        return interId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + interId;
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
        Message other = (Message) obj;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (interId != other.interId)
            return false;
        return true;
    }

}
