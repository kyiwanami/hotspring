package com.hotspring.app.musiclist;

import lombok.Data;

@Data
public class SearchForm {
	private String musicName;
	private int Key;
	private int lank;
	private boolean favorite;
}
