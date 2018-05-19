package com.nlsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlsc.common.pojo.EasyUiDataResult;
import com.nlsc.common.utils.TaotaoResult;
import com.nlsc.pojo.TbItemParam;
import com.nlsc.service.ItemParamService;
@RequestMapping("/item/param")
@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	private TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
		
		TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
		
		return result;
	}
	@RequestMapping("/save/{cid}")
	@ResponseBody
	private TaotaoResult insertItemParam(@PathVariable Long cid,String paramData){
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		
		return result;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EasyUiDataResult getItemParamlist(Integer page,Integer rows){
		EasyUiDataResult result = itemParamService.getItemParamList(page, rows);
		return result;
		
	}
}
