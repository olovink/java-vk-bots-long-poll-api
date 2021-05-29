package api.longpoll.bots.model.events.messages;

import api.longpoll.bots.model.events.EventObject;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Action with message. Used to work with Callback buttons.
 */
public class MessageEvent implements EventObject {
    /**
     * User ID.
     */
    @SerializedName("user_id")
    private Integer userId;

    /**
     * Dialog ID.
     */
    @SerializedName("peer_id")
    private Integer peerId;

    /**
     * Random string.
     */
    @SerializedName("event_id")
    private String eventId;

    /**
     * Additional info.
     */
    @SerializedName("payload")
    private JsonObject payload;

    /**
     * Message ID.
     */
    @SerializedName("conversation_message_id")
    private String conversationMessageId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId(Integer peerId) {
        this.peerId = peerId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public JsonObject getPayload() {
        return payload;
    }

    public void setPayload(JsonObject payload) {
        this.payload = payload;
    }

    public String getConversationMessageId() {
        return conversationMessageId;
    }

    public void setConversationMessageId(String conversationMessageId) {
        this.conversationMessageId = conversationMessageId;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "userId=" + userId +
                ", peerId=" + peerId +
                ", eventId='" + eventId + '\'' +
                ", payload='" + payload + '\'' +
                ", conversationMessageId='" + conversationMessageId + '\'' +
                '}';
    }
}
