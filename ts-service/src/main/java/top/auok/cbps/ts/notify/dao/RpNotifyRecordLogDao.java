package top.auok.cbps.ts.notify.dao;


import top.auok.cbps.ts.core.dao.BaseDao;
import top.auok.cbps.ts.notify.entity.RpNotifyRecordLog;

public interface RpNotifyRecordLogDao  extends BaseDao<RpNotifyRecordLog> {


    int deleteByPrimaryKey(String id);

    int insertSelective(RpNotifyRecordLog record);

    RpNotifyRecordLog selectByPrimaryKey(String id);


    int updateByPrimaryKey(RpNotifyRecordLog record);
}