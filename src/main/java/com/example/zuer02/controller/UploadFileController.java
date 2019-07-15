package com.example.zuer02.controller;

import com.example.zuer02.dao.UploadFileDao;
import com.example.zuer02.entity.FileType;
import com.example.zuer02.entity.UploadFileData;
import com.example.zuer02.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
@RestController
@EnableAutoConfiguration
@RequestMapping(value="")
public class UploadFileController {

    @Autowired
    UploadFileDao uploadFileDao;
    @Transactional(rollbackFor = { Exception.class })
    @RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public String uploadFile(@RequestBody MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 判断enctype属性是否为multipart/form-data
        //boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String base64File=FileUtil.encodeBase64File(file);
        UploadFileData uploadFileData=new UploadFileData();
        uploadFileData.setId(UUID.randomUUID().toString());
        uploadFileData.setFileName(request.getParameter("filename"));
        uploadFileData.setFileSize(request.getParameter("filesize"));
        if("image/jpeg".equals(request.getParameter("filetype"))){
           uploadFileData.setFileType(FileType.IMAGE.getCode());
        }
        uploadFileData.setFileContent(base64File);
        int i=uploadFileDao.insertUploadFileData(uploadFileData);
        return "success";
    }



}
