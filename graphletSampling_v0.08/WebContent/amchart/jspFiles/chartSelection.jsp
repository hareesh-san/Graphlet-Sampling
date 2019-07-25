<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%	String Spaces = new String("   ");
	String barHelpPath="/amchart/jspFiles/barChart.jsp",lineHelpPath="/amchart/jspFiles/lineChart.jsp",
		    cssHelpPath="/amchart/style/style.css";
	int [] data = (int[])request.getSession().getAttribute("GFDString");
	
%>
<link rel="stylesheet" href=<%=request.getContextPath()%><%=cssHelpPath%> "type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript" >

$(document).ready(function(){
	$(".show1").hide();
	$(".show2").hide();
  $(".hide1").click(function(){
	  
	 // alert(" Came into the hide");
    $(".iframe1").hide(1000);
    $(".hide1").hide();
    $(".show1").show();
  });
  $(".show1").click(function(){
	 // alert("Came into the show");
    $(".iframe1").show(1000);
    $(".show1").hide();
    $(".hide1").show();
  });
  $(".hide2").click(function(){
		 // alert(" Came into the hide");
	    $(".iframe2").hide(1000);
	    $(".hide2").hide();
	    $(".show2").show();
	  });
	  $(".show2").click(function(){
		 // alert("Came into the show");
	    $(".iframe2").show(1000);
	    $(".show2").hide();
	    $(".hide2").show();
	  });
});
</script>
<body>
<h2>The GFD values are as follows</h2>
<%	for (int i = 0; i < data.length; i++) {%>
	<%=data[i]%> 
	<%=Spaces%>
<%}%>
<br><br>

<iframe class="iframe1" src=<%=request.getContextPath()%><%=barHelpPath%> 
	width=100% height=450px frameborder="0" ></iframe>
<br>
<button class="hide1">Hide Line chart</button>
<button class="show1">Show Line chart</button>
<br><br>

<iframe class="iframe2" src=<%=request.getContextPath()%><%=lineHelpPath%> 
	width=100% height=450px frameborder="0" ></iframe>
<br>

<button class="hide2">Hide bar chart</button>
<button class="show2">Show bar chart</button>

</html>