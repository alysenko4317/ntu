package mc.hibernatetutorial.model;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public TestEntity() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("User id: %d, name: %s", getId(), getName());
    }
}
