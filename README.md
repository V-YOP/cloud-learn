# cloud-learn

该仓库为个人学习 [尚硅谷的spring cloud课程](https://www.bilibili.com/video/BV18E411x7eT) 的代码，为get到重点有一些改动，也包含一些笔记。

运行配置保存在ideaRunConfigurations文件夹下。

这也是我第一次没有使用start.spring.io，而是从零开始创建maven的spring boot项目，算挺有意义的。

这里为了方便，直接使用了嵌入式的H2作为数据库，这导致了一些麻烦问题——每个实例用的都是自己的数据库，相当于是有了自己的状态了。解决方案是使用磁盘而非内存作为datasource.url，以保证数据的共享。这h2相关配置可能是有某种bug？查看发现自己配置的datasource不生效，SO上的解决方案也不奏效，很怪。

而且cloud-payment-service使用一个ConcurrentHashMap模拟数据库，也导致了上面的问题……偷鸡不成蚀把米啊。