# OpenWIFIProject
 
서울시 데이터 포털에서 Open Wifi 정보를 받아 가장 가까운 20개를 뿌려준다.

## 사용 Tools & Libraries
 - Intellij Ultimate
 - Tomcat
 - MariaDB
 - gson
 - lombok

## 진행 과정 - load data

1. Open API 이용해 데이터 받아오기.

2. gson 이용해 객체로 파싱.

3. DB에 저장/업데이트.

## 진행 과정 - retrieve data

1. DB 데이터 불러오기.

2. 입력 위치 기반으로 거리 계산.

3. 최소거리 20개 출력.
