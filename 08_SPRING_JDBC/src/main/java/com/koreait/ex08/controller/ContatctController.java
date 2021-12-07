package com.koreait.ex08.controller;

//저장소를 부를것   사용자
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.ex08.domain.Contact;
import com.koreait.ex08.service.ContactService;

@Controller
@RequestMapping(value="contact")
public class ContatctController {
	
	@Autowired
	public ContactService service;
	
	
	@GetMapping(value="findAllContact")
	public String findAllContact(Model model) {
		model.addAttribute("list", service.findAllContact());
		return "contact/list";
	}
	@GetMapping(value="contactPage")
	public String contactPage() {
		return "contact/insert";
	}
	@PostMapping(value="addContact")
	public String addContact(Contact contact) {
		service.addContact(contact);
		return "redirect:findAllContact";		//성공 실패 처리 안함
	}
	
	@GetMapping(value="findContact")
	public String findContact(Contact contact, Model model) {
		model.addAttribute("contact", service.findContact(contact));
		return "contact/detail";
	}
	
	@GetMapping(value="updateContact")
	public String updateContact(Contact contact) {
		service.updateContact(contact);
		return "redirect:findContact?no=" + contact.getNo() ;
	}
	@GetMapping(value="deleteContact")
	public String deleteContact(Contact contact) {
		service.deleteContact(contact);
		return "redirect:findAllContact";
	}
}
