# MyBatis 第二天课堂笔记

## 1 Day 1 回顾

## 2 输入映射和输出映射

### 2.1 `parameterType`(输入类型)

#### 2.1.1 传递简单类型

参考第一天内容

#### 2.1.2 传递 pojo 对象

参考第一天内容
Mybatis 使用 **OGNL** 表达式解析对象字段的值，`#{}` 或者 `${}` 括号中的值为 pojo 属性名称。

#### 2.1.3 传递 pojo 包装对象

##### 1 **新建包装** pojo 对象 QueryVo

```java
/**
 * 包装的 pojo
 *
 * @author Lightwing Ng
 */
public class QueryVo {
    private User user;

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
```

##### 2 映射文件与 SQL

```xml
<!-- 1、resultType:如果要返回数据集合，只需设定为每一个元素的数据类型
	 2、 包装的pojo取值通过 "."来获取
-->
<select id="getUserByQueryVo" parameterType="queryvo" resultType="com.itheima.mybatis.pojo.User">
	<!-- SELECT * FROM USER WHERE username LIKE #{name} -->
	SELECT * FROM USER
WHERE username LIKE '%${ user.username }%';
</select>
```

##### 3 新增接口方法

##### 4 增加测试方法，完成测试

### 2.2 `resultType`(输出类型)

#### 2.2.1 输出简单类型

```xml
<!-- 查询用户总记录数，演示返回简单类型 -->
<select id="getUserCount" resultType="int">
	SELECT COUNT(1) FROM `user`;
</select>
```

其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

#### 2.2.2 输出 pojo 对象

参考第一天内容

#### 2.2.3 输出 pojo 列表

参考第一天内容。

### 2.3 输出 resultMap

演示基于完成订单列表的查询，由 user_id 字段与 pojo 属性不一致时引出的 resultMap。

```xml
<!-- resultMap入门
	 type:映射成的pojo类型
	 id：resultMap唯一标识
-->
<resultMap type="order" id="orderMap">
	<!-- id标签用于绑定主键 -->
	<!-- <id property="id" column="id"/> -->
	<!-- 使用result绑定普通字段 -->
	<result property="userId" column="user_id"/>
	<result property="order_number" column="order_number"/>
	<result property="createtime" column="createtime"/>
	<result property="note" column="note"/>
</resultMap>
<!-- 使用resultMap -->
<select id="getOrderListResultMap" resultMap="orderMap">
	SELECT * FROM `order`;
</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

## 3 动态 SQL 

### 3.1 `If`

​	演示基于完成用户列表查询功能，由多查询条件拼装引出 `if` 标签。

```xml
<!-- 演示动态sql-if标签的使用情景 -->
	<select id="getUserByWhere" parameterType="user" resultType="com.itheima.mybatis.pojo.User">
		<!-- SELECT * FROM USER WHERE username LIKE '%${username}%' and id = #{id} -->
		SELECT * FROM USER where 1 = 1;
		<!-- if标签的使用 -->
		<if test="id != null">
			and id = #{ id }
		</if>
		<if test="username != null and username != ''">
			and username LIKE '%${ username }%'
		</if>
	</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

### 3.2 `Where`

​	复制 getUserByWhere 修改一下，改名为 getUserByWhere2。

```xml
<!-- 演示动态sql-where标签的使用情景 -->
<select id="getUserByWhere2" parameterType="user"
	resultType="com.itheima.mybatis.pojo.User">
	<!-- include:引入sql片段,refid引入片段id -->
	SELECT
		*
	FROM `user`
	<!-- where会自动加上where同处理多余的and -->
	<where>
		<!-- if标签的使用 --> 
		<if test="id != null">
			AND id = #{ id }
		</if>
		<if test="username != null and username != ''"> 
			AND username LIKE '%${ username }%'
		</if>
	</where>
</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

### 3.3 `Foreach`

​	复制 `getUserByWhere2` 修改一下，改名为 `getUserByIds`。

```xml
<!-- 演示动态sql-foreach标签的使用情景 -->
	<select id="getUserByIds" parameterType="queryvo"
		resultType="com.itheima.mybatis.pojo.User">
		SELECT
         	*
    	FROM USER
	<!-- where会自动加上where同处理多余的and -->
	<where>
		<!-- id IN(1, 10, 25, 30, 34) -->
		<!-- foreach循环标签 
			 collection:要遍历的集合，来源入参 
			 open:循环开始前的sql 
			 separator:分隔符 
			 close:循环结束拼接的sql
		-->
		<foreach item="uid" collection="ids" open="id IN(" separator="," close=")">
			#{ uid }
		</foreach>		
	</where>
