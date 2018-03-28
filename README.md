# springboot-sample-mybatis

  springboot-1.5.10.RELEASE と mybatis-spring-boot-starter-1.3.2 を利用したサンプルアプリケーションです。

## Description

  商品展示を行うアプリケーションに仕上がっています。
  RPGの武器屋風を想定しています。

  ログインユーザの職業により展示する商品が変化する仕掛けが入っています。

### 動作環境

- Java8 (OpenJDK 1.8.0_152)

## セットアップ

```
  git clone https://github.com/NARH/springboot-sample-mybatis.git
  cd springboot-sample-mybatis
  ./gradlew bootRun
```
  http://localhost:8080


### 画面一覧

- ログイン画面

  <img src="https://user-images.githubusercontent.com/478067/38017876-4fb0649e-32ae-11e8-8082-d51526f0960b.png" width="25%">

- 一覧画面

  <img src="https://user-images.githubusercontent.com/478067/38017908-6759699c-32ae-11e8-88e3-6657ccad3206.png" width="25%">
  <img src="https://user-images.githubusercontent.com/478067/38017910-6a19a106-32ae-11e8-9260-c8e2b2afe1dd.png" width="25%">

また、データベースは H2 を組み込んでいるので
http://loalhost:8080/h2-console でデータベース操作が可能です。

  <img src="https://user-images.githubusercontent.com/478067/38017891-5b70fd2a-32ae-11e8-8dc1-a0f1467b2f96.png" width="25%">

  <img src="https://user-images.githubusercontent.com/478067/38017882-54f81c76-32ae-11e8-969c-5dc28cc2aecc.png" width="25%">


### テーブル一覧

- AUTHORITIES 権限テーブル
- JOB\_CATEGORY\_REFERENCE 職業と武器分類関連テーブル
- JOB\_MASTER 職業マスタ
- USERS ログインユーザ
- WEAPON\_CATEGORY 武器分類テーブル
- WEAPON\_MASTER 武器マスタ

### ログインとセキュリティー

  ログインは spring-security の form login を利用しています。
  spring-security　が提供するCSRF 対策が機能しているので、各リクエストには自動的にトークン連携がされています。
  remember-me 機能は利用していません。

### テンプレート

  テンプレートエンジンは thymeleaf を利用しています。
  css は bootstrap からフリー素材を選択して利用しています。
  pagination には paginationjs を利用しています。

### ファイル構成

- src/main/java 本体ソースコード
- src/main/resources/config springboot 設定ファイル
- src/main/resources/mybatis mybatis 設定ファイル
- src/main/resources/templates thymeleaf テンプレート
- src/main/resources/META-INF/mybatis/mappers mybatis SQL Mapper XML ファイル
- src/main/resources/static/css CSS
- src/main/resources/static/fonts font
- src/main/resources/static/images 画像
- src/main/resources/static/js Javascript
- src/test/java Junit テストコード
- src/test/resources/ 未使用

## デプロイ

### heroku にデプロイ

  heroku container にデプロイできる Dockerfile を用意しました。

  docker for mac で検証しています。
  docker および heroku CLI をインストールした後に以下のコマンドを実行するとデプロイできます。

  ```
  $ heroku container:login
  $ heroku create
  $ heroku container:push web
  $ heroku open

  ```


## 謝辞

  BIBENDUM (基本コンセプト・データ提供）