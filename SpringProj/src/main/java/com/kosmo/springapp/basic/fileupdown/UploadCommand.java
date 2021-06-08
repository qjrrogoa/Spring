package com.kosmo.springapp.basic.fileupdown;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadCommand {

		private String writer;
		private String title;
		private MultipartFile upload; //input type = "file"는 multipartfile타입으로
		
}
