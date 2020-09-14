# 2020年最新最实用的android-MVVM框架


<img src="https://img-blog.csdnimg.cn/20200421193001875.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1ZjU3Ryb25n,size_16,color_FFFFFF,t_70" width="300" align=center /> <img src="https://img-blog.csdnimg.cn/20200421193130501.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1ZjU3Ryb25n,size_16,color_FFFFFF,t_70" width="300" align=center />
<img src="https://img-blog.csdnimg.cn/20200421193154897.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1ZjU3Ryb25n,size_16,color_FFFFFF,t_70" width="300" align=center /> <img src="https://img-blog.csdnimg.cn/20200421193208122.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1ZjU3Ryb25n,size_16,color_FFFFFF,t_70" width="300" align=center />
<br/>
<br/>

> 注册的密码规则是数字加字母超过8位即可
> 测试账号：13126965106 密码：111111aa

**阅读此文档前，先尝试运行项目，文档中部分类名需要结合项目中代码进行参考和理解**

## 一、业务

### 1. 功能：
- 登录注册（跳转主页后关闭，已登录用户可直接进入主页）;
- 仿微信朋友圈，Recyclerview嵌套RecyclerView实现多图布局列表切换（一张横向全屏，二张横向全屏，三张横向全屏，四张分两行且横向全屏）；
- 发表朋友圈回传值刷新（viewmodel中livedata妙用）；
- 多图带参数上传；
- 首页和设置页分别退出登录（intent的flag使用）；

## 二、技术及场景

### 1. 技术要点：
- ViewModel在xml页面变量同一命名"vm"，Activity和Fragment中使用ViewModel有明显的区别，请认真仿照；
- item的xml被多个Adapter使用，而且model数据（bean）不一致的情况，切勿盲目binding.setVariable设置进行单向页面绑定，请在adapter中手动控件赋值，不要xml-binding；
- Adapter中复杂控件处理需要大对象，建议灵活使用binding寄存，参见CircleAdapter类
- ObjectBox快速实现对象存取
- LiveData对网络请求数据灵活设置，view层基于观察者模式填充
- viewbinding中onClick，onCheckedChanged等事件使用，其他事件根据Listenter实现方法的名字举一反三
- conf.gradle对于版本和打包管理，做到清晰-快速的进行组件调试；common包中build.gradle中尽量容纳整个工程所需要的第三方框架/SDK的版本，方便所有模块使用和调试，建议小组主程/架构的同学管理
- Fragment拆分组件之后的通信方案，参照下方mvvm-v3.1介绍
- 项目最低兼容4.0.3，因为objectbox最低支持这个版本

### 2. 使用的开源框架：
- <a href="https://developer.android.google.cn/jetpack/androidx">androidx</a>：这个系列的jar包和appcompat.support对立的，参见谷歌官方文档
- <a href="https://developer.android.google.cn/jetpack">lifecycle-viewmodel+livedata+DataBinding</a>：
生命周期管理，完全解耦，方便系统内存管理释放，基于观察者模式实现数据更新等等
- <a href="https://github.com/square/retrofit.git">Retrofit2</a>：感谢Square公司
- <a href="https://github.com/ReactiveX/RxJava.git">rxjava2</a>：感谢ReactiveX，突然发现Rxjava已经发布3.0了
- <a href="https://github.com/objectbox/objectbox-java.git">ObjectBox</a>：android上运行速度最快的数据库，基于c/c++开发，native接口
- <a href="https://github.com/youth5201314/banner.git">banner</a>：banner如果不需要就去掉。
- <a href="https://github.com/facebook/fresco.git">Fresco</a>：感谢FaceBook，另外给大家提个醒，
Fresco有自己的内存回收机制，但是这个回收阈值没有设置，请自行百度解决，提示：Fresco eviction哈哈哈
- <a href="https://github.com/XRecyclerView/XRecyclerView.git">XRecyclerView</a>
- <a href="https://github.com/tbruyelle/RxPermissions.git">RxPermissions</a>：权限申请比较好用
- <a href="https://github.com/alibaba/ARouter.git">Arouter</a>：感谢阿里巴巴技术团队
- <a href="https://github.com/bytedance/BoostMultiDex.git">BoostMultiDex</a>：感谢头条技术团队抖音多dex加载方案


## 三、分支更新日志（倒叙）

