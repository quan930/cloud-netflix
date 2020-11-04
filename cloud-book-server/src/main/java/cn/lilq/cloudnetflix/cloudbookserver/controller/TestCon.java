package cn.lilq.cloudnetflix.cloudbookserver.controller;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Book;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudbookserver.dao.BookDAO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/4 17:34
 */

@Controller
public class TestCon {
    @Autowired
    private BookDAO bookDAO;
    private static final Logger logger = LoggerFactory.getLogger(TestCon.class);

//    @ResponseBody
//    @RequestMapping(value = "/shop",method = RequestMethod.POST)
//    public Response updateBookRepertory(@RequestBody Book book){
//        int m  = bookDAO.updateRepertoryAdd(book.getRepertory(),book.getId());
//        logger.error("abc"+m);
//        return new Response(401,"server error",null);
//    }
}
