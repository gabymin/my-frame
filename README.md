# my-frame# 

#### 介绍
搭建基础架构

#### 软件架构
架构主要是MVVM设计模式，使用Google推荐和维护的Jetpack框架，Jetpack 是一个由多个库组成的套件，
可在各种 Android 版本和设备中一致运行的代码，应用中Application,Activity,Fragment, ViewModel
使用注解器Dagger库代码注入方式，接口的网络请求也是代码注入方式来访问的，
数据库的操作框架选择的是androidx.room库，图片加载是选择glide，游戏下载器是fetch2库，
视频播放器采用IJKplayer的多功能播放器

#### 使用说明

下载目录后

local.properties
配置如下

```
sign.key=xxxxxx
sign.keyPass=xxxxxx
sign.keystore=xxxxxx\\frame.jks
sign.keystorePass=xxxxxx
```
编译apk
./gradlew clean channelXXXXProdRelease`  XXXX为渠道名称 ,Prod为服务器环境


#### 分支说明

1. master 分支             用于上线基础代码
2. dev-benben 分支         用于打包给测试的代码
3. 新建分支  dev-功能名称    用于开发新功能

