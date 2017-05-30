package top.auok.cbps.ts.user.dao.impl;

import org.springframework.stereotype.Repository;

import top.auok.cbps.ts.core.dao.impl.BaseDaoImpl;
import top.auok.cbps.ts.user.dao.BuildNoDao;
import top.auok.cbps.ts.user.entity.SeqBuild;

/**
 *  生成编号dao实现类
 */
@Repository
public class BuildNoDaoImpl   extends BaseDaoImpl<SeqBuild> implements BuildNoDao {

    @Override
    public String getSeqNextValue(SeqBuild seqBuild) {
        return super.getSqlSession().selectOne(getStatement("getSeqNextValue"),seqBuild);
    }
}
