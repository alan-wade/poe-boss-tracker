package com.poebossdrops.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class KillLog {
    @JsonIgnore
    private UUID loggedKillId;
    private UUID bossId;
    private List<KillDrop> drops;
}
