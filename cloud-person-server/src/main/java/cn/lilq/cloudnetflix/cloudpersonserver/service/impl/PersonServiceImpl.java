package cn.lilq.cloudnetflix.cloudpersonserver.service.impl;

import brave.Span;
import brave.Tracer;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Person;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudpersonserver.dao.PersonDAO;
import cn.lilq.cloudnetflix.cloudpersonserver.service.PersonService;
import cn.lilq.cloudnetflix.cloudpersonserver.util.PersonTransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 20:06
 * Person输出类选择com 屏蔽Person Server 实现细节
 * 添加自定义跨度
 */

@Slf4j
@Service("personService")
public class PersonServiceImpl implements PersonService {
    @Resource
    PersonDAO personDAO;
    @Resource
    private Tracer tracer;

    @Override
    public Response addPerson(Person person) {
        if (person.getId()!=null)
            return new Response(400,"Id must be null",null);
        if (person.getName()==null || person.getPassword()==null){
            return new Response(400,"content is null",null);
        }
        if (person.getPermissions()==null)
            person.setPermissions(1);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("personDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())){
            return new Response(200,"successful",PersonTransformUtil.entityToPojo(personDAO.save(PersonTransformUtil.pojoToEntity(person))));
        }finally {
            newSpan.finish();
            log.debug("personService:addPerson()");
        }
    }

    @Override
    public Response listPerson() {
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("personDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())){
            List<Person> personsNew = new ArrayList<>();
            List<cn.lilq.cloudnetflix.cloudpersonserver.entity.Person> persons = personDAO.findAll();
            persons.forEach(person ->
                    personsNew.add(PersonTransformUtil.entityToPojo(person))
            );
            return new Response(200,"successful",personsNew);
        }finally {
            newSpan.finish();
            log.debug("personService:listPerson()");
        }
    }

    @Override
    public Response findPersonById(Person person) {
        if (person.getId()==null)
            return new Response(400,"id is null",null);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("personDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())){
            return personDAO.findById(person.getId())
                    .map(
                            value -> new Response(200,"successful",PersonTransformUtil.entityToPojo(value))
                    ).orElseGet(() -> new Response(404, "person is not exist", null));
        }finally {
            newSpan.finish();
            log.debug("personService:findPersonById()");
        }
    }

    @Override
    public Response updatePerson(Person person) {
        if (person.getName()==null || person.getPassword()==null || person.getId()==null || person.getPermissions()==null){
            return new Response(400,"content is null",null);
        }
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("personDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())){
            return new Response(200,"successful",PersonTransformUtil.entityToPojo(personDAO.save(PersonTransformUtil.pojoToEntity(person))));
        }finally {
            newSpan.finish();
            log.debug("personService:updatePerson()");
        }
    }

    @Override
    public Response getPersonPermissions(Person person) {
        if (person.getId()==null)
            return new Response(400,"id is null",null);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("personDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())){
            return personDAO.findById(person.getId())
                    .map(
                            value -> new Response(200,"successful",value.getPermissions())
                    ).orElseGet(() -> new Response(404, "person is not exist", null));
        }finally {
            newSpan.finish();
            log.debug("personService:updatePerson()");
        }
    }
}