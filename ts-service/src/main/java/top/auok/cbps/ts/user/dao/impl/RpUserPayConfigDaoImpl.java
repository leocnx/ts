package top.auok.cbps.ts.user.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import top.auok.cbps.ts.core.dao.impl.BaseDaoImpl;
import top.auok.cbps.ts.core.enums.PublicStatusEnum;
import top.auok.cbps.ts.user.dao.RpUserPayConfigDao;
import top.auok.cbps.ts.user.entity.RpUserPayConfig;

/**
 * 用户支付配置dao实现类
 */
@Repository
public class RpUserPayConfigDaoImpl  extends BaseDaoImpl<RpUserPayConfig> implements RpUserPayConfigDao{
    @Override
    public RpUserPayConfig getByUserNo(String userNo, String auditStatus) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("userNo",userNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", auditStatus);
        return super.getBy(paramMap);
    }
}