package com.mall.core.common.task;

import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 多线程任务调度引擎
 * <p>Title:TaskEngine</p>
 * <p>Description:多线程任务调度引擎</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市爱免费信息科技有限公司</p>
 * @author 李昌波 - lichangbo
 * @version v1.0 2015年5月15日
 */
public class TaskEngine {
	//最高优先级
    public static final int HIGH_PRIORITY = 2;
    //中等优先级
    public static final int MEDIUM_PRIORITY = 1;
    //最低优先级
    public static final int LOW_PRIORITY = 0;
    //任务队列
    private static PriorityQueue taskQueue = null;
    //线程组
    private static ThreadGroup threadGroup = new ThreadGroup("Bar Code Workers");
    //引擎工作员对象数组
    private static TaskEngineWorker workers[];
    // 任务定时载体
    private static Timer taskTimer = null;
    //公用锁对象【具有排它性】
    private static final Object lock = new Object();
    //新工作器时间
    private static long newWorkerTimestamp = System.currentTimeMillis();
    //时间
    private static long busyTimestamp = System.currentTimeMillis();
    //最大线程数
    private static final int MAX_WORKER = 220;
    //最小线程数
    private static final int MIN_WORKER = 50;
    

    static {
        workers = null;
        ////////////////////////////////        
        taskTimer = new Timer(true);       
        ////////////////////////////////////

        taskQueue = new PriorityQueue();
        //项目启动时 先初始化5个工作员
        workers = new TaskEngineWorker[MIN_WORKER];
        for (int i = 0; i < workers.length; i++)
        {
            workers[i] = new TaskEngineWorker("Worker " + i);
            workers[i].setDaemon(true);
            workers[i].start();
        }
    }
    ////////////////////////////////////////////////////////////////
    /*
     * 内部类：引擎工作员对象，继承于多线程类THREAD
     */
    private static class TaskEngineWorker extends Thread
    {
        private boolean done;

        public TaskEngineWorker(String s)
        {
            super(TaskEngine.threadGroup, s);
            done = false;
        }

        public void stopWorker()
        {
            done = true;
        }

        //实现线程类 run  方法
        public void run()
        {
            while (!done)  
                try
                {
                    TaskEngine.nextTask().run();
                }
                catch (NullPointerException e)
                {
                    done = true;
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    done = true;
                }
        }
    }

    /////////////////////////////////////////////////////////
    /*
     * 内部类:计划任务类，继承java自带的TimerTask类
     */
    private static class ScheduledTask extends TimerTask
    {
        private int priority;
        private Runnable task;

        public ScheduledTask(int i, Runnable runnable)
        {
            this.priority = i;
            this.task = runnable;
        }

        public void run()
        {
            TaskEngine.addTask(priority, task);
        }
    }

    //////////////////////////////////////////////////////
    /*
     * 内部类:优先级队列
     */
    private static class PriorityQueue
    {
        private LinkedList<Object> high;
        private LinkedList<Object> medium;
        private LinkedList<Object> low;

        private PriorityQueue()
        {
            high = new LinkedList<Object>();
            medium = new LinkedList<Object>();
            low = new LinkedList<Object>();
        }

        public void enqueue(int i, Object obj)
        {
            if (i > 2)
                i = 2;
            else if (i < 0)
                i = 0;
            switch (i)
            {
                case 2 : // '\002'      
                    high.addFirst(obj);
                    break;

                case 1 : // '\001'
                    medium.addFirst(obj);
                    break;

                case 0 : // '\0'
                    low.addFirst(obj);
                    break;
            }
        }

        public boolean isEmpty()
        {
            return high.isEmpty() && medium.isEmpty() && low.isEmpty();
        }

        public int size()
        {
            return high.size() + medium.size() + low.size();
        }

        public Object dequeue()
        {
            Object obj = null;
            if (!high.isEmpty())
            {
                obj = high.removeLast();
            }
            else if (!medium.isEmpty())
            {
                obj = medium.removeLast();
            }
            else if (!low.isEmpty())
            {
                obj = low.removeLast();
            }
            else
                throw new NoSuchElementException("Queue is empty.");

            if (!low.isEmpty())
            {
                medium.addFirst(low.removeLast());
            }
            if (!medium.isEmpty())
            {
                high.addFirst(medium.removeLast());
            }

            return obj;
        }
    }
    /////////////////////////////////////////////////////////////////
    //构造函数
    private TaskEngine()
    {
    	
    }

    /**
     * 
    * 【增加任务】(实质是增到任务队列中)
    * @param runnable  任务
     */
    public static void addTask(Runnable runnable)
    {
        if (runnable != null)
            addTask(1, runnable);
    }
    /**
     * 
    * 【添加任务】(实质是  i  优先级 增到任务队列中)
    * @param i  优先级
    * @param runnable  任务
     */
    public static void addTask(int i, Runnable runnable)
    {
        if (runnable != null)
        {
            synchronized (lock)
            {
                taskQueue.enqueue(i, runnable);
                lock.notify();
            }
        }
    }

	/**
	 * 
	* 【添加任务】(在指定的date日期执行runnable，只执行一次)
	* @param runnable  任务
	* @param date 执行时间
	* @return
	 */
    public static TimerTask scheduleTask(Runnable runnable, Date date)
    {
        return scheduleTask(1, runnable, date);
    }

