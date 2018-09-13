package com.bcht.axletempmonitor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtils {

    //字符串是否为空
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    //数据导入文件名规范校验
    public static boolean fileNameType(String s,String type){
        String regEx="";
        if(type=="bd"){
           //标动
           regEx="[1-9][0-9]{3}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])-[1-8]{1}-hg-[A|B]";
        }else{
            //统型
            regEx="[1-9][0-9]{3}(0[1-9]|1[0-2])([0-2][1-9]|3[0-1])-[1-8]{1}-hg";
        }
        boolean rs= regMatcher(regEx,s);
        return rs;
    }

    /**
     * 正则匹配
     * @param regEx 正则规则
     * @param str 要测试的字符串
     * @return
     */
    public static boolean regMatcher(String regEx,String str){
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }

    public static boolean validRealname(String realname){
        String regEx="[\\u4e00-\\u9fa5]{1,10}";
        return regMatcher(regEx,realname);
    }

    public static boolean validUsername(String username){
        String regEx="[A-Za-z0-9]{2,30}";
        return regMatcher(regEx,username);
    }
    public static boolean validPassword(String password){
        String regEx="[A-Za-z0-9]{6,20}";
        return regMatcher(regEx,password);
    }
    public static boolean validPhone(String mobilephone){
        String regEx="[0-9]{11}";
        return regMatcher(regEx,mobilephone);
    }
    public static boolean validDepartment(String department){
        String regEx="[\\u4e00-\\u9fa5]{1,20}";
        return regMatcher(regEx,department);
    }
    public static boolean validRoledesc(String roledesc){
        String regEx="[\\u4e00-\\u9fa5]{1,200}";
        return regMatcher(regEx,roledesc);
    }
    public static boolean validTrnnum(String trnnum){
        String regEx="[0-9]{4}";
        return regMatcher(regEx,trnnum);
    }
    public static boolean validBrkdwnCode(String code){
        String regEx="[A-Za-z0-9]{6}";
        return regMatcher(regEx,code);
    }
   // [\s\S]表示 匹配任意字符，包括/n换行符
    public static boolean validBrkdwnname(String name){
        String regEx="[[\\u4e00-\\u9fa5] [\\s\\S] [0-9]]{1,200}";
        return regMatcher(regEx,name);
    }


}
