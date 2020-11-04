package cn.lilq.cloudnetflix.cloudpersonserver.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/3 20:17
 */
@Entity
@Table(name = "tab_person")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Person {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private Integer permissions;//权限 1用户 2店家 3管理员 5admin

    public Person(String id, String name, String password, Integer permissions) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.permissions = permissions;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(password, person.password) &&
                Objects.equals(permissions, person.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, permissions);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
