package com.itheima.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.deploy.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.exception.MessageException;
import com.itheima.springmvc.pojo.Items;
import com.itheima.springmvc.service.ItemService;

/**
 * 商品管理
 * 
 * @author lx
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	//入门程序 第一   包类 + 类包 + 方法名
	@RequestMapping(value = "/item/itemlist.action")
	public ModelAndView itemList() throws MessageException{
//		int i = 1/0;
		List<Items> list = itemService.selectItemList();
//		if(null == null){
//			throw new MessageException("商品信息不能为空！");
//		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemList", list);
		mav.setViewName("itemList");
		return mav;
	}
	
	@RequestMapping(value="/itemEdit.action")
	public ModelAndView toEdit(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		String id = request.getParameter("id");
		Items item = itemService.selectItemById(Integer.parseInt(id));
		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.setViewName("editItem");
		return mav;
	}
	
	@RequestMapping(value="/updateitem.action")
	public String updateitem(Items items,Model model){
		
		itemService.updateItems(items);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("success");
//		model.addAllAttributes(items);     model携带数据跳转
		return "redirect:/item/itemlist.action";
	}
	
	//删除
	@RequestMapping(value="/deletes.action")
	public ModelAndView deletes(Integer[] ids){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		return mav;
	}
	
	//ajax发送数据
	@RequestMapping(value="/json.action")
	public @ResponseBody Items json(@RequestBody Items items){
		return items;
	}
	
	//用户登录拦截
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(String username,HttpSession session){
		session.setAttribute("USER_SESSION",username);
		return "redirect:/item/itemlist.action";
	}
}
