package com.sjzl.york.service.upload;

import com.sjzl.york.core.model.PictureUploadResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/3 16:19
 */
public interface IPictureUploadService {

    PictureUploadResult uploadImageIncludeWebp(InputStream inputStream, Long contentLen, File file, String webImageUrl)throws IOException;


}
