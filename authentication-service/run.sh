#!/bin/sh
JAR_NAME=authentication-service-1.0.0.100.jar
start() {
    echo "start process...";
    rm -rf ./logs
    nohup java -Xmx128m -Xms128m \
    -jar ${JAR_NAME} \
    --spring.profiles.active=test \
    --server.port=9991 \
    --eureka.client.service-url.defaultZone='http://192.168.223.136:8761/eureka/' \
    --eureka.instance.ip-address=192.168.223.136 \
    --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver \
    --spring.datasource.url='jdbc:mysql://192.168.223.136:3306/milepost_auth?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=GMT%2B8' \
    --spring.datasource.username=root \
    --spring.datasource.password='ENC(tXVsX2fiUQfrNM9Gqey3pmRiLgw+Znp/ISEaZCOMDo0=)' \
    --spring.redis.host=192.168.223.136 \
    --spring.flyway.enabled=false \
    --spring.rabbitmq.host=192.168.223.136 \
    --spring.rabbitmq.port=5672 \
    --spring.rabbitmq.username=admin \
    --spring.rabbitmq.password=admin \
    --track.enabled=true \
    --track.sampling=1 \
    --multiple-tenant.tenant=tenant1 \
    --multiple-tenant.weight=3 \
    --multiple-tenant.label-and=aa,bb \
    --multiple-tenant.label-or=dd,ee,ff \
    --scheduler-lock.enabled=true \
    --tx-lcn.client.manager-address=192.168.223.136:8070 \
    >/dev/null 2>&1 &
}

# 环境变量，
# vim /etc/profile
# source /etc/profile
# export spring_profiles_active=test
# export info_app_name=authentication-service
# export info_app_description=认证-service
# export info_app_version=1.0.0.100
# export server_port=9991
# export server_servlet_contextPath=/authentication-service
# export spring_application_name=authentication-service
# export spring_datasource_xxx_url='jdbc:mysql://localhost:3306/milepost_auth?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=GMT%2B8'
# export spring_datasource_xxx_username=root
# export spring_datasource_xxx_password=admin123
# export spring_datasource_xxx_one_username=root
# export spring_datasource_xxx_one_password='ENC(tXVsX2fiUQfrNM9Gqey3pmRiLgw+Znp/ISEaZCOMDo0=)'
# export spring_datasource_xxx_two_username=root
# export spring_redis_host=192.168.223.136
# export spring_flyway_enabled=true
# export eureka_client_serviceUrl_defaultZone='http://192.168.223.136:8761/eureka/'
# export eureka_instance_ipAddress=192.168.223.136
# export multipleTenant_tenant=tenant1
# export multipleTenant_weight=3
# export multipleTenant_labelAnd=aa,bb
# export multipleTenant_labelOr=dd,ee,ff
# export schedulerLock_enabled=true

# 环境变量操作
# 设置
#export TEST=abc
# 查看
#env | grep TEST
# 清除
#unset TEST



stop() {
    while true
    do
        process=`ps aux | grep ${JAR_NAME} | grep -v grep`;
        if [ "$process" = "" ]; then
            echo "no process";
            break;
        else
            echo "kill process...";
            ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}' | xargs kill -15
            sleep 3
        fi
    done

}

restart() {
    stop;
    start;
}

status() {
    ps -ef | grep ${JAR_NAME}
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
    echo "usage: $0 {start|stop|restart|status}"
    exit 1
        ;;
    esac
