package cn.lilq.cloudnetflix.cloudbookserver.controller;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Book;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudbookserver.service.BookService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/31 15:07
 */

@Slf4j
@Controller
@DefaultProperties(defaultFallback = "fallback",
        commandProperties = {
                //配置连接超时
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
        },
        //指定线程池名称
        threadPoolKey = "bookThreadPool",
        threadPoolProperties = {
                //设置线程池中线程数
                @HystrixProperty(name = "coreSize",value = "50"),
                //单个线程繁忙时间可排队的请求数的队列大小
                @HystrixProperty(name = "maxQueueSize",value = "20")
        })
public class BookCon {
    @Autowired
    private BookService bookService;

    /**
     * book 列表
     * @return books
     */
    @ResponseBody
    @RequestMapping(value="/", method= RequestMethod.GET)
    @HystrixCommand
    public Response bookList() {
        log.debug("BookCon:bookList()");
        return bookService.listBook();
    }

    /**
     * 查询指定book
     * @param id book-id
     * @return book
     */
    @ResponseBody
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @HystrixCommand
    public Response getBook(@PathVariable String id) {
        log.debug("BookCon:getBook("+id+")");
        return bookService.findBookById(new Book(id,null,null,null));
    }

    /**
     * 添加book
     * @param book book
     * @return response
     */
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @HystrixCommand
    public Response addBook(@RequestBody Book book){
        log.debug("BookCon:addBook("+book+")");
        return bookService.addBook(book);
    }


    /**
     * 修改book
     * @param book book
     * @return response
     */
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    @HystrixCommand
    public Response updateBook(@RequestBody Book book){
        log.debug("BookCon:updateBook("+book+")");
        return bookService.updateBook(book);
    }

    /**
     * 修改book库存
     * @param book book
     * @return response
     */
    @ResponseBody
    @HystrixCommand
    @RequestMapping(value = "/repertory",method = RequestMethod.POST)
    public Response updateBookRepertory(@RequestBody Book book){
        log.debug("BookCon:updateBookRepertory("+book+")");
        return bookService.updateBookRepertory(book);
    }

    public Response fallback(){
        log.debug("BookCon:fallback()");
        return new Response(500,"server error",null);
    }
}
