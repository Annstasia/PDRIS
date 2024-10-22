#!/bin/bash

LOG_DIR="./logs"
PID_FILE="./disk_monitor.pid"
TOTAL_INODE=0
TOTAL_SPACE=0
TOTAL_USED_INODE=0
TOTAL_USED_SPACE=0

monitor_disk_usage() {
    mkdir -p "$LOG_DIR"
    
    while true; do
        CURRENT_DATE=$(date +'%Y-%m-%d')
        TIMESTAMP=$(date +'%Y%m%d_%H:%M:%S')
	LOG_FILE="$LOG_DIR/disk_usage_$CURRENT_DATE.csv"
        if [ ! -f "$LOG_FILE" ]; then
            echo "timestamp,mount,inode_used%,space_used%,inode_used_cnt,inode_total,space_used_amount,space_total" > "$LOG_FILE"
        fi

	# Буду сохранять по информацию по каждой точке монтирования и по всем вместе
        TOTAL_INODE=0
	TOTAL_SPACE=0
	TOTAL_USED_INODE=0
	TOTAL_USED_SPACE=0
	mapfile -t df_output < <(df --output=target,ipcent,pcent,iused,itotal,used,size | tail -n +2)
	for line in "${df_output[@]}"
	do
            MOUNT=$(echo "$line" | awk '{print $1}')
            IPCENT=$(echo "$line" | awk '{print $2}')
	    PCENT=$(echo "$line" | awk '{print $3}')
	    IUSED=$(echo "$line" | awk '{print $4}')
	    ITOTAL=$(echo "$line" | awk '{print $5}')
	    USED=$(echo "$line" | awk '{print $6}')
	    SIZE=$(echo "$line" | awk '{print $7}')
            echo "$TIMESTAMP,$MOUNT,$IPCENT,$PCENT,$IUSED,$ITOTAL,$USED,$SIZE" >> "$LOG_FILE"
	    ((TOTAL_USED_INODE+=IUSED))
	    ((TOTAL_USED_SPACE+=USED))
	    ((TOTAL_SPACE+=SIZE))
	    ((TOTAL_INODE+=ITOTAL))
    	done
	echo "$TIMESTAMP,total,$((TOTAL_USED_INODE*100/TOTAL_INODE))%,$((TOTAL_USED_SPACE*100/TOTAL_SPACE))%,$TOTAL_USED_INODE,$TOTAL_INODE,$TOTAL_USED_SPACE,$TOTAL_SPACE" >> "$LOG_FILE"
        sleep 10
    done
}

start_monitoring() {
    if [ -f "$PID_FILE" ]; then
        echo "Скрипт уже запущен. PID: $(cat "$PID_FILE")"
        exit 1
    fi
    
    monitor_disk_usage & echo $! > "$PID_FILE"
    echo "Скрипт запущен. PID: $(cat "$PID_FILE")"
}

check_status() {
    if [ -f "$PID_FILE" ]; then
        if ps -p "$(cat "$PID_FILE")" > /dev/null; then
            echo "Скрипт запущен. PID: $(cat "$PID_FILE")"
        else
            echo "Скрипт не запущен, но PID файл существует."
        fi
    else
        echo "Скрипт не запущен."
    fi
}


stop_monitoring() {
    if [ -f "$PID_FILE" ]; then
        kill "$(cat "$PID_FILE")"
        rm -f "$PID_FILE"
        echo "Скрипт остановлен."
    else
        echo "Скрипт не запущен."
    fi
}


case "$1" in
    START)
        start_monitoring
        ;;
    STOP)
        stop_monitoring
        ;;
    STATUS)
        check_status
        ;;
    *)
        echo "Usage: $(basename "$0") {START|STOP|STATUS}"
        exit 1
        ;;
esac
