package com.justep.weixin.mp;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;

import com.alibaba.fastjson.JSONObject;
import com.justep.baas.data.Util;

public class InterfaceServlet extends HttpServlet {
	WxMpServiceInstance instance = WxMpServiceInstance.getInstance();

	public void service(ServletRequest request, ServletResponse response) throws IOException {
		String action=request.getParameter("action");
		if("mediaUpload".equals(action)){
			try {
				mediaUpload(request,response);
			} catch (WxErrorException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if("mediadownload".equals(action)){
			try {
				mediadownload(request,response);
			} catch (WxErrorException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	private void mediadownload(ServletRequest request, ServletResponse response) throws WxErrorException {
		// TODO 自动生成的方法存根
		JSONObject params=(JSONObject) JSONObject.parse(request.getParameter("params"));
		String mediaid=params.getString("mediaid");
		System.out.println(mediaid);
		instance.download(mediaid);
		
	}

	private void mediaUpload(ServletRequest request, ServletResponse response) throws WxErrorException, IOException {
		// TODO 自动生成的方法存根
		//JSONObject params=(JSONObject) JSONObject.parse(request.getParameter("params"));
		WxMediaUploadResult res =instance.upload();
		JSONObject c =new JSONObject() ;
		System.out.println("res.getType():"+res.getType()+"  res.getCreatedAt():"+res.getCreatedAt()+"  res.getMediaId():"+res.getMediaId()+"  res.getThumbMediaId():"+res.getThumbMediaId());
		c.put("mediaid",res.getMediaId());
		Util.writeJsonToResponse(response, c);
	}
}
