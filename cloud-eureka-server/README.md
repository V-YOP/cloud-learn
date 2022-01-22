
和Nacos不一样，Eureka是纯Java的，它的服务端并非像如Redis，Nacos那样的中间件一样另外启动，而是通过一个Spring Boot项目进行配置，我还傻乎乎地去docker下面拉了个Eureka的镜像，疑惑为何镜像如此之少呢hh

这问题并不大（就是感觉没这必要……），服务端无论是中间件还是一个特定的项目/微服务，对客户端而言是没有区别的。

写pom依赖eureka的时候第一次写成了spring-cloud-netflix-eureka-server，中间少了个starter，后来抛出一个莫名其妙的ArrayStoreException，谷歌才搜到问题所在！麻了。

如果试图搭建Eureka集群，似乎需保证每个Eureka服务端的hostname不一致。

![](https://image-table.oss-cn-beijing.aliyuncs.com/20220123004743.png)
      
I Made it!