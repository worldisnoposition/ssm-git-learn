package com.learn.zhiye.service;

import com.learn.zhiye.dao.ZhiyeDAO;
import com.learn.zhiye.vo.ZhiyeVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ZhiyeServiceImpl implements ZhiyeService{
//    @Resource
    private ZhiyeDAO zhiyeDAO;
    @Override
    public void saveZhiye(List<ZhiyeVo> list) {
        zhiyeDAO.saveZhiye(list);
    }
}
