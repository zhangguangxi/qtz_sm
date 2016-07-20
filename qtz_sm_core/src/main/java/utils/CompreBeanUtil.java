/*
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mall.core.vo.VO;

/**
 * CompreBeanUtil
 * 
 * @version 2016年5月28日上午11:12:28
 * @author guangxi.zhang 张光喜 510647180@qqcom
 */

public class CompreBeanUtil {
    /**
     * 
     * ListBean比较(list1,list2都具有相同的父类)
     * @version 2016年5月28日下午2:50:59
     * @author guangxi.zhang
     * @param list1 bean集合1
     * @param list2 bean集合2
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, List<? extends VO>> compareBean(List<? extends VO> list1, List<? extends VO> list2) {
        Map<String, List<? extends VO>> compareResult = new HashMap<String, List<? extends VO>>();
        Map<Serializable, VO> idAndList1 = new HashMap<Serializable, VO>();
        for (VO vo : list1) {
            idAndList1.put(vo.getDmId(), vo);
        }
        Map<Serializable, VO> idAndList2 = new HashMap<Serializable, VO>();
        for (VO vo : list2) {
            idAndList2.put(vo.getDmId(), vo);
        }
        Map<String, List> comparePkResult = comparePK(new ArrayList<Serializable>(idAndList1.keySet()),
                idAndList2.keySet());
                
        compareResult.put("commonList", getBeanByPk(idAndList1, comparePkResult.get("commonList")));
        compareResult.put("onlyList1", getBeanByPk(idAndList1, comparePkResult.get("onlyList1")));
        compareResult.put("onlyList2", getBeanByPk(idAndList2, comparePkResult.get("onlyList2")));
        return compareResult;
    }
    
    /**
     * 
     * 集合主键进行比较
     * @version 2016年5月28日下午2:51:03
     * @author guangxi.zhang
     * @param list1 主键集合1
     * @param list2 主键集合2
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, List> comparePK(Collection<? extends Serializable> list1,
            Collection<? extends Serializable> list2) {
        Map<String, List> compareResult = new HashMap<String, List>();
        List<? extends Serializable> backupList1 = new ArrayList(list1);
        // 获取交集(list1与list2都有的元素)
        list1.retainAll(list2);
        List<? extends Serializable> commonList = new ArrayList(list1);
        // 获取list1中有list2中没有的元素集合
        backupList1.removeAll(commonList);
        List<? extends Serializable> onlyList1 = new ArrayList(backupList1);
        // 获取list2中有list1中没有的元素集合
        list2.removeAll(commonList);
        List<? extends Serializable> onlyList2 = new ArrayList(list2);
        compareResult.put("commonList", commonList);
        compareResult.put("onlyList1", onlyList1);
        compareResult.put("onlyList2", onlyList2);
        return compareResult;
    }
    
    /**
     * 从Map中根据主键集合获取对应bean集合
     * @version 2016年5月28日下午2:51:11
     * @author guangxi.zhang
     * @param idBeanMapping 主键与集合的map映射
     * @param pkList        主键集合
     * @return List<VO>     bean集合
     */
    @SuppressWarnings("rawtypes")
    private static List<VO> getBeanByPk(Map<Serializable, ? extends VO> idBeanMapping, List pkList) {
        List<VO> voList = new ArrayList<VO>();
        for (Object pk : pkList) {
            voList.add(idBeanMapping.get(pk));
        }
        return voList;
    }
}
