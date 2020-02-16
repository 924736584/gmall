package com.deyuan.gmall.manage.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.deyuan.gmall.bean.PmsBaseAttrInfo;
import com.deyuan.gmall.bean.PmsBaseAttrValue;
import com.deyuan.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.deyuan.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.deyuan.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> getattrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo=new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(Long.parseLong(catalog3Id));
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        return pmsBaseAttrInfos;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(Long.parseLong(attrId));
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
        return pmsBaseAttrValues;
    }

    @Override
    public int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        int su=0;
         if(StringUtils.isEmpty(pmsBaseAttrInfo.getId()))
         {
              su = pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
         }
         else {
             Example example=new Example(PmsBaseAttrInfo.class);
             example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
             su = pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, example);
             PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
             pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
             pmsBaseAttrValueMapper.delete(pmsBaseAttrValue);
         }
             for(PmsBaseAttrValue value:pmsBaseAttrInfo.getAttrValueList())
             {
                 value.setAttrId(pmsBaseAttrInfo.getId());
                 pmsBaseAttrValueMapper.insertSelective(value);
             }

        return su;
    }

}
