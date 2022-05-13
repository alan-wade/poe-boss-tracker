package com.poebossdrops.drops;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DropRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

}
