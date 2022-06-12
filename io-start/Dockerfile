FROM amazoncorretto:8-alpine3.14-jdk

WORKDIR /io-start

# 将压缩包添加到容器内
ADD io-start-bin.tar.gz /

# 声明服务运行在8081端口
EXPOSE 8081
# 声明可映射路径
VOLUME /logs
VOLUME /conf

ENV TZ=Asia/Shanghai \
    PROFILE_ACTIVE="prod" \
    JVM_OPTS="-Xmx256m -Xms256m"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

ENTRYPOINT java ${JVM_OPTS} -Dlogging.file.path=/logs -Dspring.config.location=/conf/ -Dspring.profiles.active=${PROFILE_ACTIVE} -jar /lib/io-start.jar