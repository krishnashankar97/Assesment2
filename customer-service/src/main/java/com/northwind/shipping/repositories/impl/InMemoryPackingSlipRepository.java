package com.northwind.shipping.repositories.impl;



import com.northwind.shipping.domain.PackingSlip;
import com.northwind.shipping.repositories.PackingSlipRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryPackingSlipRepository implements PackingSlipRepository {
    private Map<Long, PackingSlip> data = new HashMap<Long, PackingSlip>();

    @Override
    public List<PackingSlip> findByShipName(String shipName) {
        return data.values().stream()
                .filter(c->c.getShipName().startsWith(shipName))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackingSlip> getByOrderNo(int orderNo) {
        return data.values().stream().collect(Collectors.toList());
    }

    @Override
    public PackingSlip addItem(long Id, PackingSlip addItem) {
        return null;
    }

    @Override
    public PackingSlip getById(long id) {
        return data.get(id);
    }

    @Override
    public List<PackingSlip> getAll(int offSet, int limit) {
        return data.values().stream()
                .skip(offSet)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public PackingSlip save(PackingSlip entity) {
        if (entity.getId() == 0) {
            long id = data.keySet().stream()
                    .max(Long::compare)
                    .orElse(1L);
            entity.setId(id);
        }
        entity.setVersion(entity.getVersion() + 1);
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }



}
