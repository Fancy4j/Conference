package com.example.controller;

import com.example.api.CommonResult;
import com.example.api.ResultCode;
import com.example.services.FileCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/fileCommon")
@Api(value = "文件接口", description = "提供通用的文件上传功能")
public class FileCommonController {

    @Value("${fileAccessUrlPrefix}")
    private String fileAccessUrlPrefix;

    @Value("${localPathPrefix}")
    private String localPathPrefix;

    @Autowired
    private FileCommonService fileCommonService;

    @PostMapping(value = "/uploadV2", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "多文件上传", notes = "lbf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "文件类型(1-图片|other-其他)",
                    required = true, paramType = "query", dataType = "String")
    })
    public CommonResult fileUploadV2(@RequestPart(value = "files") MultipartFile[] files,
                                     @RequestParam(value = "fileType") String fileType){
        try {
            return fileCommonService.uploadV2(files, fileType, fileAccessUrlPrefix,localPathPrefix);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("fileUploadV2接口异常，入参1：{},入参2：{}，异常信息{}",files,fileType,e.getMessage());
            return CommonResult.failed("文件上传失败");
        }
    }
}

