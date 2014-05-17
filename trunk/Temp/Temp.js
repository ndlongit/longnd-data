
	
	function setGroupingProperty (tableId) {
		var groupSequence = 0;		
		
		$('table#' + tableId + ' tr:gt(2)').each (function (i, element) {
		/*
			if(i >= 3) {
				if(i == 3) {
					groupSequence++;
					var groupName = tableId + 'Group' + groupSequence;
					var foldCell = $(element).children().first();
					//$(foldCell).attr('onclick', 'return toggleRows3("'+ groupName + '");');
					//$(foldCell)..addClass('foldButton');
				} else {				
					//$(element).attr('groupName', groupName);
				}
			}
			*/
		});
	}
		
	function toggleRows3 (groupName) {
		$('table tr[groupName=' + groupName + ']').toggle();
	}