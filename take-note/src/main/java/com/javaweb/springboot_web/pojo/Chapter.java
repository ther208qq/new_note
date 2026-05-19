package com.javaweb.springboot_web.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Chapter {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private String content;

    private Integer notebookId;

}
