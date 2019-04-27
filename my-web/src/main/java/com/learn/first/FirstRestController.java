package com.learn.first;

import com.create.compare.TestBean1;
import com.create.compare.TestBean2;
import com.learn.spring.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class FirstRestController {
    @Autowired
    private SpringContextUtil springContextUtil;
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String html() {
        System.out.println("只有这种尝试成功了");
        //todo 往applicationContext里注册bean2
        TestBean2 bean2 = new TestBean2();
        springContextUtil.registBean("testBean2",bean2);
        springContextUtil.registBean("testBean1_1",new TestBean1());
        springContextUtil.registBean(TestBean2.class);
        TestBean1 bean1 = (TestBean1)springContextUtil.getBean("testBean1_1");
        TestBean2 bean2_2 = (TestBean2)springContextUtil.getBean("testBean2");
//        bean2 = bean1.getTestBean2();
        System.out.println(bean2);
        return bean2.getId();
    }
}

