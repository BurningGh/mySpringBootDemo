package com.example.mybatisplus;

import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 * @date 2019/9/26
 */
public class Json2Markdown {
    public static void main(String[] args) {
        String readToString = readToString("E:\\IdeaProjects8\\demo\\mybatisplus\\src\\main\\resources\\data.json");
//        System.out.println(readToString);
        List<Data> data = JSONArray.parseArray(readToString, Data.class);

        System.out.println(data);
        List<String> list = new ArrayList<>();
        list.add("|属性名称" + "|参数类型|" + "描述|");
        list.add("|---------|----|-----------|");


        assert data != null;
        for (Data datum : data) {
            String[] split = datum.getType().split("java.lang.");
            list.add("|" + datum.getName() + "|" + (split.length > 1 ? split[1] : split[0]) + "|" + datum.getDescription() + "|");
        }

        writeListToFile(list);
    }


    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 实现写操作方法
     */
    public static void writeListToFile(List<String> listStr) {
        File file = new File("E:\\IdeaProjects8\\demo\\mybatisplus\\src\\main\\resources\\data.md");// 要写入的文件路径
        if (!file.exists()) {// 判断文件是否存在
            try {
                file.createNewFile();// 如果文件不存在创建文件
                System.out.println("文件" + file.getName() + "不存在已为您创建!");
            } catch (IOException e) {
                System.out.println("创建文件异常!");
                e.printStackTrace();
            }
        } else {
            System.out.println("文件" + file.getName() + "已存在!");
        }

        for (String str : listStr) {// 遍历listStr集合
            FileOutputStream fos = null;
            PrintStream ps = null;
            try {
                fos = new FileOutputStream(file, true);// 文件输出流	追加
                ps = new PrintStream(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String string = str + "\r\n";// +换行
            ps.print(string); // 执行写操作
            ps.close();    // 关闭流

        }

        System.out.println("文件写入完毕!");
    }

}

@lombok.Data
class Data {
    private String name;
    private String type;
    private String description;
    private String sourceType;
}
