//package com.moowu.common.util.file;
//
//import com.moowu.common.util.spring.SpringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Minio 文件存储工具类
// */
//@Slf4j
//public class MinioUtil
//{
//    /**
//     * 上传文件
//     *
//     * @param bucketName 桶名称
//     * @param fileName
//     * @throws IOException
//     */
//    public static String uploadFile(String bucketName, String fileName, MultipartFile multipartFile) throws IOException
//    {
//        String url = "";
//        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
//        try (InputStream inputStream = multipartFile.getInputStream())
//        {
//            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(inputStream, multipartFile.getSize(), -1).contentType(multipartFile.getContentType()).build());
//            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(fileName).method(Method.GET).build());
//            url = url.substring(0, url.indexOf('?'));
//            return ServletUtils.urlDecode(url);
//        }
//        catch (Exception e)
//        {
//            throw new IOException(e.getMessage(), e);
//        }
//    }
//    /**
//     * 查看存储bucket是否存在
//     * @return boolean
//     */
//    public static Boolean bucketExists(String bucketName) {
//        Boolean found;
//        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
//        try {
//            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return found;
//    }
//    /**
//     * 创建存储bucket
//     * @return Boolean
//     */
//    public static Boolean makeBucket(String bucketName) {
//        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
//        try {
//            if (!bucketExists(bucketName)) {
//                minioClient.makeBucket(MakeBucketArgs.builder()
//                        .bucket(bucketName)
//                        .build());
//                String policyJson = "{\n" +
//                        "\t\"Version\": \"2012-10-17\",\n" +
//                        "\t\"Statement\": [{\n" +
//                        "\t\t\"Effect\": \"Allow\",\n" +
//                        "\t\t\"Principal\": {\n" +
//                        "\t\t\t\"AWS\": [\"*\"]\n" +
//                        "\t\t},\n" +
//                        "\t\t\"Action\": [\"s3:GetBucketLocation\", \"s3:ListBucket\", \"s3:ListBucketMultipartUploads\"],\n" +
//                        "\t\t\"Resource\": [\"arn:aws:s3:::" + bucketName + "\"]\n" +
//                        "\t}, {\n" +
//                        "\t\t\"Effect\": \"Allow\",\n" +
//                        "\t\t\"Principal\": {\n" +
//                        "\t\t\t\"AWS\": [\"*\"]\n" +
//                        "\t\t},\n" +
//                        "\t\t\"Action\": [\"s3:AbortMultipartUpload\", \"s3:DeleteObject\", \"s3:GetObject\", \"s3:ListMultipartUploadParts\", \"s3:PutObject\"],\n" +
//                        "\t\t\"Resource\": [\"arn:aws:s3:::" + bucketName + "/*\"]\n" +
//                        "\t}]\n" +
//                        "}\n";
//                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(policyJson).build());
//                log.info("buckets：【{}】,创建[readwrite]策略成功！", bucketName);
//            } else {
//                log.info("minio bucket->>>【{}】already exists", bucketName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//}
