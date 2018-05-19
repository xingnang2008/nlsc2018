package com.nlsc.service;

import java.util.List;

import com.nlsc.common.pojo.EasyUiTreenoteResult;

public interface ItemCatService {
	List<EasyUiTreenoteResult> getCatList(Long parentId);

}
