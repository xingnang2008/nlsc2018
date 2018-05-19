package com.nlsc.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nlsc.common.utils.FtpUtil;
import com.nlsc.common.utils.IDUtils;
import com.nlsc.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRSSS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMG_BASE_URL}")
	private String IMG_BASE_URL;
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap();
		try{
			//生成一个新的文件名
			//取原文件的名字
			String oldName = uploadFile.getOriginalFilename();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			String filePath =new DateTime().toString("/yyyy/MM/dd");
			InputStream input = uploadFile.getInputStream();
			boolean result =	FtpUtil.uploadFile(FTP_ADDRSSS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, filePath, newName, input);
				//返回结果
			if(!result){
				resultMap.put("erro", 1);
				resultMap.put("message", "文件上传失败！");
				
				return resultMap;
			}else{
				resultMap.put("erro", 0);
				resultMap.put("url", IMG_BASE_URL+filePath+"/"+newName);
				
				return resultMap;
				
			}
			
		}catch(Exception e){
			resultMap.put("erro", 2);
			resultMap.put("message", "文件上传发生异常！");
			
			return resultMap;
		}
	}

}
