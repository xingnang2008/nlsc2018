package com.nlsc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nlsc.common.pojo.EasyUiDataResult;
import com.nlsc.common.utils.TaotaoResult;
import com.nlsc.mapper.TbItemParamMapper;
import com.nlsc.pojo.TbItem;
import com.nlsc.pojo.TbItemExample;
import com.nlsc.pojo.TbItemParam;
import com.nlsc.pojo.TbItemParamExample;
import com.nlsc.pojo.TbItemParamExample.Criteria;
import com.nlsc.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0 && list != null){
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EasyUiDataResult getItemParamList(Integer page, Integer rows) {
		
		TbItemParamExample example = new TbItemParamExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUiDataResult result = new EasyUiDataResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	

}
