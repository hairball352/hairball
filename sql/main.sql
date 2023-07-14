--=============================
-- web계정 생성 @관리자
--=============================

show user;

alter session set "_oracle_script" = true;
--drop user HAIRBALL CASCADE ;
create user hairball
identified by hairball
default tablespace users;

grant connect, resource to hairball;

grant create session,
grant create table to hairball;

alter user hairball quota unlimited on users;


--=======================
-- 테이블 생성
--=======================
create table member (
    id number,
    member_id varchar2(20),
    password varchar2(300) not null,
    name varchar2(50) not null,
    member_role varchar(59) default 'ROLE_USER' not null,
    email varchar2(200),
    phone char(20) not null,
    reg_date date default sysdate,
    address varchar(100),
    provider varchar(50),
    constraints pk_member_id primary key(id),
    constraints uq_member_member_id unique (member_id)
);

create table animal(
   id number,
   age number,
   attachment_id number not null,
   discvry_plc varchar(300),
   animal_type varchar(20),
   species varchar(20),
   weight number,
   pbl_id varchar(100),
   state varchar(100),
   sex varchar(10),
   neutered number,
   constraints pk_animal_id primary key(id)
);

create table enroll_board (
    id number,
    animal_id number not null,
    reg_date date default sysdate,
    constraints pk_enroll_board_id primary key(id),
    constraints fk_enroll_board_animal_id foreign key(animal_id) references animal(id) on delete cascade
);

create table attachment (
    id number,
    enroll_board_id number not null,
    original_filename varchar2(255) not null,
    renamed_filename varchar2(255) not null,
    reg_date date default sysdate,
    constraints pk_attachment_id primary key(id),
    constraints fk_attachment_enroll_board_id foreign key(enroll_board_id) references enroll_board(id) on delete cascade
);

create table question(
    id number,
    member_id varchar2(20),
    title varchar2(50),
    content varchar2(4000),
    reg_date date default sysdate,
    constraints pk_question_id primary key(id),
    constraints fk_question_member_writer foreign key(member_id) references member(member_id) on delete cascade
);

create table answer(
   id number,
   admin_name varchar2(20) default '관리자',
   question_id number,
   content varchar2(4000),
   reg_date date default sysdate,
   constraints pk_answer_id primary key(id),
   constraints fk_answer_question_id foreign key (question_id) references question(id) on delete cascade
);

create table chatlog (
     id number,
     member_id number,
     content varchar2(4000),
     reg_date date default sysdate,
     constraints pk_chatlog_log_id primary key(id),
     constraints fk_chatlog_member_id foreign key(member_id) references member(id) on delete cascade
);

create table adopt_board(
    id number,
    animal_id number,
    member_id number not null,
    proces number default 0,
    reg_date date default sysdate,
    visit_date date default sysdate,
    constraints pk_adopt_board_id primary key (id),
    constraints fk_adopt_board_member_id foreign key(member_id) references member(id) on delete cascade,
    constraints fk_adopt_board_animal_id foreign key(animal_id) references animal(id) on delete cascade
);

--======================
-- 시퀀스 생성
--======================
create sequence seq_member_id;
create sequence seq_animal_id;
create sequence seq_answer_id;
create sequence seq_question_id;
create sequence seq_attachment_id;
create sequence seq_enroll_board_id;
create sequence seq_chatlog_id;
create sequence seq_adopt_board_id;

--=========================================
-- 테이블, 시퀀스 DROP
--=========================================
--drop table member;
--drop table answer;
--drop table question;
--drop table animal;
--drop table enroll_board;
--drop table attachment;
--drop table shelter;
--drop table chatlog;
--drop table adopt_board;
--
--drop sequence seq_member_id;
--drop sequence seq_animal_id;
--drop sequence seq_answer_id;
--drop sequence seq_question_id;
--drop sequence seq_attachment_id;
--drop sequence seq_enroll_board_id;
--drop sequence seq_chatlog_id;
--drop sequence seq_adopt_board_id;

--===========================================


