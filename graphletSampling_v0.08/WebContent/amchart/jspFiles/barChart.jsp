<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%	String Spaces = new String("   ");
	int[] data=(int [])request.getSession().getAttribute("GFDString");
	
%>
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="../style/style.css" type="text/css">
		<script src="../javaScript/amcharts.js" type="text/javascript"></script>
        <script src="../javaScript/serial.js" type="text/javascript"></script>
		<script src="../javaScript/barChartScript.js" type="text/javascript"></script>
        <script type="text/javascript">
            var chart;
            var chartData = [
                {
                    "Graphlet": "Graphlet-1",
                    "GFD": <%=data[0]%>,
                    "color": "#FF0F00",
					"bullet": "../jspFiles/images/sg1.png",
					"baloonText" : "<img src='../jspFiles/images/sg1.png'>"
                },
                {
                    "Graphlet": "Graphlet-2",
                    "GFD": <%=data[1]%>,
                    "color": "#FF6600",
					"bullet": "../jspFiles/images/sg2.png",
					"baloonText" : "<img src='../jspFiles/images/sg2.png'>"
                },
                {
                    "Graphlet": "Graphlet-3",
                    "GFD": <%=data[2]%>,
                    "color": "#FF9E01",
					"bullet": "../jspFiles/images/sg3.png",
					"baloonText" : "<img src='../jspFiles/images/sg3.png'>"
                },
                {
                    "Graphlet": "Graphlet-4",
                    "GFD": <%=data[3]%>,
                    "color": "#FCD202",
                    "bullet": "../jspFiles/images/sg4.png",
                    "baloonText" : "<img src='../jspFiles/images/sg4.png'>"
                },
                {
                    "Graphlet": "Graphlet-5",
                    "GFD": <%=data[4]%>,
                    "color": "#F8FF01",
                    "bullet": "../jspFiles/images/sg5.png",
                    "baloonText" : "<img src='../jspFiles/images/sg5.png'>"
                },
                {
                    "Graphlet": "Graphlet-6",
                    "GFD": <%=data[5]%>,
                    "color": "#B0DE09",
                    "bullet": "../jspFiles/images/sg6.png",
                    "baloonText" : "<img src='../jspFiles/images/sg6.png'>"
                },
                {
                    "Graphlet": "Graphlet-7",
                    "GFD": <%=data[6]%>,
                    "color": "#04D215",
                    "bullet": "../jspFiles/images/sg7.png",
                    "baloonText" : "<img src='../jspFiles/images/sg7.png'>"
                },
                {
                    "Graphlet": "Graphlet-8",
                    "GFD": <%=data[7]%>,
                    "color": "#0D8ECF",
                    "bullet": "../jspFiles/images/sg8.png",
                    "baloonText" : "<img src='../jspFiles/images/sg8.png'>"
                },
                {
                    "Graphlet": "Graphlet-9",
                    "GFD": <%=data[8]%>,
                    "color": "#0D52D1",
                    "bullet": "../jspFiles/images/sg9.png",
                    "baloonText" : "<img src='../jspFiles/images/sg9.png'>"
                },
                {
                    "Graphlet": "Graphlet-10",
                    "GFD": <%=data[9]%>,
                    "color": "#2A0CD0",
                    "bullet": "../jspFiles/images/sg10.png",
                    "baloonText" : "<img src='../jspFiles/images/sg10.png'>"
                },
                {
                    "Graphlet": "Graphlet-11",
                    "GFD": <%=data[10]%>,
                    "color": "#8A0CCF",
                    "bullet": "../jspFiles/images/sg11.png",
                    "baloonText" : "<img src='../jspFiles/images/sg11.png'>"
                },
                {
                    "Graphlet": "Graphlet-12",
                    "GFD": <%=data[11]%>,
                    "color": "#CD0D74",
                    "bullet": "../jspFiles/images/sg12.png",
                    "baloonText" : "<img src='../jspFiles/images/sg12.png'>"
                },
                {
                    "Graphlet": "Graphlet-13",
                    "GFD": <%=data[12]%>,
                    "color": "#754DEB",
                    "bullet": "../jspFiles/images/sg13.png",
                    "baloonText" : "<img src='../jspFiles/images/sg13.png'>"
                },
                {
                    "Graphlet": "Graphlet-14",
                    "GFD": <%=data[13]%>,
                    "color": "#DDDDDD",
                    "bullet": "../jspFiles/images/sg14.png",
                    "baloonText" : "<img src='../jspFiles/images/sg14.png'>"
                },
                {
                    "Graphlet": "Graphlet-15",
                    "GFD": <%=data[14]%>,
                    "color": "#999999",
                    "bullet": "../jspFiles/images/sg15.png",
                    "baloonText" : "<img src='../jspFiles/images/sg15.png'>"
                },
                {
                    "Graphlet": "Graphlet-16",
                    "GFD": <%=data[15]%>,
                    "color": "#333333",
                    "bullet": "../jspFiles/images/sg16.png",
                    "baloonText" : "<img src='../jspFiles/images/sg16.png'>"
                },
                {
                    "Graphlet": "Graphlet-17",
                    "GFD": <%=data[16]%>,
                    "color": "#000000",
                    "bullet": "../jspFiles/images/sg17.png",
                    "baloonText" : "<img src='../jspFiles/images/sg17.png'>"
                },
				{
                    "Graphlet": "Graphlet-18",
                    "GFD": <%=data[17]%>,
                    "color": "#F8FF01",
                    "bullet": "../jspFiles/images/sg18.png",
                    "baloonText" : "<img src='../jspFiles/images/sg18.png'>"
                },
                {
                    "Graphlet": "Graphlet-19",
                    "GFD": <%=data[18]%>,
                    "color": "#B0DE09",
                    "bullet": "../jspFiles/images/sg19.png",
                    "baloonText" : "<img src='../jspFiles/images/sg19.png'>"
                },
                {
                    "Graphlet": "Graphlet-20",
                    "GFD": <%=data[19]%>,
                    "color": "#04D215",
                    "bullet": "../jspFiles/images/sg20.png",
                    "baloonText" : "<img src='../jspFiles/images/sg20.png'>"
                },
                {
                    "Graphlet": "Graphlet-21",
                    "GFD": <%=data[20]%>,
                    "color": "#0D8ECF",
                    "bullet": "../jspFiles/images/sg21.png",
                    "baloonText" : "<img src='../jspFiles/images/sg21.png'>"
                },
                {
                    "Graphlet": "Graphlet-22",
                    "GFD": <%=data[21]%>,
                    "color": "#0D52D1",
                    "bullet": "../jspFiles/images/sg22.png",
                    "baloonText" : "<img src='../jspFiles/images/sg22.png'>"
                },
                {
                    "Graphlet": "Graphlet-23",
                    "GFD": <%=data[22]%>,
                    "color": "#2A0CD0",
                    "bullet": "../jspFiles/images/sg23.png",
                    "baloonText" : "<img src='../jspFiles/images/sg23.png'>"
                },
                {
                    "Graphlet": "Graphlet-24",
                    "GFD": <%=data[23]%>,
                    "color": "#8A0CCF",
                    "bullet": "../jspFiles/images/sg24.png",
                    "baloonText" : "<img src='../jspFiles/images/sg24.png'>"
                },
                {
                    "Graphlet": "Graphlet-25",
                    "GFD": <%=data[24]%>,
                    "color": "#CD0D74",
                    "bullet": "../jspFiles/images/sg25.png",
                    "baloonText" : "<img src='../jspFiles/images/sg25.png'>"
                },
                {
                    "Graphlet": "Graphlet-26",
                    "GFD": <%=data[25]%>,
                    "color": "#754DEB",
                    "bullet": "../jspFiles/images/sg26.png",
                    "baloonText" : "<img src='../jspFiles/images/sg26.png'>"
                },
                {
                    "Graphlet": "Graphlet-27",
                    "GFD": <%=data[26]%>,
                    "color": "#DDDDDD",
                    "bullet": "../jspFiles/images/sg27.png",
                    "baloonText" : "<img src='../jspFiles/images/sg27.png'>"
                },
                {
                    "Graphlet": "Graphlet-28",
                    "GFD": <%=data[27]%>,
                    "color": "#999999",
                    "bullet": "../jspFiles/images/sg28.png",
                    "baloonText" : "<img src='../jspFiles/images/sg28.png'>"
                },
                {
                    "Graphlet": "Graphlet-29",
                    "GFD": <%=data[28]%>,
                    "color": "#333333",
                    "bullet": "../jspFiles/images/sg29.png",
                    "baloonText" : "<img src='../jspFiles/images/sg29.png'>"
                }
            ];

        </script>
    </head>

<body>
<div id="chartdiv" style="width: 100%; height: 400px;"></div>
</body>
</html>