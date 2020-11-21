package com.subjectmanage.utils;

import java.util.HashMap;
import java.util.List;

public class LayuiTableData extends HashMap<String,Object> {
    public static LayuiTableData layData (int count, List<?> data){ // List<?> data ?代表
        LayuiTableData layui = new LayuiTableData();
        layui.put("code", 0);		// 这里的状态码必须设为0或者200
        layui.put("msg", "");		// 这里一般为空即可
        layui.put("count",count);	// 数据的总数（必须）
        layui.put("data",data);		// 数据（必须）
        return layui;
    }
}
