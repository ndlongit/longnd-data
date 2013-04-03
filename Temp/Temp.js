<!-- Javascript -->
<script type="text/javascript" src="<#= tw.system.model.findManagedFileByPath("jquery.js", TWManagedFile.Types.Web).url #>"></script>
<script type="text/javascript" src="<#= tw.system.model.findManagedFileByPath("currency_format_library.js", TWManagedFile.Types.Web).url #>"></script>
<script type="text/javascript" src="<#= tw.system.model.findManagedFileByPath("tw_data_tables_final.js", TWManagedFile.Types.Web).url #>"></script>
<script type="text/javascript" src="<#= tw.system.model.findManagedFileByPath("q2o_util.js", TWManagedFile.Types.Server).url #>"></script>
<script type="text/javascript">
    
    $(function(){
		showActionDone(false);
		$('#Table0 tr:nth(1)').hide();
		$("#Table0 td:nth-child(1),#Table0 th:nth-child(1)").hide();
		addTableEvent(false);
	   
	   $('#ButtonGroup2_Button0').click(function() { 
		  save(false);
	   });
    });
    
	function addTableEvent(flag){
		$("#Table0 td").click(function() {
			 var row = $(this).parent('tr').prevAll().length;
			 if(row >=0){
				 if(flag==true){
					 selectRow(this, 'single', false,row, null, false);
				 }
				 var tableRow = $(this).parent('tr');
				 bindRowToControls(tableRow);             
				 $("#InputText1").val(row);
			 }
		});
	}
	
	function save(clearForm) {
		if(!validate()) {
			return;
		}
		
		 var mode = 1; //insert new
		 var row = $("#InputText1").val();		 
		 if(isNewRow(row)){
			 $("#InputText1").val(-1);
			 $("#InputText10").val(-1);
		 }
		 var id = $("#InputText10").val();    
		 if(id>0){
			 mode = 2 //update
		 }
		 saveImportFee(mode,id,clearForm);
	}	
	
	function validate() {
		if($("#ComboBox1").val() == -1) {
			showError('Vanaf artikelhoofdgroep is required')
			return false;
		}
		if($("#ComboBox2").val() == -1) {
			showError('T/m artikelhoofdgroep is required')
			return false;
		}
		if($("#ComboBox3").val() == -1) {
			showError('Art.subgroep is required')
			return false;
		}
		if(trim($("#InputText0").val()) == '') {
			showError('Opslag (%) is required')
			return false;
		}
		if(isNaN(trim($("#InputText0").val()))) {
			showError('Opslag (%) must be a number')
			return false;
		}
		
		return true;
	}
	
	function showError(message) {
		alert(message)
	}
	
	function isNewRow(row){
		if(row<0){
			return true;
		}
		
		var fromMainGroup = $("#ComboBox1").val();
		var toMainGroup = $("#ComboBox2").val();
		var tableRow= "#Table0 tr:eq(" + String(row) + ")"; 
		var tds = $(tableRow).find("td"); 
		
		var t1 = trim($(tds[2]).text());
		var t2 = trim($(tds[3]).text());
		
		if(fromMainGroup != t1 || toMainGroup != t2){
			return true;
		}
		
		return false;
	}
	
	function saveImportFee(mode,id,clearForm) {
		var importFee = "<variable name='importFee' type='ImportFee'>" +
			  "<id type='Integer'>" + String(id) + "</id>" +
			  "<groupName type='String'>" + xmlEncode($("#InputText2").val()) + "</groupName>" +
			  "<fromMainGroup type='Integer'>" + xmlEncode($("#ComboBox1").val()) + "</fromMainGroup>" +
			  "<toMainGroup type='Integer'>" + xmlEncode($("#ComboBox2").val()) + "</toMainGroup>" +
			  "<articleSubGroup type='String'>" + xmlEncode($("#ComboBox3").val()) + "</articleSubGroup>" +
			  "<feePercentage type='Decimal'>" + xmlEncode($("#InputText0").val()) + "</feePercentage>" +
			  "<countryId type='Integer'>" + xmlEncode($("#ComboBox0").val()) + "</countryId>" +
			  "</variable>";
			var saveMode = "<variable name='mode' type='Integer'>" + String(mode) + "</variable>";
			var inputXML = "<input>" + importFee + saveMode +  "</input>";
		try{				
			tw.coach.callService("Save ImportFee Ajax", inputXML, function(data) {
				var result= data.errorCode;
				if(result <= 0){
					saveTableRow(result);
					showActionDone(true);
					$('#InputText10').val(result);                                 
					if(clearForm==true)
					{
					   $("#InputText1").val(-1);
					   $("#InputText10").val(-1);
					   
					   $('#ComboBox1').val(-1);
					   $('#ComboBox2').val(-1);
					   $('#ComboBox3').val(-1);
					  
					}
					$('#InputText2').focus();
					$('#InputText2').select();     
				}else{
					alert(data.errorMessage);
					showActionDone(false);
				}
			});		
		} catch (e) {
		}
	}	
		
	function saveTableRow(id){
		var row = $("#InputText1").val();
		if(row>=0){
			updateTableRow(row);
		}else{
			addTableRow(id);
		}
	}
	
	function updateTableRow(row){
		 var rows= "#Table0 tr:eq(" + String(row) + ")"; 
		 var id ="";
		 var run=2;
		 var index=0;
		 var tr = $(rows);
		 bindControlsToRow(tr);
	}
	
	function addTableRow(inputId){
		 var id ="";
		 var run=2;
		 var index=0;
		 var tr = $('#Table0 tr:last').clone(true).insertAfter('#Table0 tr:first');
		 $('#InputText1').val(1);
		 bindControlsToRow(tr,inputId);
		 $(tr).show();
		 addTableEvent(false);
	}
	
	function bindControlsToRow(tableRow,inputId) {
		var tds = $(tableRow).find("td");
		//
		if(inputId!='undefined')
		{
			$(tds[0]).text(inputId);
		}
		//    
		$(tds[1]).text($("#ComboBox0").val());
		$(tds[2]).text($("#ComboBox1").val());   
		$(tds[3]).text($("#ComboBox2").val());
		$(tds[4]).text($("#ComboBox3").val());  
		$(tds[5]).text($("#InputText0").val());  
		$(tds[6]).text($("#InputText2").val());    
	}
	
	function bindRowToControls(tableRow) {
		var tds = $(tableRow).find("td");  
		$("#InputText10").val(trim($(tds[0]).text()));
		$("#ComboBox0").val(trim($(tds[1]).text()));
		$("#ComboBox1").val(trim($(tds[2]).text()));
		$("#ComboBox2").val(trim($(tds[3]).text()));
		$("#ComboBox3").val(trim($(tds[4]).text()));
		$("#InputText0").val(trim($(tds[5]).text()));
		$("#InputText2").val(trim($(tds[6]).text()));		
	}
	

	function showActionDone(success) {
		if(success==true) {
			$("div .coachGood").show();
		}
		else {
			$("div .coachGood").hide();
		}
	}

	function deleteRow(){
		var answer = confirm("Do you really want to delete this line?");
		if(answer) {   
			var inputXML = "<input><variable name='id' type='Integer'>" + xmlEncode($("#InputText10").val()) +  "</variable></input>";
			tw.coach.callService("Delete ImportFee Ajax", inputXML, function(data) {
				var result= data.errorCode;
				if(result <= 0){
					 var row = $("#InputText1").val();
					 var rows= "#Table0 tr:eq(" + String(row) + ")"; 
					 $(rows).remove(); 
					 $("#InputText1").val(-1);
					 $("#InputText10").val(-1);
				}else{
					 alert('Delete failed');
					 $("#InputText1").val(-1);
					 $("#InputText10").val(-1);
				}
				 $('#InputText6').focus();
				 $('#InputText6').select();
			});
		}
	}
</script>