package com.subjectmanage.services;

import com.subjectmanage.beans.File;
import com.subjectmanage.beans.Group;
import com.subjectmanage.beans.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public File getFileById(int file_id);

    public List<File> getFileList(int startIndex, int pageSize);

    public int getFileListTotal();

    public int addFile(File file);

    public int deleteFile(int file_id);

    public int batchdeleteFile(int[] file_ids);

    public List<File> searchFile(String headline,Integer topic_id,Integer group_id,String release_time,String type,int startIndex, int pageSize);

    public int searchFileTotal(String headline,Integer topic_id,Integer group_id,String release_time,String type);

    public boolean uploadFile(Group group, String type, int user_id, List<MultipartFile> uploadfile);

    public boolean uploadMissionFile(String fileType,Teacher teacher, int topic_id, List<MultipartFile> uploadfile);

    public boolean uploadScoreFile(String fileType,Teacher teacher, int group_id,int score, List<MultipartFile> uploadfile);
}
