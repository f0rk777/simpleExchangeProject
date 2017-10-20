package com.project.exchange;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alex on 19.10.2017.
 */
public class OrdersManager {
    private String fileName;

    public OrdersManager(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Orders> getOrders() {
        ArrayList<Orders> listOrders = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));) {
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                String[] orderString = s.split("\t");

                Orders order = new Orders();
                order.setClientName(orderString[0]);
                order.setTypeOperation(orderString[1]);
                order.setSecuritiesName(orderString[2]);
                order.setCostOneSecurities(Integer.parseInt(orderString[3]));
                order.setValueOneSecurities(Integer.parseInt(orderString[4]));

                listOrders.add(order);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrders;


    }
}
