package com.sjzl.york.controller.upload;

import com.sjzl.york.common.model.AppSysErrorCode;
import com.sjzl.york.common.model.PictureUploadResult;
import com.sjzl.york.common.model.RequestResult;
import com.sjzl.york.service.ITimeService;
import com.sjzl.york.service.upload.IPictureUploadService;
import com.sjzl.york.util.ExceptionPrintUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/3 16:12
 */
@Controller
public class PictureUploadController {

    private final static Logger logger = Logger.getLogger(PictureUploadController.class);

    @Autowired
    private IPictureUploadService pictureUploadService;
    @Autowired
    private ITimeService timeService;
    /**
     * 上传图片接口
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sys/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult uploadPicture(MultipartHttpServletRequest request)throws Exception{
        RequestResult result = new RequestResult();

        String path = request.getSession().getServletContext().getRealPath("/");
        long timestamp = timeService.getNow().getTime();

        Iterator<String> it = request.getFileNames();
        if (it != null && it.hasNext()) {
            String fileName = (String) it.next();
            MultipartFile imgFile = request.getFile(fileName);
            if (imgFile.getOriginalFilename().length() > 0) {
                Long contentLen = imgFile.getSize();

                String contentType = imgFile.getContentType();
                if (!contentType.contains("image")){
                    result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
                    result.setMessage("该文件不是图片请重新上传");
                    return result;
                }

                File file = new File(path+timestamp);

                InputStream inputStream = null;
                try {

                    inputStream = imgFile.getInputStream();
                    imgFile.transferTo(file);

                    PictureUploadResult uploadResult = pictureUploadService.uploadImageIncludeWebp(inputStream, contentLen, file,"");
                    result.setCode(AppSysErrorCode.SUCCESS.ordinal());
                    result.setData(uploadResult);
                } catch (IOException e) {
                    logger.info(ExceptionPrintUtil.printStackTrace(e));
                    result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
                    result.setMessage("上传失败");

                }finally{

                    try {
                        inputStream.close();
                    } catch (IOException e) {

                    }
                }
            }
        }
        return result;
    }

}
