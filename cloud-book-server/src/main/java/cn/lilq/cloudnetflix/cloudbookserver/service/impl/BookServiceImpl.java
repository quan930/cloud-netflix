package cn.lilq.cloudnetflix.cloudbookserver.service.impl;

import brave.Span;
import brave.Tracer;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Book;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudbookserver.dao.BookDAO;
import cn.lilq.cloudnetflix.cloudbookserver.service.BookService;
import cn.lilq.cloudnetflix.cloudbookserver.util.BookTransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @auther: Li Liangquan
 * @date: 2020/10/31 14:23
 * Book输出类选择com 屏蔽Book Server 实现细节
 * 添加自定义跨度
 */

@Slf4j
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDAO bookDAO;
    @Resource
    private Tracer tracer;

    @Override
    public Response addBook(Book book) {
        if (book.getId() != null)
            return new Response(400, "Id must be null", null);
        if (book.getName() == null || book.getCategory() == null) {
            return new Response(400, "content is null", null);
        }
        if (book.getRepertory() == null) {
            book.setRepertory(0);
        }
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("bookDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            return new Response(200, "successful", BookTransformUtil.entityToPojo(bookDAO.save(BookTransformUtil.pojoToEntity(book))));
        } finally {
            newSpan.finish();
            log.debug("bookService:addBook()");
        }
    }

    @Override
    public Response listBook() {
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("bookDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            List<Book> booksNew = new ArrayList<>();
            List<cn.lilq.cloudnetflix.cloudbookserver.entity.Book> books = bookDAO.findAll();
            books.forEach(book ->
                    booksNew.add(BookTransformUtil.entityToPojo(book))
            );
            return new Response(200, "successful", booksNew);
        } finally {
            newSpan.finish();
            log.debug("bookService:listBook()");
        }
    }

    @Override
    public Response findBookById(Book book) {
        if (book.getId() == null)
            return new Response(400, "id is null", null);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("bookDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            return bookDAO.findById(book.getId())
                    .map(
                            value -> new Response(200, "successful", BookTransformUtil.entityToPojo(value))
                    ).orElseGet(() -> new Response(404, "person is not exist", null));
        } finally {
            newSpan.finish();
            log.debug("bookService:findBookById()");
        }
    }

    @Override
    public Response updateBook(Book book) {
        if (book.getName() == null || book.getCategory() == null || book.getId() == null || book.getRepertory() == null) {
            return new Response(400, "content is null", null);
        }
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("bookDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            return new Response(200, "successful", BookTransformUtil.entityToPojo(bookDAO.save(BookTransformUtil.pojoToEntity(book))));
        } finally {
            newSpan.finish();
            log.debug("bookService:updateBook()");
        }
    }

    @Override
    public Response updateBookRepertory(Book book) {
        if (book.getId() == null || book.getRepertory() == null)
            return new Response(400, "content is null", null);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("bookDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            int m = book.getRepertory() > 0 ? bookDAO.updateRepertoryAdd(book.getRepertory(), book.getId()) : bookDAO.updateRepertorySubtract(Math.abs(book.getRepertory()), book.getId());
            return m > 0 ? new Response(200, "successful", null) : new Response(400, "update error", null);
        } finally {
            newSpan.finish();
            log.debug("bookService:updateBookRepertory()");
        }
    }
}
