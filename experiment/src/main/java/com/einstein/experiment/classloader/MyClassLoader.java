package com.einstein.experiment.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义classLoader
 * <p/>
 * Java 虚拟机是如何判定两个 Java 类是相同的。
 * Java 虚拟机不仅要看类的全名是否相同，还要看加载此类的类加载器是否一样。只有两者都相同的情况，才认为两个类是相同的。即便是同样的字节代码，被不同的类加载器加载之后所得到的类，也是不同的。
 */
public class MyClassLoader extends ClassLoader {

    private String rootDir;

    public MyClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String name) {
        String path = rootDir + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
        FileInputStream fileInputStream = null;
        try {
            File file = new File(path);
            byte[] ret = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(ret);
            fileInputStream.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream == null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
