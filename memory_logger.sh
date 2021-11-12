#!/bin/bash

OPTION=$1

LOGFILE="log.csv"
STATUSFILE="/tmp/pidTempFile.txt"

function start_logging()
{
	while ((1 < 2))
	do
		timestamp=$(date +%s)
		
		info1=$(head -n 1 /proc/meminfo)
		info1=${info1//[A-Za-z: ]/}
		
		info2=$(head -n 2 /proc/meminfo | grep 'MemFree')
		info2=${info2//[A-Za-z: ]/}
		
		del=$(bc<<<"scale=2;$info2/$info1")
		percent=$(bc<<<"scale=0;$del*100")
		percent=${percent/.00/%}
		export percent
			
		concated="$timestamp;$info1;$info2;$percent"
		echo $concated >> $LOGFILE
		sleep 600
	done
}

function stop_logging()
{
	pid=$(cat $STATUSFILE)
	kill $pid
	rm $STATUSFILE
	echo "Logging was stopped"
}



trap stop_logging SIGHUP

case $OPTION in
"START")
	if [ ! -e $STATUSFILE ]
	then
		start_logging &
		echo $! > $STATUSFILE
	fi
	echo "Logging is active"
	;;
	
"STOP")
	if [ ! -e $STATUSFILE ]
	then
		echo "Logging isn't active"
	else
		pid=$(cat $STATUSFILE)
		kill $pid
		rm $STATUSFILE
	fi
	;;

"STATUS")
	if [ -e $STATUSFILE ]
	then
		echo "Logging is active"
	else
		echo "Logging isn't active"
	fi
esac
