package com.sjzl.york.util.upload;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/3 16:13
 */
@Service("ossUploadUtil")
public class OssUploadUtil {



    private OSSClient client;
    @Value("${application.parameter.sys.accessid}")
    private String accessId;
    @Value("${application.parameter.sys.accesskey}")
    private String accessKey;
    @Value("${application.parameter.sys.user.endpoint}")
    private String endPoint;


    @PostConstruct
    private void initClient(){
        client = new OSSClient(endPoint,accessId, accessKey);
    }

    /**
     * 上传文件,并返回文件的MD5摘要码.
     * 如果制定的key包含文件目录，那么文件将被放到正确的目录下。
     * 如果没有key中指定的目录，那么oss会创建所有层级的目录。
     * 返回上传文件的MD5码.
     *
     * 不能调用ensureBucket方法，该方法会导致bucket访问控制被设置为私有。
     * @param bucketName
     * @param key
     * @param inputStream
     * @param contentType
     * @param contentLen
     * @return
     * @throws OSSException
     * @throws ClientException
     * @throws FileNotFoundException
     */
    public String uploadFile(String bucketName, String key, InputStream inputStream, String contentType, long contentLen)
            throws OSSException, ClientException, FileNotFoundException {

        //ensureBucket(bucketName);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(contentLen);
        // 可以在metadata中标记文件类型
        objectMeta.setContentType(contentType);
        PutObjectResult result = this.client.putObject(bucketName, key, inputStream, objectMeta);
        return result.getETag();
    }

}
