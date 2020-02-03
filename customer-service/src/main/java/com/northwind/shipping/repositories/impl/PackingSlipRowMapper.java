package com.northwind.shipping.repositories.impl;

import com.northwind.shipping.domain.PackingSlip;

import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PackingSlipRowMapper implements RowMapper<PackingSlip> {
    @Override
    public PackingSlip mapRow(ResultSet rs, int rowNum) throws SQLException {
        PackingSlip shipping = new PackingSlip(rs.getString("ShipName"));
        shipping.setShipCountry(rs.getString("ShipCountry"));
        shipping.setShipPostalCode(rs.getString("ShipPostalCode"));
        shipping.setShipRegion(rs.getString("ShipRegion"));
        shipping.setShipCity(rs.getString("ShipCity"));
        shipping.setShipAddress(rs.getString("ShipAddress"));
        shipping.setOrderNo(rs.getInt("OrderID"));
        shipping.setVersion(rs.getLong("Version"));

        return shipping;
    }
}
