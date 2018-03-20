package com.itheima.springmvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.springmvc.dao.ItemsMapper;
import com.itheima.springmvc.pojo.Items;

/**
 * 查询商品信息
 * @author zh2
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemsMapper itemsMapper;
	public List<Items> selectItemList(){
		return itemsMapper.selectByExampleWithBLOBs(null);
	}
	public Items selectItemById(Integer id){
		return itemsMapper.selectByPrimaryKey(id);
	}
	
	public void updateItems(Items items){
		items.setCreatetime(new Date());
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}
}
