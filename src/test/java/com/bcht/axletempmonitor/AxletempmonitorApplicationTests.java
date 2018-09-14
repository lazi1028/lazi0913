package com.bcht.axletempmonitor;

import com.bcht.axletempmonitor.controller.FileController;
import com.bcht.axletempmonitor.controller.UserController;
import com.bcht.axletempmonitor.pojo.BchSensorStatus;
import com.bcht.axletempmonitor.pojo.BchSensorTemperature;
import com.bcht.axletempmonitor.utils.ZipFilesUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AxletempmonitorApplicationTests {

    //private IUserService userServiceImpl;
    @Autowired
    private UserController userController;

    @Value("${file.uploadpath}")
    private String uploadPath;
    @Value("${file.tmppath}")
    private String tmppath;
    @Test
    public void test1() {
        File f1=new File("F:\\数据导入\\11.txt");
        File f2=new File("F:\\数据导入\\22.txt");
        File[] files={f1,f2};
        File zip=new File("F:\\数据导入\\zipfiles1");

        ZipFilesUtils.zipFiles(files,zip);
        /*File zip=new File("F:\\数据导入\\zipfiles");
        ZipFilesUtils.unZipFiles(zip,"F:\\数据导入\\unzip");*/

    }
    @Test
    public void test2(){
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "admin";//密码原值
        Object salt = null;//盐值
        int hashIterations = 1;//加密1次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        System.out.println("admin加密后结果："+result);
    }

    @Test
    public void test3(){
        //System.out.println(uploadPath);
        //System.out.println(tmppath);
        String classPath = FileController.class.getResource("/").getPath();
        ///E:/workspace/axletempmonitor/target/test-classes/
        System.out.println(classPath+"--");
    }

    @Test
    public void test4() throws Exception {
        BchSensorTemperature tmp=new BchSensorTemperature();
        BchSensorStatus bchSensorStatus = new BchSensorStatus();
        bchSensorStatus.setAb1sts("01");
        tmp.setAb1tmp("11");
        tmp.setSensorStatus(bchSensorStatus);
       // String i="get"+"ab1".substring(0,1).toUpperCase()+"ab1".substring(1)+"tmp()";
       // Method method = tmp.getClass().getDeclaredMethod(i, String.class);
        Object ab1tmp = getGetMethod(tmp, "ab1tmp");
        Object bch = getGetMethod(tmp, "sensorStatus");
        Object o=getGetMethod(bchSensorStatus,"ab1sts");
        System.out.println(ab1tmp+"--"+bch+"="+o);
       /* String format="yyyy-MM-dd HH:mm:ss";
        String d1="2018-08-23 17:22:01";
        String d2="2018-08-23 17:22:08";
        long time1=DateUtils.parseDate(format,d1).getTime();
        long time2=DateUtils.parseDate(format,d2).getTime();*/
       // System.out.println(time2+"----"+time1);
       /* for(int i=0;i<=(time2-time1)/1000;i++){
           // System.out.println(DateUtils.timeToString(time1+i*1000,format));
        }*/

    }
    @Test
    public void test5(){
        System.out.println();

    }
    public static Object getGetMethod(Object ob , String name)throws Exception {
        Method[] m = ob.getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            if (("get" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                return m[i].invoke(ob);
            }
        }
        return null;
    }
}
