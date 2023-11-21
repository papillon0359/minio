//借助API完成MINIO图片或者json文件上传到云端
package com.example.minio.demo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

public class FileUploader {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException{
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = MinioClient.builder().endpoint("http://192.168.170.1:9000").credentials("myname", "mypassword").build();

            // 检查存储桶是否已经存在
            String bucketName = "testminio1";
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object("1.jpg")
                            .filename("C:\\Users\\23858\\Desktop\\1.jpg")
                            .build()
            );

            System.out.println("文件上传成功");
        }catch(MinioException e){
            System.out.println("Error occured: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
