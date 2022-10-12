<%@ page import="classes.Wifi" %>
<%@ page import="classes.ApiModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div>
      <h2>
        와이파이 정보 구하기
      </h2>
    </div>
    <div>
      <a href="#">
        홈
      </a>
      |
      <a href="#">
        위치 히스토리 목록
      </a>
      |
      <a href="/MainServlet.do?comm=list">
        Open API 와이파이 정보 가져오기
      </a>
    </div>
    <div>
      <form action="/MainServlet.do" method="post">
        LAT: <input type="text" name="lat" size="15">,
        LNT: <input type="text" name="lnt" size="15">
      <button type="button">내 위치 가져오기</button>
      <button type="submit">근처 WIPI 정보 보기</button>
      </form>
    </div>
    <div>
      <table>
        <tr>
          <th>거리(Km)</th>
          <th>관리번호</th>
          <th>자치구</th>
          <th>와이파이명</th>
          <th>도로명주소</th>
          <th>상세주소</th>
          <th>설치위치(층)</th>
          <th>설치유형</th>
          <th>설치기관</th>
          <th>서비스구분</th>
          <th>망종류</th>
          <th>설치년도</th>
          <th>실내외구분</th>
          <th>WIFI접속환경</th>
          <th>X좌표</th>
          <th>Y좌표</th>
          <th>작업일자</th>
        </tr>
        <% if(!Wifi.list.isEmpty()) { %>
        <% for(int i = 0; i < 20 && Wifi.list.peek() != null; i++){ %>
        <% ApiModel apiModel = Wifi.list.poll(); %>
        <% Wifi wifi = apiModel.getWifi(); %>
        <tr>
          <td><%= apiModel.dist %></td>
          <td><%= wifi.getX_SWIFI_MGR_NO() %></td>
          <td><%= wifi.getX_SWIFI_WRDOFC() %></td>
          <td><%= wifi.getX_SWIFI_MAIN_NM() %></td>
          <td><%= wifi.getX_SWIFI_ADRES1() %></td>
          <td><%= wifi.getX_SWIFI_ADRES2() %></td>
          <td><%= wifi.getX_SWIFI_INSTL_FLOOR() %></td>
          <td><%= wifi.getX_SWIFI_INSTL_TY() %></td>
          <td><%= wifi.getX_SWIFI_INSTL_MBY() %></td>
          <td><%= wifi.getX_SWIFI_SVC_SE() %></td>
          <td><%= wifi.getX_SWIFI_CMCWR() %></td>
          <td><%= wifi.getX_SWIFI_CNSTC_YEAR() %></td>
          <td><%= wifi.getX_SWIFI_INOUT_DOOR() %></td>
          <td><%= wifi.getX_SWIFI_REMARS3() %></td>
          <td><%= wifi.getLAT() %></td>
          <td><%= wifi.getLNT() %></td>
          <td><%= wifi.getWORK_DTTM() %></td>
        </tr>
        <% } %>
        <% } %>
      </table>
    </div>
  </body>
</html>
