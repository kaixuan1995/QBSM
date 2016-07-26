


<script src="<%=basePath%>assets/js/bootbox.min.js"></script>
		<script type="text/javascript">
		/**
			var bootbox_regular = #bootbox-regular;
			
			var bootbox_confirm = #bootbox-confirm;
			var confirm_message = "???";
			var bootbox_options = #bootbox-options;
		*/
		
			jQuery(function($) {

				$(bootbox_regular).on(ace.click_event, function() {
					bootbox.prompt("What is your name?", function(result) {
						if (result === null) {
							//Example.show("Prompt dismissed");
						} else {
							//Example.show("Hi <b>"+result+"</b>");
						}
					});
				});
					
				$(bootbox_confirm).on(ace.click_event, function() {
					bootbox.confirm(confirm_message, function(result) {
						if(result) {
							//
						}
					});
				});
					
				$(bootbox_options).on(ace.click_event, function() {
					bootbox.dialog({
						message: "<span class='bigger-110'>I am a custom dialog with smaller buttons</span>",
						buttons: 			
						{
							"success" :
							 {
								"label" : "<i class='icon-ok'></i> Success!",
								"className" : "btn-sm btn-success",
								"callback": function() {
									//Example.show("great success");
								}
							},
							"danger" :
							{
								"label" : "Danger!",
								"className" : "btn-sm btn-danger",
								"callback": function() {
									//Example.show("uh oh, look out!");
								}
							}, 
							"click" :
							{
								"label" : "Click ME!",
								"className" : "btn-sm btn-primary",
								"callback": function() {
									//Example.show("Primary button");
								}
							}, 
							"button" :
							{
								"label" : "Just a button...",
								"className" : "btn-sm"
							}
						}
					});
				});
			
			
			
				
			});
		</script>