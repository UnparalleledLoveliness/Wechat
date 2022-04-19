package com.shopping.cloudShopping.smsCode;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;

import java.util.Random;

public class Sample {
    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAI4GF6HLTPQKJhEEbrWQc8")
                // 您的AccessKey Secret
                .setAccessKeySecret("Ohgp2q0texyEA0eT8rdVttrxPezaPk");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static String sendSms(String phone) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient();
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(phone);
        sendSmsRequest.setSignName("岳麓书信小分队");
        sendSmsRequest.setTemplateCode("SMS_206552238");
        String randomCode = randomCode();
        sendSmsRequest.setTemplateParam("{\"code\":\""+ randomCode +"\"}");
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        return randomCode;
    }

    public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
