# Android图文发帖与浏览

## 简述

很多的社交类项目中都有类似论坛的发帖功能，在探索了很多的实现方式之后找到了一种最为简单的实现。

>[richeditor-android](https://github.com/wasabeef/richeditor-android)

功能非常丰富，核心文件只有5个：

- RichEditor.java
- editor.html
- normalize.css
- rich_editor.js
- style.css

**RichEditor 是一个自定义的View，继承与WebView又有输入框的功能，输入的内容都转换为HTML。RichEditor核心是提供HTML的操作接口，调用rich_editor.js文件里的JavaScript方法对HTML进行操作。**

Post这个项目主要是利用`richeditor-android`实现了图文混排发帖的功能。

## 发帖

这个帖子的编辑是在APP本地进行的，并不是加载的H5。

### NewPostActivity

1. 布局文件中使用RichEditor，添加两个按钮拍照和图库，用来插入图片。
2. 可以调用RichEditor的各种API对样式进行设置，例如，字体大小，字体颜色。
3. 选择照片后调用RichEditor.insertImage在内容中插入图片，将直接显示出来。
4. 对于样式的调整，例如，图片居中，padding等可以在css文件中调整。
5. 最终生成的是一个HTML的内容，通过RichEditor.getHtml()获取最终的内容

## 浏览

帖子的浏览Post直接使用WebView加载了一个HTML，通常浏览帖子就是使用WebView加载URL。
1. Post加载的是本地资产文件asset里的一个HTML，这个HTML提供了一个js方法，传递了文章中所有图片的URL。
2. 在实际开发中，我们可以利用这种方式来传递参数，这里只是一个示例。
3. 在拿到所有图片URL集合后我们就可以实现点击图片浏览的功能，ImageDetailFragment是一个使用示例，采用Glide加载网络图片。



## License
Copyright 2017 qiaop

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
