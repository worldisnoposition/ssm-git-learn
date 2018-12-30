package com.create.compare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    private Test2 test2;
    @Autowired
    private Test1 test1;
    @RequestMapping(value = "/testCompare" , method = RequestMethod.GET)
    public String testCompare(){
        Collection<TestBean1> newData = new ArrayList<>();
        newData.add(new TestBean1("id1","hello1.1",1));
        newData.add(new TestBean1("id2","hello2",1));
        newData.add(new TestBean1("id3","hello3",1));
        Collection<TestBean1> oldData = new ArrayList<>();
        oldData.add(new TestBean1("id0","hello0",1));
        oldData.add(new TestBean1("id1","hello1",1));
        oldData.add(new TestBean1("id2","hello2",1));
        CompareResult<TestBean1> compareResult = test1.compare(oldData,newData);
        CompareResult<TestBean1> compareResult2 = test2.compare2(oldData,newData);
        System.out.println("没什么理由就是下个断点");
        return "complete";
    }
}
