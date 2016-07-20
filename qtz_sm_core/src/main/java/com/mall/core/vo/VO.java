package com.mall.core.vo;
import java.io.Serializable;

/**
 * <p>Title:VO</p>
 * <p>Description:VO</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-30
 */

public class VO<PK extends Serializable> implements Serializable {
	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = -4443871748239452071L;

	/**业务ID*/
	protected PK dmId;

	public PK getDmId() {
		return dmId;
	}

	public void setDmId(PK dmId) {
		this.dmId = dmId;
	}

	public static enum Status{
		NO(1),OK(0);
		private int value;
		
		private Status(int value) {
			this.value = value;
		}

		public int value() {
			return value;
		}
	}

}