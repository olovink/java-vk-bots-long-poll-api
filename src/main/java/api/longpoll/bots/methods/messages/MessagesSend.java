package api.longpoll.bots.methods.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.converters.JsonToPojoConverter;
import api.longpoll.bots.converters.response.messages.MessagesSendResultConverterImpl;
import api.longpoll.bots.exceptions.ApiHttpException;
import api.longpoll.bots.methods.GetMethod;
import api.longpoll.bots.methods.VkApi;
import api.longpoll.bots.methods.docs.DocsGetMessagesUploadServer;
import api.longpoll.bots.methods.docs.DocsSave;
import api.longpoll.bots.methods.other.UploadDoc;
import api.longpoll.bots.methods.other.UploadPhoto;
import api.longpoll.bots.methods.photos.PhotosGetMessagesUploadServer;
import api.longpoll.bots.methods.photos.PhotosSaveMessagesPhoto;
import api.longpoll.bots.model.objects.media.Doc;
import api.longpoll.bots.model.objects.media.Photo;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.docs.DocsGetUploadServerResponse;
import api.longpoll.bots.model.response.other.UploadDocResult;
import api.longpoll.bots.model.response.other.UploadPhotoResult;
import api.longpoll.bots.model.response.photos.PhotosGetMessagesUploadServerResponse;
import api.longpoll.bots.model.response.photos.PhotosSaveMessagesPhotoResponse;
import org.jsoup.Connection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implements <b>messages.send</b> method.
 *
 * @see <a href="https://vk.com/dev/messages.send">https://vk.com/dev/messages.send</a>
 */
public class MessagesSend extends GetMethod<GenericResult<Object>> {
    /**
     * User ID.
     */
    private Integer userId;

    /**
     * Unique identifier to avoid resending the message.
     */
    private Integer randomId = Long.valueOf(System.currentTimeMillis()).intValue();

    /**
     * Destination ID.
     */
    private Integer peerId;

    /**
     * User's short address (for example, illarionov).
     */
    private String domain;

    /**
     * ID of conversation the message will relate to.
     */
    private Integer chatId;

    /**
     * IDs of message recipients (if new conversation shall be started).
     */
    private List<Integer> userIds;

    /**
     * Text of the message.
     */
    private String message;

    /**
     * Geographical latitude of a check-in, in degrees (from -90 to 90).
     */
    private Float latitude;

    /**
     * Geographical longitude of a check-in, in degrees (from -180 to 180).
     */
    private Float longitude;

    /**
     * List of objects attached to the message.
     */
    private List<String> attachments;

    /**
     * Id of replied message.
     */
    private Integer replyTo;

    /**
     * ID of forwarded messages.
     */
    private List<Integer> forwardMessages;

    /**
     * Sticker id.
     */
    private Integer stickerId;

    /**
     * <b>true</b> - links will not attach snippet.
     */
    private Boolean dontParseLinks;

    /**
     * <b>true</b> - mention of user will not generate notification for him.
     */
    private Boolean disableMentions;

