package com.newlec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.domain.NoticeBoardVO;
import com.newlec.service.NoticeServiceImpl;

public class NoticeDetailController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NoticeDetailController");
		NoticeBoardVO notice = null;
		NoticeBoardVO nextTitle = null;
		NoticeBoardVO previousTitle = null;
		NoticeServiceImpl noticeServiceImpl = new NoticeServiceImpl();
		
		// 게시글 번호
		int contentNum;
		if(request.getParameter("contentNum") == null) {
			contentNum = 1; // 나중에 에러메세지와 뒤로가기 작동으로 변경
		} else {
			contentNum = Integer.parseInt(request.getParameter("contentNum"));
		}
		System.out.println("contentNum : "+contentNum);
		
		try {
			// 조회수 hit +1
			noticeServiceImpl.noticeHit(contentNum);
//			System.out.println("noticeServiceImpl.noticeDetail 전");
			notice = noticeServiceImpl.noticeDetail(contentNum);
//			System.out.println("noticeServiceImpl.noticeDetail 후");
			
			// 현재 게시글의 rownum 구하기
			int rownum = noticeServiceImpl.noticeCurRownum(contentNum);
			
			// 현재 게시글의 이전, 다음글 제목 검색하기
			nextTitle = noticeServiceImpl.noticeNextTitle(rownum);
			previousTitle = noticeServiceImpl.noticePreviousTitle(rownum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(notice == null) {
			System.out.println("게시글 불러오기 실패");
		} else {
			System.out.println("게시글 불러오기 성공");
		}
		
		System.out.println(notice.toString());
		request.setAttribute("notice", notice);
		
		
		
		if(nextTitle == null) {
			System.out.println("다음 게시글 제목 불러오기 실패");
		} else {
			System.out.println("다음 게시글 제목 불러오기 성공");
		}
		
		System.out.println("nextTitle : "+nextTitle);
		request.setAttribute("nextTitle", nextTitle);

		if(previousTitle == null) {
			System.out.println("이전 게시글 제목 불러오기 실패");
		} else {
			System.out.println("이전 게시글 제목 불러오기 성공");
		}
		
		System.out.println("previousTitle : "+previousTitle);
		request.setAttribute("previousTitle", previousTitle);
		
		

		return "dispatcher:/customer/noticeDetail.jsp";
	}

}
