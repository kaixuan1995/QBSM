<script src="<%=basePath%>assets/js/chosen.jquery.min.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
							$(".chosen-select").chosen();
				$('#chosen-multiple-style').on('click', function(e) {
					var target = $(e.target).find('input[type=radio]');
					var which = parseInt(target.val());
					if (which == 2) $('#form-field-select-4').addClass('tag-input-style');
					else $('#form-field-select-4').removeClass('tag-input-style');
				});
				
			});
		</script>