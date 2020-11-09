package cn.lilq.cloudnetflix.cloudpersonserver.controller;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Person;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudpersonserver.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 20:19
 */

@Slf4j
@Controller
@DefaultProperties(defaultFallback = "fallback",
        commandProperties = {
                //配置连接超时
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
        },
        //指定线程池名称
        threadPoolKey = "personThreadPool",
        threadPoolProperties = {
                //设置线程池中线程数
                @HystrixProperty(name = "coreSize",value = "50"),
                //单个线程繁忙时间可排队的请求数的队列大小
                @HystrixProperty(name = "maxQueueSize",value = "20")
        })
public class PersonCon {
    @Autowired
    private PersonService personService;
    /**
     * person 列表
     * @return list
     */
    @ResponseBody
    @RequestMapping(value="/", method= RequestMethod.GET)
    @HystrixCommand
    public Response personList() {
        log.debug("PersonCon:personList()");
        return personService.listPerson();
    }

    /**
     * 根据id 返回person
     * @param id person.id
     * @return 存在返回person 否则返回code400
     */
    @ResponseBody
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @HystrixCommand
    public Response getPerson(@PathVariable String id) {
        log.debug("PersonCon:getPerson("+id+")");
        return personService.findPersonById(new Person(id,null,null,null));
    }

    /**
     * 查询指定person权限
     * @param id person.id
     * @return 存在返回Permissions 否则返回code400
     */
    @ResponseBody
    @RequestMapping(value="/{id}/permissions", method= RequestMethod.GET)
    @HystrixCommand
    public Response findPersonPermissions(@PathVariable String id) {
        log.debug("PersonCon:findPersonPermissions("+id+")");
        return personService.getPersonPermissions(new Person(id,null,null,null));
    }

    /**
     * 提交person
     * @param person 需要提交的person对象
     * @return 成功返回提交后的person 否则code400
     */
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @HystrixCommand
    public Response addPerson(@RequestBody Person person){
        log.debug("PersonCon:addPerson("+person+")");
        return personService.addPerson(person);
    }

    /**
     * 更改person
     * @param person 需要更改的person对象
     * @return 成功返回提交后的person 否则code400
     */
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    @HystrixCommand
    public Response updatePerson(@RequestBody Person person){
        log.debug("PersonCon:updatePerson("+person+")");
        return personService.updatePerson(person);
    }

    public Response fallback(){
        log.debug("PersonCon:fallback()");
        return new Response(500,"server error",null);
    }
}