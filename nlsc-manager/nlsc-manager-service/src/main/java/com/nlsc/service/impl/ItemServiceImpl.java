package com.nlsc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nlsc.common.pojo.EasyUiDataResult;
import com.nlsc.common.utils.IDUtils;
import com.nlsc.common.utils.TaotaoResult;
import com.nlsc.mapper.TbItemDescMapper;
import com.nlsc.mapper.TbItemMapper;
import com.nlsc.mapper.TbItemParamItemMapper;
import com.nlsc.mapper.TbItemParamMapper;
import com.nlsc.pojo.TbItem;
import com.nlsc.pojo.TbItemDesc;
import com.nlsc.pojo.TbItemExample;
import com.nlsc.pojo.TbItemExample.Criteria;
import com.nlsc.pojo.TbItemParam;
import com.nlsc.pojo.TbItemParamItem;
import com.nlsc.service.ItemService;
/*
 * 
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Override
	public TbItem getTbItemById(long ItemId) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(ItemId);
		List<TbItem> list  = itemMapper.selectByExample(example);
		
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public EasyUiDataResult getItemList(int page,int rows){
		
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUiDataResult result = new EasyUiDataResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
		

	}

	@Override
	public TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception {
		//补全item
		//生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态:1正常,2下架,3删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		TaotaoResult result = insertItemDesc(itemId,desc);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		
		 result = insertItemParamItem(itemId,itemParam);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}

	TaotaoResult insertItemDesc(Long itemId,String desc){
		TbItemDesc record= new TbItemDesc();
		record.setItemId(itemId);
		record.setItemDesc(desc);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		
		itemDescMapper.insert(record);
		return TaotaoResult.ok();
		
	}
	TaotaoResult insertItemParamItem(Long itemId,String itemParam){
		TbItemParamItem record= new TbItemParamItem();
		record.setItemId(itemId);
		record.setParamData(itemParam);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		
		itemParamItemMapper.insert(record);
		return TaotaoResult.ok();
		
	}
}
