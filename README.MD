# 航次项目概述

## 项目主要参与人员

主要参与人员(排名不分先后)：

[evaseemefly](https://github.com/evaseemefly)

[stupidanimal](https://github.com/stupidanimal)

[xz]()

## 项目目录

<pre><code>.
├──README.MD  
├── webclient/                      <= 前端工程 
├── webserver/                      <= 后端工程  
├── data/                           <= 保存部分测试数据
│ ├──  1/                           <= 原始版本数据 
│ ├──  2/                           <= 初步整编后的数据  
│ ├──  3/                           <= 汇交版本的数据  
├── document/                       <= 项目的所有文档均放在此处，
除了项目的README与 前后端分系统的README在前后端的项目根目录下
│ ├──  img/                         <= 部分markdown需要引用的图片
│ ├──  SCHEDULE.MD/                 <= 项目进度安排


</code></pre>

[前端项目说明](webclient\README.md)
[后端项目说明](webserver\README.md)
[进度安排](document\SCHEDULE.md)

---

后端 by [xz]()
前端 by [evaseemefly](https://github.com/evaseemefly) and [stupidanimal](https://github.com/stupidanimal)

---

一些规约：

1. git 的规约

所有的本地 ide 的相关配置请不要提交

2. 分支及 todo 规约
   对于本地已有的已忽略的文件，第一次拉取时请先删除，随后再恢复，此种方法比较省力。  
    对于大面积修改或新增的代码段，请在起始位置加入`TODO[] yyyy-mm-dd -by user`  
    其中`[x]`为已修改完成的 todo  
    `[*]`为尚未完成的 todo
   每次修改之后请新建其他的修改分支，分支命名规范:
   > 新增的主要功能分支： _added*分支描述*作者_
   > 重构的主要功能分支: _rebbuild*分支描述*作者_

修改后会将新建分支的 release 版本，合并至主分支 master 中，请及时同步 master 分支。  
合并后有错误请先在本地自行修改并调试，再提交请求。

---

## 其他规约

代码规范参考  
[JavaScript 编码规范](https://github.com/fex-team/styleguide/blob/master/javascript.md)  
[JavaScript 开发规范](https://juejin.im/entry/599d433cf265da24797b5c66)

python 参考规范（参考 pep8）
[pep8 规范](https://zh-google-styleguide.readthedocs.io/en/latest/google-python-styleguide/)

引入的顺序

1. 框架库
2. 第三方库

3. 自定义组件及 `mixin`
4. 自定义的接口及 `model`

---

## 引用的第三方库

会逐渐补充

js 的 date 库:
[fecha](https://github.com/taylorhakes/fecha)
[vue-class-component](https://github.com/vuejs/vue-class-component)
[Vue Property Decorator](https://github.com/kaorun343/vue-property-decorator)

地图相关库：
[超图的 iclent](http://iclient.supermap.io/)

### 2 python 使用的库

[python docx](https://python-docx.readthedocs.io/en/latest/)
