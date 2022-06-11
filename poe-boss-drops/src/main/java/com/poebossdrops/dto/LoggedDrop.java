package com.poebossdrops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedDrop {
    private UUID loggedDropId;
    private UUID itemId;
    private UUID loggedKillId;
    private Float itemValue;
}
