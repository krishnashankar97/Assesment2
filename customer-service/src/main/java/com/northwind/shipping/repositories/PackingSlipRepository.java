package com.northwind.shipping.repositories;


import com.northwind.customerservice.domain.Customer;
import com.northwind.shipping.domain.PackingSlip;
import com.northwind.shipping.repositories.Repository;
import com.northwind.shipping.domain.PackingSlip;

import java.util.List;

public interface PackingSlipRepository extends Repository<PackingSlip> {
    List<PackingSlip> findByShipName(String shipName);
    List<PackingSlip> getByOrderNo(int orderNo);
    PackingSlip addItem(long Id, PackingSlip addItem);
}
