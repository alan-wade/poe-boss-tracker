package com.poebossdrops.drops;

import com.poebossdrops.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DropRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //public List<Item> getDropsByBossUUID(UUID bossId) {
    //}
}
