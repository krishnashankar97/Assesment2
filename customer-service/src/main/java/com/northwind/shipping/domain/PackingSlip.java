package com.northwind.shipping.domain;

import com.northwind.shipping.api.PackingSlipModel;

import java.util.*;

public class PackingSlip {
    private long id;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;
    private long version;
    private int orderNo;
    private List<PackingSlipDetails> addItem = new ArrayList<>();




    public String getShipName() {
        return shipName;
    }
    public void setShipName(String shipName) {
        if (shipName == null ) {
            throw new IllegalArgumentException("ShipName is required.");
        }
        String cleanName = shipName.trim();
        if (cleanName.length() == 0 || cleanName.length() > 50) {
            throw new IllegalArgumentException("ShipName must be between 1 and 50 characters.");
        }
        this.shipName = cleanName;

    }

    public PackingSlip(String shipName){setShipName(shipName);}

    public PackingSlip(long id, String shipName)
    {
        this(shipName);
        setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }




    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        String cleanName = null;
        if (shipAddress != null) {
            cleanName = shipAddress.trim();
        }
        if (cleanName != null && (cleanName.length() == 0 || cleanName.length() > 60)) {
            throw new IllegalArgumentException("ShipAddress must be between 1 and 60 characters.");
        }
        this.shipAddress = cleanName;
    }

    public String getShipCity() {
        return shipCity;
    }

   public void setShipCity(String shipCity) {
//        String cleanName = null;
//        if (shipCity != null) {
//            cleanName = shipAddress.trim();
//        }
//        if (cleanName != null && (cleanName.length() == 0 || cleanName.length() > 30)) {
//            throw new IllegalArgumentException("ShipCity must be between 1 and 30 characters.");
//        }
           this.shipCity = shipCity;
   }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
//        String cleanName = null;
//        if (shipRegion != null) {
//            cleanName = shipRegion.trim();
//        }
//        if (cleanName != null && (cleanName.length() == 0 || cleanName.length() > 24)) {
//            throw new IllegalArgumentException("ShipAddress must be between 1 and 24 characters.");
//        }
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

//    public void setShipPostalCode(String shipPostalCode) {
//        String cleanName = null;
//        if (shipPostalCode != null) {
//            cleanName = shipAddress.trim();
//        }
//        if (cleanName != null && (cleanName.length() == 0 || cleanName.length() > 24)) {
//            throw new IllegalArgumentException("ShipPostalCode must be between 1 and 24 characters.");
//        }
//        this.shipPostalCode = cleanName;
//    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        if (version == 0)
            throw new IllegalArgumentException("Version cannot be zero.");

        if (version < this.version)
            throw new IllegalArgumentException("Version cannot be older than the current version.");

        this.version = version;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
//        String cleanName = null;
//        if (shipCountry != null) {
//            cleanName = shipCountry.trim();
//        }
//        if (cleanName != null && (cleanName.length() == 0 || cleanName.length() > 24)) {
//            throw new IllegalArgumentException("ShipCountry must be between 1 and 24 characters.");
//        }

        this.shipCountry = shipCountry;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public List<PackingSlipDetails> getAddItem() {
        return Collections.unmodifiableList(addItem);
    }

    public void addItem(PackingSlipDetails addItems)
    {
        addItem.add(addItems);
    }

    public void generateOrderNo() {
        int i = 1;
        if (orderNo != 0)
            throw new UnsupportedOperationException("OrderNo has already been generated");
        if (id == 0)
            throw new UnsupportedOperationException("OrderNo cannot be generated till the ID has been set.");

        orderNo = i++;
    }


}
