# io-community

一个基于Java开箱即用的项目骨架

# 快速开始

```shell
git clone https://github.com/pannanxu/io-community.git
```

# 项目打包

```shell
mvn clean package
```

# 项目运行

## windows

```shell
start.bat
```

## linux

```shell
sh start.sh
```

## docker

- build image
```shell
docker build -t io-app:v1 .
```
- run
```shell
docker run -p 8888:8081 \
-dit io-app:v1 \
--name io-app \
-v /data/io-app/logs:/io-start/logs \
-v /data/io-app/conf:/io-start/conf \
/bin/bash
```

# 项目结构

- `io-infra` 基础设施
- `io-*-service-api` 服务对外提供的接口, 也可以是给其他模块提供的接口
- `io-*-service-provider` 服务的实现, 对外提供的接口需要在 api 包中定义后在此包实现
- `io-start` 启动器

# 许可证

[Apache-2.0 license](https://github.com/pannanxu/io-community/blob/main/LICENSE)
