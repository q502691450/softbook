package com.ht.demo;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FtlTest {

    public static void process(String template, Map<String, Object> data) throws Exception {
        Configuration cfg = new Configuration();
//        cfg.setSettings(FtlTest.class.getResourceAsStream("/config/ftl.properties"));
        cfg.setClassForTemplateLoading(FtlTest.class,"/com/ht/template");
//        cfg.setDirectoryForTemplateLoading(new File("D:\\OwnerProject\\FtlDemo\\src\\com\\ht\\template"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        //设置字符集
        cfg.setDefaultEncoding("UTF-8");

        //设置尖括号语法和方括号语法,默认是自动检测语法
        //  自动 AUTO_DETECT_TAG_SYNTAX
        //  尖括号 ANGLE_BRACKET_TAG_SYNTAX
        //  方括号 SQUARE_BRACKET_TAG_SYNTAX
        cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);

        Template temp = cfg.getTemplate(template);

        Writer out = new OutputStreamWriter(System.out);
        temp.process(data, out);
        out.flush();

//        StringWriter writer = new StringWriter();
//        temp.process(data, writer);
//        temp.process(data, writer);
//        System.out.println(writer.toString());
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userName","大司徒");

        data.put("userSex",1);

        List<String> stringList = new ArrayList<String>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        data.put("stringList",stringList);

        data.put("testInclude","测试引入模板文件");

        data.put("address","桂箐路");
        data.put("email","100001");
        data.put("phone","021-11111111");



        process("test.ftl", data);
    }
}
