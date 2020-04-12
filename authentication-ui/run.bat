java -Xmx64m -Xms32m ^
-jar authentication-ui-1.0.0.100.jar ^
--spring.profiles.active=test ^
--eureka.client.service-url.defaultZone=http://192.144.136.195:8761/eureka/ ^
--eureka.instance.ip-address=47.92.206.179 ^
--spring.rabbitmq.host=192.144.136.195 ^
--spring.redis.host=192.144.136.195 ^
--spring.redis.password=qweQWEadmin123
