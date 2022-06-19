package com.example.services;

import com.example.api.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileCommonService<T> {

    CommonResult uploadV2(MultipartFile[] files, String fileType, String fileAccessUrlPrefix, String localPathPrefix);
}
