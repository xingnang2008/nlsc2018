package com.nlsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlsc.common.pojo.EasyUiDataResult;
import com.nlsc.common.utils.TaotaoResult;
import com.nlsc.pojo.TbItem;
import com.nlsc.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{ItemId}")
	@ResponseBody
	public TbItem getItemByItemId(@PathVariable Long ItemId){
		TbItem item = itemService.getTbItemById(ItemId);
		return item;
		
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataResult getItemlist(Integer page,Integer rows){
		EasyUiDataResult result = itemService.getItemList(page, rows);
		return result;
		
	}
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
		TaotaoResult result = itemService.createItem(item,desc,itemParams);
		return result;
		
	}
}
