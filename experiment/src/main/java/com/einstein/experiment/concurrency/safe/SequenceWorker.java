package com.einstein.experiment.concurrency.safe;

/**
 * @author liujiaming
 * @since 2017/02/25
 */
public class SequenceWorker implements Runnable {

    private Sequence sequence;

    public SequenceWorker(Sequence unsafeSequence) {
        this.sequence = unsafeSequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(Thread.currentThread()
                                     .getName() + " get: " + sequence.getNextSafe());

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
