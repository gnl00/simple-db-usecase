package com.dbuc.entity.poetry;

import com.dbuc.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "yuanqu")
@Getter
@Setter
@ToString
public class YuanQu extends BasePoetry implements BaseEntity {
    @Serial
    private static final long serialVersionUID = 5951772321803075051L;
    protected String title;
}
