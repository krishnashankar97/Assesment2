package com.northwind.shipping.api;


import com.northwind.shipping.domain.PackingSlip;
import com.northwind.shipping.services.PackingSlipServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/shipping")
public class PackingSlipController {
    private PackingSlipServices service;

    public PackingSlipController(PackingSlipServices service) {
        this.service = service;
    }

    // Specifiying required = false and using Optional<T> allows us to use
    // just the path /customers (without parameters) to query using default offset/limit values
    @GetMapping("/getAll")
    public ResponseEntity<List<PackingSlipModel>> get(@RequestParam(required = false) Optional<Integer> offset, @RequestParam(required = false) Optional<Integer> limit) {
        int skip = offset.orElse(0); //default offset to 0 if not specified
        int take = limit.orElse(50); //default limit to 50 if not sepcified
        if (take > 50) {
            throw new IllegalArgumentException("Limit cannot be more than 50.");
        }

        List<PackingSlipModel> shipping = service.getAll(skip, take)
                .stream().map(e-> PackingSlipMapper.toModel(e))
                .collect(Collectors.toList());

        return new ResponseEntity<>(shipping, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PackingSlipModel> get(@PathVariable long id) {
        PackingSlip shipping = service.getById(id);
        if (shipping == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(PackingSlipMapper.toModel(shipping), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PackingSlipModel> create(@RequestBody PackingSlipModel model) {
        PackingSlip shipping = PackingSlipMapper.toEntity(model);
        PackingSlip savedShipping = service.save(shipping);
        return ResponseEntity.created(URI.create("/shipping/" + savedShipping.getId()))
                .body(PackingSlipMapper.toModel(savedShipping));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackingSlipModel> update(@PathVariable long id,
                                                @RequestBody PackingSlipModel model) {
        PackingSlip existingShipping = service.getById(id);
        if (existingShipping == null) {
            return ResponseEntity.notFound().build();
        }

        if (existingShipping.getVersion() != model.getVersion()) {
            return new ResponseEntity<>(PackingSlipMapper.toModel(existingShipping), HttpStatus.CONFLICT);
        }
        PackingSlip mergedShipping = PackingSlipMapper.toEntity(model, existingShipping);

        PackingSlip savedShipping = service.save(mergedShipping);

        return new ResponseEntity<>(PackingSlipMapper.toModel(savedShipping), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        PackingSlip existingShipping = service.getById(id);
        if (existingShipping ==  null)
            return;

        service.delete(existingShipping);
    }

    // Specifying expected query parameters tells MVC to map to this action when the parameter is present
    @GetMapping(params = "shipName")
    public ResponseEntity<List<PackingSlipModel>> findByShipName(@RequestParam String shipName) {
        return ResponseEntity
                .ok()
                .body(service.findByShipName(shipName).stream()
                        .map(c->PackingSlipMapper.toModel(c))
                        .collect(Collectors.toList()));
    }


}
