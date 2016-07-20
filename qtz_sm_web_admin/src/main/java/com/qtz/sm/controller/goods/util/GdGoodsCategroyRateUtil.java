package com.qtz.sm.controller.goods.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.qtz.sm.goods.vo.GdGoodsType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 商品分类工具类
 * 
 * @author gyl
 *
 */
public class GdGoodsCategroyRateUtil {
	
    public JSONArray treeMenuList(JSONArray menuList, int parentId) {
        JSONArray childMenu = new JSONArray();
        for (Object object : menuList) {
            JSONObject jsonMenu = JSONObject.fromObject(object);
            int menuId = jsonMenu.getInt("id");
            int pid = jsonMenu.getInt("parentId");
            if (parentId == pid) {
                JSONArray c_node = treeMenuList(menuList, menuId);
                jsonMenu.put("childNode", c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }

    
    public static List<Map<String,Object>> treeGdGoodsCategroyRate(List<Map<String,Object>> gdGoodsTypes,Object parentId){
    	List<Map<String,Object>> gdGoodsType = new ArrayList<Map<String,Object>>();
    	
    	for(Map<String,Object> info:gdGoodsTypes){
    		
//    		System.out.println("11111111=================="+parentId+"|||"+info.get("parentId")+"|||"+(Long.parseLong(parentId.toString())==Long.parseLong(info.get("parentId").toString())));
    		if(Double.parseDouble(parentId.toString())==Double.parseDouble(info.get("parentId").toString())){
    			List<Map<String,Object>> childs = treeGdGoodsCategroyRate(gdGoodsTypes,info.get("dmId"));
    			if(!childs.isEmpty()){
    				info.put("childs", childs);
    			}
        		gdGoodsType.add(info);
    		}
    		
    	}
    	return gdGoodsType;
    }

}
