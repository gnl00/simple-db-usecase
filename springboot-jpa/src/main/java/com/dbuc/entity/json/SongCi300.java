package com.dbuc.entity.json;

import com.dbuc.entity.BaseEntity;
import com.dbuc.entity.poetry.BasePoetry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Getter
@Setter
@ToString
public class SongCi300 extends BasePoetry implements BaseEntity {
    @Serial
    private static final long serialVersionUID = -1619397114209318394L;

    private String rhythmic; // 词牌名
    private String[] tags;
}
