package com.kosmo.springapp.onememo.service;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListPagingData {

	private List lists;
	private int TotalRecordCount;
	private int pageSize;
	private int blockSize;
	private int nowPage;
	private String pagingString;
	
}//////