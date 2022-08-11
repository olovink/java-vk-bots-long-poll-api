package api.longpoll.bots.methods.impl.messages;

import api.longpoll.bots.methods.impl.VkMethod;
import api.longpoll.bots.model.response.IntegerResponseBody;

/**
 * Implements <b>messages.markAsAnsweredConversation</b> method.
 *
 * @see <a href="https://vk.com/dev/messages.markAsAnsweredConversation">https://vk.com/dev/messages.markAsAnsweredConversation</a>
 */
public class MarkAsAnsweredConversation extends VkMethod<IntegerResponseBody> {

    public MarkAsAnsweredConversation(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getUri() {
        return property("messages.markAsAnsweredConversation");
    }

    @Override
    protected Class<IntegerResponseBody> getResponseClass() {
        return IntegerResponseBody.class;
    }

    public MarkAsAnsweredConversation setPeerId(int peerId) {
        return addParam("peer_id", peerId);
    }

    public MarkAsAnsweredConversation setAnswered(boolean answered) {
        return addParam("answered", answered ? 1 : 0);
    }

    public MarkAsAnsweredConversation setGroupId(int groupId) {
        return addParam("group_id", groupId);
    }

    @Override
    public MarkAsAnsweredConversation addParam(String key, Object value) {
        return (MarkAsAnsweredConversation) super.addParam(key, value);
    }
}
