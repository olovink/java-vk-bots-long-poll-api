package api.longpoll.bots.model.response.groups;

import api.longpoll.bots.adapters.BoolIntAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Response to <b>groups.isMember</b> request.
 */
public class GroupsIsMemberResponse {
    /**
     * Whether the user is a member of the community.
     */
    @SerializedName("member")
    @JsonAdapter(BoolIntAdapter.class)
    private Boolean member;

    /**
     * Whether the user left a request to join the group, which can be declined by the groups.leave method.
     */
    @SerializedName("request")
    @JsonAdapter(BoolIntAdapter.class)
    private Boolean request;

    /**
     * Whether the user is invited to join the group or event.
     */
    @SerializedName("invitation")
    @JsonAdapter(BoolIntAdapter.class)
    private Boolean invitation;

    /**
     * Whether user can invite others.
     */
    @SerializedName("can_invite")
    @JsonAdapter(BoolIntAdapter.class)
    private Boolean canInvite;

    /**
     * Whether user can deny invitation.
     */
    @SerializedName("can_recall")
    @JsonAdapter(BoolIntAdapter.class)
    private Boolean canRecall;

    /**
     * User ID.
     */
    @SerializedName("user_id")
    private Integer userId;

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public Boolean getRequest() {
        return request;
    }

    public void setRequest(Boolean request) {
        this.request = request;
    }

    public Boolean getInvitation() {
        return invitation;
    }

    public void setInvitation(Boolean invitation) {
        this.invitation = invitation;
    }

    public Boolean getCanInvite() {
        return canInvite;
    }

    public void setCanInvite(Boolean canInvite) {
        this.canInvite = canInvite;
    }

    public Boolean getCanRecall() {
        return canRecall;
    }

    public void setCanRecall(Boolean canRecall) {
        this.canRecall = canRecall;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
