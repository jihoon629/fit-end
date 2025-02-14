application.properties 에 있는 
spring.datasource.username=JH
spring.datasource.password=1234 

이름이랑 비번 해당 로컬에 맞게 변경꼭해야 해요

spring.datasource.url=jdbc:mysql://localhost:3306/test
test 가 해당하는 데이터 베이스





""""""jpa 에서 자동으로 대문자>소문자 카멜케이스를 스네이크로 변환하니 유의하세요"""""



해당하는 테이블 정보
mysql> DESCRIBE user_body_info;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| id            | bigint       | NO   | PRI | NULL    | auto_increment |
| date          | datetime(6)  | YES  |     | NULL    |                |
| fat_mass      | double       | NO   |     | NULL    |                |
| fatpercentage | double       | NO   |     | NULL    |                |
| height        | double       | NO   |     | NULL    |                |
| inbody_score  | double       | NO   |     | NULL    |                |
| userid        | varchar(255) | YES  |     | NULL    |                |
| weight        | double       | NO   |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+


mysql> DESCRIBE user_info;
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| id       | bigint       | NO   | PRI | NULL    | auto_increment |
| userid   | varchar(255) | NO   | UNI | NULL    |                |
| password | varchar(255) | NO   | UNI | NULL    |                |
+----------+--------------+------+-----+---------+----------------+

mysql> DESCRIBE entity_name;
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint       | NO   | PRI | NULL    | auto_increment |
| name        | varchar(255) | YES  |     | NULL    |                |
| description | varchar(255) | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
