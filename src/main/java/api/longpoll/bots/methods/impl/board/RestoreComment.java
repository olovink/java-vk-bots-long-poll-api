package api.longpoll.bots.methods.impl.board;

import api.longpoll.bots.config.VkBotsConfig;
import api.longpoll.bots.methods.impl.VkMethod;
import api.longpoll.bots.model.response.IntegerResponse;

/**
 * Implements <b>board.deleteComment</b> method.
 * <p>
 * Restores a comment deleted from a topic on a community's discussion board.
 *
 * @see <a href="https://vk.com/dev/board.deleteComment">https://vk.com/dev/board.deleteComment</a>
 */
public class RestoreComment extends VkMethod<IntegerResponse> {
    public RestoreComment(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getUrl() {
        return VkBotsConfig.getInstance().getBotMethods().getProperty("board.restoreComment");
    }

    @Override
    protected Class<IntegerResponse> getResponseType() {
        return IntegerResponse.class;
    }

    public RestoreComment setGroupId(int groupId) {
        return addParam("group_id", groupId);
    }

    public RestoreComment setTopicId(int topicId) {
        return addParam("topic_id", topicId);
    }

    public RestoreComment setCommentId(int commentId) {
        return addParam("comment_id", commentId);
    }

    @Override
    public RestoreComment addParam(String key, Object value) {
        return (RestoreComment) super.addParam(key, value);
    }
}
