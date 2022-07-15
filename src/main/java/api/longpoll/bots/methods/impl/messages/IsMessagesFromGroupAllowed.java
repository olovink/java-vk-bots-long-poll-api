package api.longpoll.bots.methods.impl.messages;

import api.longpoll.bots.adapters.deserializers.BoolIntDeserializer;
import api.longpoll.bots.methods.impl.VkMethod;
import api.longpoll.bots.model.response.GenericResponseBody;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Implements <b>messages.isMessagesFromGroupAllowed</b> method.
 * <p>
 * Returns information whether sending messages from the community to current user is allowed.
 *
 * @see <a href="https://vk.com/dev/messages.isMessagesFromGroupAllowed">https://vk.com/dev/messages.isMessagesFromGroupAllowed</a>
 */
public class IsMessagesFromGroupAllowed extends VkMethod<IsMessagesFromGroupAllowed.ResponseBody> {
    public IsMessagesFromGroupAllowed(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getUrl() {
        return VK_METHODS.getProperty("messages.isMessagesFromGroupAllowed");
    }

    @Override
    protected Class<ResponseBody> getResponseType() {
        return ResponseBody.class;
    }

    public IsMessagesFromGroupAllowed setGroupId(int groupId) {
        return addParam("group_id", groupId);
    }

    public IsMessagesFromGroupAllowed setUserId(int userId) {
        return addParam("user_id", userId);
    }

    @Override
    public IsMessagesFromGroupAllowed addParam(String key, Object value) {
        return (IsMessagesFromGroupAllowed) super.addParam(key, value);
    }

    /**
     * Response to <b>messages.isMessagesFromGroupAllowed</b> request.
     */
    public static class ResponseBody extends GenericResponseBody<ResponseBody.Response> {
        /**
         * Response object.
         */
        public static class Response {
            @SerializedName("is_allowed")
            @JsonAdapter(BoolIntDeserializer.class)
            private Boolean isAllowed;

            public Boolean getAllowed() {
                return isAllowed;
            }

            public void setAllowed(Boolean allowed) {
                isAllowed = allowed;
            }


            @Override
            public String toString() {
                return "Response{" +
                        "isAllowed=" + isAllowed +
                        '}';
            }
        }
    }
}
