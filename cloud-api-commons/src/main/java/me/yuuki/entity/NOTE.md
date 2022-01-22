已使用下面的解决方案——将数据类型和接口另外抽象成一个maven模块。

---

这两个实体都是copy过来的。为什么用copy呢？不是说Ctrl+C/V是最应该避免的吗？

我认为这种copy是合理的，它是为了避免两个微服务之间在模块层面上的依赖（换句话说，在pom里直接有依赖关系）这可能会造成很多负面影响，如增加开发者的心智负担，第三方依赖版本难控制，被Spring错误地扫描到等，但是更合适的解决方案显然是将这些实体直接单独放到一个工程里，谁想依赖就谁去引用。

将该微服务所用到的数据类型和接口作为一个单独的工程，从而同实现分离是一个常用的手段，这样其它微服务和该微服务的实现只需要依赖这个接口的工程即可，该接口的工程只需要最少数量的依赖（使Spring能够根据接口创建远程服务即可）。

当然，这个解决方案的耦合程度仍然是比较大的（请求者仍然知道接受者姓甚名谁，是单播不是广播），更松耦合的交互方案则是这里试图使用的——完全使用Http客户端（同步则使用RestTemplate，异步使用HttpClient）（结合服务发现之类来屏蔽请求接受者的ip等信息）进行请求，或是使用消息队列，Reactive（就像Vert.x）等方式，管发不管收，甚至不关心是谁收。