	/**
	 * 
	* 【添加任务】(延时delay毫秒后执行runnable，只执行一次)
	* @param runnable 任务
	* @param delay 延时时间  毫秒
	* @return
	 */
    public static TimerTask scheduleTask(Runnable runnable, long delay)
    {
        ScheduledTask scheduledtask = new ScheduledTask(1, runnable);//以优先级1 加入计划任务
        taskTimer.schedule(scheduledtask, delay);
        return scheduledtask;
    }

	/**
	 * 
	* 【添加任务】(延时delay毫秒后，以i优先级执行runnable，只执行一次)
	* @param i  优先级
	* @param runnable  任务
	* @param delay 延时时间  毫秒
	* @return
	 */
    public static TimerTask scheduleTask(int i, Runnable runnable, long delay)
    {
        ScheduledTask scheduledtask = new ScheduledTask(i, runnable);
        taskTimer.schedule(scheduledtask, delay);
        return scheduledtask;
    }

	/**
	 * 
	* 【添加任务】(在指定的date以i优先级来执行runnable，只执行一次)
	* @param i  优先级
	* @param runnable  任务
	* @param date  执行时间  
	* @return
	 */
    public static TimerTask scheduleTask(int i, Runnable runnable, Date date)
    {
        ScheduledTask scheduledtask = new ScheduledTask(i, runnable);
        taskTimer.schedule(scheduledtask, date);
        return scheduledtask;
    }

	/**
	 * 
	* 【添加任务】(延时delay毫秒后执行runnable，之后每隔period毫秒执行一次)
	* @param runnable  任务
	* @param delay  延时时间  毫秒
	* @param period  间隔时间  毫秒
	* @return
	 */
    public static TimerTask scheduleTask(Runnable runnable, long delay, long period)
    {
        return scheduleTask(1, runnable, delay, period);
    }

	/**
	 * 
	* 【添加任务】(延时delay毫秒后以i优先级执行runnable，之后每隔period毫秒执行一次)
	* @param i  优先级
	* @param runnable  任务
	* @param delay  延时   毫秒
	* @param period  间隔时间  毫秒
	* @return
	 */
    public static TimerTask scheduleTask(int i, Runnable runnable, long delay, long period)
    {
        ScheduledTask scheduledtask = new ScheduledTask(i, runnable);
        taskTimer.scheduleAtFixedRate(scheduledtask, delay, period);
        return scheduledtask;
    }

	/**
	 * 
	* 【添加任务】(在指定的date执行runnable，之后每隔period毫秒执行一次)
	* @param runnable  任务
	* @param date  执行时间
	* @param period  间隔时间  （毫秒）
	* @return
	 */
    public static TimerTask scheduleTask(Runnable runnable, Date date, long period)
    {
        return scheduleTask(1, runnable, date, period);
    }

    /**
     * 
    * 【添加任务】(在指定的date以i优先级执行runnable，之后每隔period毫秒执行一次)
    * @param i  优先级
    * @param runnable  任务
    * @param date  初始执行时间
    * @param period  间隔执行时间  （毫秒）
    * @return
     */
    public static TimerTask scheduleTask(int i, Runnable runnable, Date date, long period)
    {
        ScheduledTask scheduledtask = new ScheduledTask(i, runnable);
        taskTimer.scheduleAtFixedRate(scheduledtask, date, period);
        return scheduledtask;
    }

    private static Runnable nextTask()
    {
        Runnable runnable;
        synchronized (lock)
        {
            while (taskQueue.isEmpty())
            {
                try
                {
                    lock.wait();
                }
                catch (InterruptedException interruptedexception)
                {
                }
            }
            boolean flag = (double) taskQueue.size() > Math.ceil(workers.length / 2);
            if (flag)
            {
                busyTimestamp = System.currentTimeMillis();
                addWorker();
            }
            else
            {
                removeWorker();
            }
            runnable = (Runnable) taskQueue.dequeue();
        }
        return runnable;
    }
    /**
     * 
    * 【添加工作者】(这里用一句话描述这个方法的作用)
     */
    private static void addWorker()
    {
        if (workers.length < MAX_WORKER && System.currentTimeMillis() > newWorkerTimestamp + 2000L)
        {
            int i = workers.length + 1;
            int j = i - 1;
            TaskEngineWorker ataskengineworker[] = new TaskEngineWorker[i];
            for (int k = 0; k < workers.length; k++)
                ataskengineworker[k] = workers[k];

            ataskengineworker[j] = new TaskEngineWorker("Worker " + j);
            ataskengineworker[j].setDaemon(true);
            ataskengineworker[j].start();
            workers = ataskengineworker;
            newWorkerTimestamp = System.currentTimeMillis();
        }
    }
    /**
     * 
    * 【删除工作者】(这里用一句话描述这个方法的作用)
     */
    private static void removeWorker()
    {
        if (workers.length > MIN_WORKER && System.currentTimeMillis() > busyTimestamp + 5000L)
        {
            workers[workers.length - 1].stopWorker();
            int i = workers.length - 1;
            TaskEngineWorker ataskengineworker[] = new TaskEngineWorker[i];
            for (int j = 0; j < i; j++)
                ataskengineworker[j] = workers[j];

            workers = ataskengineworker;
            busyTimestamp = System.currentTimeMillis();
        }
    }
    /**
     * 
    * 【停止引擎】(这里用一句话描述这个方法的作用)
     */
    public static void stopTaskEngine()
    {
        for (int i = 0; i < workers.length; i++)
        {
            workers[i].stopWorker();
        }
        workers = null;
        taskQueue = null;
        taskTimer.cancel();
    }
}
