#!/bin/bash
#启动主类
MAINCLASS=MainClass
#Java虚拟机设置，视情况而调整 -Xms512m -Xmx512m -XX:NewSize=192m -XX:MaxNewSize=384m
JAVA_OPTS="-Xms512m -Xmx512m -XX:NewSize=192m -XX:MaxNewSize=384m"
PIDPREFIX=pid.file
PIDFILE=run.pid
cd `dirname $0`
BINDIR=`pwd`
cd ..
BASEDIR=`pwd`
cd $BINDIR
type java >/dev/null 2>&1 || { echo >&2 "I require java but it's not installed.  Aborting."; exit 1; }
CLASSPATH=$BASEDIR/classes
for i in "$BASEDIR"/lib/*.jar; do
   CLASSPATH="$CLASSPATH":"$i"
done
PID=0;
checkpid() {
    if [ -f "$PIDFILE" ] ; then
        PID=`cat "$PIDFILE"`
        if [ $PID ] ; then
            RPID=`ps aux | awk '{print $2}' | grep $PID`
            if [ "$PID" != "$RPID" ] ; then
                PID=0
            fi
        else
            PID=0
        fi
    fi
}

console() {
    echo "Starting $MAINCLASS ...[OK]"
    java $JAVA_OPTS -classpath $CLASSPATH $MAINCLASS
}

start() {
    checkpid
    if [ $PID -eq 0 ] ; then
        echo -n "Starting $MAINCLASS ..."
        nohup java $JAVA_OPTS -classpath $CLASSPATH -D$PIDPREFIX=$BINDIR/$PIDFILE $MAINCLASS 1>/dev/null 2>&1 &
        sleep 1
        checkpid
        if [ $PID -eq 0 ] ; then
            echo "[ING]"
        else
            echo "(pid=$PID)[OK]"
        fi
    else
        echo "$MAINCLASS is already running!(pid=$PID)"
    fi
}

stop() {
    checkpid
    if [ $PID -ne 0 ] ; then
        echo -n "Stopping $MAINCLASS ...(pid=$PID) "
        kill $PID
	sleep 1
	if [ -f "$PIDFILE" ] ; then
		rm -rf $BINDIR/$PIDFILE
	fi
        if [ $? -eq 0 ]; then
            PID=0
            echo "[OK]"
        else
            echo "[Failed]"
        fi
    else
        echo "$MAINCLASS is not running"
    fi
}

status() {
    checkpid
    if [ $PID -ne 0 ];  then
        echo "$MAINCLASS is already running! (pid=$PID)"
    else
        echo "$MAINCLASS is not running"
    fi
}

case "$1" in
    'console')
        console
    ;;
    'start')
        start
    ;;
    'stop')
        stop
    ;;
    'restart')
        stop
        start
    ;;
    'status')
    status
    ;;
    *)
    echo "Usage: $0 {console|start|stop|restart|status}"
    exit 1
esac
exit 0