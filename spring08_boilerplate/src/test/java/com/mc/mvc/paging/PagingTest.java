package com.mc.mvc.paging;

import org.junit.Test;

import com.mc.mvc.common.paging.Paging;

public class PagingTest {
	
	@Test
	public void testPaging() {
		
		Paging paging = Paging.builder()
				.cntPerPage(3)
				.currentPage(1)
				.total(0)
				.blockCnt(10)
				.build();
		
		System.out.println(paging.getStart());
		System.out.println(paging.getCntPerPage());
		System.out.println(paging.getBlockStart());
		System.out.println(paging.getBlockEnd());
		
	}

}
