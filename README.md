#Android图文发帖与浏览
##简述
很多的社交类项目中都有类似论坛的发帖功能，在探索了很多的实现方式之后找到了一种最为简单的实现。

**实现方式**

* 帖子编辑采用了github上一个start很高的富文本编辑的项目[https://github.com/wasabeef/richeditor-android](https://github.com/wasabeef/richeditor-android)，只是用了插入图片等一小部分功能。生成的内容将是一个标准的html。

* 浏览帖子就很简单了，直接使用WebView加载html，并实现与JS的交互

* 帖子内的图片增加了大图浏览模式

##目前还处于完善阶段，敬请期待
