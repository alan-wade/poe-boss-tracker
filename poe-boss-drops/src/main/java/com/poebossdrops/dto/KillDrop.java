package com.poebossdrops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KillDrop {
    private UUID itemId;
    private Float itemValue;
    private int count;
}
