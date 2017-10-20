package com.project.exchange;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * Created by Alex on 19.10.2017.
 */
public class ClientsManager {
    private String fileName;

    public ClientsManager(String fileName) {
        this.fileName = fileName;
    }

    public TreeMap<String, Clients> getClientsList() {
        TreeMap<String, Clients> hashMap = new TreeMap<>();

        String[] names = {"A", "B", "C", "D"};

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));) {
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                String[] clientString = s.split("\t");

                Clients clients = new Clients();
                clients.setName(clientString[0]);
                clients.setBalance(Integer.parseInt(clientString[1]));

                HashMap<String, Securities> securitiesHashMap = new HashMap<>();

                for (int i = 0; i < names.length; i++) {
                    Securities securities = new Securities();
                    securities.setName(names[i]);
                    securities.setValue(Integer.parseInt(clientString[i + 2]));
                    securitiesHashMap.put(names[i], securities);
                }

                clients.setHashMap(securitiesHashMap);
                hashMap.put(clientString[0], clients);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hashMap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
