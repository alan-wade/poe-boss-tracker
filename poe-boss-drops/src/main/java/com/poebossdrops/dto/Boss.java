package com.poebossdrops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boss {
    private UUID bossId;
    private String bossName;
    private League league;
    private List<Item> drops;
}
