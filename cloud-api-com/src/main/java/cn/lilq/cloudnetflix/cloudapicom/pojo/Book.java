package cn.lilq.cloudnetflix.cloudapicom.pojo;

import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/3 23:09
 */
public class Book {
    private String id;
    private String name;
    private String category;//类别
    private Integer repertory;//库存

    public Book(String id, String name, String category, Integer repertory) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.repertory = repertory;
    }

    public Book() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(category, book.category) &&
                Objects.equals(repertory, book.repertory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, repertory);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", repertory=" + repertory +
                '}';
    }
}
