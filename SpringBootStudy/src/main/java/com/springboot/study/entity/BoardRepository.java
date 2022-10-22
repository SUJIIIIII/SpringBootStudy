package com.springboot.study.entity;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

// 엔티티 클래스의 타입(Board)과 PK에 해당하는 데이터 타입(Long)을 선언하면
// 해당 엔티티 클래스와 매핑되는 테이블인 board 테이블의 CRUD 기능을 사용할 수 있다.
public interface BoardRepository extends JpaRepository<Board, Long> {
	// 게시글 조회 - 삭제된 데이터 제외
	List<Board> findAllByDeleteYN(final char deleteYN, final Sort sort);
}
