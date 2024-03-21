package com.dbuc.entity.poetry;

import com.dbuc.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * 宋词
 */
@Entity
@Table(name = "songci")
@Getter
@Setter
@ToString
public class SongCi extends BasePoetry implements BaseEntity {
    @Serial
    private static final long serialVersionUID = -1619397114209318394L;
    private String rhythmic; // 词牌名
}
