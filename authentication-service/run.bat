java -Xmx128m -Xms64m ^
-jar authentication-service-1.0.0.100.jar ^
--spring.profiles.active=test ^
--eureka.client.service-url.defaultZone=http://192.144.136.195:8761/eureka/ ^
--eureka.instance.ip-address=47.92.206.179 ^
--spring.datasource.url=jdbc:mysql://localhost:3306/milepost_auth?serverTimezone=GMT ^
--spring.rabbitmq.host=192.144.136.195 ^
--spring.redis.host=192.144.136.195 ^
--spring.redis.password=qweQWEadmin123
