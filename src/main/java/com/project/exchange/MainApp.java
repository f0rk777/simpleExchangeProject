package com.project.exchange;

import java.util.*;

/**
 * Created by Alex on 19.10.2017.
 */
public class MainApp {
    public static void main(String[] args) {
        ClientsManager clientsManager = new ClientsManager("clients.txt");
        TreeMap<String, Clients> clientsHashMap = clientsManager.getClientsList();

        OrdersManager ordersManager = new OrdersManager("orders.txt");
        ArrayList<Orders> listOrders = ordersManager.getOrders();

        ResultManager resultManager = new ResultManager("result.txt");
        resultManager.makeResult(listOrders, clientsHashMap);
        resultManager.writeResultToFile(clientsHashMap);
    }
}
