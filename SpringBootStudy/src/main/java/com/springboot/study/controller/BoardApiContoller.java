package com.springboot.study.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.dto.BoardRequestDto;
import com.springboot.study.dto.BoardResponseDto;
import com.springboot.study.model.BoardService;

import lombok.RequiredArgsConstructor;

// 컨트롤러 클래스 하위 메서드에  @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있습니다.
@RestController
// 특정 uri로 요청을 보내면 Controller에서 어떠한 방식으로 처리할지 정의를 한다. 이때 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것.
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiContoller {
	private final BoardService boardService;
	
	// 게시글 생성
	// 주어진 URI 표현식과 일치하는 HTTP POST 요청을 처리
	@PostMapping("/boards")
	public Long save(@RequestBody final BoardRequestDto params) {
		return boardService.save(params);
	}
	
	// 게시글 리스트
	@GetMapping("/boards")
	public List<BoardResponseDto> findAll(@RequestParam final char deleteYN){
		return boardService.findAllByDeleteYN(deleteYN);
	}
	
	// 게시글 수정
	@PatchMapping("/boards/{id}")
	// @PathVariable: 매핑의 URL에 { } 로 들어가는 패스 변수(path variable)를 받는다.
	// @RequestBody: HTTP 요청의 body 내용을 자바 객체로 매핑하는 역할
	public Long update(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
		return boardService.update(id, params);
	}
	
	// 게시글 삭제
	@DeleteMapping("/boards/{id}")
	public Long delete(@PathVariable final Long id) {
		return boardService.delete(id);
	}
	
	// 게시글 삭제
	@GetMapping("/boards/{id}")
	public BoardResponseDto findById(@PathVariable final Long id) {
		return boardService.findById(id);
	}
}
