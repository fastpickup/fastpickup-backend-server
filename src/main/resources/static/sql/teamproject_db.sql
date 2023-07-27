CREATE DATABASE projectdb;

CREATE USER 'projectuser'@'localhost' IDENTIFIED BY 'projectuser';

CREATE USER 'projectuser'@'%' IDENTIFIED BY 'projectuser';


GRANT ALL PRIVILEGES ON projectdb.* TO 'projectuser'@'localhost';

GRANT ALL PRIVILEGES ON projectdb.* TO 'projectuser'@'%';

ALTER USER 'projectuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'projectuser';

ALTER USER 'projectuser'@'%' IDENTIFIED WITH mysql_native_password BY 'projectuser';

##회원 테이블 생성
create table tbl_member (
	email varchar(100) not null primary key,
	memberPw varchar(200) not null,
	memberName varchar(100) not null,
	memberPhone varchar(20) not null,
	store varchar(100) not null default 'm'
)
;

##회원 권환 테이블 생성
CREATE TABLE tbl_member_role (
	email varchar(100) not null,
	rolename varchar(50) not null
)
;

##회원 로그인 유지 여부 테이블 생성
create table persistent_logins (
       username varchar(64) not null,
       series varchar(64) primary key,
       token varchar(64) not null,
       last_used timestamp not null
)
;

##상품 테이블 생성
create table tbl_product (
	pno bigint auto_increment primary key,
	productName varchar(200) not null,
	productContent varchar(1000) not null,
	productPrice int not null,
	registDate timestamp default now(),
	updateDate timestamp default now(),
	viewCount int default 0,
	likeCount int default 0,
	isRecommend int,
	isDeletedProduct tinyint not null default true
)
;

##상품 이미지 테이블 생성
CREATE TABLE tbl_product_image (
	uuid varchar(50) PRIMARY KEY,
	file_name varchar(200) not null,
	bno int not null,
	ord int default 0 not null,
	FOREIGN KEY (bno) REFERENCES tbl_board(bno) ON DELETE CASCADE
)
;

##회원
alter table tbl_member add `joinDate` timestamp default now() after `store`;

create index idx_tbl_member_joindate on tbl_member (joinDate asc);

select * from tbl_member;

select * from tbl_member_role;

select * from persistent_logins;

##Member Select
SELECT * FROM tbl_member
WHERE joinDate < now()
ORDER BY joinDate DESC
LIMIT 0, 10
;

##/회원

##상품
select * from tbl_product;

##/상품