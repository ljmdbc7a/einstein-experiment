package com.einstein.experiment.concurrency.safe;

/**
 * @author liujiaming
 * @since 2017/02/24
 * <p/>
 * 总结：多线程环境下注意非线程安全方法。
 */
public class Sequence {

    private int value;

    public int getNextUnsafe() {
        return ++value;
    }

    public synchronized int getNextSafe() {
        return ++value;
    }
}
