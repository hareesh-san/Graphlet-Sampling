/**
 * This has the java script code for the welcome jsp file of the project
 */
function validateFile(CallBackFunction,form,action_url,div_id) {
	document.getElementById('uplform_file_errorloc').innerHTML = "";
	if(!validateFileExtension()){
		return false;
	}
	var finput = document.getElementById("datafile");
	console.log("This is inside validateFile");
	try{
		// Mozilla Firefox browser, Chrome,IE 
		var f = finput.files[0];
		if (f) {
			var fsize = f.size/1024/1024;
			console.log("uploaded File size is: "+f.size);
			if(fsize >= 200){
				document.getElementById('uplform_file_errorloc').innerHTML = "File size should be less than 200MB";
				return false;
			}

			var r = new FileReader();
			r.onload = function(e) { 
				var mine = e.target.result;
				var trim_mine = mine.trim();
				trim_mine = trim_mine.replace(/^\s+|\s+|\t+$/g, '');
				var isnum = /^\d+$/.test(trim_mine);
				console.log("The isnum is:"+isnum);
				if(isnum )
				{
					if(CallBackFunction != undefined){
						CallBackFunction(form,action_url,div_id);
					}
				}
				else{
					document.getElementById('uplform_file_errorloc').innerHTML = "File can only contain digits and whitespaces";
					return false;
				}   
			};
			r.readAsText(f);
		}
		else{
			if(CallBackFunction != undefined){
				CallBackFunction(form,action_url,div_id);
			}
		}
	}
	catch(err ){
			  txt="There was an error on this page.\n\n";
			  txt+="Error description: " + err.message + "\n\n";
			  txt+="Click OK to continue.\n\n";
			  alert(txt);
	}
}

function format(c) { document.execCommand(c, false, false); }

function validateFileExtension() 
{
	var _validFileExtensions = [".txt"];
	var oInput = document.getElementById("datafile");;
	var sFileName = oInput.value;
	console.log("This is inside validate FileExtention");
	if(sFileName == undefined)
	{
		document.getElementById('uplform_file_errorloc').innerHTML = "No file selected";
		return false;
	}
	var tmp1 = sFileName.replace(/^\s+|\s+$/g, '');
	if(tmp1.length < 1){
		document.getElementById('uplform_file_errorloc').innerHTML = "No file selected";
		return false;
	}
	if (sFileName.length>0) 
	{
		var blnValid = false;
		for (var j = 0; j < _validFileExtensions.length; j++) 
		{
			var sCurExtension = _validFileExtensions[j];
			if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) 
			{
				blnValid = true;
				break;
			}
		}

		if (!blnValid) {
			document.getElementById('uplform_file_errorloc').innerHTML = "Only text file can be uploaded";
			return false;
		}
	}
	return true;
}
function validateEmail(mail)   
{  
	console.log("This is inside validate Email");
	document.getElementById('uplform_email_errorloc').innerHTML = "";
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))  
	{  
		return (true)  ;
	}  
	document.getElementById('uplform_email_errorloc').innerHTML = "Enter a valid email address";
	return (false);  
}  

function validateIterations(number){
	document.getElementById('uplform_Iterations_errorloc').innerHTML = "";
	console.log("This is inside validate Iterations function");
	if(number < 1)
	{
		document.getElementById('uplform_Iterations_errorloc').innerHTML = "Please enter number of " +
				"iterations not less than 1";
		return false;
	}
	return true;
}
function validateName(data){
	console.log("This is inside validateName function");
	document.getElementById('uplform_name_errorloc').innerHTML = "";
	if(data == undefined)
	{
		document.getElementById('uplform_name_errorloc').innerHTML = "Name field cannot be empty";
		return false;
	}
	var dat1 = data.replace(/^\s+|\s+$/g, '');
	if(dat1.length < 1){
		document.getElementById('uplform_name_errorloc').innerHTML = "Name field cannot be empty";
		return false;
	}
	return true;
}

function fileUpload(form, action_url, div_id) {
	// Create the iframe...
	var a = validateName(document.getElementById('name').value);
	var b = validateEmail(document.getElementById('email').value);
	var c = validateFileExtension(); 
	if(!a || !b|| !c){
		return;
	}
	console.log("This is before validateFile");
	// cont_upload(form, action_url, div_id);
	validateFile(cont_upload,form,action_url,div_id);
}

function cont_upload(form, action_url, div_id)
{
	// Set properties of form...
	// form.setAttribute("target", "upload_iframe");
	form.setAttribute("action", action_url);
	form.setAttribute("method", "post");
	form.setAttribute("enctype", "multipart/form-data");
	form.setAttribute("encoding", "multipart/form-data");
	console.log("This is before form submission");
	// Submit the form...
	form.submit();
	console.log("This is after form submission");
	document.getElementById(div_id).innerHTML = "Uploading...";
}

