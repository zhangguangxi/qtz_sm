package com.qtz.sm.session.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.common.RedisUtils;
import com.mall.core.dao.model.RedisGroupKey;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;
import com.qtz.sm.session.dao.DdmSessionDao;

import cache.BaseProperties;

@Repository("ddmSessionAppDaoImpl")
public class DdmSessionAppDaoImpl implements DdmSessionDao {
	private static final String APP_SESSION_GROUP=RedisGroupKey.amf_user_app_session;
	
	private static long FAILURE_Time=0l;
	
	private long getFailureTime(){
		
		if(FAILURE_Time == 0) {
			FAILURE_Time = Long.valueOf(BaseProperties.getBaseProperties("APP_FAILURE_Time"));
		}
		return FAILURE_Time;
	}
	
	@Override
	public DdmSession getSession(String sid) throws ServiceException {
		
		Object  obj =  RedisUtils.getValue(sid, APP_SESSION_GROUP);
		if(obj instanceof DdmSession) {
			DdmSession session = (DdmSession) obj;
			return session;
		}
		return null;
	}
	@Override
	public void saveSession(DdmSession s) throws ServiceException {
		RedisUtils.setValue(s.getId(), APP_SESSION_GROUP, s,getFailureTime());
	}

	@Override
	public boolean removeSession(String sid) throws ServiceException {
		RedisUtils.removeValue(sid, APP_SESSION_GROUP);
		return true;
	}


	@Override
	public List<DdmSession> getAPPAllSession() throws ServiceException {
		List<Serializable> values = RedisUtils.getValues(APP_SESSION_GROUP);
		if (values==null || values.isEmpty()) {
			return new ArrayList<DdmSession>();
		}
		List<DdmSession> DdmSessions=new ArrayList<DdmSession>();
		Iterator<Serializable> it = values.iterator();
		 while (it.hasNext()) {
			 Object next = (Object)it.next();
			 if(next instanceof DdmSession){
				 DdmSessions.add((DdmSession) next);
			 }
		 }
		return DdmSessions;
	}


	@Override
	public void saveSession(DdmSession s, int minute) throws ServiceException {
		RedisUtils.setValue(s.getId(), APP_SESSION_GROUP, s,minute);
		
	}


	

}
