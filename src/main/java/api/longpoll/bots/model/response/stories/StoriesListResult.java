package api.longpoll.bots.model.response.stories;

import api.longpoll.bots.model.objects.additional.Story;
import api.longpoll.bots.model.objects.additional.VkList;
import api.longpoll.bots.model.response.GenericResult;

/**
 * Response to <b>stories.get</b>
 */
public class StoriesListResult extends GenericResult<VkList<Story>> {
}
