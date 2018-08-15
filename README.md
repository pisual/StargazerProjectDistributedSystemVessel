
# StargazerProjectCloudSystem（文档正在努力书写！）
     观星者计划 分布式云框架

StargazerProjectCloudSystem是StargazerProject的顶级项目，云计算中间事务框架，用于承担海量请求的冲击。不光承担海量的用户请求，还要承担着与云计算系统的海量数据交互，其本身也可以进行定制化的云计算及云事务。

# 目录


----------


##     1. StargazerProjectCloudSystem 简介


    StargazerProjectCloudSystem并不是用于颠覆Hadoop或者是Spark这类云计算框架，这点我在开发初期很迷茫，我错误的一直认为自己在开发传统的云计算框架，直到近2年前开始第3次大规模重构的时候，我才发现，我究竟需要什么。
    我需要一种承担用户与Hadoo这类计算集群之间交互的框架，也就是云计算中间件，其本身为了应对海量的冲击，作为云计算集群最重要的外壳，也是一种计算集群，那么这种集群需要如下的功能：
        1. 容错
            即使出现错误，无论是系统工作出现错误，还是使用者人为的造成错误，都不会影响整体，不会干扰其他的工作。
        2. 支持灾难重组（自愈合）
            允许集群内指定范围内的计算节点下线，并且排除故障后允许其重新上线及入队。
        3. 高并发
            框架采用非阻塞的高速Disruptor队列，可以灵活的配置消费者线程及等待寻轮策略，并可以显著控制高速队列的背景功率（Power Background，意指高速队列在空闲时期其反复空寻轮对系统资源的占用率）。
        4. 分布式事务
         支持分布式事务的分发、决策、回滚。
        5. 准实时计算
            支持在限定时间内完成事物或者报告超时错误，在事物分发阶段，可以根据分布式系统的负载情况给出运算模拟情况，如果模拟情况没有满足要求，会警告上游使用者从而采取模拟超时策略。
        6. 集群及去中心化的集群组建
            计算节点按照其容灾策略自行成组（Group），并进一步自行结合成区（Zone），在节点下线的情况下提供再均衡及重组的能力。
        7. 集群的横向扩展及纵向扩展
            根据现有系统的负载及产能报告的情况进行系统的的水平扩张或水平衰退。
        8. 空中编译及分布式运算模块的动态注入
            支持代码的空中编译及运行时动态加载及动态注入，可以把编写好的模块代码注入集群，支持热部署及红黑部署。
        9. 运算模块的自行动态均衡
        10. 代码检测
        11. 核心模块化
        12，基于区块链的分布式存储于系统架构
        13，基于人工智能的系统审计集及系统内动态构架
        

----------

##     2. Cache 综合缓存模块介绍
##     3. Queue 综合队列介绍
##     4. Log 集中托管日志介绍
##     5. Bus 系统总线介绍
##     6. Server 服务托管与运维介绍
##     7. Sequence 序列介绍
##     8. UserInterface 界面介绍
StargazerCloud使用的是定制化的StargazerCellsUI，以下为UI的启动界面及使用界面，UI已经开发完毕，因为其依赖了StargazerCloud的一些内部模块，所以就不单独发布了。
# 启动界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/1.jpeg)
# Translucent Surface运行界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/2.jpeg)
# 启动界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/7.jpeg)
# Black Lotus运行界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/6.jpeg)
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/4.jpeg)
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/3.jpeg)
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/5.jpeg)
##     9. Negotiate 协商介绍
协商服务模块负责分布式系统整体架构的构建，StargazerCloud系统并没有要求强行指使用定某一种架构模式，架构是演变与进化的，今天的架构并不一定适合明日的架构，这个项目的架构模式也不适用另外项目的，保持架构的灵活性是为了延长StargazerCloud生存周期，使得StargazerCloud能够不断的分支演化，StargazerCloud在架构哲学上遵守着最基本的一点就是协商架构，在StargazerCloud系统之内，节点的分化及架构成型，是由模型来进行指定，每一次系统新生都不会形成完全相同的架构。

现阶段，Stargazer分布式系统的拓扑结构是扁平化布局，使得节点的加入和退出或者局部扩张和消亡对整体构架造成的影响降到最低，

    Group - Group - Group * *
         
    Cell - Cell - Cell - Cell - Cell * 
   
 Group: 是一个内部包含节点的拓扑结构，根据BackupLevel（集群事务备份级别）来确定包含的数目以此实现备份级别。
 
    例如：

         BackupLevel（节点备份级别）: 3
         
         Group A (Cell 1 : Cell 2 : Cell 3)
         
组内节点互相跟踪，节点分为跟踪节点（Follow，用于同步跟踪节点）和 领导节点（Leader，负责实际的运算），为了提高负载能力系统将根据BackupLevel来进行固定分组，

    Group A (Cell 1 : Cell 2 : Cell 3)
 
    CellsGroup1 (Cell 1(Leader) : Cell 2(Follow) : Cell 3(Follow))
    
    CellsGroup1 (Cell 1(Follow) : Cell 2(Leader) : Cell 3(Follow))
    
    CellsGroup1 (Cell 1(Follow) : Cell 2(Follow) : Cell 3(Leader))
    
    组成员将会定期通信来记录已经处理完成的指令，Leader将定期发送指令来指示Follow推进备份指令队列的位置
    
