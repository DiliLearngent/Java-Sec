package org.dili.springlearn.object;

public class UserService implements ServiceInterface {
    @Override
    public void add() {
        System.out.println("添加方法");
    }

    @Override
    public void query() {
        System.out.println("查询方法");
    }

    @Override
    public void delete() {
        System.out.println("删除方法");
    }

    @Override
    public void update() {
        System.out.println("更新方法");
    }
}
