package cn.lilq.cloudnetflix.cloudbookserver.dao;

import cn.lilq.cloudnetflix.cloudbookserver.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 20:04
 */

@Repository
public interface BookDAO extends JpaRepository<Book,String> {
    /**
     * 减少 指定商品库存
     * @param number 要减少的数量
     * @param id 商品id
     * @return 修改后的book
     */
    @Transactional
    @Modifying
    @Query("UPDATE Book o SET o.repertory = o.repertory - :number WHERE o.id= :id")
    int updateRepertorySubtract(@Param("number")Integer number,@Param("id") String id);


    /**
     * 增加 指定商品库存
     * @param number 要增加的数量
     * @param id 商品id
     * @return 修改后的book
     */
    @Transactional
    @Modifying
    @Query("UPDATE Book o SET o.repertory = o.repertory + :number WHERE o.id= :id")
    int updateRepertoryAdd(@Param("number")Integer number,@Param("id") String id);
}