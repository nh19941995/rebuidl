package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "table_type")
public class TableType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "comment", nullable = false, length = 500)
    private String comment;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "type")
    private Set<TableList> tableLists = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<TableList> getTableLists() {
        return tableLists;
    }

    public void setTableLists(Set<TableList> tableLists) {
        this.tableLists = tableLists;
    }

}