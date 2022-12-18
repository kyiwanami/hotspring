package com.hotspring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotspring.Util.BoolUtil;
import com.hotspring.app.musiclist.MusicForm;
import com.hotspring.entity.MMusic;
import com.hotspring.entity.MusicKey;
import com.hotspring.repository.MusicMapper;

@Service
public class MusicService {
	
	@Autowired
	MusicMapper musicMapper;
	
	/**
	 * 楽曲リストを取得.
	 */
	public List<MMusic> selectMusic() {
		return musicMapper.selectMusic();
	}
	
	/**
	 * キーと結合した楽曲リストを取得.
	 */
	public List<MusicKey> selectMusicKey() {
		return musicMapper.selectMusicKey();
	}
	
	public List<MusicForm> doProcessInit() {
		
		//Entity取得
		List<MMusic> musicEntityList =  musicMapper.selectMusic();
	
		// EntityからFormに変換
		List<MusicForm> musicFormList = musicEntityList.stream()
				.map(music -> {
					return MusicForm.builder()
						.musicId(music.getMusicId())
						.musicName(music.getMusicName())
						.key1(music.getKey1())
						.key2(music.getKey2())
						.key3(music.getKey3())
						.key4(music.getKey4())
						.key5(music.getKey5())
						.myLank(music.getMyLank())
						.favoriteFlg(BoolUtil.getFlg(music.getFavoriteFlg()))
						.build();
				}).toList();
		
		return musicFormList;
	}
	
	public void doRegister(List<MusicForm> musicFormList) {
		
		//FormからEntityに変換
		List<MMusic> musicEntityList = musicFormList.stream()
				.map(music -> {
					return MMusic.builder()
						.musicId(music.getMusicId())
						.musicName(music.getMusicName())
						.key1(music.getKey1())
						.key2(music.getKey2())
						.key3(music.getKey3())
						.key4(music.getKey4())
						.key5(music.getKey5())
						.myLank(music.getMyLank())
						.favoriteFlg(BoolUtil.getFlgValue(music.isFavoriteFlg()))
						.build();
				}).toList();
		
		musicMapper.upsert(musicEntityList);
		
	}

	
}
