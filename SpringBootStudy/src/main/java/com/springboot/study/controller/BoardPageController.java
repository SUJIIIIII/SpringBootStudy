package com.springboot.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardPageController {
	
	@GetMapping("/list")
	public String onBoardList() {
		return "board/list";
	}
	
	@GetMapping("/write")
	public String onBoardWrite(@RequestParam(required = false) final Long id, Model model) {
		model.addAttribute("id", id);
		return "board/write";
	}
	
	@GetMapping("/detail/{id}")
	public String onBoardDetail(@PathVariable final Long id, Model model) {
		model.addAttribute("id", id);
		return "board/detail";
	}
}
