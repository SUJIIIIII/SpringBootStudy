package com.springboot.study.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.study.entity.Board;
import com.springboot.study.entity.BoardRepository;

import java.util.List;

// 스프링 부트는 해당 어노테이션만 선언하면 테스팅이 가능
@SpringBootTest
public class BoardTest {
	// 스프링 컨테이너에 등록된 BoardRepository 객체(Bean)를 주입받는다.
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	void save() {
		// 게시글 파라미터 생성
		Board param = Board.builder()
				.title("글 제목 입니당~")
				.content("글 내용 테스트!")
				.writer("슈루룹")
				.views(0)
				.deleteYN('N')
				.build();
		
		// 게시글 저장
		boardRepository.save(param);
		
		// 게시글 정보 조회
		Board entity = boardRepository.findById((long)2).get();
		assertThat(entity.getTitle()).isEqualTo("글 제목 입니당~");
		assertThat(entity.getContent()).isEqualTo("글 내용 테스트!");
		assertThat(entity.getWriter()).isEqualTo("슈루룹");
	}
	
	@Test
	void findAll() {
		// 전체 게시글 수 조회
		long boardCount = boardRepository.count();
		
		// 전체 게시글 리스트 조회
		List<Board> boardList = boardRepository.findAll();
	}
	
	@Test
	void delete() {
		// 게시글  조회
		Board entity = boardRepository.findById((long)1).get();
				
		// 게시글 삭제
		boardRepository.delete(entity);
	}
}
