package com.einstein.experiment;

import com.einstein.experiment.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.annotations.Constructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Base {

    public static class User {
        public int id;
        public String name;
        private String customerID;

        public User(int id, String name, String customerID) {
            this.id = id;
            this.name = name;
            this.customerID = customerID;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }
    }

    public static class Item {
        public int id;
        public String itemName;
        public User owner;
        boolean hasOpenProblems;

        public Item(int id, String itemName, User owner, boolean hasOpenProblems) {
            this.id = id;
            this.itemName = itemName;
            this.owner = owner;
            this.hasOpenProblems = hasOpenProblems;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public void setOwner(User owner) {
            this.owner = owner;
        }

        public void setHasOpenProblems(boolean hasOpenProblems) {
            this.hasOpenProblems = hasOpenProblems;
        }

        public int getId() {
            return id;
        }

        public String getItemName() {
            return itemName;
        }

        public User getOwner() {
            return owner;
        }

        public boolean isHasOpenProblems() {
            return hasOpenProblems;
        }
    }

    public static void main(String[] args) {

        Double dd = 1D;
        System.out.println(new BigDecimal(dd).scale());

        System.out.println(0.05 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

        System.out.println(UUID.randomUUID());

//        String ss = "x\n" +
//                "y\n" +
//                "z\n" +
//                "a\n" +
//                "a";
//        String[] split = ss.split("\n");
//        for (String a : split) {
//            System.out.println(a);
//        }
//
//        Date date = new Date();
//        System.out.println(date);

//        String a  = null;
//        System.out.println("a".equals(a));

        ObjectMapper objectMapper = new ObjectMapper();
        Item myItem = new Item(1, "theItem", new User(2, "theUser", "CustomerId"), true);


        try {
            String serialized = objectMapper.writeValueAsString(myItem);
            System.out.println(serialized);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
