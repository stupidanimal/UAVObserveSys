package com.ocean;

import com.ocean.entity.BalloonVo;
import com.ocean.entity.ShipLocationVo;
import com.ocean.service.BalloonService;
import com.ocean.service.ShipLocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xingzhe
 * @date 2019-07-13
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FileImportTest {

    @Autowired
    ShipLocationService shipLocationService;
    @Autowired
    BalloonService balloonService;

    @Test
    public void fileImport() {
        //导入前先清空站点数据和气球数据
        balloonService.delAll();
        shipLocationService.delAll();
        // 获取指定目录下的全部文件
        String TargetPath = "../data/1/";
//        String TargetPath = "D:\\01proj\\UAVObserveSys\\data\\1";
        // 获取该目录下的全部文件
        Path[] files = getFiles(TargetPath);
        for(Path path:files){
            //根据文件导入
            fileImport(path.toString());
        }

    }

    private void saveBalloon(List<String> list, ShipLocationVo shipLocationVo) {
        BalloonVo balloonVo = new BalloonVo();
        balloonVo.setMinuteOrder(Integer.parseInt(getNumByDefaultVal(list.get(0))));
        balloonVo.setSecondOrder(Integer.parseInt(getNumByDefaultVal(list.get(1))));
        String timeStr = shipLocationVo.getDate() + " " + list.get(2);
        balloonVo.setTimeStamp(date2time(timeStr));
        balloonVo.setTimeStr(timeStr);
        balloonVo.setPre(Float.parseFloat(getNumByDefaultVal(list.get(3))));
        balloonVo.setTemp(Float.parseFloat(getNumByDefaultVal(list.get(4))));
        balloonVo.setHum(Float.parseFloat(getNumByDefaultVal(list.get(5))));
        balloonVo.setQpm(Float.parseFloat(getNumByDefaultVal(list.get(6))));
        balloonVo.setDir(Float.parseFloat(getNumByDefaultVal(list.get(7))));
        balloonVo.setVel(Float.parseFloat(getNumByDefaultVal(list.get(8))));
        balloonVo.setDew(Float.parseFloat(getNumByDefaultVal(list.get(9))));
        balloonVo.setDis(Float.parseFloat(getNumByDefaultVal(list.get(10))));
        balloonVo.setRise(Float.parseFloat(getNumByDefaultVal(list.get(11))));
        Double lat = Double.parseDouble(getNumByDefaultVal(list.get(12)));
        Double lng = Double.parseDouble(getNumByDefaultVal(list.get(13)));
        Double[] point = { lat, lng };
        balloonVo.setPoint(point);
        balloonVo.setPre(Float.parseFloat(getNumByDefaultVal(list.get(14))));
        balloonVo.setBalloonCode(shipLocationVo.getBalloonCode());
        balloonVo.setLineCode(shipLocationVo.getLineCode());
        balloonService.saveBalloon(balloonVo);
    }

    private void saveData(String lineStr, int lineNum, ShipLocationVo shipLocationVo) {
        switch (lineNum) {
        case 1:
            shipLocationVo.setBalloonCode(getVal(lineStr));
            break;
        case 2:
            String val = getVal(lineStr, "\\):");
            String date = val.split(" ")[0];
            shipLocationVo.setStartTime(date2time(val));
            shipLocationVo.setDate(date);
            break;
        case 3:
            shipLocationVo.setEndTime(date2time(getVal(lineStr, "\\):")));
            break;
        case 4:
            shipLocationVo.setMaxHeightTime(date2time(getVal(lineStr, "\\):")));
            break;
        case 5: shipLocationVo.setMaxHeight(Float.parseFloat(getVal(lineStr)));
            break;
        case 6:
            shipLocationVo.setAvgRise(Float.parseFloat(getVal(lineStr)));
            break;
        case 7:
            shipLocationVo.setLocationName(getVal(lineStr));
            break;
        case 8:
            String[] latlng = getVal(lineStr).split("  ");
            double lat = Double.parseDouble(latlng[0]);
            double lng = Double.parseDouble(latlng[1]);
            Double[] point = { lat, lng };
            shipLocationVo.setPoint(point);
            shipLocationService.saveShipLocationVo(shipLocationVo);
            break;
        }
    }

    private long date2time(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String getVal(String lineStr) {
        String result = lineStr.split(":")[1].trim();
        return getNumByDefaultVal(result);
    }

    private String getNumByDefaultVal(String in){
        if(StringUtils.isEmpty(in) || in.contains("//")){
            return "-99.99";
        } else {
            return in;
        }
    }

    private String getVal(String lineStr, String spilt) {
        return lineStr.split(spilt)[1].trim();
    }

    private Path[] getFiles(String path) {
        // 获取指定目录下的所有文件名称
        File file = new File(path);
        File[] array = file.listFiles();
        String[] filesName = new String[array.length];
        Path[] fullpaths = new Path[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                // filesName.add(array[i].getName());
                Path pathTemp = Paths.get(path, array[i].getName());
                fullpaths[i] = pathTemp;
                // fullpaths.add(pathTemp);
            } else {
                // fullpaths[i]='';
            }
        }
        return fullpaths;

    }

    private void fileImport(String path){
        File file = new File(path);
        if (file.exists()) {
            InputStream is = null;
            Reader reader = null;
            BufferedReader bufferedReader = null;
            try {
                is = new FileInputStream(file);
                reader = new InputStreamReader(is, StandardCharsets.UTF_8);
                bufferedReader = new BufferedReader(reader);
                String line = null;
                int tmp = 0;
                ShipLocationVo shipLocationVo = new ShipLocationVo();
                shipLocationVo.setLineCode("1");
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    tmp++;
                    if (tmp <= 8) {
                        saveData(line, tmp, shipLocationVo);
                    } else {
                        if (line.trim().length() > 1) {
                            if (!"0123456789".contains(line.trim().substring(0, 1))) {
                                System.out.println("不是这行");
                            } else {
                                String[] lines = line.split(" ");
                                List<String> list = new ArrayList<>();
                                Collections.addAll(list, lines);
                                list.removeIf(StringUtils::isEmpty);
                                if(list.size() == 15) {
                                    saveBalloon(list, shipLocationVo);
                                }else{
                                    System.out.println("第" + tmp + "行数据有问题!");
                                }
                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bufferedReader) {
                        bufferedReader.close();
                    }
                    if (null != reader) {
                        reader.close();
                    }
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
