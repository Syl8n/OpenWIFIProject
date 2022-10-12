<%@ page import="classes.Log" %>
<%@ page import="classes.WifiService" %>
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
      <a href="/">
        홈
      </a>
      |
      <a href="">
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
          <th>ID</th>
          <th>X좌표</th>
          <th>Y좌표</th>
          <th>조회일자</th>
          <th>비고</th>
        </tr>
        <% if(!Log.list.isEmpty()) {%>
        <% for(int i = Log.list.size() - 1; i >= 0; i--){ %>
        <% Log log = Log.list.get(i); %>
        <tr>
          <td><%= log.getId() %></td>
          <td><%= log.getLnt() %></td>
          <td><%= log.getLat() %></td>
          <td><%= log.getDttm() %></td>
          <td><button type="button" onclick=" location='/MainServlet.do?comm=delete&num=<%= log.getId()%>'">삭제</button></td>
        </tr>
        <% } %>
        <% } %>
      </table>
    </div>
  </body>
</html>
