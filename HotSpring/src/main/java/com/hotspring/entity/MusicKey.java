package com.hotspring.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MusicKey {

    private Integer musicId;

    private String musicName;

    private Integer keyId;
}
