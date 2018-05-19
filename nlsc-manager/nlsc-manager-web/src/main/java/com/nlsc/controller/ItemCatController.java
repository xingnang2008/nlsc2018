package com.nlsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlsc.common.pojo.EasyUiTreenoteResult;
import com.nlsc.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	
	/*
	 *查询分类信息 
	 */
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUiTreenoteResult> getItemCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		List<EasyUiTreenoteResult> list = itemCatService.getCatList(parentId);
		
		return list;
		
	}
	
	
}
