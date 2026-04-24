package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
public class ChapterController {

    @Autowired //自动注入
    private ChapterService chapterService;

    //展示所有笔记
    @RequestMapping("/showChapter")
    public List<Chapter> showNote(){


        List<Chapter> chapterList = chapterService.findAll();

        return chapterList;
    }

    //插入笔记
    @RequestMapping("/insert")
    public String insert(@RequestBody Chapter chapter){

        System.out.println("正在调用");
        chapterService.insert(chapter);
        return "success";
    }

    //根据id删除单个笔记
    @RequestMapping("/deletebyid")
    public String deletebyid(@RequestParam Integer id){

        Integer lines = chapterService.deleteById(id);

        System.out.println("删除成功,已删除:"+lines+"行");

        return "success delete";
    }

    //根据id更新笔记
    @RequestMapping("/updatebyid")
    public String updatebyid(@RequestBody Chapter chapter){

        Integer lines = chapterService.updateById(chapter);

        return "success update";

    }

    //删除所有笔记
    @RequestMapping("/clear")
    public String clear(){
        chapterService.clear();

        return "success clear";
    }

}
