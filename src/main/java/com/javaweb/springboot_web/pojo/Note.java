package com.javaweb.springboot_web.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Note {

    private Integer id;

    private String content;


//    private LocalDateTime updatetime;

}
