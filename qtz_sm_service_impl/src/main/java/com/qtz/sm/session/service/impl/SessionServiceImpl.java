package com.qtz.sm.session.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mall.core.common.Constants;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;
import com.qtz.sm.session.dao.DdmSessionDao;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;


@Service("sessionServiceImpl")
public class SessionServiceImpl implements SessionService {
	@Resource(name="ddmSessionAppDaoImpl")
	private DdmSessionDao appDao;

	@Override
	public User getSessionUser(String sid) throws ServiceException {
		User user = null;
		if (!StringUtils.isEmpty(sid)) {
			DdmSession session = appDao.getSession(sid);
			if(null!=session) {
				Object userObj = session.getSessionObject(Constants.SESSION_USER);
				if (userObj instanceof User) {
					return (User) userObj;
				}
			}
		}
		return user;
	}
	
	@Override
	public DdmSession getAppSession(String sid) throws ServiceException{
		DdmSession sesion = appDao.getSession(sid);
		//if(null == sesion) sesion = newAppSession();
		return sesion;
	}
	@Override
	public void saveAppSession(DdmSession s) throws ServiceException{
		s.setLastOperaTime(new Date().getTime());
		appDao.saveSession(s);
	}
	@Override
	public DdmSession newAppSession() throws ServiceException{
		DdmSession sesion = new DdmSession();
		saveAppSession(sesion);
		return sesion;
	}
	@Override
	public boolean removeAppSession(String sid) throws ServiceException{
		return appDao.removeSession(sid);
	}
	@Override
	public List<DdmSession> getAPPAllSession() throws ServiceException {
		return appDao.getAPPAllSession();
	}
	@Override
	public void saveWaitTime(String sid, int minuits) throws ServiceException {
		
	}
	@Override
	public void saveAppSession(DdmSession s, int minute)
			throws ServiceException {
		
	}
	@Override
	public void refreshUser(Long userID,String sid) throws ServiceException {
		
	}
	

}