### 7.master 2020.09.13
- **对于ObjectBox一些建议**
  1. 大小限制：ObjectBox默认最大为1GB，当db文件达到1G，新增数据会报出SQL异常，可通过maxSizeInKByte()改变最大限制，建议同SQLite一样，设置为磁盘大小，使用其他方法在部分业务上做磁盘大小检测，磁盘不足及时预警；另外从插入1条-100W条数据来看，ObjectBox扩容基本上从SQlite原生数据库几十倍降低到2.5倍左右；
  2. 经过测试，在插入速度方面有以下几个建议：
     - ObjectBox相比于原生Sqlite在批量插入数据方面速度快很多，而且使用put(list or obj...)都是自动开启事务；反之Sqlite对于单条多频次数据入库在速度上远远超过ObjectBox;
     - 业务角度出发，如果是单条多频次bean对象插入明显Sqlite占优，ObjectBox执行速度有所下降，这种场景下可以考虑队列的方式做批量插入；
  3. 经过测试，在查询速度方面有以下建议：
     - 主键ID查询单条数据在百万级数据中Sqlite稍优；在单表条件查询之下，数据量上万情况下，Objectbox过于优秀。
  4. 在删除数据方面，由于都是Sqlite数据库，所以删除只是删数据，并不会减小磁盘内存，如果要缩小db大小，可以自行设计方案；
  ```
  注：测试使用小米9手机，单表数据量从最小100条到最大200W条，字段为30个String+一个自增ID，每个字符串长度都在20到30长度的随机字符，测试过程没有严格做到控制变量法，所以测试并不是很严谨，仅供参考
  ```

### 6.master 2020.09.05
- 新增scope插件，在gct文件夹中，这是一个idea插件，需要打开idea/studio进行plugin插件安装，安装之后会生成两个入口，1.VCS提交面板commit message增加小文本按钮；2.idea/studio面板增加VVScopeTree树形结构面板；插件作用是为了标准化commit message文案，规范每一次commit message的描述。
- **scope插件是什么呢？怎么使用？**
  1. 参见这位外国大兄弟：https://github.com/MobileTribe/commit-template-idea-plugin.git
  2. 在此基础上，根据公司项目情况，我又开发了Tree树形结构功能，安装插件之后，会在idea或studio主页上增加一个VVScopeTree，方便所有模块业务进行管理。
```
  注：在Version Control中commit弹框，输入message使用插件编辑;
```

### 5.master 2020.08.09
- 首页UI结构更新：MainActivity使用FragmentManager的add hide show；**MainFromViewPagerActivity使用ViewPager+Fragment**；使用的时候只需要注释或者放开@Router注解即可
> **对于组件aar版本的说明**  
> **前提/背景/须知：**  
> 目前此工程可以满足大家开发需要，但随着项目上线，组件越来越多，小组成员不断增多，我们整体工程越来越大，所以我们期望一个工程只包含两部分：app壳工程+自己负责的组件，对于自己不负责的组件只需要在壳工程中引入依赖即可。
> 需要承认一点，现在包括之前的版本都没有做到**组件开发和维护的灵活性**，所以最近有时间会继续改进一版，做到一个工程只包含两部分：app壳工程+自己负责的组件；
> 使用了现在或以前版本的小伙伴也不要伤心，组件改进不涉及任何业务功能，所以你依旧可以根据下方提供的技术方案对自己的项目改进。
>
> **技术方案流程：**
> 1. 建议大家一步到位，不要本地打包拷贝libs，请自行搭建公司私有仓库（本地或者远程服务器均可），然后配置gradle发布脚本，发布aar到私服上；
> 2. 请主程/小组长在app的build.gradle中dependencies引入所有的aar，在config.gradle做好aar版本管理，视情况删除工程中build.gradle的打包流程（可保留，应对后续组件功能扩展）；
> 3. 将整理好的工程clone多份，删除.git，在settings.gradle中删除不需要的组件名称，然后在每份源码中保留不同的组件，重新上传仓库，完成改版。
>
> **关于工具类改进的一些想法：**  
> 考虑了很多次是否改进，最终个人决定不改进，留给各位小伙伴完成，目前common中util工具类比较少，而且只使用了其中的某几个，大家可以根据需求自行增加，
> 另外比较重要的Log/File工具类强烈建议根据业务和Android版本做深度改进，推荐大家在github中搜索一下相关的工具类工程。
>

