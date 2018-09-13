package com.bcht.axletempmonitor.utils;

import com.bcht.axletempmonitor.controller.LoginController;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5 加密
 */
public class PwdEncryUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * @param hashAlgorithmName  加密方式  本项目使用MD5
     * @param crdentials  原始密码
     * @param salt 盐值 默认null
     * @param hashIterations 加密次数  默认1次
     * @return  加密结果
     */
     public static Object getEncryptionResult(String hashAlgorithmName,Object crdentials,Object salt,int hashIterations){
         Object obj = new SimpleHash(hashAlgorithmName, crdentials, salt,hashIterations);
         logger.info("MDS加密方法：原始密码+"+crdentials+"。。。加密后密码："+obj);
         return obj;
     }


}
