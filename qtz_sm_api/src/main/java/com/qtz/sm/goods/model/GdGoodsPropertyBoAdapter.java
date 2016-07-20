package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GdGoodsPropertyBoAdapter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 属性ID
	 */
	private Long propId;
	
	/**
	 * 属性ID名称
	 */
	private String propIdName;
	
	/**
	 * 属性值
	 */
	private List<GdGoodsPropertyValueBo> values = new ArrayList<GdGoodsPropertyValueBo>();
	
	public static GdGoodsPropertyBoAdapter transFrom(List<GdGoodsPropertyBo> boList){
		GdGoodsPropertyBoAdapter adapter = new GdGoodsPropertyBoAdapter();
		if (boList == null || boList.isEmpty()) {
			return adapter;
		}
		List<GdGoodsPropertyValueBo> valueBos = new ArrayList<GdGoodsPropertyValueBo>(); 
		for (GdGoodsPropertyBo bo:boList) {
			adapter.setPropId(bo.getPropId());
			adapter.setPropIdName(bo.getPropIdName());
			
			GdGoodsPropertyValueBo valueBo = new GdGoodsPropertyValueBo();
			valueBo.setDmId(bo.getDmId());
			valueBo.setOtherKey(bo.getOtherKey());
			valueBo.setOtherValue(bo.getOtherValue());
			valueBo.setPropValId(bo.getPropValId());
			valueBo.setPropValName(bo.getPropValName());
			valueBos.add(valueBo);
		}
		adapter.setValues(valueBos);
		return adapter;
	}
	
	public void addValueBo(GdGoodsPropertyValueBo bo){
		this.values.add(bo);
	}
	
	public static class GdGoodsPropertyValueBo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * 属性值主键ID
		 */
		private Long dmId;

		/**
		 * 自定义KEY
		 */
		private String otherKey;

		/***
		 * 属性键ID
		 */
		private Long propValId;
		
		/**
		 * 属性值名称
		 */
		private String propValName;
		
		/**
		 * 其他值
		 */
		private String otherValue;
		
		
		public GdGoodsPropertyValueBo(){
			
		}
		
		public GdGoodsPropertyValueBo(Long propValId, String otherValue) {
			super();
			this.propValId = propValId;
			this.otherValue = otherValue;
		}

		public GdGoodsPropertyValueBo(String otherKey, Long propValId, String propValName, String otherValue) {
			super();
			this.otherKey = otherKey;
			this.propValId = propValId;
			this.propValName = propValName;
			this.otherValue = otherValue;
		}

		public Long getPropValId() {
			return propValId;
		}

		public void setPropValId(Long propValId) {
			this.propValId = propValId;
		}

		public String getPropValName() {
			return propValName;
		}

		public void setPropValName(String propValName) {
			this.propValName = propValName;
		}

		public String getOtherValue() {
			return otherValue;
		}

		public void setOtherValue(String otherValue) {
			this.otherValue = otherValue;
		}

		public String getOtherKey() {
			return otherKey;
		}

		public void setOtherKey(String otherKey) {
			this.otherKey = otherKey;
		}

		public Long getDmId() {
			return dmId;
		}

		public void setDmId(Long dmId) {
			this.dmId = dmId;
		}

		@Override
		public String toString() {
			return "GdGoodsPropertyValueBo [dmId=" + dmId + ", otherKey=" + otherKey + ", propValId=" + propValId
					+ ", propValName=" + propValName + ", otherValue=" + otherValue + "]";
		}

	}
	
	public GdGoodsPropertyBoAdapter() {
		super();
	}

	public GdGoodsPropertyBoAdapter(Long propId, String propIdName) {
		super();
		this.propId = propId;
		this.propIdName = propIdName;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public String getPropIdName() {
		return propIdName;
	}

	public void setPropIdName(String propIdName) {
		this.propIdName = propIdName;
	}

	public List<GdGoodsPropertyValueBo> getValues() {
		return values;
	}

	public void setValues(List<GdGoodsPropertyValueBo> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "GdGoodsPropertyBoAdapter [propId=" + propId + ", propIdName=" + propIdName + ", values=" + values + "]";
	}
}
