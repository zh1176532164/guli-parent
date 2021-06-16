package com.atguigu.eduservice.entity.subject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-05 21:02
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OneEduSubject {
    private String id;
    private String title;
    private List<TwoEduSubject> eduSubjects=new ArrayList<>();
}