### 4. mvvm-v3.1 2020.06.12
- **Fragment组件化通信怎么实现的，实现原理？**
    1. 使用组合设计模式，ViewModel（以下简称VM）必须和Activity放在同一个模块，Fragement必须继承WDFragment，因为其中使用自定义FragViewModel（以下简称FVM）抽象类（这个类没有继承VM，是自定义的一个抽象类）；
    2. Activity中通过Arouter获取其他组件中的Fragment，然后通过此Fragment的方法拿到其绑定的FVM，然后将Activity的VM中LiveData变量传递给FVM，从而实现了数据共享。

- **我怎么快速使用Fragment组件化通信？**
    1. 打开open_user组件，找到UserViewModel类，打开布局文件frag_me.xml，找到dataShare方法，即可快速了解并使用。

>组合设计模式（Component），通过对子节点初始化赋值，利用MutableLiveData达到共享数据的目的，建议使用Message对象，减少代码量，增加功能最大适配性。

- **Arouter中path是常量字符串，当模块众多的时候，需要实现path共享。**
    1. 新增常量生成插件，VcStrong自己开发的，放到了jitpack.io上，根据module的build.gradle配置constant动态生成常量类。
    2. 目前支持自定义项目名，报名，类名生成常量类，常量参数生成的时候默认使用追加策略（不是覆盖哦）

- **如何使用常量插件呢？**
```
在工程的build.gradle中dependencies添加：
classpath 'com.github.VcStrong:ConstantPlugin:0.0.1'
```
```
在module的build.gradle中使用：
apply plugin: 'com.vc.constant'//启用常量插件
constant {
    enable false//不进行编译，不写的话，默认每次都进行编译，查看build日志
    moduleName "common"//生成新代码存放的moduleName
    packageName "com.vc.wd.common.util"//生成的新代码放在哪个包下
    className "Constant"//生成的常量类名
    fieldMap = [
        ACTIVITY_URL_MAIN : '/main/MainActivity',
        ACTIVITY_URL_ADD_CIRCLE : '/main/AddCircleActivity'
    ]//属性参数
}
```

- **常量插件使用都有哪些场景呢？**
    1. Arouter多模块之间path常量共享；
    2. Intent隐式跳转action/data共享问题；
    3. 只要你需要生成常量类，都可以灵活使用。

### 3.mvvm-v3 追风中。
v3版本绝对让你眼前一新，重新提起兴致，追求适配到4.x，由于ObjectBox框架最低支持4.0.3，所以本项目最低只能支持4.0.3版本机型，
新增功能处处都能体现代码的奇妙：
- 重新定义config.gradle中常量：分为SDK_VERSION（不因发布分支改变的常量）和active（跟分支相关的参数）。
这么做出于对项目在不同阶段不同部门的打包的时候，部分参数需要进行调整，例如：推送key，包名，域名，项目名等；
主要目的提升研发-测试-运维运营等部门沟通协作；
- 新增今日头条-<a href="https://github.com/bytedance/BoostMultiDex.git">抖音团队multidex打包</a>，适配4.x平台加载dex问题，详细请参照WDApplication代码；
- 拆分请求接口，放到各个相关module中，开发阶段尽量减少多人操作common包。

### 2.mvvm-v2 质量的提高来自不断地追求
v2版本在v1基础上进行组件化升级，由于对组件和模块的概念有了更深的了解，参考了网上的组件化教程，实践总结利弊之后，决定自己写一套优秀高效率的组件运行gradle：
- 公司场景：多模块业务联调，统一运行；
- 此demo种组件化打包好处：根据gradle配置动态改变模块的引入，分分钟能解决一个模块或者多个模块打包联调；
- 具体方式如下：
    1. 项目根目录新建了config.gradle存放系统变量；
    2. 项目根目录新建了module.gradle存放业务module中build.gradle公用参数，common和app不建议引入(部分重要配置必须写在这两个module中);
    3. 项目根目录build.gradle使用groovy动态改变app（module）对模块的引入；
    4. 所有选中的模块可根据自己要求，决定是否需要改变AndroidManifest.xml的引入，仿照open_main模块中的sourceSets；
    5. 支持多个Module—Application共存，方便处理推送，IM等组件初始化问题
> 注：请认真查看config.gradle中的变量备注

### 1.mvvm-v1 2020.04.20
这是一个整合架构，所有功能开发都只能在一个module中