</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

### 3.4 `SQL` 片段

​	演示通过 `select *` 不好引出查询字段名，抽取共用 SQL 片段。

#### 3.4.1 定义

```xml
<!-- sql片段 定义，id:片段唯一标识 -->
<sql id="user_column">
	`id`,
	`username`,
	`birthday`,
	`sex`,
	`address`,
	`uuid2`
</sql>
```

#### 2 使用

```xml
SELECT
	<!-- sql片段的使用：include:引入sql片段,refid引入片段id -->
	<include refid="user_column" />
	FROM `user` 
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

## 4 关联查询

### 4.1 一对一关联

#### 4.1.1 方法一，使用 resultType

##### 1 新建 OrderUser 的 pojo，继承自 Order。

```java
public class OrderUser extends Order {
    private String username;
    ...
}
```

##### 2 修改 order 的映射文件，新增查询方法 getOrderUser。

```xml
<!-- 一对一关联查询，使用resultType -->
<select id="getOrderUser" resultType="orderuser">
	SELECT
		o.`id`,
         o.`user_id` userId,
         o.`number`,
         o.`createtime`,
         o.`note`,
         u.`username`,
         u.`address`
	FROM `order` o
		LEFT JOIN `user` u
			ON u.id = o.`user_id`
</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

#### 4.1.2 方法二，使用 resultMap

##### 1 改造 order 的 pojo

##### 2 修改 order 的映射文件

```xml
<resultMap type="order" id="order_user_map">
    <!-- <id>用于映射主键 -->
    <id property="id" column="id"/>
    <!-- 普通字段用<result>映射 -->
    <result property="userId" column="user_id"/>
    <result property="order_number" column="order_number"/>
    <result property="createtime" column="createtime"/>
    <result property="note" column="note"/>

    <!-- association用于配置一对一关系
         property:order里面的User属性
         javaType:user的数据类型，支持别名
     -->
    <association property="user" javaType="com.lightwing.mybatis.pojo.User">
        <id property="id" column="user_id"/>
        <result property="username" column="username"/>
        <result property="address" column="address"/>
        <result property="birthday" column="birthday"/>
        <result property="sex" column="sex"/>
    </association>
</resultMap>

<!-- 一对一关联查询：resultType使用 -->
<select id="getOrderUserMap" resultMap="order_user_map">
	SELECT
		o.`id`,
		o.`user_id`,
		o.`order_number`,
		o.`createtime`,
		o.`note`,
		u.username,
		u.address,
		u.birthday,
		u.sex
	FROM
		`order` o
		LEFT JOIN `user` u
			ON u.id = o.user_id;
</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

### 4.2 一对多关联

#### 1 改造 user 的 pojo

#### 2 修改  user  的映射文件

```xml
<resultMap type="user" id="user_order_map">
    <id property="id" column="id"/>
    <result property="username" column="username"/>
    <result property="address" column="address"/>
    <result property="birthday" column="birthday"/>
    <result property="sex" column="sex"/>
    <!-- collection用于配置一对多关联
         property:User当中Order的属性
         ofType:orders的数据类型，支持别名
    -->
    <collection property="orders" ofType="com.lightwing.mybatis.pojo.Order">
        <!-- <id>用于映射主键 -->
        <id property="id" column="oid"/>
        <!-- 普通字段用<result>映射 -->
        <result property="userId" column="id"/>
        <result property="order_number" column="order_number"/>
        <result property="createtime" column="createtime"/>
        <result property="note" column="note"/>
    </collection>
</resultMap>

<select id="getUserOrderMap" resultMap="user_order_map">
    SELECT
        u.`id`,
        u.`username`,
        u.`birthday`,
        u.`sex`,
        u.`address`,
        u.`uuid2`,
        o.`id` oid,
        o.`order_number`,
        o.`createtime`,
        o.`note`
    FROM
        `user` u
        LEFT JOIN `order` o
            ON o.`user_id` = u.`id`;
