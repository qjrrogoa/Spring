package com.kosmo.springapp.basic.fileupdown;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;


public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		    *다운로드 요청을 처리하는 다운로드 컨트롤러에서
		    *모델계열(Model,Map,ModelMap)에 저장한 파일객체가
		    *
		    *첫번째 매개변수인 Map에 전달됨.  
		*/
		
		//다운로드 로직 구현]
		FileUpDownUtils.download(request, response, ((File)model.get("file")).getName(), "/upload");
	}

}