    public MessagesSend(LongPollBot bot) {
        super(bot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApi() {
        return VkApi.getInstance().messagesSend();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JsonToPojoConverter<GenericResult<Object>> getConverter() {
        return new MessagesSendResultConverterImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Stream<Connection.KeyVal> getKeyValStream() {
        return Stream.of(
                keyVal("user_id", userId),
                keyVal("random_id", randomId),
                keyVal("peer_id", peerId),
                keyVal("domain", domain),
                keyVal("user_ids", userId),
                keyVal("user_ids", userIds),
                keyVal("message", message),
                keyVal("lat", latitude),
                keyVal("long", longitude),
                keyVal("attachment", attachments),
                keyVal("reply_to", replyTo),
                keyVal("forward_messages", forwardMessages),
                keyVal("sticker_id", stickerId),
                keyVal("dont_parse_links", dontParseLinks, true),
                keyVal("disable_mentions", disableMentions, true)
        );
    }

    private String attachment(String type, Integer ownerId, Integer mediaId, String accessKey) {
        return type + ownerId + "_" + mediaId + (accessKey == null ? "" : "_" + accessKey);
    }

    private MessagesSend attach(String type, Integer ownerId, Integer mediaId, String accessKey) {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        attachments.add(attachment(type, ownerId, mediaId, accessKey));
        return this;
    }

    public MessagesSend attachPhoto(Photo photo) {
        return attach("photo", photo.getOwnerId(), photo.getId(), photo.getAccessKey());
    }

    public MessagesSend attachPhoto(File photo) throws ApiHttpException {
        PhotosGetMessagesUploadServerResponse uploadServer = new PhotosGetMessagesUploadServer(bot)
                .setPeerId(peerId)
                .execute()
                .getResponse();
        UploadPhotoResult uploadPhoto = new UploadPhoto()
                .setUploadUrl(uploadServer.getUploadUrl())
                .setPhoto(photo)
                .execute();
        PhotosSaveMessagesPhotoResponse savePhoto = new PhotosSaveMessagesPhoto(bot)
                .setHash(uploadPhoto.getHash())
                .setPhoto(uploadPhoto.getPhoto())
                .setServer(uploadPhoto.getServer())
                .execute()
                .getResponse()
                .get(0);

        return attach("photo", savePhoto.getOwnerId(), savePhoto.getId(), savePhoto.getAccessKey());
    }

    public MessagesSend attachDoc(Doc doc) {
        return attach("doc", doc.getOwnerId(), doc.getId(), doc.getAccessKey());
    }

    public MessagesSend attachDoc(File doc) throws ApiHttpException {
        DocsGetUploadServerResponse uploadServer = new DocsGetMessagesUploadServer(bot)
                .setType("doc")
                .setPeerId(peerId)
                .execute()
                .getResponse();
        UploadDocResult uploadDoc = new UploadDoc()
                .setUploadUrl(uploadServer.getUploadUrl())
                .setDoc(doc)
                .execute();
        Doc uploadedDoc = (Doc) new DocsSave(bot)
                .setFile(uploadDoc.getFile())
                .execute()
                .getResponse()
                .getAttachable();

        return attach("doc", uploadedDoc.getOwnerId(), uploadedDoc.getId(), uploadedDoc.getAccessKey());
    }

    public Integer getUserId() {
        return userId;
    }

    public MessagesSend setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getRandomId() {
        return randomId;
    }

    public MessagesSend setRandomId(Integer randomId) {
        this.randomId = randomId;
        return this;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public MessagesSend setPeerId(Integer peerId) {
        this.peerId = peerId;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public MessagesSend setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Integer getChatId() {
        return chatId;
    }

    public MessagesSend setChatId(Integer chatId) {
        this.chatId = chatId;
        return this;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public MessagesSend setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessagesSend setMessage(String message) {
        this.message = message;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public MessagesSend setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public MessagesSend setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public MessagesSend setAttachments(List<String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Integer getReplyTo() {
        return replyTo;
    }

    public MessagesSend setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public List<Integer> getForwardMessages() {
        return forwardMessages;
    }

    public MessagesSend setForwardMessages(List<Integer> forwardMessages) {
        this.forwardMessages = forwardMessages;
        return this;
    }

    public Integer getStickerId() {
        return stickerId;
    }

    public MessagesSend setStickerId(Integer stickerId) {
        this.stickerId = stickerId;
        return this;
    }

    public Boolean getDontParseLinks() {
        return dontParseLinks;
    }

    public MessagesSend setDontParseLinks(Boolean dontParseLinks) {
        this.dontParseLinks = dontParseLinks;
        return this;
    }

    public Boolean getDisableMentions() {
        return disableMentions;
    }

    public MessagesSend setDisableMentions(Boolean disableMentions) {
        this.disableMentions = disableMentions;
        return this;
    }
}
