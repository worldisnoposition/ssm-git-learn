package com.zhiye.service;

import com.zhiye.dao.ZhiyeDAO;
import com.zhiye.vo.ZhiyeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZhiyeServiceImpl implements ZhiyeService{
    @Autowired
    private ZhiyeDAO zhiyeDAO;
    @Override
    public void saveZhiye(List<ZhiyeVo> list) {
        zhiyeDAO.saveZhiye(list);
    }
}