--===========================================
-- 테스트 쿼리문 생성
--===========================================
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member1', 'password1', 'John Doe', 'U', 'john.doe@example.com', '1234567890', '123 Example Street', 'K');

-- 2번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member2', 'password2', 'Jane Smith', 'U', 'jane.smith@example.com', '9876543210', '456 Example Avenue', '');

-- 3번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member3', 'password3', 'Michael Johnson', 'A', 'michael.johnson@example.com', '1112223333', '789 Example Road', 'N');

-- 4번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member4', 'password4', 'Emily Davis', 'U', 'emily.davis@example.com', '4445556666', '987 Example Boulevard', 'K');

-- 5번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member5', 'password5', 'Robert Wilson', 'U', 'robert.wilson@example.com', '7778889999', '321 Example Lane', '');

-- 6번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member6', 'password6', 'Sophia Lee', 'A', 'sophia.lee@example.com', '2223334444', '654 Example Court', 'N');

-- 7번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member7', 'password7', 'David Brown', 'U', 'david.brown@example.com', '5556667777', '987 Example Drive', 'K');

-- 8번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member8', 'password8', 'Olivia Johnson', 'U', 'olivia.johnson@example.com', '8889990000', '789 Example Circle', '');

-- 9번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member9', 'password9', 'William Davis', 'A', 'william.davis@example.com', '3334445555', '987 Example Court', 'N');

-- 10번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member10', 'password10', 'Ava Wilson', 'U', 'ava.wilson@example.com', '6667778888', '321 Example Lane', '');

-- 11번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member11', 'password11', 'James Smith', 'U', 'james.smith@example.com', '9990001111', '987 Example Drive', 'K');

-- 12번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member12', 'password12', 'Mia Johnson', 'A', 'mia.johnson@example.com', '4445556666', '789 Example Circle', '');

-- 13번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member13', 'password13', 'Benjamin Davis', 'U', 'benjamin.davis@example.com', '1112223333', '987 Example Boulevard', 'N');

-- 14번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member14', 'password14', 'Charlotte Wilson', 'U', 'charlotte.wilson@example.com', '8889990000', '321 Example Street', '');

-- 15번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'member15', 'password15', 'Henry Brown', 'A', 'henry.brown@example.com', '5556667777', '987 Example Avenue', 'K');

-- 16번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'honggd', '1234', '홍드래곤', 'U', 'honggd@example.com', '5156669777', '역삼동 KH본관', '');

-- 17번째 쿼리
INSERT INTO member (id, member_id, password, name, member_role, email, phone, address, provider)
VALUES (seq_member_id.nextval, 'admin', '1234', '관리자', 'A', 'admin@example.com', '5656669771', '역삼동 KH본관', '');

select * from member;

