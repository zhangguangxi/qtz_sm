package com.qtz.sm.controller.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.mall.core.common.DateUtil;
import com.mall.core.common.UUIDFactory;
import com.qtz.sm.config.WebAdminConstants;

/**
 * 阿里云存储
 * <p>Title:UploadOss</p>
 * <p>Description:上传文件到阿里云</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * @author 欧江波 928482427@qq.com
 * @version v1.0 2015年5月21日
 */
public class UploadOssService {
	
	/**
	 * 
	* 【上传图片到阿里云桶3】
	* @param stream		输入流
	* @param fileName	文件名称,如a.gif
	* @return			图片路径，如http://amfimg03.oss-cn-shenzhen.aliyuncs.com/201506/01/a.gif
	* @throws Exception
	 */
	public static String uploadImgFile(String fileName, InputStream stream) throws IOException{
		String accessKeyId= WebAdminConstants.getValueByKeyFromfilePro(WebAdminConstants.Aliyun.ALIYUN_OSS_ACCESS_KEY);
		String accessKeySecret= WebAdminConstants.getValueByKeyFromfilePro(WebAdminConstants.Aliyun.ALIYUN_OSS_ACCESS_KEY_SECRET);
		String endpoint= WebAdminConstants.getValueByKeyFromfilePro(WebAdminConstants.Aliyun.qtzimg02);//http://img03.yw01.com
		String bucketName = WebAdminConstants.getValueByKeyFromfilePro(WebAdminConstants.Aliyun.ALIYUN_OSS_BUCKET_TWO);
		
		//String endpoint="http://oss-cn-shenzhen.aliyuncs.com";
		//String bucketName = "amfimg03";
		//String file_path_pattern = "http://"+bucketName+".oss-cn-shenzhen.aliyuncs.com/%s";
		
		String file_path_pattern = endpoint + "/%s";
		
		//格式："201506/01/a.gif"
		String d = DateUtil.dateToStr(new Date());  
		String yearMonth = d.substring(0, 7).replace("-", "");
		String day = d.substring(8, 10);
		fileName = UUIDFactory.newUUID()+"."+fileName;
		String key = String.format("%s/%s/%s", yearMonth, day, fileName);
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	    ObjectMetadata meta = new ObjectMetadata();
	    int size = stream.available();
	    meta.setContentLength(size);
	    client.putObject(bucketName, key, stream, meta);
	    //图片路径：Bucket.endpoint/key,如http://amfimg03.oss-cn-shenzhen.aliyuncs.com/201506/01/a.gif
	    String filePath = String.format(file_path_pattern, key);
	    return filePath;
	}
}
