package com.javaweb.springboot_web.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Note {


    private Integer id;

    private String content;

    @TableField("chapter_id")
    private String chapterId;


//    private LocalDateTime localDateTime;


//    private LocalDateTime updatetime;

}
