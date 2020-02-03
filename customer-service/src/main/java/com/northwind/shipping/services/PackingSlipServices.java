package com.northwind.shipping.services;



import com.northwind.customerservice.domain.Customer;
import com.northwind.shipping.repositories.PackingSlipRepository;
import com.northwind.shipping.domain.PackingSlip;


import java.util.List;

public class PackingSlipServices {
    private PackingSlipRepository repository;

    public PackingSlipServices(PackingSlipRepository repository) {
        this.repository = repository;
    }

    public PackingSlip getById(long id) {
        return repository.getById(id);
    }

    public List<PackingSlip> getAll(int offset, int limit) {
        return repository.getAll(offset, limit);
    }

    public List<PackingSlip> findByShipName(String shipName) {
        return repository.findByShipName(shipName);
    }


    public void delete(PackingSlip shipping) {
        repository.delete(shipping.getId());
    }

    public PackingSlip save(PackingSlip shipping) {
        PackingSlip savedShipping = repository.save(shipping);
        if (savedShipping.getOrderNo() == 0) {
            savedShipping.generateOrderNo();
            return repository.save(savedShipping);
        }
        return savedShipping;
    }



    public PackingSlip addItem(long Id, PackingSlip addItem) {
        return repository.addItem(Id, addItem);
    }
}
