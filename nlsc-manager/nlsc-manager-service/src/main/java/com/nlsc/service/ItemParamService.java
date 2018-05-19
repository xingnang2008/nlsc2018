package com.nlsc.service;

import com.nlsc.common.pojo.EasyUiDataResult;
import com.nlsc.common.utils.TaotaoResult;
import com.nlsc.pojo.TbItemParam;

public interface ItemParamService {

		TaotaoResult getItemParamByCid(Long cid);
		TaotaoResult insertItemParam(TbItemParam itemParam);
		EasyUiDataResult getItemParamList(Integer page,Integer rows);
}
