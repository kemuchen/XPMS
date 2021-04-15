package cn.xpms.system.framework.queue;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.constant.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName BaseConsumer
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/12 10:46
 * @Version 1.0
 */
public abstract class BaseConsumer<T> {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(BaseConsumer.class);

    /** 消息队列执行一次后睡眠时间 */
    private int sleep_time;

    /** 消息队列对象 */
    private TripartiteQueue<T> tripartiteQueue = null;

    /**
     * @Description: 无参构造函数
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 10:51
     */
    public BaseConsumer() {
        sleep_time = SystemConstants.QUEUE_DEFAULT_SLEEP_TIME;
        // 创建消息队列
        generateQueue();
    }

    /**
     * @Description: 有参构造函数
     * @Params: [sleep_time]
     * @return:
     * @Author: 柯雷
     * @Date: 2020/11/12 11:09
     */
    public BaseConsumer(int sleep_time) {
        this.sleep_time = sleep_time;
        // 创建消息队列
        generateQueue();
    }

    /**
     * @Description: 创建消息队列
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 11:15
     */
    public synchronized void generateQueue() {
        if (tripartiteQueue == null) {
            tripartiteQueue = new TripartiteQueue<T>();
        }
        // 启动消费者线程
        ConsumerThread thread = new ConsumerThread();
        thread.start();
    }

    /**
     * @ClassName ConsumerThread
     * @Desc 消费者线程类
     * @Author 柯雷
     * @Date 2020/11/12 10:46
     * @Version 1.0
     */
    class ConsumerThread extends Thread {
        @Override
        public void run() {
            initConsumer();
        }
    }

    /**
     * @Description: 初始化消费者并开始消费
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 11:08
     */
    private void initConsumer() {
        logger.info("【BaseConsumer.initConsumer】初始化消费者");
        try {
            while (true) {
                T data =  tripartiteQueue.poll();
                if(data != null){
                    try {
                        handle(data);
                    } catch (AppException e) {
                        logger.error("【BaseConsumer.init】处理数据异常：" + e);
                        handleError(data);
                    }
                }
                // 睡眠3秒执行一次
                Thread.sleep(this.sleep_time);
            }
        } catch (AppException e) {
            logger.error("【BaseConsumer.init】处理数据异常：" + e);
        } catch (Exception e) {
            logger.error("【BaseConsumer.init】处理数据异常：" + e);
            e.printStackTrace();
        }
    }

    /**
     * @Description: 业务处理抽象方法
     * @Params: [data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 10:50
     */
    protected abstract void handle(T data) throws AppException;

    /**
     * @Description: 错误处理抽象方法
     * @Params: [data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 10:58
     */
    protected abstract void handleError(T data) throws AppException;
    
    /** 
     * @Description: 数据注入消费队列
     * @Params: [data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 11:18
     */ 
    public void offer(T data) {
        this.tripartiteQueue.offer(data);
    }
}
