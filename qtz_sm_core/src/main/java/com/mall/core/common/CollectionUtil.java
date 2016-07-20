package com.mall.core.common;

import java.util.List;

/**
 * @Description:List、Set集合工具类
 * @author:SunXuan
 * @time:2016年6月28日 上午10:04:09
 */
public class CollectionUtil {

	/**
	 * 拿到第几页多少条数据
	 * 
	 * @Description:TODO
	 * @param list
	 * @param page
	 * @param pnum
	 * @return List<Long>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月28日 上午9:59:12
	 */
	public static List trunList(List list, int nowPage, int pageSize) {
		int start = (nowPage - 1) * pageSize;
		int end = start + pageSize;
		if (list.size() > end)
			return list.subList(start, end);
		else if (list.size() > start)
			return list.subList(start, list.size());
		else
			return list;
	}

	/**
	 * 取出自定义条数记录的list
	 * 
	 * @Description:TODO
	 * @param list
	 * @param start
	 * @param end
	 * @return List
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月28日 上午10:07:52
	 */
	public static List trunListByStart(List list, int start, int end) {
		if (list == null)
			return null;
		else if (list.size() > end)
			return list.subList(start, end);
		else if (list.size() > start)
			return list.subList(start, list.size());
		else
			return list;
	}

	/**
	 * 总页数
	 * 
	 * @Description:TODO
	 * @param total
	 * @param pnum
	 * @return int
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月28日 上午10:08:24
	 */
	public static int totalPage(int total, int pnum) {
		if (total < pnum) {
			return 1;
		} else {
			return (total / pnum + (total % pnum == 0 ? 0 : 1));
		}
	}

	/**
	 * 分页 页码 数组
	 * 
	 * @Description:TODO
	 * @param totalpage
	 * @param pageNo
	 * @param showNum
	 * @return int[]
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月28日 上午10:08:47
	 */
	public static int[] getShowPageNum(int totalpage, int pageNo, int showNum) {
		int[] se = new int[2];
		se[0] = 0;
		se[1] = 0;
		if (totalpage > 0 && pageNo > 0) {
			int tmpNum = pageNo - showNum / 2;
			int start = 1 + tmpNum;
			int end = showNum + tmpNum;

			if (start < 1) {
				end = end + (1 - start);
				start = 1;
				if (end > totalpage) {
					end = totalpage;
				}
			}
			if (end > totalpage) {
				start = start - (end - totalpage);
				end = totalpage;
				if (start < 1) {
					start = 1;
				}
			}
			se[0] = start;
			se[1] = end;
		}
		return se;
	}
}
