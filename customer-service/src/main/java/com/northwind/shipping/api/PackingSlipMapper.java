package com.northwind.shipping.api;

import com.northwind.shipping.domain.PackingSlip;

public class PackingSlipMapper {
    public static PackingSlipModel toModel(PackingSlip entity) {
        PackingSlipModel model = new PackingSlipModel();
        model.setId(entity.getId());
        model.setShipName(entity.getShipName());
        model.setShipAddress(entity.getShipAddress());
        model.setShipCity(entity.getShipCity());
        model.setShipRegion(entity.getShipRegion());
        model.setShipPostalCode(entity.getShipPostalCode());
        model.setShipCountry(entity.getShipCountry());
        model.setOrderNo(entity.getOrderNo());
        model.setVersion(entity.getVersion());

        com.northwind.shipping.api.Link addressLink = new com.northwind.shipping.api.Link();
        addressLink.setHref(String.format("/shipping/%d/", entity.getId()));
        model.getLinks().add(addressLink);
        return model;
    }

    public static PackingSlip toEntity(PackingSlipModel model) {
        PackingSlip entity = new PackingSlip(model.getShipPostalCode());
        entity.setId(model.getId());
        entity.setShipName(model.getShipName());
        entity.setShipAddress(model.getShipAddress());
        entity.setShipCity(model.getShipCity());
        entity.setShipRegion(model.getShipRegion());
        entity.setShipPostalCode(model.getShipPostalCode());
        entity.setShipCountry(model.getShipCountry());
        entity.setOrderNo(model.getOrderNo());
        entity.setVersion(model.getVersion());

        return entity;
    }

    public static PackingSlip toEntity(PackingSlipModel model, PackingSlip entity) {
        entity.setShipName(model.getShipName());
        entity.setShipAddress(model.getShipAddress());
        entity.setShipCity(model.getShipCity());
        entity.setShipRegion(model.getShipRegion());
        entity.setShipPostalCode(model.getShipPostalCode());
        entity.setShipCountry(model.getShipCountry());
        return entity;
    }


}
