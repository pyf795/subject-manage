package com.subjectmanage.mapper;


import com.subjectmanage.beans.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    public File getFileById(int file_id);

    public int addFile(File file);

    public int deleteFile(int file_id);
}
