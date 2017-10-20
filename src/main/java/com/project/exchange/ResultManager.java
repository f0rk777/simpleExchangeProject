package com.project.exchange;

import java.io.*;
import java.util.*;

/**
 * Created by Alex on 20.10.2017.
 */
public class ResultManager {
    private String fileName;

    public ResultManager() {
        fileName = "result.txt";
    }

    public ResultManager(String fileName) {
        this.fileName = fileName;
    }

    public void makeResult(ArrayList<Orders> listOrders, TreeMap<String, Clients> clientsHashMap) {
        TreeSet<Integer> passedIndexes = new TreeSet<>();

        for (int i = 0; i < listOrders.size() - 1; i++) {
            if (passedIndexes.contains(i)) {
                continue;
            } else {
                Orders orders = listOrders.get(i);
                for (int j = i + 1; j < listOrders.size(); j++) {
                    if (passedIndexes.contains(j)) {
                        continue;
                    } else {
                        Orders orders1 = listOrders.get(j);
                        if (!(orders.getClientName().equals(orders1.getClientName()))
                                & !(orders.getTypeOperation().equals(orders1.getTypeOperation()))
                                & (orders.getSecuritiesName().equals(orders1.getSecuritiesName()))
                                & (orders.getCostOneSecurities() == orders1.getCostOneSecurities())
                                & (orders.getValueOneSecurities() == orders1.getValueOneSecurities())) {
                            passedIndexes.add(j);

                            //проводим для первого покупку, для второго продажу
                            if (orders.getTypeOperation().equals("b")) {
                                //изменение баланса
                                int oldBalance = clientsHashMap.get(orders.getClientName()).getBalance();
                                int cost = orders.getCostOneSecurities();
                                int values = orders.getValueOneSecurities();
                                int valueBuying = cost * values;
                                int newBalance = oldBalance - valueBuying;
                                clientsHashMap.get(orders.getClientName()).setBalance(newBalance);


                                //изменение кол-ва ценных бумаг
                                int oldSecuritiesValues = clientsHashMap.get(orders.getClientName()).getHashMap().get(orders.getSecuritiesName()).getValue();
                                int newSecuritiesValues = oldSecuritiesValues + values;
                                clientsHashMap.get(orders.getClientName()).getHashMap().get(orders.getSecuritiesName()).setValue(newSecuritiesValues);


                                //для второго идет продажа
                                //баланс + кол-во бумаг -
                                //изменение баланса
                                oldBalance = clientsHashMap.get(orders1.getClientName()).getBalance();
                                newBalance = oldBalance + valueBuying;
                                clientsHashMap.get(orders1.getClientName()).setBalance(newBalance);


                                //изменение кол-ва ценных бумаг
                                oldSecuritiesValues = clientsHashMap.get(orders1.getClientName()).getHashMap().get(orders.getSecuritiesName()).getValue();
                                newSecuritiesValues = oldSecuritiesValues - values;
                                clientsHashMap.get(orders1.getClientName()).getHashMap().get(orders1.getSecuritiesName()).setValue(newSecuritiesValues);
                            } else if (orders.getTypeOperation().equals("s")) {
                                //изменение баланса
                                //первый продает баланс + кол-во бумаг -
                                int oldBalance = clientsHashMap.get(orders.getClientName()).getBalance();
                                int cost = orders.getCostOneSecurities();
                                int values = orders.getValueOneSecurities();
                                int valueBuying = cost * values;
                                int newBalance = oldBalance + valueBuying;
                                clientsHashMap.get(orders.getClientName()).setBalance(newBalance);


                                //изменение кол-ва ценных бумаг
                                int oldSecuritiesValues = clientsHashMap.get(orders.getClientName()).getHashMap().get(orders.getSecuritiesName()).getValue();
                                int newSecuritiesValues = oldSecuritiesValues - values;
                                clientsHashMap.get(orders.getClientName()).getHashMap().get(orders.getSecuritiesName()).setValue(newSecuritiesValues);


                                //для второго идет покупка
                                //баланс - кол-во бумаг +
                                //изменение баланса
                                oldBalance = clientsHashMap.get(orders1.getClientName()).getBalance();
                                newBalance = oldBalance - valueBuying;
                                clientsHashMap.get(orders1.getClientName()).setBalance(newBalance);


                                //изменение кол-ва ценных бумаг
                                oldSecuritiesValues = clientsHashMap.get(orders1.getClientName()).getHashMap().get(orders1.getSecuritiesName()).getValue();
                                newSecuritiesValues = oldSecuritiesValues + values;
                                clientsHashMap.get(orders1.getClientName()).getHashMap().get(orders1.getSecuritiesName()).setValue(newSecuritiesValues);
                            }
                        }
                    }
                }
            }
        }
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeResultToFile(TreeMap<String, Clients> clientsHashMap) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName)));) {

            for (Map.Entry<String, Clients> entry : clientsHashMap.entrySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(entry.getKey());
                stringBuilder.append("\t");
                stringBuilder.append(entry.getValue().getBalance());
                stringBuilder.append("\t");


                for (Map.Entry<String, Securities> entry1 : entry.getValue().getHashMap().entrySet()) {
                    stringBuilder.append(entry1.getValue().getValue());
                    stringBuilder.append("\t");
                }
                stringBuilder.append("\n");
                writer.write(stringBuilder.toString());
            }
            writer.flush();
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
