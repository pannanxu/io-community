FROM amazoncorretto:8-alpine3.14-jdk

ENV OPTS="-Dlogging.file.path=/${project.build.finalName}/conf/logback-spring.xml -Dspring.config.location=/${project.build.finalName}/conf/ -Dspring.profiles.active=prod"

# 将压缩包添加到容器内
ADD ${project.build.finalName}-bin.tar.gz /

# 声明服务运行在8081端口
EXPOSE 8081
# 声明可映射路径
VOLUME /${project.build.finalName}/logs
VOLUME /${project.build.finalName}/conf

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

ENTRYPOINT java ${OPTS} -jar /${project.build.finalName}/lib/${project.build.finalName}.jar