package com.subjectmanage.mapper;


import com.subjectmanage.beans.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    public File getFileById(int file_id);

    public List<File> getFileList(int startIndex, int pageSize);

    public int getFileListTotal();

    public int addFile(File file);

    public int deleteFile(int file_id);

    public int batchdeleteFile(int[] file_ids);

    public List<File> searchFile(String headline,Integer topic_id,Integer group_id,String release_time,String type,int startIndex, int pageSize);

    public int searchFileTotal(String headline,Integer topic_id,Integer group_id,String release_time,String type);
}
