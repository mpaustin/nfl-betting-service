package com.ex.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BetDTO {
    private String betUser;
    private String betTeam;
    private float betAmount;
    private String gameId;
    private String favTeam;
    private String dogTeam;
    private float line;
    private String gameDateTime;
    private boolean completed;
}
