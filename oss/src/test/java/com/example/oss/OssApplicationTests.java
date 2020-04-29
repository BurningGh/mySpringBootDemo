package com.example.oss;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author lz
 * @date 2018/7/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OssApplication.class)
public class OssApplicationTests {
    @Autowired
    private OSSClient ossClient;


    @Test
    public void contextLoads() {

        final String bucketName = "faceweb";// oss上的容器名称
        final String fileKey = "image/ceshi.jpg";// 文件名
        final String localFile = "D:\\bd9bcf7d6b8017664d821691feb80e51.jpeg";// 本地的文件
        final String downloadFile = "D:\\demo.jpg";// 下载到本地的文件路径
        // 上传
        this.ossClient.putObject(bucketName, fileKey, new File(localFile));
        // 下载
        final InputStream inputStream = this.ossClient.getObject(bucketName, fileKey).getObjectContent();
        try {
            StreamUtils.copy(inputStream, new FileOutputStream(downloadFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取图片地址
        // String url = this.ossClient.generatePresignedUrl(bucketName, fileKey, new
        // Date(System.currentTimeMillis() + (1000 * 30)), HttpMethod.GET).toString();

        // System.out.println(url);
        // 删除
        this.ossClient.deleteObject(bucketName, fileKey);

        ossClient.shutdown();

    }
}
