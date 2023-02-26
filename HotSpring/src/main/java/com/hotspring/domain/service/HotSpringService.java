package com.hotspring.domain.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotspring.domain.model.HotSpringEvaluationData;
import com.hotspring.entity.MHotSpringEntity;
import com.hotspring.entity.TEvaluationEntity;
import com.hotspring.repository.MHotSpringRepository;
import com.hotspring.repository.TEvaluationRepository;

@Service
public class HotSpringService {

	@Autowired
	private MHotSpringRepository hotSpringRepository;

	@Autowired
	private TEvaluationRepository evaluationRepository;

	@Autowired
	private AwsService awsService;

	/**
	 * 温泉リストを全件取得.
	 * 
	 * @return 温泉リスト
	 */
	public List<MHotSpringEntity> selectHotspring() {
		return hotSpringRepository.findAll();
	}

	/**
	 * 温泉と口コミを登録する.
	 * 
	 * @throws IOException
	 */
	public HotSpringEvaluationData register(HotSpringEvaluationData data) throws IOException {

		// 温泉の登録
		MHotSpringEntity hotSpringEntity = new MHotSpringEntity();
		BeanUtils.copyProperties(data, hotSpringEntity);
		if (StringUtils.isBlank(data.getImageS3url())) {
			String key = UUID.randomUUID().toString();
			data.setImageS3url(key);
			awsService.putObject(key, data.getImage().getInputStream());
		}
		Integer hotSpringId = hotSpringRepository.save(hotSpringEntity).getHotSpringId();

		TEvaluationEntity evaluationEntity = new TEvaluationEntity();
		BeanUtils.copyProperties(data, evaluationEntity);
		evaluationEntity.setHotSpringId(hotSpringId);
		Integer evaluationId = evaluationRepository.save(evaluationEntity).getEvalationId();

		data.setHotSpringId(hotSpringId);
		data.setEvalationId(evaluationId);

		return data;
	}
}
