package cn.lilq.cloudnetflix.cloudpersonserver.util;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Person;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/3 20:20
 * 用户转换类 Entity pojo 转换
 */
public class PersonTransformUtil {
    /**
     * 数据库对象转pojo对象
     *
     * @param person entity person
     * @return pojo person
     */
    static public Person entityToPojo(cn.lilq.cloudnetflix.cloudpersonserver.entity.Person person) {
        return new Person(person.getId(), person.getName(), person.getPassword(), person.getPermissions());
    }

    /**
     * pojo对象转pojo对象
     *
     * @param person pojo person
     * @return entity person
     */
    static public cn.lilq.cloudnetflix.cloudpersonserver.entity.Person pojoToEntity(Person person) {
        return new cn.lilq.cloudnetflix.cloudpersonserver.entity.Person(person.getId(), person.getName(), person.getPassword(), person.getPermissions());
    }
}
