package api.longpoll.bots.methods.photos;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.GenericConverterFactory;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.photos.PhotosGetMessagesUploadServerResponse;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;

import java.util.stream.Stream;

/**
 * Implements <b>photos.getMessagesUploadServer</b> method.
 *
 * @see <a href="https://vk.com/dev/photos.getMessagesUploadServer">https://vk.com/dev/photos.getMessagesUploadServer</a>
 */
public class PhotosGetMessagesUploadServer extends GetMethod<GenericResult<PhotosGetMessagesUploadServerResponse>> {
    /**
     * User ID.
     */
    private Integer peerId;

    public PhotosGetMessagesUploadServer(LongPollBot bot) {
        super(bot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApi() {
        return VkApi.getInstance().photosGetMessagesUploadServer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JsonToPojoConverter<GenericResult<PhotosGetMessagesUploadServerResponse>> getConverter() {
        return GenericConverterFactory.get(new TypeToken<GenericResult<PhotosGetMessagesUploadServerResponse>>(){}.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(keyVal("peer_id", peerId));
    }

    public Integer getPeerId() {
        return peerId;
    }

    public PhotosGetMessagesUploadServer setPeerId(Integer peerId) {
        this.peerId = peerId;
        return this;
    }
}
