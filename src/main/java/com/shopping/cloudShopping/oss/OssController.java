package com.shopping.cloudShopping.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static com.shopping.cloudShopping.oss.OssConfig.*;

@Controller
public class OssController {

    @ResponseBody
    @RequestMapping("/conserveImg")
    public String conserveImg(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        byte[] content = file.getBytes();
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content));
        ossClient.shutdown();
        return "https://smy-image.oss-cn-beijing.aliyuncs.com/"+fileName;
    }
}
