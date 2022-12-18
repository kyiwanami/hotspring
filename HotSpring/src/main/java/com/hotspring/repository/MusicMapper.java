package com.hotspring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hotspring.entity.MMusic;
import com.hotspring.entity.MusicKey;

@Mapper
public interface MusicMapper {
	
	/**
	 * M_MUSIC一覧を取得.
	 *
	 */
	@Select("SELECT * FROM M_MUSIC")
    public List<MMusic> selectMusic();
	
	/**
	 * M_MUSIC一覧に追加または更新する.
	 *
	 */
	@Insert({
	 "<script>",
	  "INSERT INTO M_MUSIC ( "
	  + "    MUSIC_ID"
	  + "    , MUSIC_NAME"
	  + "    , KEY1"
	  + "    , KEY2"
	  + "    , KEY3"
	  + "    , KEY4"
	  + "    , KEY5"
	  + "    , MY_LANK"
	  + "    , FAVORITE_FLG"
	  + ") VALUES ",
	  "<foreach collection=\"musicList\" item=\"music\" separator=\",\"> ",
	  "(",
	    "#{music.musicId}",
	    ", #{music.musicName}",
	    ", #{music.key1}",
	    ", #{music.key2}",
	    ", #{music.key3}",
	    ", #{music.key4}",
	    ", #{music.key5}",
	    ", #{music.myLank}",
	    ", #{music.favoriteFlg}",
	  ")",
	  "</foreach>",
	  "ON DUPLICATE KEY UPDATE ",
	    "MUSIC_ID = VALUES(MUSIC_ID), ",
	    "MUSIC_NAME = VALUES(MUSIC_NAME), ",
	    "KEY1 = VALUES(KEY1), ",
	    "KEY2 = VALUES(KEY2), ",
	    "KEY3 = VALUES(KEY3), ",
	    "KEY4 = VALUES(KEY4), ",
	    "KEY5 = VALUES(KEY5), ",
	    "MY_LANK = VALUES(MY_LANK), ",
	    "FAVORITE_FLG = VALUES(FAVORITE_FLG)",
	  "</script>"
	})
	public void upsert(@Param("musicList") List<MMusic> musicList);
	
	/**
	 * M_KEYに紐づくM_MUSIC一覧を取得.
	 *
	 */
	@Select("SELECT"
			+ "    MUSIC_ID"
			+ "    , MUSIC_NAME"
			+ "    , KEY_ID "
			+ "FROM"
			+ "    M_MUSIC "
			+ "    LEFT OUTER JOIN M_KEY "
			+ "        ON M_MUSIC.KEY1 = M_KEY.KEY_ID "
			+ "        OR M_MUSIC.KEY2 = M_KEY.KEY_ID "
			+ "        OR M_MUSIC.KEY3 = M_KEY.KEY_ID "
			+ "        OR M_MUSIC.KEY4 = M_KEY.KEY_ID "
			+ "        OR M_MUSIC.KEY5 = M_KEY.KEY_ID;"
			+ "")
    public List<MusicKey> selectMusicKey();
}
