package cn.lilq.cloudnetflix.cloudpersonserver.service;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Person;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 20:05
 */
public interface PersonService {
    /**
     * 增加用户
     *
     * @param person 用户对象
     * @return response
     */
    Response addPerson(Person person);

    /**
     * 用户列表
     *
     * @return Response data List<Person>
     */
    Response listPerson();

    /**
     * 根据id 查询用户
     *
     * @param person 需要person id
     * @return Response data Person
     */
    Response findPersonById(Person person);

    /**
     * 更新用户
     *
     * @param person 需要更新的用户
     * @return Response data Person(更新后)
     */
    Response updatePerson(Person person);

    /**
     * 查询用户权限
     *
     * @param person person 需要person id
     * @return Response data Integer(权限)
     */
    Response getPersonPermissions(Person person);
}
