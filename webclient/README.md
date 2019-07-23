本文档为前端的相关说明文档

## 本项目说明

- 1 探空
- 2 走航（或航线？）

## 前端项目工程及说明

组件的命名规范为`驼峰式`命名，首字母小写
推荐使用`document this` 组件 对 ts 的 class 进行注释，很好用额

<pre>
<code>
├──README.MD  
├── document                      <= 一些遇见的问题的归档 
├── src                           <= 所有页面的所在根目录
│ ├── main.ts                     <= vue项目的入口文件，加载一些公共的组件
│ ├── App.vue                     <= vue 页面的入口文件
│ ├── api                         <= 存放所有与后端交互的api所在路径，现在统一为api.ts
│ │ ├── api.ts                    <= 先统一放在该文件内，也可以分离成多个api文件
│ ├── common                      <= 存放一些功能的文件，eg：enum等
│ │ ├── enum                      <= ts用到的公共枚举ts文件，并按照主要的功能进行分类
│ │ │ ├── map.ts
│ │ │ ├── menu.ts
....
│ ├── interface                   <= 用来定义ts的接口的所在目录    
│ ├── middleModel                 <= 前台需要使用到的中间class  
│ ├── views                       <= 存放所有的视图所在路径（有些人使用page，我习惯使用views命名）  
│ │ ├── content                  <= 主要页面
│ │ │ ├──  mapSearch             <= 以功能名称来划分的子路径 
│ │ ├── head                     <= 页面的head集中放在此处
│ │ ├── footer                   <= 页面的footer集中放在此处
│ │ ├── members                   <= 一些功能的组件，可复用的一些组件放在此处（也有人叫components，我习惯叫members）

│ │ ├── backup                    <= 保存一些备份的页面（基本不再使用了）——本系统中不再需要这个路径（在之前的台风系统中发现由于该备份的一些vue可能会出现，修改了接口或重定义了class，导致本路径下的部分vue提示有错误，而为此修改又无意义——因为已经不使用了）

│ ├── index                       <= 几个 home 页面  （本系统拟考虑不再使用此路径，直接放在content路径中）
│ ├── member                      <= 组件
├── store                           <= 使用vuex进行存储的数据
│ ├── modules                      <= 将vuex按照主要的页面（或功能）划分为单独的ts文件
│ │ ├── map.ts                     <= eg：地图页面的全部的vuex存放在此处
│ ├─  index.ts                      <= 为所有的vuex的入口文件
</code>
</pre>
