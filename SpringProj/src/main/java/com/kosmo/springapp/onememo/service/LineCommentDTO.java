package com.kosmo.springapp.onememo.service;

import lombok.Data;

//@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor
@Data
public class LineCommentDTO {

	private String lno;
	private String lineComment;
	private java.sql.Date lpostDate;
	private String id; //댓글을 작성한 사람
	private String no; //댓글이 달릴 글번호
	private String name; //테이블에는 없지만 멤버의 name 출력용

}////LineCommentDTO