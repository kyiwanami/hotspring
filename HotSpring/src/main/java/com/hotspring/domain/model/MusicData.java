package com.hotspring.domain.model;

import com.hotspring.Constants.myLank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MusicData {
    private Integer musicId;
    private String musicName;
    private Integer key1;
    private Integer key2;
    private Integer key3;
    private Integer key4;
    private Integer key5;
    private myLank myLank;
    private Integer favoriteFlgValue;
}
