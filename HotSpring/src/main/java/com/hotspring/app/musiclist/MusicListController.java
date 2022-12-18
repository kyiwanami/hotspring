package com.hotspring.app.musiclist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotspring.Constants.myLank;
import com.hotspring.domain.service.KeyService;
import com.hotspring.domain.service.MusicService;
import com.hotspring.entity.MKey;

@Controller
public class MusicListController {
	
	@Autowired
	MusicService musicService;
	
	@Autowired
	KeyService keyService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(1000);
	}
	
	@ModelAttribute("keyList") 
	public List<MKey> keyList() {
		List<MKey> keyList = keyService.selectKey();
		keyList.add(0,new MKey());
		return keyList;
	}
	
	@ModelAttribute("myLankList") 
	public List<myLank> myLankList() {
		List<myLank> lankList = new ArrayList<>(Arrays.asList(myLank.values()));
		lankList.add(0, null);
		return lankList;
	}
	
	@GetMapping(value = "/musiclist")
	public String init(Model model) {
		
		MusicListForm musicListForm = new MusicListForm();
		List<MusicForm> musicList = musicService.doProcessInit();
		musicListForm.setMusicList(musicList);  
		model.addAttribute("form", musicListForm);
	
		return "musiclist/musiclist";
	}
	
	@PostMapping(value = "/musiclist")
	public String register(Model model, @ModelAttribute("form") MusicListForm musicListForm) {

		musicService.doRegister(musicListForm.getMusicList());
		
		return "musiclist/musiclist";
	}
}
