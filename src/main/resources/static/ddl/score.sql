

CREATE TABLE score (
    stu_num NUMBER(10),
    stu_name VARCHAR(20) NOT NULL,
    kor NUMBER(3) NOT NULL,
    eng NUMBER(3) NOT NULL,
    math NUMBER(3) NOT NULL,
    total NUMBER(3),
    average NUMBER(5,2),
    CONSTRAINT pk_score PRIMARY KEY(stu_num)
);
--연속된 숫자 생성
CREATE SEQUENCE seq_score;

SELECT*FROM score;

INSERT INTO score VALUES(seq_score.nextval, '홍길동',90,90,90,270,90);
INSERT INTO score VALUES(seq_score.nextval, '김철수',80,80,80,240,80);
INSERT INTO score VALUES(seq_score.nextval, '박영희',100,100,100,300,100);
COMMIT;