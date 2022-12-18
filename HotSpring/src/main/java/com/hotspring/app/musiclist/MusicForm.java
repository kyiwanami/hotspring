package com.hotspring.app.musiclist;

import com.hotspring.Constants.myLank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicForm {
    private Integer musicId;
    private String musicName;
    private Integer key1;
    private Integer key2;
    private Integer key3;
    private Integer key4;
    private Integer key5;
    private myLank myLank;
    private boolean favoriteFlg;
}
