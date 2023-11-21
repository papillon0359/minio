//借助API完成MINIO图片或者json文件下载到本地

package com.example.minio.demo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import io.minio.*;
import io.minio.errors.MinioException;

public class DownLoadDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException{
        String bucketName = "testminio1";
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://192.168.170.1:9000")
                        .credentials("myname", "mypassword")
                        .build();

        try{
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object("1.jpg")
                            .filename("C:\\Users\\23858\\Desktop\\2.jpg")
                            .build()
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
