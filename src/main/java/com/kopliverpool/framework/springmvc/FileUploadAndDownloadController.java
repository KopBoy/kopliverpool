package com.kopliverpool.framework.springmvc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kopliverpool.framework.core.model.UploadFile;

/** 
 *
 * Description: 上传文件
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月2日	 KOP          1.0       1.0 Version 
 * </pre>
 */
@Controller(value = "/uploadanddownload")
public class FileUploadAndDownloadController {

	public static final String BASIC_PATH = "D:/KOPLIVERPOOL";
	
	 /**
	 * Description: 单文件上传
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年7月20日 上午11:17:31
	 */
	@RequestMapping(params="method=oneUpload", method = RequestMethod.POST)
	public String handleFormUpload(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload";
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
		if (!file.isEmpty()) {
			try {
				String realName = this.copyFile(file.getInputStream(), dir, file.getOriginalFilename());
				UploadFile u = new UploadFile();
				u.setRealName(realName);
				u.setName(name);
				uploadFiles.add(u);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("uploadFiles", uploadFiles);
		return "success";
	}

	 /**
	 * Description: 多文件上传
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年7月20日 上午11:17:42
	 */
	@RequestMapping(value = "/multipartFileUpload", method = RequestMethod.POST)
	public String handleMultiUpload(MultipartHttpServletRequest request, @RequestParam("name") String name // 页面上的控件值
	) {
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
		List<MultipartFile> files = request.getFiles("file");
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload";
		for (int i = 0; i < files.size(); i++) {
			if (!files.get(i).isEmpty()) {
				try {
					String realName = this.copyFile(files.get(i).getInputStream(), dir, files.get(i).getOriginalFilename());
					UploadFile u = new UploadFile();
					u.setRealName(realName);
					u.setName(files.get(i).getOriginalFilename());
					uploadFiles.add(u);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("uploadFiles", uploadFiles);
		return "success";
	}

	 /**
	 * Description: 文件下载
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年7月20日 上午11:17:53
	 */
	@RequestMapping("/download/{realName}/{name}")
	public void handleDownload(HttpServletResponse response, HttpServletRequest request, @PathVariable String realName, @PathVariable String name) {
		OutputStream os = null;
		response.reset();
		realName = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + realName;
		response.setHeader("Content-Disposition", "attachment; filename=" + name);
		response.setContentType("application/octet-stream; charset=utf-8");
		try {
			os = response.getOutputStream();
			os.write(FileUtils.readFileToByteArray(new File(realName)));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	 /**
	 * Description: 写文件到指定目录
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年7月20日 上午11:18:11
	 */
	private String copyFile(InputStream in, String dir, String fileName) throws IOException {
		fileName = fileName.substring(fileName.lastIndexOf("."));
		String realName = UUID.randomUUID().toString() + fileName;
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}

}
