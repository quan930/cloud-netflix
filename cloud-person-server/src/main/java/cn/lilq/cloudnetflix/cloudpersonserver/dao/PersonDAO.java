package cn.lilq.cloudnetflix.cloudpersonserver.dao;

import cn.lilq.cloudnetflix.cloudpersonserver.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 20:04
 */

@Repository
public interface PersonDAO extends JpaRepository<Person, String> {
}
