package com.poebossdrops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class League {
    private UUID leagueId;
    private String name;
    private String patch;
    private LocalDate startDate;
    private LocalDate endDate;
}
