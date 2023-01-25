package com.mc.mvc.mybatis;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mc.mvc.book.dto.Book;
import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.repository.MemberRepository;
import com.mc.mvc.rent.dto.Rent;
import com.mc.mvc.rent.dto.RentBook;
import com.mc.mvc.rent.dto.RentHistory;

//가상으로 만들어지는 web.xml을 사용해 테스트환경을 구축
@WebAppConfiguration

//JUNIT의 실행방법을 지정
@RunWith(SpringJUnit4ClassRunner.class)

//테스트 때 사용할 가상의 ApplicationContext를 생성하고 관리
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MybatisTest {
	
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private MemberRepository memberRepository;
	
	private final String NAMESPACE = "com.mc.mvc.Myabtis.";
	
	//SqlSessionTemplate 객체의 메서드
	
	//selectOne : 단일 행 반환 받는 select문
	//selectList : 다중 행 반환 받는 select문
	//insert	: insert문 실행. 쿼리에 의해 영향을 받은 row수
	//update	: update문 실행. 쿼리에 의해 영향을 받은 row수
	//delete	: delete문 실행. 쿼리에 의해 영향을 받은 row수

	@Test
	public void testCheckId() {
		System.out.println(memberRepository.selectMemberByUserId("admin"));
	}
	
	@Test
	public void testSelectOne() {
		//com.mc.mvc.Myabtis.selectOne
		session.selectOne(NAMESPACE + "selectOne", "admin");
	}
	
	@Test
	public void testSelectList() {
		List<Member> members = session.selectList(NAMESPACE+"selectList");
		System.out.println(members);
	}
	
	@Test
	public void testSelectListWithDTO() {
		Member member = new Member();
		member.setUserId("admin");
		member.setPassword("1234");
		
		// #{userId} => member.getUserId
		// #{password} => member.getPassword		
		List<Member> members = session.selectList(NAMESPACE+"selectListWithDTO", member);
		System.out.println(members);
	}
	
	@Test
	public void testSelectListWithMap() {
		List<Member> members = session.selectList(NAMESPACE+"selectListWithMap", Map.of("userId", "admin", "password", "1234"));
		System.out.println(members);
	}
	
	@Test
	public void testSelectListToMap() {
		List<Map<String, Object>> commandMaps = session.selectList(NAMESPACE+"selectListToMap");
		System.out.println(commandMaps);
	}
	
	
	@Test
	public void testInsertWithDTO() {
		Member member = new Member();
		member.setUserId("updateDumy");
		member.setPassword("1234");
		member.setTell("010-0000-2222");
		member.setEmail("mb@mb.com");
		
		int rows = session.insert(NAMESPACE+"insertWithDTO", member);
		System.out.println("영향을 받은 행의 개수 : " + rows);
	}
	
	@Test
	public void testInsertWithMap() {
		Map<String, Object> commandMap = new HashMap<>();
		
		Member member = new Member();
		member.setUserId("mybatis");
		
		commandMap.put("member", member);
		commandMap.put("title", "해리포터 외 2권");
		commandMap.put("rentBookCnt", 3);
		
		session.insert(NAMESPACE+"insertWithMap", commandMap);
	}
	
	@Test
	public void insertRentInfo() {
		
		//1. RentMaster에 대출건에대한 정보를 입력
		//2. 새롭게 입력된 RentMaster 테이블의 행을 참조하는 RentBook 정보를 입력, => 새롭게 입력된 RentMaster의 rm_idx
		//3. 새롭게 입력된 RentBook 테이블 행을 참조하는 RentHistory 정보를 입력 => 새롭게 입력된 RentBook의 rb_idx
		
		Rent rent = new Rent();
		rent.setUserId("mybatis");
		rent.setTitle("바깥은 여름 외 0권");
		rent.setRentBookCnt(1);
		
		session.insert(NAMESPACE + "insertRent", rent);
		//System.out.println(rent);
		
		RentBook rentBook = new RentBook();
		rentBook.setRmIdx(rent.getRmIdx());
		rentBook.setBkIdx(42);
		rentBook.setReturnDate(LocalDateTime.now().plusDays(7));
		
		session.insert(NAMESPACE+"insertRentBook", rentBook);
		
		//RentHistory에 대출히스토리 내용을 입력
		// rm_idx, rb_idx, bk_idx, state("RE00")
		RentHistory rentHistory = new RentHistory();
		rentHistory.setRmIdx(rentBook.getRmIdx());
		rentHistory.setRbIdx(rentBook.getRbIdx());
		rentHistory.setBkIdx(rentBook.getBkIdx());
		rentHistory.setState("RE00");
		
		session.insert(NAMESPACE + "insertRentHistory", rentHistory);
	}
	
	@Test
	public void update() {
		Member member = new Member();
		member.setUserId("updateDumy");
		member.setPassword("updatepassword");
		session.update(NAMESPACE+"update", member);
	}
	
	@Test
	public void delete() {
		session.delete(NAMESPACE+"delete", "updateDumy");
	}
	
	@Test
	public void dynamicIf() {
		// 사용자가 도서 검색필터에서 title를 선택하고 검색하면 사용자의 키워드로 title 컬럼을 검색 (where title = #{title})
		// 사용자가 도서 검색필터에서 author를 선택하고 검색하면 author 컬럼을 검색 (where author = #{author})
		List<Book> books = session.selectList(NAMESPACE + "dynamicIf", Map.of("filter", "author", "keyword","김애란"));
		System.out.println(books);
	}
	
	
	@Test
	public void dynamicChoose() {
		// 사용자가 도서 검색필터에서 title를 선택하고 검색하면 사용자의 키워드로 title 컬럼을 검색 (where title = #{title})
		// 사용자가 도서 검색필터에서 author를 선택하고 검색하면 author 컬럼을 검색 (where author = #{author})
		List<Book> books = session.selectList(NAMESPACE + "dynamicChoose", Map.of("filter", "title", "keyword","디디"));
		System.out.println(books);
	}
	
	
	@Test
	public void dynamicSet() {
		// 사용자로부터 수정하고 싶은 항목의 사용자 데이터를 입력받아, 수정하고 싶은 항목의 데이터만 update하는 쿼리
		// admin 사용자는 email과 tell 항목을 수정하고자 한다.
		
		Member member = new Member();
		member.setUserId("admin");
		member.setEmail("zzz@mc.com");
		member.setTell("010-0119-0112");
		
		session.update(NAMESPACE + "dynamicSet", member);
	}
	
	

}
