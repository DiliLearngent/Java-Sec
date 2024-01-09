package org.dili.mybatislearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.dili.mybatislearn.object.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> selectAll();

    // 根据id查询单个
    User selectById(int id);

    // 方法1:直接传参
    // List<User> selectByCondition(@Param("age") int age, @Param("address") String address);

    // 方法2:传递对象
    // List<User> selectByCondition(User user);

    // 方法2:传递Map
    List<User> selectByCondition(Map map);

    List<User> selectByConditionSingle(User user);

    // 添加用户
    void add(User user);

    // 修改
    void update(User user);

    // 删除
    void deleteById(int id);

    // 批量删除
    void deleteByIds(@Param("ids") int[] ids);
}
