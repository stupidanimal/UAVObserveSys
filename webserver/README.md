## 接口文档
### 1.获取所有航次信息
待定
### 2.根据航次lineCode获取所有站位信息
url地址:http://localhost:60888/ship_location?lineCode=1 <br>
入参<br>
lineCode:string:航次id，唯一<br>
返回数据结构
````json
{
    "lineCode":"1",
    "shipLocationVos":[
        {
            "lineCode":"1",
            "balloonCode":"15188603",
            "startTime":1469080741,
            "endTime":1469096869,
            "maxHeightTime":1469085183,
            "maxHeight":18789.3,
            "avgRise":253.7,
            "locationName":"IND-STC083",
            "date":"2016-07-21",
            "point":[
                104.50972,
                -7.50361
            ]
        }
    ]
}
````
### 3.根据探空仪编号balloonCode获取所有观察信息(分钟维度，每分钟第一条)
url地址:http://localhost:60888/balloon?balloonCode=15188603<br>
入参<br>
balloonCode:探空仪唯一id<br>
返回数据结构
````json
{
    "balloonCode":"15188603",
    "lineCode":"1",
    "balloons":[
        {
            "lineCode":"1",
            "balloonCode":"15188603",
            "minuteOrder":0,
            "secondOrder":0,
            "timeStamp":1469080741,
            "timeStr":"2016-07-21 13:59:01",
            "pre":16,
            "temp":28.39,
            "hum":76.8,
            "qpm":16,
            "dir":49.7,
            "vel":0.21,
            "dew":23.9,
            "dis":0.03,
            "rise":0,
            "alt":-1,
            "point":[
                104.51253,
                -7.505
            ]
        },
        {
            "lineCode":"1",
            "balloonCode":"15188603",
            "minuteOrder":1,
            "secondOrder":0,
            "timeStamp":1469080801,
            "timeStr":"2016-07-21 14:00:01",
            "pre":13.9,
            "temp":28.16,
            "hum":75.1,
            "qpm":13.8,
            "dir":354.8,
            "vel":1.32,
            "dew":23.3,
            "dis":0.03,
            "rise":-0.1,
            "alt":-1,
            "point":[
                104.51279,
                -7.50521
            ]
        }
    ]
}
````
### 4.根据探空仪编号balloonCode及分钟顺序编号，获取某一分钟内的60条秒级数据
