# OpenWIFIProject
 
## 기능

서울시 데이터 포털에서 Open Wifi 정보를 받아 입력 좌표에 가장 가까운 20개를 뿌려준다.

## 목적

인근 공공 Wifi 탐색.

## 사용 Tools & Libraries
 - Intellij Ultimate
 - Tomcat
 - SQLite
 - gson
 - lombok
 - exERD

## 진행 과정 - load data

1. Open API 이용해 데이터 받아오기.

2. gson 이용해 객체로 파싱.

3. DB에 저장/업데이트.

## 진행 과정 - retrieve data

1. DB 데이터 불러오기.

2. 입력 위치 기반으로 거리 계산.

3. 최소거리 20개 출력.

## 실행 방법

1. Tomcat 다운로드

2. Run - Edit configurations 창에서 Tomcat Server 밑에 Tomcat 8.5.83 클릭

3. Application Server에 다운한 Tomcat server 등록 후 저장&실행