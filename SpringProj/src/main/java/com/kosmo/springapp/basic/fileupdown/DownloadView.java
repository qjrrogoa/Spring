package com.kosmo.springapp.basic.fileupdown;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//다운로드 로직 구현]
		FileUpDownUtils.download(request, response,((File)model.get("file")).getName(), "/upload");
	}

}
