package com.subjectmanage.services;


import com.subjectmanage.beans.File;
import com.subjectmanage.beans.Group;
import com.subjectmanage.mapper.FileMapper;
import com.subjectmanage.mapper.GroupMapper;
import com.subjectmanage.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private GroupMapper groupMapper;


    @Override
    public File getFileById(int file_id) {
        return fileMapper.getFileById(file_id);
    }

    @Override
    public int addFile(File file) {
        return fileMapper.addFile(file);
    }

    @Override
    public int deleteFile(int file_id) {
        return fileMapper.deleteFile(file_id);
    }

    @Override
    public boolean uploadFile(Group group,String type, int user_id,List<MultipartFile> uploadfile) {

        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String uploader_name = studentMapper.getStudentById(user_id).getStudent_name();
        int topic_id = group.getTopic_id();
        int group_id = group.getGroup_id();
        if (!uploadfile.isEmpty() && uploadfile.size()>0) {
            //循环输出上传的文件  MultipartFile 支持传输多个文件
            for (MultipartFile file : uploadfile) {
                String path = System.getProperty("user.dir");
                String realPath = path + "\\src\\main\\resources\\static";

                //String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
                // 获取上传文件的原始名称
                String originalFilename = file.getOriginalFilename();
                // 设置上传文件的保存地址目录
                String urlPath = "file/";

                String savePath = realPath+"/file/";
                String visitPath = urlPath;
                java.io.File filePath = new java.io.File(savePath);
                // 如果保存文件的地址不存在，就先创建目录
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                // 使用UUID重新命名上传的文件名称(上传人_uuid_原始文件名称)
                String newFilename = uploader_name + "_" + UUID.randomUUID() +
                        "_" + originalFilename;
                try {
                    // 使用MultipartFile接口的方法完成文件上传到指定位置
                    if(!file.isEmpty()){
                        file.transferTo(new java.io.File(savePath + newFilename));
                        File file1 = new File();
                        file1.setFile_url(visitPath+newFilename);
                        file1.setGroup_id(group_id);
                        file1.setType(type);
                        file1.setHeadline(originalFilename);
                        file1.setRelease_time(simpleDate.format(date));
                        file1.setTopic_id(topic_id);
                        fileMapper.addFile(file1);
                        System.out.println(file1.getFile_id());
                        if(type.equals("m")){//中期文档
                            if(group.getMfile_id()!=0) {
                                String url = realPath+fileMapper.getFileById(group.getMfile_id()).getFile_url();
                                java.io.File deleteFile = new java.io.File(url);
                                System.out.println(deleteFile.delete());
                                fileMapper.deleteFile(group.getMfile_id());
                            }
                            group.setMfile_id(file1.getFile_id());
                        }else if(type.equals("e")){
                            if(group.getEfile_id()!=0){
                            String url = realPath+fileMapper.getFileById(group.getMfile_id()).getFile_url();
                            java.io.File deleteFile = new java.io.File(url);
                                System.out.println(deleteFile.delete());
                                fileMapper.deleteFile(group.getEfile_id());
                            }
                            group.setEfile_id(file1.getFile_id());
                        }
                        groupMapper.updateGroup(group);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            }
        }
        return false;
    }
}
