package com.nlsc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlsc.common.pojo.EasyUiTreenoteResult;
import com.nlsc.mapper.TbItemCatMapper;
import com.nlsc.pojo.TbItemCat;
import com.nlsc.pojo.TbItemCatExample;
import com.nlsc.pojo.TbItemCatExample.Criteria;
import com.nlsc.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EasyUiTreenoteResult> getCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		//创建查询
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list  = itemCatMapper.selectByExample(example);
		List<EasyUiTreenoteResult> rlist = new ArrayList<EasyUiTreenoteResult>();
		for( TbItemCat itemCat:list){
			EasyUiTreenoteResult euTreenoteResult = new EasyUiTreenoteResult();
			euTreenoteResult.setId(itemCat.getId());
			euTreenoteResult.setText(itemCat.getName());
			euTreenoteResult.setState(itemCat.getIsParent()?"closed":"open");
			rlist.add(euTreenoteResult);
			
		}
	
		return rlist;
		
	}

}
