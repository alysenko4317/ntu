package com.khpi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="foo")
public class Foo
{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _pk;

    private String _bar1;
    private Integer _bar2;

    public Foo() { }
    public Foo(final Integer id) {
        _pk = id;
    }
}
