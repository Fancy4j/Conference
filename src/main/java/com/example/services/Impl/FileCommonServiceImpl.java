package com.example.services.Impl;

import com.example.api.CommonResult;
import com.example.services.FileCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Slf4j
public class FileCommonServiceImpl<T> implements FileCommonService<T> {

    public static final long MAX_FILE_SIZE_200KB = 200 * 1024;
    public static final long MAX_FILE_SIZE_1MB = 1024 * 1024;

    @Override
    public CommonResult uploadV2(MultipartFile[] files, String fileType,String fileAccessUrlPrefix, String localPathPrefix) {
        if (files == null || files.length <= 0) {
            return CommonResult.failed( "请选择上传的文件");
        }

        if (StringUtils.isEmpty(fileType)) {
            return CommonResult.failed( "请选择文件类型");
        }

        List<Map<String, String>> list = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        for (MultipartFile file : files) {
            if (file != null) {
                Map<String, String> map = new HashMap<>();
                String fileName = file.getOriginalFilename();
                if (fileName.contains(",")) {
                    return CommonResult.failed( "文件名中不能含有英文逗号");
                }

                // 上传文件格式检查
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!checkFileFormat(fileType, suffix.trim().toLowerCase())) {
                    return CommonResult.failed( "上传文件格式不允许");
                }

                //检查文件大小是否超出限制
                if (checkFileSize(fileType, file)) {
                    return CommonResult.failed( "文件过大");
                }

                // 本地随机文件名
                String localFileName = UUID.randomUUID().toString().replaceAll("-", "");

                String savePath =  localFileName + "-" + fileName;
                String accessPath = fileAccessUrlPrefix + savePath;
                map.put("savePath", savePath);
                map.put("accessPath", accessPath);

                //	File filePath = new File(projectPath + BosConstants.FILE_PREFIX + typeDirectoryName);
                File filePath = new File(localPathPrefix);

                System.out.println("filePath = " + filePath.getAbsolutePath());
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }

                File localFile = new File(localPathPrefix + File.separator + savePath);
                try {
                    file.transferTo(localFile);
                    log.info("bos上传文件，入参为{}，localFile为{}",file,localFile);
                    localFile.setReadable(true, false);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("bos上传文件失败，入参{}，错误信息{}",files,e.getMessage());
                    return CommonResult.failed("文件上传失败");
                }
                list.add(map);
            }
        }
        hashMap.put("list", list);
        return CommonResult.success(hashMap,"上传成功");
    }

    /**
     * 根据文件类型判断是否超过大小限制
     * @param fileType
     * @return true-超出限制 false-未超出限制
     */
    private boolean checkFileSize(String fileType, MultipartFile file) {
        switch (fileType) {
            //图片
            case "1":
                if (file.getSize() > MAX_FILE_SIZE_1MB) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 根据文件类型判断格式是否允许
     * @param fileType 文件类型
     * @param suffix 文件后缀（小写）
     * @return true-允许 false-不允许
     */
    private boolean checkFileFormat(String fileType, String suffix) {
        String suffixList = "jpg,jpeg,png,gif";
        switch (fileType) {
            //图片
            case "1":
                if (suffixList.contains(suffix)) {
                    return true;
                }
                break;
            //其他
            case "other":
                return true;
            default:
                break;
        }

        return false;
    }

}
