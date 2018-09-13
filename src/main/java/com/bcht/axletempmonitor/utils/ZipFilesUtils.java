package com.bcht.axletempmonitor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩
 * createdBy lazi 2018/08/21
 */
public class ZipFilesUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZipFilesUtils.class);

    /**
     * 文件压缩
     * @param srcFiles 源文件
     * @param zipFile  压缩后文件
     */
    public static Boolean zipFiles(File[] srcFiles, File zipFile) {
        Boolean flag;
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String zipFileName=zipFile.getAbsolutePath();
        logger.info("压缩文件getAbsolutePath()："+zipFileName);
        /*if(!zipFileName.toLowerCase().endsWith(".zip")){
            zipFile.renameTo(new File(zipFileName+".zip"));
        }*/

        FileOutputStream fileOutputStream=null;
        ZipOutputStream zipOutputStream=null;
        FileInputStream fileInputStream = null;
        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.length; i++) {
                logger.info("原文件+"+i+"的getAbsolutePath()："+srcFiles[i].getAbsolutePath());
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles[i]);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            logger.info("压缩完成");
            flag=true;
        } catch (IOException e) {
            e.printStackTrace();
            flag=false;
        }finally {
            try {
                if(fileInputStream!=null){
                    fileInputStream.close();
                }
                if(zipOutputStream!=null){
                    zipOutputStream.closeEntry();
                    zipOutputStream.close();
                }
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }

    /**
     * 解压
     */
    public static void unZipFiles(File zipfile,String descDir) {
        InputStream in=null;
        OutputStream out=null;
        try {
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                in = zf.getInputStream(entry);
                out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                logger.info("解压缩完成.............");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
