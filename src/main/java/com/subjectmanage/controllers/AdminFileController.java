package com.subjectmanage.controllers;


import com.subjectmanage.services.FileServiceImpl;
import com.subjectmanage.utils.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.subjectmanage.beans.File;

@Controller
@RequestMapping("/admin/file/")
public class AdminFileController {
    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping("/toFileList")
    public String toNewsList(){
        return "admin-file";
    }

    @RequestMapping("/getFileList")
    @ResponseBody
    public LayuiTableData getNewsList(@RequestParam int page, @RequestParam int limit, HttpSession session){

        int startIndex = (page-1)*limit;
        int count = fileService.getFileListTotal();
        List<File> fileList = fileService.getFileList(startIndex,limit);
        System.out.println(fileList);
        LayuiTableData layuiTableData = LayuiTableData.layData(count,fileList);//转换成前端需要的数据格式
        return layuiTableData;
    }

    @RequestMapping("/batchdeleteFile")
    @ResponseBody
    public Map<String,Object> batchdeleteNews(@RequestBody int[] file_ids){

        Map<String,Object> map = new HashMap<>();
        if(fileService.batchdeleteFile(file_ids)>0){
            map.put("result","success");
        }else{
            map.put("result","failed");
        }
        return map;
    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public Map<String,Object> deleteNews(@RequestBody int file_id){

        Map<String,Object> map = new HashMap<>();
        if(fileService.deleteFile(file_id)>0){
            map.put("result","success");
        }else{
            map.put("result","failed");
        }
        return map;
    }

    @RequestMapping("/searchFile")
    @ResponseBody
    public LayuiTableData searchFileList(@RequestParam int page, @RequestParam int limit,@RequestParam String headline,
                                         @RequestParam Integer group_id, @RequestParam Integer topic_id,
                                         @RequestParam String release_time, @RequestParam String type,
                                         HttpSession session){

        if(release_time.equals("")){
            release_time = null;
        }
        if(type.equals("unselect")){
            type = null;
        }
        int startIndex = (page-1)*limit;
        int count = fileService.searchFileTotal(headline,topic_id,group_id,release_time,type);
        List<File> fileList = fileService.searchFile(headline,topic_id,group_id,release_time,type,startIndex,limit) ;
        LayuiTableData layuiTableData = LayuiTableData.layData(count,fileList);//转换成前端需要的数据格式
        return layuiTableData;
    }

    @RequestMapping(value="/download")
    public void downloadFile(HttpServletRequest req, Model model, HttpServletResponse response, @RequestParam int file_id)
            throws ServletException, IOException {

        File file = fileService.getFileById(file_id);
        String url = file.getFile_url();
        Path path = Paths.get("src/main/resources/static/",url);
        String filename=path.toString();
        String downFilename=filename.substring(filename.lastIndexOf("\\")+1);//要下载的文件名称
        response.setContentType("text/plain");
        response.setHeader("Location",downFilename);
        response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream( filename);
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }
}
