package com.music.fileupload;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	String artistImageUpload(MultipartFile uploadFile, Integer id);
	
	public boolean fileWriteIntoLocation(MultipartFile file, String oldPath, String newPath, String fileName);

}
