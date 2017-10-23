package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
    TbItem getIteById(Long itemId);
    EUDataGridResult getItemList(int page, int rows); 
    
}