--==========================
-- QnA테스트쿼리
--==========================
INSERT INTO question VALUES (seq_question_id.nextval, 'member10', '질문있습니다', '요ㅚ르ㅏ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member1', '입양어케해요', '요ㅂㅂ거요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member13', '강아지가 물어요', '요바요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '고양이가 밥을 안먹어요', '요ㅂ비ㅣ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member5', '호랑이 입양절차', '요ㄴㄹㅇ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member7', '코끼리 vs 하마', 'ㄴㅇㄹㅇㄹ', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member8', '시베리안허스키 없어요?', '요ㅎㄴㅇ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '고양이 vs 개', '요ㅁㅁㅁ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member4', '가입하는법', '요5ㅎㅎ5요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member11', '결제 오류입니다', '요555요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member12', '강아지가 밥을 안먹어요', '요132231요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member6', '아이디 변경해주세요', '요', default);
--
INSERT INTO question VALUES (seq_question_id.nextval, 'member10', '있습니다', '요ㅚ르ㅏ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member1', '입양어케함', '요ㅂㅂ거요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member13', '강아지좋아여', '요바요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '고양이가물어요', '요ㅂ비ㅣ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member5', '김상훈 입양절차', '요ㄴㄹㅇ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member7', '김상훈 vs 김담희', 'ㄴㅇㄹㅇㄹ', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member8', '김상훈이 밥을 안먹어요', '요ㅎㄴㅇ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '전예라 vs 정건룡', '요ㅁㅁㅁ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member4', '결제가 안돼요', '요5ㅎㅎ5요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member11', '결제 오류?', '요555요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member12', '킹킹킹', '요132231요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member6', '아이디 변경요청', '요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '김다미가 안알려줘요', 'ㅡ ㅡ', default);
--
INSERT INTO question VALUES (seq_question_id.nextval, 'member10', '있다', '요ㅚ르ㅏ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member1', '입양케함', '요ㅂㅂ거요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member13', '강아지', '요바요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '고양이', '요ㅂ비ㅣ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member5', '김담희 입양절차', '요ㄴㄹㅇ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member7', '김상훈 vs 전예라', 'ㄴㅇㄹㅇㄹ', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member8', '김상훈파양절차', '요ㅎㄴㅇ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '전예라입양절차', '요ㅁㅁㅁ요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member4', '결제문의', '요5ㅎㅎ5요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member11', '결제 오류?', '요555요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member12', '문의문의', '요132231요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member6', '고양이 문의합니다', '요', default);
INSERT INTO question VALUES (seq_question_id.nextval, 'member2', '답변해주세요ㅡㅡ', 'ㅡ ㅡ', default);
select * from question;

--=============================
-- 애니멀 테이블 더미 DB 
--=============================
insert into animal (id, age, attachment_id, discvry_plc, animal_type, species, weight, pbl_id, state, sex, neutered) values (seq_animal_id.nextval, 5, seq_attachment_id.nextval, '김대원 집 앞', '개', '슈나우저', 6, '가123', '보호중', 'M', 1);

insert into animal (id, age, attachment_id, discvry_plc, animal_type, species, weight, pbl_id, state, sex, neutered) values (seq_animal_id.nextval, 5, seq_attachment_id.nextval, '대원행님의 오토바이 창고', '개', '요크셔테리어', 2.7, '나123', '보호중', 'M', 0);

insert into animal (id, age, attachment_id, discvry_plc, animal_type, species, weight, pbl_id, state, sex, neutered) values (seq_animal_id.nextval, 5, seq_attachment_id.nextval, '석촌역 3번출구 쓰레기통 옆 벤치', '개', '말티즈', 3, '다123', '입양완료', 'F', 1);

insert into animal (id, age, attachment_id, discvry_plc, animal_type, species, weight, pbl_id, state, sex, neutered) values (seq_animal_id.nextval, 5, seq_attachment_id.nextval, '모현읍 앞마당', '고양이', '코숏', 3, '라123', '보호중', 'F', 0);

insert into animal (id, age, attachment_id, discvry_plc, animal_type, species, weight, pbl_id, state, sex, neutered) values (seq_animal_id.nextval, 5, seq_attachment_id.nextval, 'KH정보교육원 1관 휴게실', '개', '진돗개', 8, '마123', '입양완료', 'M', 1);

insert into animal (id, age, attachment_id, discvry_plc, animal_type, species, weight, pbl_id, state, sex, neutered) values (seq_animal_id.nextval, 5, seq_attachment_id.nextval, '양아치 상훈이의 보물창고 안', '고양이', '렉돌', 3, '바123', '보호중', 'F', 0);

select * from animal;


--===========================================
-- 연습장입니다
--===========================================
select * from question;
select * from answer where question_id = 38;


insert into answer values(seq_answer_id.nextval, default, 38, '뒤지세요 ㅋㅋㅋㅋ',default );
insert into answer values(seq_answer_id.nextval, default, ?, ?, default);
delete answer where id = 4;
-- select * from (select row_number() over (order by q.id desc) rnum, q.* from question q) where rnum between 1 and 10;

select * from (select row_number() over (order by q.id desc) rnum, q.*, (select count(*) from answer where question_id = q.id) answer_cnt from question q) where rnum between 1 and 10;





