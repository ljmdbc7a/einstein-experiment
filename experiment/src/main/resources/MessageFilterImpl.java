import com.alibaba.rocketmq.common.filter.MessageFilter;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author liujiaming
 * @since 2017/02/21
 */
public class MessageFilterImpl implements MessageFilter {

    public boolean match(MessageExt msg) {
        if (msg.getTags() != null && msg.getTags().equals("tagsA")) {
            return true;
        }
        return false;
    }
}
