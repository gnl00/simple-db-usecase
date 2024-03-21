package com.dbuc.entity.poetry;

import com.dbuc.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
public abstract class BasePoetry implements BaseEntity {
    @Serial
    private static final long serialVersionUID = 6994480728877377723L;
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "author")
    protected String author;
    @Column(name = "paragraphs")
    protected String paragraphs;
}
