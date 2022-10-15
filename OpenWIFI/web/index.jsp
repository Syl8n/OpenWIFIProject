<%@ page import="classes.Wifi" %>
<%@ page import="classes.ApiModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
    <link href="styles.css?ver1" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div>
      <h2>
        와이파이 정보 구하기
      </h2>
    </div>
    <div>
      <a href="/">
        홈
      </a>
      |
      <a href="/MainServlet.do?comm=log">
        위치 히스토리 목록
      </a>
      |
      <a href="/MainServlet.do?comm=list">
        Open API 와이파이 정보 가져오기
      </a>
    </div>
    <div>
      <form action="/MainServlet.do" method="post">
        <input type="hidden" name="comm" value="search">
        LAT: <input type="text" id="lat" name="lat" size="15" value="0.0">,
        LNT: <input type="text" id="lnt" name="lnt" size="15" value="0.0">
      <button type="button" onclick="getUserLocation()">내 위치 가져오기</button>
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
          <th>위도</th>
          <th>경도</th>
          <th>작업일자</th>
        </tr>
        <% if(!Wifi.list.isEmpty()) { %>
        <% for(int i = 0; i < Math.min(20, Wifi.list.size()); i++){ %>
        <% ApiModel apiModel = Wifi.list.get(i); %>
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
        <% } else { %>
        <tr>
          <td colspan="17">
            <b>위치 정보를 입력한 후에 조회해 주세요.</b>
          </td>
        </tr>
        <% } %>
      </table>
    </div>
    <script type="text/javascript" src="scripts.js"></script>
  </body>
</html>
