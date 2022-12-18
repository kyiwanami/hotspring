package com.hotspring.app.key;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotspring.domain.service.KeyService;
import com.hotspring.domain.service.MusicService;
import com.hotspring.entity.MKey;
import com.hotspring.entity.MusicKey;

@Controller
public class KeyListController {
	
	@Autowired
	KeyService keyService;
	
	@Autowired
	MusicService musicService;

	@GetMapping(value = "/keylist")
	public String init(Model model) {
		
		List<MKey> keyList = keyService.selectKey();
		
		//MusicKey取得
		List<MusicKey> musicKeyList =  musicService.selectMusicKey();
		
		//Map生成
		Map<String, List<MusicKey>> musicMap = new LinkedHashMap<>();
		
		for(MKey key : keyList) {
			
			List<MusicKey> filteredList = musicKeyList.stream()
			.filter(musicKey -> musicKey.getKeyId() == key.getKeyId())
			.toList();
			
			//keyごとに楽曲をグルーピングしてmapに追加
			musicMap.put(key.getKeyName(), filteredList);
		}
		
		model.addAttribute("musicKeyMap", musicMap);
				
				
		return "keylist/keylist";
	}
	
}
