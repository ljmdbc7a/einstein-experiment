package com.einstein.experiment.memoryleak;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujiaming on 2017/02/13 10:20.
 * <p/>
 * 实例：包：Group:com.meidusa.venus.commons Name:venus-common-validator
 * 参数验证会动态生成验证类，并缓存到hashMap中，map的key是自定义类，但是未重写hashCode和equals方法，导致hashMap无限增长，导致perm内存泄露
 * <p/>
 * 总结：自定义类作为Map的key，只重写了hashCode 没有重写equals方法，导致Map(static)无限增长，导致内存泄露
 */
public class VisitorFieldValidator {
    //    private static Map<ClassPolicy, Validator> internalValidatorChainMapping = new HashMap();

    public static class ClassPolicy {
        private Class<?> clazz;
        private String policy;

        public ClassPolicy(Class<?> clazz, String policy) {
            this.clazz = clazz;
            this.policy = policy;
        }

        public Class<?> getClazz() {
            return this.clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

        public String getPolicy() {
            return this.policy;
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }

        @Override
        public int hashCode() {
            return (new HashCodeBuilder(780293071, -917577685)).append(this.policy).append(this.clazz).toHashCode();
        }

//        @Override
//        public boolean equals(Object obj) {
//            return this.hashCode() == obj.hashCode();
//        }
    }

    public static void main(String[] args) {

        Map<Object, Object> map = new HashMap<Object, Object>();
        Object object1 = new Object();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ClassPolicy classPolicy = new ClassPolicy(object1.getClass(), "in");
            map.put(classPolicy, "xx");
            System.out.println(map.size());
        }
    }
}
