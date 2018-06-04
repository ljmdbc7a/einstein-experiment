package com.einstein.experiment.uid;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHashConflict implements Runnable {

    private String fileName;

    /**
     * 使用Executors工具类创建线程池
     */
    private static final int THREADS = 10;
    private static final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(THREADS);

    private static int PROGRAM_KEY_LENGTH = 11;
    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public TestHashConflict(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Conflict rate: 172032/24000000 = 0.007168
     *
     * @return
     */
    public static String getFormatedUUID() {
        String formatedProgramKey;
        int key = UUID.randomUUID().hashCode();
        if ((key & 0x7FFFFFFF) == key) {
            formatedProgramKey = String.valueOf(key);
        } else {
            formatedProgramKey = "1" + String.valueOf(key & 0x7FFFFFFF);
        }

        StringBuffer buffer = new StringBuffer();
        for (int index = formatedProgramKey.length(); index < PROGRAM_KEY_LENGTH; index++) {
            buffer.append("0");
        }
        return buffer.toString() + formatedProgramKey;
    }

    /**
     * Generate UID. Conflict rate: 0/20000000
     *
     * @param tag
     * @return
     */
    public static String generateUIDWithTag(String tag) {
        StringBuilder stringBuilder = new StringBuilder();
        if (tag.length() >= 2) {
            stringBuilder.append(tag.substring(tag.length() - 2));
        } else {
            stringBuilder.append(tag);
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int strInteger = Integer.parseInt(str, 16);
            stringBuilder.append(chars[strInteger % 0x3E]);
        }
        return stringBuilder.toString().toLowerCase();
    }

    public static String generateSimple() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = Long.toHexString(uuid.getMostSignificantBits() + uuid.getLeastSignificantBits());
        return uuidStr;
    }

    public static String randomAlphanumeric() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");
            for (int i = 0; i < 50 * 1000 * 1000; i++) {
                String formatedUUID = randomAlphanumeric();
                writer.println(formatedUUID);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        TestHashConflict testHashConflict1 = new TestHashConflict("/Users/lijiamin/xx/programKeysAlpha1");
        threadPoolExecutor.submit(testHashConflict1);

        TestHashConflict testHashConflict2 = new TestHashConflict("/Users/lijiamin/xx/programKeysAlpha2");
        threadPoolExecutor.submit(testHashConflict2);

    }
}
