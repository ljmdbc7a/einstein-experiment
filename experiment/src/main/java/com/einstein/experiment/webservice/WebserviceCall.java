package com.einstein.experiment.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * Created by liujiaming on 2017/01/19 15:04.
 */
public class WebserviceCall {

    public static void main(String[] args) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://10.1.22.91:8144/ma-web?wsdl");
        Object[] objects;
        try {
            objects = client.invoke("AccountFacade", "queryAccountById");
            //输出调用结果
            System.out.println(objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();
//        jwpfb.setServiceClass(UserService.class);
//        jwpfb.setAddress("http://localhost:8080/userService");
//        UserService us = (UserService) jwpfb.create();
//        System.out.println(us.getUser("a001"));
//        List<Users> users = us.geAlltUsers();
//        for (Users u : users) {
//            System.out.println(u.toJson());
//        }
    }

    public static void init() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // 注册WebService接口
//        factory.setServiceClass(IBankAccountFacade.class);
        // 设置WebService地址
        factory.setAddress("http://localhost:9090/BankAccountFacade?wsdl");
//        bankAccountFacade = (IBankAccountFacade)factory.create();
    }

}
