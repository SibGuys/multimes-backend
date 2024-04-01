package org.multimes.backend.core.web.model.response;

public class MessageResp {
    private final String userName;
    private final String text;
    private final String messageTime;
    private final boolean isInter;

    public MessageResp(String userName, String text,
                       String messageTime, boolean isInter) {
        this.userName = userName;
        this.text = text;
        this.messageTime = messageTime;
        this.isInter = isInter;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public boolean getIsInter() {
        return isInter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((messageTime == null) ? 0 : messageTime.hashCode());
        result = prime * result + (isInter ? 1231 : 1237);
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
        MessageResp other = (MessageResp) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (messageTime == null) {
            if (other.messageTime != null)
                return false;
        } else if (!messageTime.equals(other.messageTime))
            return false;
        if (isInter != other.isInter)
            return false;
        return true;
    }

}
