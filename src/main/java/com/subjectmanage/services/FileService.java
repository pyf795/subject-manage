package com.subjectmanage.services;

import com.subjectmanage.beans.File;
import com.subjectmanage.beans.Group;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public File getFileById(int file_id);

    public int addFile(File file);

    public int deleteFile(int file_id);

    public boolean uploadFile(Group group, String type, int user_id, List<MultipartFile> uploadfile);
}
