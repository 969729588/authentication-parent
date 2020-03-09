#!/bin/sh
JAR_NAME=authentication-service-1.0.0.100.jar
start() {
    echo "start process...";
    rm -rf ./logs
    nohup java -Xmx256m -Xms256m \
	-jar ${JAR_NAME} \
    --spring.profiles.active=test \
    --server.port=9993 \
	--eureka.client.service-url.defaultZone=http://192.168.223.129:8761/eureka/ \
    --multiple-tenant.tenant=tenant1 \
    --multiple-tenant.weight=3 \
    --multiple-tenant.label-and=aa,bb \
    --multiple-tenant.label-or=aa,dd,ee \
    --eureka.instance.ip-address=192.168.223.129 \
    --spring.application.name=authentication-service \
    --server.servlet.context-path=/authentication-service \
    --info.app.version=0.0.2 \
    --dist-transaction.enabled=true \
    --dist-transaction.tx-server-app-name=tx-server \
    --spring.datasource.druid.db-type=mysql \
    --spring.datasource.druid.driver-class-name=com.mysql.cj.jdbc.Driver \
    --spring.datasource.druid.url="jdbc:mysql://192.168.223.129:3306/milepost_auth?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=GMT%2B8" \
    --spring.datasource.druid.username=root \
    --spring.datasource.druid.password='ENC(q5FdjvhfUhA/TeaHgllHOkaE6c+jYjDFxTae1Fnyqb4=)' \
    --spring.datasource.druid.one.password=admin123 \
    --spring.flyway.enabled=true \
    --scheduler-lock.enabled=true \
    >/dev/null 2>&1 &
}


stop() {    
    while true
    do
        process=`ps aux | grep ${JAR_NAME} | grep -v grep`;
        if [ "$process" = "" ]; then
            echo "no process";
            break;
        else
            echo "kill process...";
            ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}' | xargs kill -9
            sleep 3
        fi
    done    

}

restart() {
    stop;
    start;

}
case "$1" in
    'start')
        start
        ;;
    'stop')
        stop
        ;;
    'status')
        status
        ;;
    'restart')
        restart
        ;;
    *)
    echo "usage: $0 {start|stop|restart}"
    exit 1
        ;;
    esac
