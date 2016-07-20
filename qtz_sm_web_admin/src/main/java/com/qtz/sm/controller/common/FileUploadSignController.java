package com.qtz.sm.controller.common;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.qtz.sm.controller.BaseController;

/**
 * 文件上传签名
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping(value = "fileUploadSign/")
public class FileUploadSignController extends BaseController {

	/**
	 * 获取文件上传签名
	 * 
	 * @param sid
	 *            凭证
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getSign", method = RequestMethod.GET)
	public void getSign(@RequestHeader("token") String sid, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String endpoint = "*";
		String accessId = "*";
		String accessKey = "*";
		String bucket = "*";
		String dir = "user-dir";
		String host = "http://" + bucket + "." + endpoint;
		OSSClient client = new OSSClient(endpoint, accessId, accessKey);
		try {
			long expireTime = 30;
			long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
			Date expiration = new Date(expireEndTime);
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

			String postPolicy = client.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = client.calculatePostSignature(postPolicy);

			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("accessid", accessId);
			respMap.put("policy", encodedPolicy);
			respMap.put("signature", postSignature);
			respMap.put("dir", dir);
			respMap.put("host", host);
			respMap.put("expire", String.valueOf(expireEndTime / 1000));
			JSONObject json = new JSONObject();
			json.putAll(respMap);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST");
			response(request, response, json.toString());
		} catch (Exception e) {
			log.error("获取文件上传签名失败");
			RespJsonPHandler.respError(RespMsg.get_file_upload_sign_fail, response, request);
		}
	}

	private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
		String callbackFunName = request.getParameter("callback");
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase("")) {
			response.getWriter().println(results);
		} else {
			response.getWriter().println(callbackFunName + "( " + results + " )");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	}

}
