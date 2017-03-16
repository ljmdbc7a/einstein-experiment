package com.einstein.experiment.finalize;

/**
 *
 * @author liujiaming
 * @since 2017/03/16
 *
 * @see java.lang.ref.Finalizer
 *
 */
public class FinalizeMethodTest {

    public static class MyObjectOverrideFinalize {

        private int count;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize called. " + count);
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            MyObjectOverrideFinalize myObjectOverrideFinalize = new MyObjectOverrideFinalize();
            myObjectOverrideFinalize.setCount(i);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
