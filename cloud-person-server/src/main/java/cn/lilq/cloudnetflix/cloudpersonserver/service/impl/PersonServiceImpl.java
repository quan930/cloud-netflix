package cn.lilq.cloudnetflix.cloudpersonserver.service.impl;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Person;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudpersonserver.dao.PersonDAO;
import cn.lilq.cloudnetflix.cloudpersonserver.service.PersonService;
import cn.lilq.cloudnetflix.cloudpersonserver.util.PersonTransformUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 20:06
 */

@Service("personService")
public class PersonServiceImpl implements PersonService {
    @Resource
    PersonDAO personDAO;

    @Override
    public Response addPerson(Person person) {
        if (person.getId()!=null)
            return new Response(400,"Id must be null",null);
        if (person.getName()==null || person.getPassword()==null){
            return new Response(400,"content is null",null);
        }
        if (person.getPermissions()==null)
            person.setPermissions(1);
//        Person personSave = personDAO.save(person);
//        Person personSave = PersonTransformUtil.entityToPojo(personDAO.save(PersonTransformUtil.pojoToEntity(person)));
        return new Response(200,"successful",PersonTransformUtil.entityToPojo(personDAO.save(PersonTransformUtil.pojoToEntity(person))));
    }

    @Override
    public Response listPerson() {
        List<Person> personsNew = new ArrayList<>();
        List<cn.lilq.cloudnetflix.cloudpersonserver.entity.Person> persons = personDAO.findAll();
        persons.forEach(person ->
                personsNew.add(PersonTransformUtil.entityToPojo(person))
        );
        return new Response(200,"successful",personsNew);
    }

    @Override
    public Response findPersonById(Person person) {
        if (person.getId()==null)
            return new Response(400,"id is null",null);
        return personDAO.findById(person.getId())
                .map(
                        value -> new Response(200,"successful",PersonTransformUtil.entityToPojo(value))
                ).orElseGet(() -> new Response(404, "person is not exist", null));
    }

    @Override
    public Response updatePerson(Person person) {
        if (person.getName()==null || person.getPassword()==null || person.getId()==null || person.getPermissions()==null){
            return new Response(400,"content is null",null);
        }
//        Person personSave = PersonTransformUtil.entityToPojo(personDAO.save(PersonTransformUtil.pojoToEntity(person)));
        return new Response(200,"successful",PersonTransformUtil.entityToPojo(personDAO.save(PersonTransformUtil.pojoToEntity(person))));
    }

    @Override
    public Response getPersonPermissions(Person person) {
        if (person.getId()==null)
            return new Response(400,"id is null",null);
        return personDAO.findById(person.getId())
                .map(
                        value -> new Response(200,"successful",value.getPermissions())
                ).orElseGet(() -> new Response(404, "person is not exist", null));
    }
}