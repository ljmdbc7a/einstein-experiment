package com.einstein.experiment.memoryleak;

import com.meidusa.venus.validate.validator.VisitorFieldValidator;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存泄露问题。
 * <p/>
 * 实例: Group:com.meidusa.venus.commons Name:venus-common-validator
 * from: http://maven.hexnova.com/nexus/content/groups/public/com/meidusa/venus/commons/venus-common-validator/3.3.1/
 * 参数验证会动态生成验证类，并缓存到hashMap中，map的key是自定义类，但是未重写hashCode和equals方法，导致hashMap无限增长，导致perm内存泄露
 * <p/>
 * 总结：自定义类作为Map的key，只重写了hashCode 没有重写equals方法，导致Map(static)无限增长，导致内存泄露
 */
public class VisitorFieldValidatorTest {

    private static Map<ClassPolicy, Object> internalValidatorChainMapping = new HashMap<ClassPolicy, Object>();

    VisitorFieldValidator validator = new VisitorFieldValidator();

    public static class ClassPolicy {
        private Class<?> clazz;
        private String policy;

        public ClassPolicy(Class<?> clazz, String policy) {
            this.clazz = clazz;
            this.policy = policy;
        }

        @Override
        public int hashCode() {
            return (new HashCodeBuilder(780293071, -917577685)).append(this.policy)
                    .append(this.clazz)
                    .toHashCode();
        }

//        @Override
//        public boolean equals(Object obj) {
//            return this.hashCode() == obj.hashCode();
//        }
    }

    public static void main(String[] args) {
        Object object = new Object();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ClassPolicy classPolicy = new ClassPolicy(object.getClass(), "in");
            if (internalValidatorChainMapping.get(classPolicy) != null) {
                System.out.println("Hit cache!");
            } else {
                internalValidatorChainMapping.put(classPolicy, i);
                System.out.println(internalValidatorChainMapping.size());
            }
        }
    }
}
