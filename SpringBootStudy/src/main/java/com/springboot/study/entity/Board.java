package com.springboot.study.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entity 클래스, 테이블(Table) 또는 레코드(Record) 역할을 하는 데이터베이스

// 해당 클래스에 포함된 멤버 변수의 모든 getter 메서드를 생성.
@Getter
// 해당 클래스의 기본 생성자를 생성해 주는 어노테이션.
// access 속성을 이용해서 동일한 패키지 내의 클래스에서만 객체를 생성할 수 있도록 제어.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 해당 클래스가 테이블과 매핑되는 JPA의 엔티티(Entity) 클래스임을 의미한다.
// 기본적으로 클래스명(Camel Case)을 테이블명(Snake Case)으로 매핑한다.
// 예를 들어, user_role이라는 테이블은 UserRole이라는 이름의 클래스로 네이밍 하면 되고,
// 혹시라도 클래스명과 테이블명이 다를 수밖에 없는 상황에서는 클래스 레벨에 @Table을 선언하고,
// @Table(name = "user_role")과 같이 name 속성을 이용해서 처리하면 된다.
@Entity
public class Board {
	// 해당 멤버가 엔티티(Entity)의 PK임을 의미
    @Id
    // PK 생성 전략을 설정하는 어노테이션. GenerationType.AUTO로 설정하게 되면 DB에서 제공하는 PK 생성 전략을 가져가게 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    private int views; // 조회 수

    private char deleteYN; // 삭제 여부

    private LocalDateTime regdate = LocalDateTime.now(); // 생성일

    private LocalDateTime moddate; // 수정일
    
    // 롬복에서 제공해주는 빌더라는 기능으로, 생성자 대신에 이용하는 패턴.
    @Builder
    public Board(String title, String content, String writer, Integer views, char deleteYN) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.views = views;
        this.deleteYN = deleteYN;
    }
    
    // 게시글 수정
    public void update(String title, String content, String writer) {
    	this.title = title;
    	this.content = content;
    	this.writer = writer;
    	this.moddate = LocalDateTime.now();
    }
    
    // 게시글 조회수 증가
    public void increaseViews() {
    	this.views++;
    }
    
    // 게시글 삭제
    public void delete() {
    	this.deleteYN = 'Y';
    }

}

//@Setter가 없다?
//해당 엔티티 클래스에는 이전 게시판 프로젝트에서 보았던 @Setter가 없는데요.
//앞에서 엔티티 클래스는 테이블 그 자체라는 말씀을 드렸습니다.
//이는 각각의 멤버 변수는 해당 테이블의 컬럼이라는 의미가 되고,
//컬럼에 대한 setter를 무작정 생성하는 경우, 객체의 값이 어느 시점에 변경되었는지 알 수가 없습니다.
//이러한 이유로 Entity 클래스에는 절대로 Set 메서드가 존재해서는 안된답니다.
