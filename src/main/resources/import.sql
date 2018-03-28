/*
 * Copyright (c) 2018, NARH https://github.com/NARH
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

-- 職業マスター
DROP TABLE IF EXISTS JOB_MASTER CASCADE;
CREATE TABLE JOB_MASTER (
    CODE     CHAR(2)      NOT NULL PRIMARY KEY
  , NAME     VARCHAR(64)  NOT NULL
  , CURSE    BOOLEAN DEFAULT FALSE NOT NULL
  ,
);

-- 会員マスター
DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS (
    USERNAME VARCHAR(256) NOT NULL PRIMARY KEY
  , PASSWORD VARCHAR(256) NOT NULL
  , NAME     VARCHAR(32)  NOT NULL
  , JOB      CHAR(2)      NOT NULL
  , STRENGTH INT(3) DEFAULT 0 NOT NULL
  , ENABLED  BOOLEAN NOT NULL
  , CONSTRAINT FK_USERS_JOB_MASTER FOREIGN KEY (JOB) REFERENCES JOB_MASTER(CODE)
);

-- 権限
DROP TABLE IF EXISTS AUTHORITIES CASCADE;
CREATE TABLE AUTHORITIES (
    USERNAME  VARCHAR(256) NOT NULL
  , AUTHORITY VARCHAR(256) NOT NULL
  , CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME)
  , CONSTRAINT UK_AUTHORITIES UNIQUE (USERNAME, AUTHORITY)
);

-- 武器マスター
DROP TABLE IF EXISTS WEAPON_MASTER CASCADE;
CREATE TABLE WEAPON_MASTER(
    CODE     CHAR(2)       NOT NULL PRIMARY KEY
  , NAME     VARCHAR(64)   NOT NULL
  , CATEGORY CHAR(2)       NOT NULL
  , STRENGTH INT(3) DEFAULT 0 NOT NULL
  , CURSE    BOOLEAN DEFAULT FALSE NOT NULL
);

-- 武器カテゴリ
DROP TABLE IF EXISTS WEAPON_CATEGORY CASCADE;
CREATE TABLE WEAPON_CATEGORY(
    CODE     CHAR(2)       NOT NULL PRIMARY KEY
  , NAME     VARCHAR(64)   NOT NULL
);

-- 職業-武器カテゴリリファレンス
DROP TABLE IF EXISTS JOB_CATEGORY_REFERENCE;
CREATE TABLE JOB_CATEGORY_REFERENCE(
    JOB      CHAR(2)       NOT NULL
  , CATEGORY CHAR(2)       NOT NULL
  , CONSTRAINT FK_JOB_REFERENCE FOREIGN KEY (JOB) REFERENCES JOB_MASTER(CODE)
  , CONSTRAINT FK_CATEGORY_REFERENCE FOREIGN KEY (CATEGORY) REFERENCES WEAPON_CATEGORY(CODE)
  , CONSTRAINT UK_JOB_CATEGORY_REFERENCE UNIQUE (JOB, CATEGORY)
);

-- データ登録 職業マスター
INSERT INTO JOB_MASTER(CODE,NAME,CURSE) VALUES
  ('01','勇者'  ,FALSE)
, ('02','戦士'  ,FALSE)
, ('03','魔導士',FALSE)
, ('04','僧侶'  ,FALSE)
, ('05','魔剣士',TRUE)
, ('06','盗賊'  ,TRUE)
;

-- データ登録 会員マスター
INSERT INTO USERS(USERNAME,PASSWORD,NAME,JOB,STRENGTH,ENABLED) VALUES
  ('001@example.com','$2a$10$DrBnpIi7xp4ASZvXnPMqKO6CKBlVUO1N7Pw99XEulYx9pMWzqOyHq','オリバー','01',50,TRUE)
, ('002@example.com','$2a$10$DrBnpIi7xp4ASZvXnPMqKO6CKBlVUO1N7Pw99XEulYx9pMWzqOyHq','ハリー','02',100,TRUE)
, ('003@example.com','$2a$10$DrBnpIi7xp4ASZvXnPMqKO6CKBlVUO1N7Pw99XEulYx9pMWzqOyHq','ジャック','03',30,TRUE)
, ('004@example.com','$2a$10$DrBnpIi7xp4ASZvXnPMqKO6CKBlVUO1N7Pw99XEulYx9pMWzqOyHq','チャーリー','06',80,TRUE)
;

-- データ登録 権限
INSERT INTO AUTHORITIES VALUES
  ('001@example.com', 'USER')
, ('002@example.com', 'USER')
, ('003@example.com', 'USER')
, ('004@example.com', 'USER')
;

-- データ登録 武器マスター
INSERT INTO WEAPON_MASTER(CODE,NAME,CATEGORY,STRENGTH,CURSE) VALUES
  ('01','木のつるぎ','01',50,FALSE)
, ('02','どうのつるぎ','01',70,FALSE)
, ('03','はがねのつるぎ','01',100,FALSE)
, ('04','金のつるぎ','01',120,FALSE)
, ('05','ダイヤモンドのつるぎ','01',150,FALSE)
, ('06','やみのつるぎ','01',170,TRUE)
, ('07','あくまのつるぎ','01',170,TRUE)
, ('08','木のつえ','02',50,FALSE)
, ('09','どうのつえ','02',70,FALSE)
, ('10','はがねのつえ','02',80,FALSE)
, ('11','金のつえ','02',100,FALSE)
, ('12','ダイヤモンドのつえ','02',120,FALSE)
, ('13','あくまのつえ','02',180,TRUE)
, ('14','やみのつえ','02',200,TRUE)
, ('15','木のおの','03',40,FALSE)
, ('16','どうのおの','03',60,FALSE)
, ('17','はがねのおの','03',80,FALSE)
, ('18','金のおの','03',100,FALSE)
, ('19','ダイヤモンドのおの','03',190,FALSE)
, ('20','あくまのおの','03',200,TRUE)
, ('21','やみのおの','03',210,TRUE)
, ('22','木のゆみ','04',20,FALSE)
, ('23','どうのゆみ','04',40,FALSE)
, ('24','はがねのゆみ','04',60,FALSE)
, ('25','金のゆみ','04',120,FALSE)
, ('26','ダイヤモンドのゆみ','04',170,FALSE)
, ('27','あくまのゆみ','04',180,TRUE)
, ('28','やみのゆみ','04',180,TRUE)
;

-- データ登録 武器カテゴリ
INSERT INTO WEAPON_CATEGORY(CODE, NAME) VALUES
  ('01','剣')
, ('02','杖')
, ('03','斧')
, ('04','弓')
;

-- データ登録 職業-武器カテゴリリファレンス
INSERT INTO JOB_CATEGORY_REFERENCE(JOB,CATEGORY) VALUES
  ('01','01')
, ('01','03')
, ('02','01')
, ('02','03')
, ('03','02')
, ('04','02')
, ('04','03')
, ('05','01')
, ('05','03')
, ('06','01')
, ('05','04')
;