package com.site.model.project;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by wang0 on 2016/9/21.
 */

@Getter
@Setter
@ToString
public class Project implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String content;
    private String images;
    private String groups;
}
