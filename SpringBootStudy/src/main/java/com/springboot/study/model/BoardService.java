package com.springboot.study.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springboot.study.dto.BoardRequestDto;
import com.springboot.study.dto.BoardResponseDto;
import com.springboot.study.entity.Board;
import com.springboot.study.entity.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
// 롬복에서 제공해주는 어노테이션으로, 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어 준다. 예를들면, 
//public BoardService(BoardRepository boardRepository) {
//    this.boardRepository = boardRepository;
//} 이다.
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	// 게시글 생성
	public Long save(final BoardRequestDto params) {
		
		Board entity = boardRepository.save(params.toEntity());
		return entity.getId();
		
	}
	
	// 게시글 리스트 조회
	public List<BoardResponseDto> findAll(){
		Sort sort = Sort.by(Direction.DESC, "id", "regdate");
		List<Board> list = boardRepository.findAll(sort);
		
		// stream은 list 변수에는 게시글 Entity가 담겨 있고,
		// 각각의 Entity를  BoardResponseDto 타입으로 변경(생성)해서 리턴해준다.
		return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
	}
	
	// 게시글 수정
	// 서비스(Service) 클래스에서 필수적으로 사용되어야 하는 어노테이션입니다.
	// 일반적으로 메서드 레벨에 선언하게 되며, 메서드의 실행, 종료, 예외를 기준으로
	// 각각 실행(begin), 종료(commit), 예외(rollback)를 자동으로 처리해 줍니다.
	@Transactional
	public Long update(final Long id, final BoardRequestDto params) {
		// Board Entity 클래스에 update() 메서드에는 update 쿼리를 실행하는 로직이 없다.
		// 하지만, 해당 메서드의 실행이 종료(commit)되면 update 쿼리가 자동으로 실행됩니다.
		// JPA에는 영속성 컨텍스트라는 개념이 있는데, 영속성 컨텍스트란 Entity를 영구히 저장하는 환경이라는 뜻이며,
		// 애플리케이션과 데이터베이스 사이에서 객체를 보관하는 가상의 영역.
		// JPA의 엔티티 매니저(Entity Manager)는 Entity가 생성되거나, Entity를 조회하는 시점에 영속성 컨텍스트에 Entity를 보관 및 관리한다.
		// Entity를 조회하면 해당 Entity는 영속성 컨텍스트에 보관(포함)될 테고, 영속성 컨텍스트에 포함된 Entity 객체의 값이 변경되면,
		// 트랜잭션(Transaction)이 종료(commit)되는 시점에 update 쿼리를 실행한다.
		// 이렇게 자동으로 쿼리가 실행되는 개념을 더티 체킹(Dirty Checking)이라고 하는데,"영속성 컨텍스트에 의해 더티 체킹이 가능해진다".

		Board entity = boardRepository.findById(id).get();
		entity.update(params.getTitle(), params.getContent(), params.getWriter());
		return id;
	}
	
	// 게시글 삭제
	@Transactional
	public Long delete(final Long id) {
		Board entity = boardRepository.findById(id).get();
		entity.delete();
		return id;
	}
	
	// 게시글 상세 조회
	public BoardResponseDto findById(final Long id) {
		Board entity = boardRepository.findById(id).get();
		entity.increaseViews();
		return new BoardResponseDto(entity);
	}
	
	// 게시글 조회 - 삭제글 제외
	public List<BoardResponseDto> findAllByDeleteYN(final char deleteYN){
		Sort sort = Sort.by(Direction.DESC, "id", "regdate");
		List<Board> list = boardRepository.findAllByDeleteYN(deleteYN, sort);
		return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
	}
	
}
