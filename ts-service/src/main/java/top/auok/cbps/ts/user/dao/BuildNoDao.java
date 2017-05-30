package top.auok.cbps.ts.user.dao;

import top.auok.cbps.ts.core.dao.BaseDao;
import top.auok.cbps.ts.user.entity.SeqBuild;

/**
 * 生成编号dao
 */
public interface BuildNoDao extends BaseDao<SeqBuild> {

    public String getSeqNextValue(SeqBuild seqBuild);

}
