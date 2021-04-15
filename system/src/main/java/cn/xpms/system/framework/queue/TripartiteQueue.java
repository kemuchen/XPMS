package cn.xpms.system.framework.queue;

import cn.xpms.system.framework.util.sys.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @ClassName TripartiteQueue
 * @Desc 消息队列
 * @Author 柯雷
 * @Date 2020/11/12 10:35
 * @Version 1.0
 */
public class TripartiteQueue<T> {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(TripartiteQueue.class);

    /** 队列对象 */
    public Queue<T> queue;

    /**
     * @Description: 构造函数
     * @Params: []
     * @return:
     * @Author: 柯雷
     * @Date: 2020/11/12 10:38
     */
    public TripartiteQueue() {
        queue = new LinkedList<T>();
    }

    /**
     * @Description: 获取队列对象
     * @Params: []
     * @return: java.util.Queue<T>
     * @Author: 柯雷
     * @Date: 2020/11/12 10:40
     */
    public Queue<T> queue() {
        return queue;
    }

    /**
     * @Description: 向队列中添加数据
     * @Params: [data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 10:41
     */
    public void offer(T data) {
        queue.offer(data);
    }

    /**
     * @Description: 队列中弹出数据
     * @Params: []
     * @return: T
     * @Author: 柯雷
     * @Date: 2020/11/12 10:45
     */
    public T poll() {
        T data = queue.poll();
        if (SysUtil.isEmpty(data)) {
            return null;
        } else {
            return data;
        }
    }
}
