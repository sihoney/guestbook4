package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/guest")
public class GuestController {
	
	///////////////////////
	// field
	///////////////////////
	@Autowired
	private GuestbookDao guestDao;
	
	///////////////////////
	// constructor
	///////////////////////
	
	
	///////////////////////
	// method
	/////////////////////// 
	@RequestMapping(value="addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("/guest/addList");
		
		List<GuestbookVo> guestList = guestDao.getList();
		
		model.addAttribute("guestList", guestList);
		
		return "addList";
	}
	
	@RequestMapping(value="deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@RequestParam int no, Model model) {
		System.out.println("/guest/deleteForm");
		
		model.addAttribute("no", no);
		
		return "deleteForm";
	}
	
	
	@RequestMapping(value="delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo gvo) {
		System.out.println("/gueset/delete");
		
		System.out.println(gvo);
		
		guestDao.delete(gvo);
		
		return "redirect:/guest/addList";
	}
	
	
	@RequestMapping(value="insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestbookVo gvo) {
		System.out.println("/guest/insert");
		
		guestDao.addGuest(gvo);
		
		return "redirect:/guest/addList";
	}
	
}