</select>
```

​	其它步骤跟前面类似，添加接口方法与测试方法，完成测试。

## 5 Mybatis 整合 Spring

### 5.1 整合思路

1. SqlSessionFactory 对象应该放到 spring 容器中作为单例存在。
2. 传统 dao 的开发方式中，应该从 spring 容器中获得 sqlsession 对象。
3. Mapper 代理形式中，应该从spring容器中直接获得mapper的代理对象。
4. 数据库的连接以及数据库连接池事务管理都交给spring容器来完成。

### 5.2 整合步骤

1.  创建一个 Java 工程。
2.  导入 jar 包。（课前资料中mybatis与spring整合所有包）
3.  mybatis 的配置文件 sqlmapConfig.xml
4.  编写Spring的配置文件
    1) 数据库连接及连接池
    2) sqlsessionFactory对象，配置到spring容器中
    3) 编写Spring的配置文件
5.  复制jdbc.properties配置文件到新工程
6.  复制log4j.properties配置文件到新工程

### 5.3 Dao开发

#### 5.3.1 复制 user 的 pojo 到新工程

#### 5.3.2 传统 Dao 开发

1. 复制user.xml到新工程，并修改，只留下要测试的三个方法

2. 在SqlMapConfig.xml加载user.xml

3. 复制UserDao接口到新工程，并修改，只留下要测试的三个方法

4. 编写UserDaoImpl实现类，关键是继承 SqlSessionDaoSupport


```java
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    @Override
    public User getUserById(Integer id) {
        SqlSession sqlSession = super.getSqlSession();
        //查询用户
        User user = sqlSession.selectOne("user.getUserById", id);
        //不能关闭SqlSession
        //sqlSession.close();
        return user;
    }
    
    @Override
    public List<User> getUserByUserName(String name) {
        SqlSession sqlSession = super.getSqlSession();
        List<User> list = sqlSession.selectList("user.getUserByName", name);
        //不能关闭SqlSession
        return list;
    }
    
    @Override
    public void insertUser(User user) {
        SqlSession sqlSession = super.getSqlSession();
        sqlSession.insert("user.insertUser", user);
        //不用手动提交事务，交给spring
    }
}
```


5. 在 `applicationContext.xml` 中配置 `UserDaoImpl` 实现类 

```xml
<!-- 传统dao  -->
<bean class="com.itheima.mybatis.dao.impl.UserDaoImpl">
	<property name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>
```

6. 编写测试类，新建单完测试类

```java
public class UserDaoTest {
    private ApplicationContext applicationContext;
    
    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    
    @Test
    public void testGetUserById() {
        UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = userDao.getUserById(10);
        System.out.println(user);
    }
    ………省略其它方法
}
```

#### 5.3.3 Mapper 代理模式开发 Dao

1. 复制 UserMapper.xml 到新工程，并修改，只留下要测试的三个方法
2. 复制 UserMapper 接口到新工程，并修改，只留下要测试的三个方法
3. 配置 Mapper
   1) 单个接口配置 MapperFactoryBean

<!-- 动态代理Dao开发，第一种方式 -MapperFactoryBean -->

```xml
<bean id="baseMapper" class="org.mybatis.spring.mapper.MapperFactoryBean" abstract="true" lazy-init="true">
    	<property name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>

<!-- 用户动态代理扫描 -->
<bean parent="baseMapper">
     <property name="mapperInterface" value="com.itheima.mybatis.mapper.UserMapper" />
</bean>
```

   2) 配置包扫描器

```xml
<!-- 动态代理Dao开发，第一种方式，包扫描器(推荐使用) -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<!-- basePackage：配置映射包装扫描，多个包时用","或";"分隔 -->
	<property name="basePackage" value="com.itheima.mybatis.mapper" />
</bean>
```




4. 测试

```java
public class UserMapperTest {
    private ApplicationContext applicationContext;
    
    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    
    @Test
    public void testGetUserById() {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user = userMapper.getUserById(10);
        System.out.println(user);
    }
}
```

## 6 Mybatis 逆向工程

注意的点：在 `generatorConfig.xml` 中配置 mapper 生成的详细信息，注意改下几点：

1. 添加要生成的数据库表
2. po 文件所在包路径
3. mapper 文件所在包路径