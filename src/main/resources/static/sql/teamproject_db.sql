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
	pno bigint not null,
	ord int default 0 not null,
	FOREIGN KEY (pno) REFERENCES tbl_product(pno) ON DELETE CASCADE
)
;

##문의 테이블 생성
create table tbl_qna (
	qno bigint auto_increment primary key,
	qnaTitle varchar(200) not null,
	qnaContent varchar(1000) not null,
	registDate timestamp default now(),
	updateDate timestamp default now(),
	email varchar(100) not null,
	FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
)
;

##가맹점 테이블 생성
create table tbl_store (
	sno bigint auto_increment primary key,
	storeName varchar(200) not null,
	storeNumber varchar(20) not null,
	storeAddress varchar(100) not null,
	email varchar(100) not null,
	FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
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
ORDER BY joinDate DESC
LIMIT 0, 10
;

delete from tbl_member where memberName = '이범수';

##/회원

##상품
select * from tbl_product;
select * from tbl_product_image;

insert into tbl_product
    (productName, productContent, productPrice, isRecommend)
    values ('상품테스트', '상품테스트', 5000, 0)
;

select prdt.pno, prdt.productName, prdt.productPrice, prdt.registDate, prdt.viewCount,
       prdt.likeCount, prdt.isRecommend, prdt.isDeletedProduct
from (select tp.pno, tp.productName, tp.productPrice, tp.registDate, tp.viewCount,
             tp.likeCount, tp.isRecommend, tp.isDeletedProduct
      from tbl_product tp
      where tp.pno > 0 and tp.isDeletedProduct = true
      order by tp.pno desc
      limit 0, 10
    ) as prdt
;

update tbl_product tp
set
  tp.productName = '테스트', tp.productContent = '테스트', tp.productPrice = 6000, tp.updateDate = now(), tp.isRecommend = '99'
where tp.pno = 6


##/상품

##문의
select * from tbl_qna;
##/문의

##가맹점
select * from tbl_store;
##/가맹점