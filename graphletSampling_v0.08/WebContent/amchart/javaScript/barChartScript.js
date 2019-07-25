/**
 * 
 */

            AmCharts.ready(function () {
                // SERIAL CHART
                
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "Graphlet";
                chart.pathToImages = "../jspFiles/images/";
                // the following two lines makes chart 3D
                chart.depth3D = 20;
                chart.angle = 30;
				chart.startDuration = 2;
                chart.marginLeft = 10;
				//chart.autoMargins = true;

				// listen for "dataUpdated" event (fired when chart is inited) and call zoomChart method when it happens
                chart.addListener("dataUpdated", zoomChart);
				
				// AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.labelRotation = 60;
                categoryAxis.dashLength = 5;
                categoryAxis.gridPosition = "start";
                categoryAxis.minorGridEnabled = true;
                categoryAxis.minorGridAlpha = 0.1;
                
                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.title = "GFD Score";
                valueAxis.dashLength = 5;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.type = "column";
                graph.bullet = "diamond";
                graph.valueField = "GFD";
                graph.customBulletField = "bullet";
				graph.bulletOffset = 15;
                graph.colorField = "color";
				graph.bulletSize = 30; // bullet image should be rectangle (width = height)
                graph.balloonText = "<span style='font-size:14px'>[[category]]:<b>[[value]]</b><b>[[baloonText]]</b></br></span>";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                chart.addGraph(graph);

                // CURSOR
                var chartCursor = new AmCharts.ChartCursor();
                chartCursor.cursorAlpha = 0;
                chartCursor.zoomable = true;
                chartCursor.categoryBalloonEnabled = false;
                chart.addChartCursor(chartCursor);
                
             // SCROLLBAR
                var chartScrollbar = new AmCharts.ChartScrollbar();
                chart.addChartScrollbar(chartScrollbar);
                chart.creditsPosition = "top-left";

                // WRITE
                chart.write("chartdiv");
            });
            function zoomChart() {
                // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
                chart.zoomToIndexes(0,28);
            }