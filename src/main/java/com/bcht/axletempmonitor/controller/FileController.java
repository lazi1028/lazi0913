package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.annotation.MyLog;
import com.bcht.axletempmonitor.pojo.BchFile;
import com.bcht.axletempmonitor.pojo.BchTraininfo;
import com.bcht.axletempmonitor.service.ITrainService;
import com.bcht.axletempmonitor.utils.FileUploadUtils;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.utils.ResultModel;
import com.bcht.axletempmonitor.utils.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ITrainService trainServiceImpl;
    @Value("${file.uploadpath}")
    private String uploadPath;
    @Value("${file.tmppath}")
    private String tmppath;
    @Value("${file.downloadpath}")
    private String downloadpath;

    @MyLog(value = "数据导入")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResultModel fileUpload(@RequestParam(value = "files",required = false) MultipartFile[] files,@RequestParam(value = "trnnum",required = false) String trnnum ,
                                  @RequestParam(value = "upload1",required = false) String upload1 ,HttpServletRequest request){
        if(MyStringUtils.isEmpty(trnnum)){
            return ResultModel.ERROR(ResultStatus.TRAINNUM_NOT_INPUT);
        }
        if(files==null || files.length<=0){
            return ResultModel.ERROR(ResultStatus.FILE_UPLOAD_NOT_NULL);
        }
        logger.info("上传文件个数："+files.length);
        // String uploadPath = request.getSession().getServletContext().getRealPath("/") + "uploadFile/";
        //String uploadPath = "E:/workspace/axletempmonitor/uploadFile/";
        File dir = new File(uploadPath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        BchTraininfo trainInfo = trainServiceImpl.queryTrainByNum(trnnum);
        if(trainInfo==null){
            return ResultModel.ERROR(ResultStatus.TRAINNUM_NOT_EXIST);
        }
        //MultipartFile file;
        Map<String, Object> map = new HashMap<String, Object>();
        List<MultipartFile> mfList=new ArrayList();//校验通过的文件放fileList中
        Map<String, Object> fileMap = new HashMap<String, Object>();//校验通过的提示信息fileMap中
        for (int i=0;i<files.length;i++){
            if(files[i]!=null){
                String fileName = files[i].getOriginalFilename();
                logger.info("上传的原文件名："+fileName);
                //int index=fileName.lastIndexOf(".");
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                String name=fileName.substring(0,fileName.lastIndexOf("."));//不加后缀的文件名
                if(!(".dat").equals(suffix)){
                    map.put(fileName,ResultStatus.FILE_TYPE_ERROR.getMessage());
                    //return ResultModel.ERROR(ResultStatus.FILE_TYPE_ERROR);
                   continue;
                }
                if(trainInfo.getTrntype().contains("标动")){
                    if(!MyStringUtils.fileNameType(name,"bd")){
                        map.put(fileName,ResultStatus.FILE_TRNTYPE_ERROR.getMessage());
                        continue;
                    }
                }else{
                    if(!MyStringUtils.fileNameType(name,"tx")){
                        map.put(fileName,ResultStatus.FILE_TRNTYPE_ERROR.getMessage());
                        continue;
                    }
                }
                //校验通过后
                mfList.add(files[i]);
            }
        }
        logger.info("校验通过的文件个数:"+mfList.size());
       // map.put("msg",fileList);
        if(MyStringUtils.isEmpty(upload1)){
            //返回错误文件信息
            return ResultModel.OK(map);
        }else {
            //执行上传
            File[] srcFiles=null;
            if(mfList.size()>0){
                srcFiles=new File[mfList.size()];
                for(int i=0;i<mfList.size();i++){
                    File file=null;
                    file = FileUploadUtils.multipartFileToFile(mfList.get(i), file,tmppath);
                    srcFiles[i]=file;
                    //logger.info(srcFiles[i].getAbsolutePath());
                }
            }else {
                return ResultModel.ERROR(ResultStatus.FILE_UPLOAD_ISNULL);
            }
            //logger.info("mutipartFile 转 file结果："+srcFiles.toString());
            String zipFileName = "zipfile"+new Date().getTime()+".zip";
            File zipFile=new File(uploadPath+zipFileName);
            Boolean bool=FileUploadUtils.zipFileUpload(srcFiles,zipFile);
            if(bool == true){

                FileUploadUtils.deleteTmpFiles(srcFiles);
                fileMap.put("msg",ResultStatus.FILE_UPLOAD_SUCCESS.getMessage());
            }else{
                fileMap.put("msg",ResultStatus.FILE_UPLOAD_FAILED.getMessage());
            }
            return ResultModel.OK(fileMap);
        }
    }

    @RequestMapping(value = "/uploadSingle",method = RequestMethod.POST)
    public ResultModel fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        File dir = new File(uploadPath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        Map<String,String> map=new HashMap<>();
        if(file!=null){
            File file1 = FileUploadUtils.fileUplaod(file, uploadPath);
            if(file1!=null){
                String filename=file1.getName();
                String absolutePath = file1.getAbsolutePath();
                String suffix = filename.substring(filename.lastIndexOf(".")+1);
                double size = Double.parseDouble(String.valueOf(file1.length() / 1024));
                BchFile file2=new BchFile();
                file2.setFilename(filename);
                file2.setFilepath(absolutePath);
                file2.setFilesuffix(suffix);
                file2.setFilesize(size);
                int i=trainServiceImpl.addFile(file2);
                if(i>0){
                    map.put("msg","附件上传成功");
                    map.put("fileid", String.valueOf(file2.getId()));
                }else{
                    map.put("msg","附近上传失败");
                }
            }
        }
        return ResultModel.OK(map);
    }

    //文件下载
    @RequestMapping(value = "/fileDownload",method = RequestMethod.POST)
    public void fileDownload(HttpServletResponse response){
      /* File f1=null;
       File file1 = FileUploadUtils.multipartFileToFile(file, f1, tmppath);
       FileUploadUtils.fileDownload(file1.getAbsolutePath(),response);*/
       String path=downloadpath+"brkdwncode.xlsx";
       FileUploadUtils.fileDownload(path,response);
    }
}
