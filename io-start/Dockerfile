FROM amazoncorretto:8-alpine3.14-jdk
# arm 架构 jdk
# FROM azul/zulu-openjdk-alpine:17.0.4-17.36.15-arm64

ENV JAVA_OPS -server \
             -Xms512m \
             -Xmx2048m \
             -Xss1m \
             -Djava.security.egd=file:/dev/./urandom org.springframework.boot.loader.JarLauncher \
             -XX:MaxDirectMemorySize=256m \
             -XX:+UseCompressedOops \
             -XX:+UseCompressedClassPointers \
             -XX:+SegmentedCodeCache \
             -XX:+PrintCommandLineFlags

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

WORKDIR io-start

COPY ./target/io-start.jar ./app.jar

EXPOSE 8082
VOLUME /io-start/logs

ENTRYPOINT java ${JVM_OPTS} -jar app.jar