####     分布式区域协议
Cell节点需要自行组建Group结构，依靠共识算法（例如比特币的POW工作量证明）来获取组建的权利，分布式区域协议的建组是动态模式，任何时候都可以进行自由建组。

         
 
##     10. Cell 细胞介绍
##     11. MessageQueue 消息队列介绍
##     12. Transaction 事务介绍
##     13. Check 检测介绍
##     14. Resources 资源介绍
##     15. @SHELL壳式编程
@SHELL是一种对于未来编程的描绘与设计，很多我开发的项目，最后都死在了无法维护、无法更新、无法更迭上，随着项目的不断扩张，原来能稳定运行的项目不断被腐蚀，最后，巨大的无法维护的代码堆就摆在一代代接盘侠的眼前，无法重构，没人能了解这个代码堆的全局架构，最后，往往是成为一个被死去的孩子。
我们能开发出一个万能的架构�吗？对不起不能。这是一个虚幻的梦，但是我们能做的，我们最容易做的就是保持这个项目架构的可成长性，使得这个项目不断成长的代价降低，使得这个项目在搭建初期就能为如今或者未来描绘好成长的轨迹，即使有一日，她再也不能承担起重任需要逝去，她，也是以闪亮的姿态谢幕，而不是一堆烂代码。
@SHELL壳式编程不是终点，未来可能会有更多天才的解决方法，但是如今她为超大型项目的构建提供了未来解决方案，在StargazerProjectCloudSystem中，经过漫展与痛苦第一次SHELL大型重构，重构后的代码已经全部采用了@SHELL的编程方案，初见就展现了其独特的魅力。在Spring的辅助下，@SHELL更是极大的缩小了代码量，下面，放空心态，与她一起走近@SHELL的世界。
##     16. Analysis 注入式分析器介绍
##     17. Serializables 托管序列化介绍
##     18. Information 交换与信息介绍
##     19. Inject 注入与空中编译介绍
##     20. Annotations 注解扫描介绍
##     21. Check 代码检测与自动化测试介绍
##     22. Validation 集中校验介绍


  ### 后记：
  ### 每个人都有一个伴随了自己大半个人生的故事，总有一天会拿出来讲一讲，每个人都会有自己最大的隐藏的能力，默默的在深夜里磨练，希望有他成熟的一天，会闪耀在夜空中，那么，就让我讲一讲我的故事吧。
  ### 从第一次接触编程，我就认为，编程是未来世界的改编者之一，从Pisual项目的幼稚到StargazerProject项目的成熟，未来总会有一天，在某某项目里继续成长，正是不断的书写，不断的完善，不断的重构，给了我从幼稚到成长的故事。
  ### StargazerProject顶层项目CloudSystem的最初的设想很简单，首先设计出一个工作引擎，这个工作引擎可以完成大量的工作，系统会自动的设计指令，自动的调度运行，自动的运算结果，自动的分布布局，自动的容灾重组，并在指定时间内返回结果，这就是我最初希望的结果，抛掉Hadoop，抛掉Spark，最为纯正的需要，最为纯正的设想。
  ### 现在看来，这是多么的奢想，这是多么的梦幻，没想到这简简单单的几句话就写了5年代码。
  ### 至今为止，正如前面说的，StargazerProjectCloudSystem项目已经书写了近5年，重构了8次，其子成果已经用在其它很多的项目之上，因为我本身是一名UI设计师、前端技术设计师，动画师、摄影师、平日里还要在银行工作，留给我的时间并不多，但即使如此，正如小镇在慢慢的向前前进者，我也在尽力的书写者，这便是爱吧。虽然我在银行的工作并不涉及到编程，但是这个平凡的工作确实给了我很多，给了我很多的时间与精力去完成我自己想做的事情，有时候，看到开发中心的正在痛苦编程的人们，不由得苦笑一下，她们，可能永远都不知道身后站着一位与编程恋爱的人吧。直到现在，我还保持者一个习惯，经常打开我在的银行的所使用的系统，对里面的代码如数家珍，心里幻想一下，好像是我的孩子一样。
  ### 最后，说一些最后的话语，StargazerProject给我最大的启示，那就是代码不是重要的东西，代码并不是你的成果，谁都可以写出来的，最重要的是你，是你书写代码的那种感觉，是你设计架构的那种泰然自信，是你那种真正爱他的这种灵魂，所以，我将我的书写过的全部代码开源，如果能在茫茫人海中与你相见，给看到这里的你一点启示，那便是我最大的快乐了。
  ### 最后的最后，StargazerProject顶级项目CloudSystem预计2018年上半年将开发出第一个测试版本，并用于承担StargazerProject项目组内所有的图像分析及计算任务。
    

### StargazerProject首席设计师 FelixErio & Love 喵喵大胖猫
