任务分工
子系统1为显示船舶轨迹，船舶在指定的站位（或者探空仪code）放置的气球，显示每个气球的连续的观测数据。
站位之前是离散的。
ballonModel中的linecode与shipmodel中的对应字段关联，
balloncode与shipmodel中的对应字段关联
大体orm模型如下：
![avatar](/document/img/20190711002.png)
![avatar](/document/img/20190711003.png)

后端接口遵循restfulapi规范
![avatar](/document/img/111.png)
<table>
  <thead>
    <tr>
      <th>描述</th>
      <th align="left">传入参数</th>
      <th align="right">url</th>
      <th align="center">func name</th>
      <th align="center">返回json</th>
      <th align="center">备注</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>返回连续的采样值</td>
      <td align="left">balloncode,interval（时间间隔单位s）</td>
      <td align="right">你自己定吧，写在这块就行</td>
      <td align="right"></td>
      <td>1.1</td>
    </tr>
    <tr>
      <td>返回多个要素的连续的采样值</td>
      <td >balloncode:[],interval</td>
      <td ></td>
      <td ></td>
      <td >1.2</td>
      <td >数组</td>
    </tr>
    <tr>
      <td>根据指定的的航次编号 linecode 返回每个站位的空间信息值</td>
      <td >linecode</td>
      <td ></td>
      <td ></td>
      <td >1.3</td>
      <td >数组</td>
    </tr>
  </tbody>
</table>

* [1.1](#1)
<pre>
<code>
balloncode         
	|__linecode
	|__balloncode
	    |__code
		|__realdata
			|__val
			|__datetime
			|__alt
		|__factor
linecode
</code>
</pre>
* [1.2](#1)
<pre>
<code>
balloncode         
	|__linecode
	|__balloncode
	|__code
	|__realdata
		|__val
		|__datetime
		|__alt
	|__factor
linecode
</code>
</pre>
* [1.3](#1)
<pre>
<code>
Linecode
Point
	|__lat
	|__lon
balloncode
</code>
</pre>