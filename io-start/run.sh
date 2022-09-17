#!/usr/bin/env bash

#操作符 start(启动)|stop(停止)|restart(重启)|status(运行状态)
OPTION=$1
#项目名称 xxx.jar|xxx.war
APP_NAME=$2
#运行端口号 8080
APP_PORT=$3
#运行环境 dev
APP_PROFILE=${4:-dev}

#echo $0
#cd "$(dirname "$0")" || return

usage() {
  echo "Usage [start(启动)|stop(停止)|restart(重启)|status(运行状态) xxx.jar|xxx.war(项目名称) 8080(运行端口号) dev|prod|xxx(运行环境)]"
  exit 1
}

status() {
  _status
  [[ $? -eq 0 ]] && (
    echo '(未运行)NOT RUNNING'
  ) || _info
}

_status() {
  if [[ -z "$APP_NAME"  ||  -z "$APP_PORT" ]];then
      usage;
  else
    echo "运行参数信息: workdir=$(pwd) option=$OPTION app_name=$APP_NAME server_port=$APP_PORT profiles=$APP_PROFILE"
    #ps -ef | grep $APP_NAME | grep $APP_PORT | grep java | awk 'NR==1 {print $2}'
  fi
  pid=$(ps -ef | grep $APP_NAME | grep $APP_PORT | grep java | awk 'NR==1 {print $2}')
  #echo $BASHPID
  #echo $pid
  [[ ! "$pid" || "$pid" == "1" ]] && (
    return 0
  ) || (
    return 1
  )
}

start() {
  _status
  [[ $? -eq 0 ]] && (
    nohup java -Xms512m -Xmx1024m -jar "$APP_NAME" --spring.profiles.active="$APP_PROFILE" --server.port="$APP_PORT" 2>&1 | /usr/sbin/cronolog "$APP_PORT"-log/%Y-%m-%d-log.out >>/dev/null &
    echo 'OK'
  ) || _info
}

_info(){
 pwd
 echo "(正在运行中)RUNINING. pid=$pid app_name=$APP_NAME server_port=$APP_PORT"
}

stop() {
  _status
  [[ $? -eq 1 ]] && (
    kill -9 "$pid"
    echo 'OK'
  ) || (echo "(未运行)NOT RUNINING")
}

restart() {
  stop
  start
}

case "$OPTION" in
"start") start ;;
"stop") stop ;;
"restart") restart ;;
"status") status ;;
*) usage ;;
esac