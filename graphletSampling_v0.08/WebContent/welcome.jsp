<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="amchart/javaScript/welcomeScript.js" type="text/javascript"></script>
<link rel="stylesheet" type='text/css' href="amchart/style/upload.css" />
</head>
<body>
	<div class="main">
		<form id='uplform'>
			<fieldset>
				<legend>Uniform Sampling of Graphlets for large Graph</legend>
				<div class='container'>
					<label for='name'>Your Full Name*: </label><br /> <input
						type="text" name='name' id='name' maxlength="75"
						onblur="validateName(this.value)" /><br /> <span
						id='uplform_name_errorloc' class='error'></span>
				</div>
				<div class='container'>
					<label for='email'>Email Address*:</label><br /> <input
						type='text' name='email' id='email' maxlength="75"
						onblur="validateEmail(this.value)" /><br /> <span
						id='uplform_email_errorloc' class='error'></span>
				</div>
				<div class='container'>
					<input type="checkbox" name='sendmail' value="checked">
					<label for='sendemail'>Check this to send an email </label><br /> 
						
				</div>
				<div class='container'>
					<label for='Iterations'>Enter number of iterations:</label><br />
					<input type="text" name='Iterations' id='Iterations' maxlength='8'
					onchange="validateIterations(this.value)" value='2000'><br /><span
					id='uplform_Iterations_errorloc' class='error'></span>
				</div>
				<div class='container'>
					<label for='datafile'>Select a file*:</label><br /> <input
						type="file" name="datafile" id="datafile"
						onchange="validateFile()" size="48" /> <span
						id='uplform_file_errorloc' class='error'></span>
				</div>
				<div class='container'>
					<input type="button" value="Upload"
						onClick="fileUpload(this.form,'UploadServlet','upload'); return false;">
					<input type="reset" value="Reset!"><br>
				</div>
				<div id="upload"></div>
				<div class='error'>${errorMessage}</div>
			</fieldset>
		</form>
	</div>
</body>
</html>