<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<!--
/*
* Date : 2023.07.27
* Author : 권성준
* E-mail : thistrik@naver.com
*/
-->

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FastPickup</title>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<body>
<%@ include file="../include/header.jsp" %>
<h3>월별 일별 통계</h3>
<div class="card">
  <div class="bg-light rounded h-100">
    <div style="display: flex;">
      <div id="curve_chart" style="width: 50%; height: 500px"></div>
      <div id="column_chart_month" style="width: 50%; height: 500px;"></div>
    </div>
    <div style="display: flex;">
      <div id="curve_chart_member" style="width:50%; height: 500px"></div>
      <div id="column_chart_month_member" style="width: 50%; height: 500px;"></div>
    </div>
  </div>
</div>
<%@ include file="../include/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
  // Goggle Chart Start
  google.charts.load('current', {'packages': ['corechart']});
  google.charts.setOnLoadCallback(drawChartDay);

  function drawChartDay() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '날짜');
    data.addColumn('number', '가맹점');
    <c:forEach var="entry" items="${listStoreEntryDay}">
      console.log('날짜: ${entry.formattedRegistDate}, 가맹점 수: ${entry.storeCount}'); 
    data.addRow(['${entry.formattedRegistDate}', ${entry.storeCount}]);
    </c:forEach>

    var options = {
      title: '일별 가맹점 입점 통계',
      curveType: 'function',
      legend: {position: 'bottom'},
      colors: ["#ae2d33"]
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    chart.draw(data, options);
  }

  // Goggle Chart Start
  google.charts.load('current', {'packages': ['corechart']});
  google.charts.setOnLoadCallback(drawChartMonth);

  function drawChartMonth() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '월');
    data.addColumn('number', '가맹점');
    <c:forEach var="entry" items="${listStoreEntry}">
    data.addRow(['${entry.registMonth}', ${entry.storeCount}]);
    </c:forEach>

    var options = {
      title: '월별 가맹점 입점',
      bars: 'vertical', // 막대 방향 (가로: 'horizontal', 세로: 'vertical')
      legend: {position: 'bottom'},
      colors: ["#ae2d33"]
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('column_chart_month'));
    chart.draw(data, options);
  }

  //////////////////////////////////
  // Member Chart Start
  // Goggle Chart Start
  //////////////////////////////////
  google.charts.load('current', {'packages': ['corechart']});
  google.charts.setOnLoadCallback(drawChartDayMember);

  function drawChartDayMember() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '날짜');
    data.addColumn('number', '회원수');
    <c:forEach var="entry" items="${listMemberEntryDay}">
    data.addRow(['${entry.registDate}', ${entry.signUpCount}]);
    </c:forEach>

    var options = {
      title: '일별 회원가입 통계',
      curveType: 'function',
      legend: {position: 'bottom'},
      colors: ["#ae2d33"]
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart_member'));
    chart.draw(data, options);
  }

  // Goggle Chart Start
  google.charts.load('current', {'packages': ['corechart']});
  google.charts.setOnLoadCallback(drawChartMonthMember);


  function drawChartMonthMember() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '월');
    data.addColumn('number', '회원수');
    <c:forEach var="entry" items="${listMemberEntry}">
    data.addRow(['${entry.registMonth}', ${entry.signUpCount}]);
    </c:forEach>

    var options = {
      title: '월별 회원가입 통계',
      bars: 'vertical', // 막대 방향 (가로: 'horizontal', 세로: 'vertical')
      legend: {position: 'bottom'},
      colors: ["#ae2d33"]
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('column_chart_month_member'));
    chart.draw(data, options);
  }


</script>

</body>
</html>