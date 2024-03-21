# simple-db-usecase

...

## Jpa

### 多数据源配置

有几个可能会出现问题的地方

1. `HibernateProperties that could not be found`，需要加上 `@Primary`

```java
@Primary // fix 'org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties' that could not be found.
@Bean
@Qualifier("poetryDataSource")
@ConfigurationProperties(prefix = "spring.datasource.poetry")
public DataSource poetryDataSource() {
    return DataSourceBuilder.create().build();
}
```

2. 报错 `Multi entityManagerFactoryXXX...`，加上 `@Primary`

```java
@Primary
@Bean
@Qualifier("entityManagerFactoryPoetry")
public LocalContainerEntityManagerFactoryBean entityManagerFactoryPoetry(EntityManagerFactoryBuilder builder) {
    return builder.dataSource(poetryDataSource)
            .properties(getHibernateProperties())
            .packages("com.fts.jpa.entity.poetry")
            .persistenceUnit("poetryPersistenceUnit")
            .build();
}
```

3. 报错 `jdbc-url require...` 进行如下修改

```yml
# 未使用多数据源写法
datasource:
  username: root
  password: root
  driver-class-name: com.mysql.cj.jdbc.Driver
  url: jdbc:mysql://localhost:3306/db

---

# 使用多数据源写法
datasource:
    primary:
      username: root
      password: root
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: jdbc:mysql://localhost:3306/db_primary # 其实就是将参数名 url 修改成 jdbc-url
```

### 百万级数据导入

导入数据，使用天池[天猫推荐数据](https://tianchi.aliyun.com/dataset/140281)

之前以为 Jpa 的 saveAll 是批量新增方法，今天点进去源码一看：

```java
@Transactional
@Override
public <S extends T> List<S> saveAll(Iterable<S> entities) {
    Assert.notNull(entities, "Entities must not be null");
    List<S> result = new ArrayList<>();
    for (S entity : entities) {
        result.add(save(entity));
    }
    return result;
}
```

原来是循环调用 save 方法 🤡 怪不得每次插入大量数据的时候都这么慢，可恶！

优化方法可以使用下面几种：
1. 拼接批量插入 SQL
```sql
insert into table_name (column1, column2, column3, column4) 
values ("", "", "", ""), ("", "", "", ""), ("", "", "", "");
```

[参考](https://riun.xyz/work/3825161)

2. 使用 EntityManager#persist

需要标注 @Transactional，必须是 public 修饰的方法

```java
@PersistenceContext
private EntityManager entityManager;

@Transactional(rollbackOn = {Exception.class})
public int batchSave(List<Product> list) {
    int batchSize = 10000; // 设置组大小，分组插入
    boolean hasPersist = false;
    for (Product product : list) {
        entityManager.persist(product); // 减少一步查询操作
        int currCount = insertCount.incrementAndGet();
        hasPersist = true;

        if(currCount % batchSize == 0) {
            entityManager.flush();
            entityManager.clear();
            hasPersist = false;
        }
    }

    if(hasPersist) {
        entityManager.flush();
        entityManager.clear();
    }
    return 1;
}
```

[参考1](https://www.jianshu.com/p/a8ef0b04afa8/)

[参考2](https://www.jianshu.com/p/11153affb528)

SQL 拼接实现的时候比 EntityManager.persist 麻烦，但是实现的效果是最好的。数据量越大，SQL 拼接的优势越明显。

```java
// entityManager.persist // 25s for 10k data // 53634ms for 20k
// sqlConcat // 4s for 10k data // 6432ms for 20k 👍
```

...

## Mybatis

...

## 参考

...