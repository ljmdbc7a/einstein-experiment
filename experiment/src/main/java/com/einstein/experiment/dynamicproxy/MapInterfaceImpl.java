package com.einstein.experiment.dynamicproxy;

/**
 *
 * @author liujiaming
 * @since 2017/03/19
 *
 */
public class MapInterfaceImpl implements MapInterface {

    @Override
    public int calculateArea(String location) {
        System.out.println("calculateArea...");
        if (location == null) {
            return 0;
        } else {
            return location.length();
        }
    }

    @Override
    public boolean isAccess(String location) {
        if (location == null) {
            return false;
        } else {
            return true;
        }
    }
}

