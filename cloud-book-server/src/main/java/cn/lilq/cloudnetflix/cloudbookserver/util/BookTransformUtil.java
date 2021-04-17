package cn.lilq.cloudnetflix.cloudbookserver.util;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Book;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/4 13:49
 * book转换类 Entity pojo 转换
 */
public class BookTransformUtil {
    /**
     * 数据库对象转pojo对象
     *
     * @param book entity book
     * @return pojo book
     */
    public static Book entityToPojo(cn.lilq.cloudnetflix.cloudbookserver.entity.Book book) {
        return new Book(book.getId(), book.getName(), book.getCategory(), book.getRepertory());
    }

    /**
     * pojo对象转数据库对象
     *
     * @param book pojo book
     * @return entity book
     */
    public static cn.lilq.cloudnetflix.cloudbookserver.entity.Book pojoToEntity(Book book) {
        return new cn.lilq.cloudnetflix.cloudbookserver.entity.Book(book.getId(), book.getName(), book.getCategory(), book.getRepertory());
    }
}
