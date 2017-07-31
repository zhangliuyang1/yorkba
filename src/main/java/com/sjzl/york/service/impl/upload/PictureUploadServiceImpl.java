package com.sjzl.york.service.impl.upload;

import com.sjzl.york.core.model.PictureUploadResult;
import com.sjzl.york.service.upload.IPictureUploadService;
import com.sjzl.york.util.upload.ContentTypeExtentionResolver;
import com.sjzl.york.util.ExceptionPrintUtil;
import com.sjzl.york.util.GUIDUtil;
import com.sjzl.york.util.upload.OssUploadUtil;
import net.sf.javavp8decoder.imageio.WebPImageReader;
import net.sf.javavp8decoder.imageio.WebPImageReaderSpi;
import org.apache.log4j.Logger;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.io.*;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/3 16:20
 */
@Service("pictureUploadService")
public class PictureUploadServiceImpl implements IPictureUploadService {

    private final static Logger logger = Logger.getLogger(PictureUploadServiceImpl.class);

    @Autowired
    private OssUploadUtil ossUploadUtil;
    @Value("${application.parameter.sys.user.endpoint}")
    private String endPoint;
    @Value("${application.parameter.sys.user.bucket.name}")
    private String bucketName;
    @Value("${application.parameter.sys.user.bucket.photos.directory}")
    private String workDirectory;
    @Value("${application.parameter.sys.user.getpoint}")
    private String getEndPoint;


    @Override
    public PictureUploadResult uploadImageIncludeWebp(InputStream inputStream, Long contentLen) throws IOException {
        PictureUploadResult result = null;
        String url = "";
        String md5 = "";
        Integer width = 0;
        Integer height = 0;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Info info = null;
        String imgType = "";
        ImageInputStream imageInputStream = null;

        if (!inputStream.markSupported()) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            inputStream.close();
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

        inputStream.mark(contentLen.intValue());
        try {
            //im4java(ImageMagick)处理jpg，png，gif
            info = new Info("-", inputStream);
            //图片格式
            imgType = info.getImageFormat();
            width = info.getImageWidth();
            height = info.getImageHeight();
            if (imgType.contains("GIF")) {
                //由于默认的info.getImageWidth()获取的是gif图最后一帧的宽度，导致此宽度不一定是此gif图的真实宽度；
                // 所以我们循环每一帧的宽度，获取最大的一个。
                for (int i = 0; i < 10000; i++) {
                    try {
                        int w = info.getImageWidth(i);
                        int h = info.getImageHeight(i);
                        if (w > width) {
                            width = w;
                        }
                        if (h > height) {
                            height = h;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }

            }
        } catch (InfoException e) {
            logger.info("该文件格式im4java处理不了");
            logger.info(ExceptionPrintUtil.printStackTrace(e));

            /*try {
                imageInputStream = new FileImageInputStream(file);
                WebPImageReaderSpi spi = new WebPImageReaderSpi();
                WebPImageReader reader = new WebPImageReader(spi);
                reader.setInput(imageInputStream);
                width = reader.getWidth(0);
                height = reader.getHeight(0);
                imgType = reader.getFormatName();
            } catch (IOException e1) {
                logger.info(ExceptionPrintUtil.printStackTrace(e1));
            } finally {
                imageInputStream.close();//关闭输入流才能删除临时文件
            }*/
        } finally {
            /*if (file != null) {
                file.delete();
            }*/

        }


        inputStream.reset();
        String key = workDirectory + "/" + GUIDUtil.normalGUID()
                + ContentTypeExtentionResolver.resolve(imgType);
        md5 = ossUploadUtil.uploadFile(bucketName, key,
                inputStream, ContentTypeExtentionResolver.backResolve(imgType), contentLen);
        url = getEndPoint + key;
        result = new PictureUploadResult(url, md5, width, height);
        return result;
    }
}
