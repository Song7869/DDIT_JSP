/**
 * 
 */
$(document).on("click", ".linkBtn", function(){
	let gopage = $(this).data("gopage");
	if(!gopage) return false;
	location.href = gopage;
});