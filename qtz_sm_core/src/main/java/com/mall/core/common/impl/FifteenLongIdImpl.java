package com.mall.core.common.impl;

import com.mall.core.common.FifteenLongId;


/**
 * 生成  15位长整形 ID
 * <p>Title:FifteenLongIdImpl</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2014-12-10
 */
public class FifteenLongIdImpl implements FifteenLongId{
	private long workerId;
	private final static long twepoch = 1361753741828L;
	private long sequence = 0L;
	/**机器标识位数*/
	private final static long workerIdBits = 4L;
	/**机器ID最大值*/
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	/**毫秒内自增位*/
	private final static long sequenceBits = 10L;
	/**机器ID偏左移12位*/
	private final static long workerIdShift = sequenceBits;
	/**时间毫秒左移22位*/
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	/**(用一句话描述这个变量表示什么)*/
	public final static long sequenceMask = -1L ^ -1L << sequenceBits;

	private long lastTimestamp = -1L;

	public FifteenLongIdImpl() {
		init(2);
	}
	
	public FifteenLongIdImpl(long workerId) {
		init(workerId);
	}
	
	private void init(long workerId){
		if (workerId > FifteenLongIdImpl.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",FifteenLongIdImpl.maxWorkerId));
		}
		this.workerId = workerId;
	}

	/* (non-Javadoc)
	 * @see com.mall.core.common.FifteenLongId#nextId()
	 */
	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & FifteenLongIdImpl.sequenceMask;
			if (this.sequence == 0) {
				// System.out.println("###########" + sequenceMask);
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(
						String.format(
								"Clock moved backwards.  Refusing to generate id for %d milliseconds",
								this.lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift))
				| (this.workerId << FifteenLongIdImpl.workerIdShift) | (this.sequence);
		// System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
		// + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
		// + workerId + ",sequence:" + sequence);
		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		System.out.println(maxWorkerId);
		FifteenLongIdImpl worker2 = new FifteenLongIdImpl(0);
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println("-----------------------------------------");

		worker2 = new FifteenLongIdImpl(10);
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println("-----------------------------------------");

		worker2 = new FifteenLongIdImpl(5);
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		worker2 = new FifteenLongIdImpl(5);
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println(worker2.nextId());
		System.out.println("-----------------------------------------");
	}
}