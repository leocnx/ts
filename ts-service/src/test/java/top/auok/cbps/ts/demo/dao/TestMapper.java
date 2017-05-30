package top.auok.cbps.ts.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.auok.cbps.ts.demo.model.Test;
import top.auok.cbps.ts.demo.model.TestCriteria;

public interface TestMapper {
    int countByExample(TestCriteria example);

    int deleteByExample(TestCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    List<Test> selectByExample(TestCriteria example);

    Test selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Test record, @Param("example") TestCriteria example);

    int updateByExample(@Param("record") Test record, @Param("example") TestCriteria example);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}