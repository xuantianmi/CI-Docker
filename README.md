# CI with Docker

## 
### 阶段1 本地部署到docker并执行
```
# 打包测试
mvn package
# 启动测试
java -jar target/spring-boot-docker-1.0.jar
# 使用 Maven+DockerFile 构建镜像
mvn package docker:build
# 命令行执行
docker run --name cidocker -p 8080:8080 -t phoenix/spring-boot-docker
# 启动容器：docker start cidocker
# 后台运行
docker run --name cidocker -d -p 8080:8080 -t phoenix/spring-boot-docker
docker ps -a
```

### 阶段2 Jenkins部署并执行
- 在宿主机上安全Docker
- 在宿主机上创建目录，并对Docker共享此卷，以便jenkins应用运行时读写文件:
```
$ mkdir -p /var/jenkins_home
$ chmod 777 /var/jenkins_home
```
- 构建并启动Jenkins
```
$ docker build -t ml_jenkins -f Dockerfile-jenkins .
$ docker-compose up -d
```
- 初始化Jenkins
```
$ cat /var/jenkins_node/secrets/initialAdminPassword # 查看密码
# 查看终端: docker logs ml_jenkins、
```
- Jenkins安装Publish Over SSH插件
```
设置Jenkins服务器SSH信息
# 进入容器 
$ docker exec -it ml_jenkins /bin/bash
$ mkdir ~/.ssh && cd ~/.ssh
$ ssh-keygen -t rsa # 一路回车
# 使用下面的命令添加到宿主机中(确保宿主机已开启ssh服务). 也可手动复制id_rsa.pub到宿主机的.ssh/authorized_keys文件中
$ ssh-copy-id -i ~/.ssh/id_rsa.pub <username>@<host>
设置ssh-server允许使用私钥和公钥对的方式登录
配置文件为/etc/ssh/sshd_config
Restart SSH Service.
添加私钥:jenkins首页，系统管理 -> 系统设置 -> 下拉，找到Publish over SSH，填写Key 和 SSH Server -> 保存
```

## 参考
1. [Docker-理解三大核心](https://segmentfault.com/a/1190000014110196)
1. [Docker 官方仓库](https://hub.docker.com/)
    1. [Jenkins](https://hub.docker.com/_/jenkins)
    2. [Docker in docker!](https://hub.docker.com/_/docker)
        1. 关注下多级列表的用法！
1. 镜像加速／镜像仓库
    1. [Aliyun](https://cr.console.aliyun.com/?spm=5176.1971733.0.2.duOGn4#/accelerator)
1. 样例参考
    1. [_使用 Docker 部署 Spring Boot_](http://www.ityouknow.com/springboot/2018/03/19/spring-boot-docker.html)
    1. [SpringBoot学习实例](https://github.com/ityouknow/spring-boot-examples)
    1. [SprigBoot系列文章](http://www.ityouknow.com/spring-boot.html)
    1. [Docker入门-持续集成](https://segmentfault.com/a/1190000014924494)
    1. [docker使用maven远程构建](https://blog.csdn.net/liaomin416100569/article/details/81338604)
  
## Others
[测试一下mk的内部链接](#参考)