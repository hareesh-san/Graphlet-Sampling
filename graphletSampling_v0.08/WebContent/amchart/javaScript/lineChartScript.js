/**
 * 
 */

AmCharts.ready(function() {
				// SERIAL CHART
				linechart = new AmCharts.AmSerialChart();
				linechart.pathToImages = "../jspFiles/images/";
				linechart.dataProvider = linechartData;
				linechart.marginLeft = 10;
				linechart.startDuration = 2;
				linechart.categoryField = "Graphlet";
				linechart.addListener("dataUpdated", zoomChart);

				// AXES
				// category
				var categoryAxis = linechart.categoryAxis;
				categoryAxis.labelRotation = 60;
				categoryAxis.dashLength = 5;
				categoryAxis.gridPosition = "start";
				categoryAxis.minorGridEnabled = true;
				categoryAxis.minorGridAlpha = 0.1;

				// value
				var valueAxis = new AmCharts.ValueAxis();
				valueAxis.title = "GFD Score";
				valueAxis.dashLength = 5;
				linechart.addValueAxis(valueAxis);

				// GRAPH
				graph = new AmCharts.AmGraph();
				graph.type = "line"; // this line makes the graph smoothed line.
				graph.lineColor = "#77B6B4";
				graph.bullet = "diamond";
				graph.bulletSize = 30; // bullet image should be rectangle (width = height)
				graph.bulletOffset = 30;
				graph.bulletBorderColor = "#FFFFFF";
				graph.bulletBorderAlpha = 1;
				graph.bulletBorderThickness = 2;
				graph.valueField = "GFD";
				graph.customBulletField = "bullet";
				graph.balloonText = "<span style='font-size:14px'>[[category]]:<b>[[value]]</b></br><b>[[baloonText]]</b></span>";
				graph.lineThickness = 2;
				linechart.addGraph(graph);

				// CURSOR
				var chartCursor = new AmCharts.ChartCursor();
				chartCursor.cursorAlpha = 0;
				chartCursor.cursorPosition = "mouse";
				linechart.addChartCursor(chartCursor);

				// SCROLLBAR
				var chartScrollbar = new AmCharts.ChartScrollbar();
				linechart.addChartScrollbar(chartScrollbar);
				linechart.creditsPosition = "top-left";

				// WRITE
				linechart.write("linechartdiv");
			});

	function zoomChart() {
		// different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
		linechart.zoomToIndexes(0, 28);
	}