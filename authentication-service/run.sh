#!/bin/sh
JAR_NAME=authentication-service-1.0.0.100.jar
start() {
    echo "start process...";
    rm -rf ./logs
    nohup java -Xmx128m -Xms128m \
    -jar ${JAR_NAME} \
    --spring.profiles.active=test \
    --info.app.name=authentication-service \
    --info.app.description=认证-service \
    --info.app.version=1.0.0.100 \
    --server.port=9991 \
    --server.servlet.context-path=/authentication-service \
    --spring.application.name=authentication-service \
    --spring.datasource.druid.url='jdbc:mysql://localhost:3306/milepost_auth?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=GMT%2B8' \
    --spring.datasource.druid.username=root \
    --spring.datasource.druid.password=admin123 \
    --spring.datasource.druid.one.username=root \
    --spring.datasource.druid.one.password='ENC(tXVsX2fiUQfrNM9Gqey3pmRiLgw+Znp/ISEaZCOMDo0=)' \
    --spring.datasource.druid.two.username=root \
    --spring.redis.host=192.168.223.129 \
    --spring.flyway.enabled=true \
    --eureka.client.service-url.defaultZone='http://192.168.223.129:8761/eureka/' \
    --eureka.instance.ip-address=192.168.223.129 \
    --multiple-tenant.tenant=tenant1 \
    --multiple-tenant.weight=3 \
    --multiple-tenant.label-and=aa,bb \
    --multiple-tenant.label-or=dd,ee,ff \
    --dist-transaction.enabled=true \
    --dist-transaction.tx-server-app-name=tx-server \
    --scheduler-lock.enabled=true \
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
# export spring_datasource_druid_url='jdbc:mysql://localhost:3306/milepost_auth?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=GMT%2B8'
# export spring_datasource_druid_username=root
# export spring_datasource_druid_password=admin123
# export spring_datasource_druid_one_username=root
# export spring_datasource_druid_one_password='ENC(tXVsX2fiUQfrNM9Gqey3pmRiLgw+Znp/ISEaZCOMDo0=)'
# export spring_datasource_druid_two_username=root
# export spring_redis_host=192.168.223.129
# export spring_flyway_enabled=true
# export eureka_client_serviceUrl_defaultZone='http://192.168.223.129:8761/eureka/'
# export eureka_instance_ipAddress=192.168.223.129
# export multipleTenant_tenant=tenant1
# export multipleTenant_weight=3
# export multipleTenant_labelAnd=aa,bb
# export multipleTenant_labelOr=dd,ee,ff
# export distTransaction_enabled=true
# export distTransaction_txServerAppName=tx-server
# export schedulerLock_enabled=true


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
