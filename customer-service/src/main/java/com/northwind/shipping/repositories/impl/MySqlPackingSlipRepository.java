package com.northwind.shipping.repositories.impl;

import com.northwind.customerservice.domain.Address;
import com.northwind.customerservice.domain.Customer;
import com.northwind.shipping.domain.PackingSlip;
import com.northwind.shipping.domain.PackingSlipDetails;
import com.northwind.shipping.repositories.PackingSlipRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MySqlPackingSlipRepository implements PackingSlipRepository {
    private DataSource dataSource;
    private PackingSlipRowMapper PackingSlipRowMapper;

    private final static String getPackingSlipSql  =
            "SELECT `PackingSlips`.`PackingSlipID`,\n" +
                    "    `PackingSlips`.`OrderID`,\n" +
                    "    `PackingSlips`.`ShipName`,\n" +
                    "    `PackingSlips`.`ShipAddress`,\n" +
                    "    `PackingSlips`.`ShipCity`,\n" +
                    "    `PackingSlips`.`ShipRegion`,\n" +
                    "    `PackingSlips`.`ShipPostalCode`,\n" +
                    "    `PackingSlips`.`ShipCountry`,\n" +
                    "    `PackingSlips`.`Version`\n" +
                    "FROM `shipping-db`.`PackingSlips`\n" +
                    "WHERE PackingSlipID = :id";

    public MySqlPackingSlipRepository(DataSource dataSource,
                                   PackingSlipRowMapper PackingSlipRowMapper) {
        this.dataSource = dataSource;
        this.PackingSlipRowMapper = PackingSlipRowMapper;

    }
    @Override
    public List<PackingSlip> findByShipName(String shipName) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT `PackingSlips`.`PackingSlipID`,\n" +
                "    `PackingSlips`.`OrderID`,\n" +
                "    `PackingSlips`.`ShipName`,\n" +
                "    `PackingSlips`.`ShipAddress`,\n" +
                "    `PackingSlips`.`ShipCity`,\n" +
                "    `PackingSlips`.`ShipRegion`,\n" +
                "    `PackingSlips`.`ShipPostalCode`,\n" +
                "    `PackingSlips`.`ShipCountry`,\n" +
                "    `PackingSlips`.`Version`\n" +
                "FROM `shipping-db`.`PackingSlips`\n" +
                "WHERE ShipName LIKE :shipName";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("shipName", shipName + "%");

        List<PackingSlip> packingSlips = db.query(sql, params, PackingSlipRowMapper);
        return packingSlips;
    }

    @Override
    public List<PackingSlip> getByOrderNo(int orderNo) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT `PackingSlips`.`PackingSlipID`,\n" +
                "    `PackingSlips`.`OrderID`,\n" +
                "    `PackingSlips`.`ShipName`,\n" +
                "    `PackingSlips`.`ShipAddress`,\n" +
                "    `PackingSlips`.`ShipCity`,\n" +
                "    `PackingSlips`.`ShipRegion`,\n" +
                "    `PackingSlips`.`ShipPostalCode`,\n" +
                "    `PackingSlips`.`ShipCountry`,\n" +
                "    `PackingSlips`.`Version`\n" +
                "FROM `shipping-db`.`PackingSlips`\n" +
                "WHERE OrderNo = :orderNo";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderNo", orderNo + "%");

        List<PackingSlip> packingSlips = db.query(sql, params, PackingSlipRowMapper);
        return packingSlips;
    }

    @Override
    public PackingSlip addItem(long Id, PackingSlip addItem) {
        return null;
    }

    @Override
    public PackingSlip getById(long id) {
        return null;
    }

    @Override
    public List<PackingSlip> getAll(int offSet, int limit) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT `PackingSlips`.`PackingSlipID`,\n" +
                "    `PackingSlips`.`OrderID`,\n" +
                "    `PackingSlips`.`ShipName`,\n" +
                "    `PackingSlips`.`ShipAddress`,\n" +
                "    `PackingSlips`.`ShipCity`,\n" +
                "    `PackingSlips`.`ShipRegion`,\n" +
                "    `PackingSlips`.`ShipPostalCode`,\n" +
                "    `PackingSlips`.`ShipCountry`,\n" +
                "    `PackingSlips`.`Version`\n" +
                "FROM `shipping-db`.`PackingSlips`\n" +
                "LIMIT :offset, :limit";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("offset", offSet)
                .addValue("limit", limit);

        List<PackingSlip> packingSlips = db.query(sql, params, PackingSlipRowMapper);
        return packingSlips;
    }

    @Override
    public PackingSlip save(PackingSlip entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

}
