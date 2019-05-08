package com.einstein.experiment.classloader;

import java.net.URL;

public class ContextClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.class.getClassLoader();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(contextClassLoader);

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

    }
}