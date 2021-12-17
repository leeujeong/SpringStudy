drop table members;

create table members
(
    member_no number primary key,
    id varchar2(100) not null unique,
    name varchar2(100),
    gender varchar2(3),
    address varchar2(100)
);

drop sequence member_seq;
create sequence member_seq nocache;

drop table boards;
create table boards(
    board_no number primary key,
    title varchar2(100) not null,
    content varchar2(100),
    writer varchar2(100),
    ip varchar2(100),
    created date,
    lastmodified date
    );
drop table board_attach;
create table board_attach
(
    uuid varchar2(32) primary key,      --저장된 첨부파일명을 의미함
    path varchar2(300),
    origin varchar2(300),
    filetype varchar2(1) default '1',
    board_no number references board(board_no)
);
drop sequence board_seq;
create sequence board_seq nocache;

insert into members values( member_seq.nextval, 'ID1', '이름1', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID2', '이름2', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID3', '이름3', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID4', '이름4', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID5', '이름5', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID6', '이름6', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID7', '이름7', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID8', '이름8', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID9', '이름9', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID10', '이름10', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID11', '이름11', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID12', '이름12', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID13', '이름13', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID14', '이름14', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID15', '이름15', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID16', '이름16', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID17', '이름17', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID18', '이름18', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID19', '이름19', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID20', '이름20', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID21', '이름21', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID22', '이름22', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID23', '이름23', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID24', '이름24', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID25', '이름25', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID26', '이름26', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID27', '이름27', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID28', '이름28', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID29', '이름29', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID30', '이름30', '남' , '서울시1');
insert into members values( member_seq.nextval, 'ID31', '이름13', '여' , '서울시1');
insert into members values( member_seq.nextval, 'ID32', '이름13', '여' , '서울시1');
commit;