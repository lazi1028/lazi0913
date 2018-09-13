package com.bcht.axletempmonitor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

//import java.io.*;

/**
 * 文件上传
 * createdBy lazi 2018/08/21
 */

public class FileUploadUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    /**
     * 文件上传
     *
     */
    public static File fileUplaod(MultipartFile file,String uploadPath){
         Boolean flag;
        String fileName = file.getOriginalFilename();
        logger.info("上传的原文件名："+fileName);

        String suffix = fileName.substring(fileName.lastIndexOf("."));

       // String serverFileName = fileName.substring(0,fileName.lastIndexOf("."))+new Date().getTime()+suffix;
        File serverFile = new File(uploadPath + fileName);
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(serverFile));
            out.write(file.getBytes());

            flag=true;
        } catch (IOException e) {
            e.printStackTrace();
            flag=false;
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return serverFile;
    }

    /**
     * 压缩文件上传
     *
     * @param srcFiles 源文件
     * @param zipFile 压缩文件
     * @return Boolean
     */
    public static Boolean zipFileUpload(File[] srcFiles,File zipFile){
       Boolean flag=ZipFilesUtils.zipFiles(srcFiles,zipFile);
       return flag;
    }

    /**
     * MultipartFile 转 File
     *
     */
    public static File multipartFileToFile(MultipartFile multipartFile,File file,String tmppath) {
        File dir = new File(tmppath);
        if(!dir.exists()) {
            dir.mkdir();
        }

        if(multipartFile.equals("") || multipartFile.getSize()<=0){
            file=new File(tmppath+multipartFile.getOriginalFilename());
        }else{
            InputStream ins=null;
            FileOutputStream os=null;
            try {
                ins = multipartFile.getInputStream();
               // file=new File(multipartFile.getOriginalFilename());
                file=new File(tmppath+multipartFile.getOriginalFilename());
                os = new FileOutputStream(file);
                int len=0;
                byte[] bytes = new byte[8192];
                while((len=ins.read(bytes))!= -1){
                    os.write(bytes,0,len);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try{
                    if(os!=null){
                        os.close();
                    }
                    if(ins!=null){
                        //ins.close();
                        ins.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 删除临时文件
     *
     */
    public static void deleteTmpFiles(File[] files){
        if(files!=null && files.length>0){
            for (File f:files ) {
               new File(f.toURI()).delete();
            }
        }
    }

    /**
     * 文件下载
     */
    public static void fileDownload(String path, HttpServletResponse response){
        try{

            File file = new File(path);
            // 取得文件名
            String filename = file.getName();
            // 取得文件的后缀名。
            //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
           // response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename).getBytes());
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(buffer);
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
