package com.project.exchange;

/**
 * Created by Alex on 19.10.2017.
 */
public class Orders {
    private String clientName;
    private String typeOperation;
    private String securitiesName;
    private int costOneSecurities;
    private int valueOneSecurities;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public int getCostOneSecurities() {
        return costOneSecurities;
    }

    public void setCostOneSecurities(int costOneSecurities) {
        this.costOneSecurities = costOneSecurities;
    }

    public int getValueOneSecurities() {
        return valueOneSecurities;
    }

    public void setValueOneSecurities(int valueOneSecurities) {
        this.valueOneSecurities = valueOneSecurities;
    }
}
