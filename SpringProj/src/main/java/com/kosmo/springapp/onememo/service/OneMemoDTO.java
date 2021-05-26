package com.kosmo.springapp.onememo.service;

import java.util.List;

import lombok.Data;

@Data
public class OneMemoDTO {
	private String no;
	private String title;
	private String content;
	private java.sql.Date postDate;
	private String id;
	private String name;//이름 출력용
	//각 글에 따른 댓글 총수 출력용
	private String commentCount;	
	//마이바티스의 ResultMap 태그의 collection태그 적용용
	List<LineCommentDTO> comments;
}
