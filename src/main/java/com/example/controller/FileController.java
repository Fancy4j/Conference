package com.example.controller;

import com.example.pojo.FileMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("upload")
    @ResponseBody
    public FileMsg uploadPicture(MultipartFile file, HttpServletRequest request){
        final String DIRS = "/WEB-INF/file/";
        FileMsg picMsg = new FileMsg();
        if (file == null){
            return picMsg;
        }
        String realPath = request.getServletContext().getRealPath("");
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        File file2 = new File(realPath+DIRS);
        if (!file2.exists()){
            file2.mkdir();
        }
        String relativePath = DIRS+filename;
        realPath = realPath + relativePath;
        try {
            File file1 = new File(realPath);
            file.transferTo(file1);
            picMsg.setError(0);
            picMsg.setUrl(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picMsg;
    }

    @RequestMapping("download")
    public void downloadFileByFileName(@RequestParam("fileName")String fileName,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {
        fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        String realPath = request.getServletContext().getRealPath("/WEB-INF/file");
        File file = new File(realPath+"/"+fileName);
        if (!file.exists()){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("文件不存在");
            return;
        }
        FileInputStream resourceAsStream = new FileInputStream(file);
        response.reset();
         response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        BufferedInputStream inputStream = new BufferedInputStream(resourceAsStream);
        byte [] bytes = new byte[1024];
        int len = 0;
        while ( (len = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
        }
        outputStream.flush();
        outputStream.close();
        resourceAsStream.close();
        inputStream.close();
    }

}
