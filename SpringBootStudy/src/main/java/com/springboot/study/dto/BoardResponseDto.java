package com.springboot.study.dto;

import java.time.LocalDateTime;

import com.springboot.study.entity.Board;

import lombok.Getter;

//Entity 클래스는 테이블(Table) 또는 레코드(Record) 역할을 하는 데이터베이스 그 자체로 생각할 수 있고,
//절대로 요청(Request)이나 응답(Response)에 사용되어서는 안 되기 때문에
//반드시 Request, Response 클래스를 따로 생성(구분)해 주어야 합니다.

@Getter
public class BoardResponseDto {
    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private Integer views; // 조회 수
    private char deleteYN; // 삭제 여부
    private LocalDateTime regdate; // 생성일
    private LocalDateTime moddate; // 수정일
    
    public BoardResponseDto(Board entity) {
    	this.id = entity.getId();
    	this.title = entity.getTitle();
    	this.content = entity.getContent();
    	this.writer = entity.getWriter();
    	this.views = entity.getViews();
    	this.deleteYN = entity.getDeleteYN();
    	this.regdate = entity.getRegdate();
    	this.moddate = entity.getModdate();
    }
}
