package com.nlsc.service;

import com.nlsc.common.pojo.EasyUiDataResult;
import com.nlsc.common.utils.TaotaoResult;
import com.nlsc.pojo.TbItem;

public interface ItemService {

		TbItem getTbItemById(long ItemId);
		EasyUiDataResult getItemList(int page,int rows);
		TaotaoResult createItem(TbItem item,String desc ,String itemParam) throws Exception;
}
