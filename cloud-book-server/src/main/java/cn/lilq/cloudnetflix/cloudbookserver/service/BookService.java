package cn.lilq.cloudnetflix.cloudbookserver.service;


import cn.lilq.cloudnetflix.cloudapicom.pojo.Book;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/31 14:21
 */

public interface BookService {
    /**
     * 增加书籍
     *
     * @param book 用户对象
     * @return response
     */
    Response addBook(Book book);

    /**
     * 书籍列表
     *
     * @return Response data List<Book>
     */
    Response listBook();

    /**
     * 根据id 查询书籍
     *
     * @param book 需要book id
     * @return Response data Book
     */
    Response findBookById(Book book);

    /**
     * 更新书籍
     *
     * @param book 需要更新的书籍
     * @return Response data Book(更新后)
     */
    Response updateBook(Book book);

    /**
     * 更新库存
     *
     * @param book 需要更新的书籍 id category(增加或减少的库存数)
     * @return Response data
     */
    Response updateBookRepertory(Book book);
}

