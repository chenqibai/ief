$(document).ready(function() {
	

	function getQueryStringArgs() {
	 	var qs = (location.search.length > 0 ? location.search.substring(1) : "")
	 	var args = {}
	 	var items = qs.length ? qs.split("&") : []
	 	var item = null, name = null,value = null 
	 	for (i=0 ; i<items.length ; i++) {
	 		item = items[i].split("=")
	 		name = item[0]
	 		value = item[1]
	 		if (name.length) {
	 			args[name] = value
	 		}
	 	}
	 	return args
	 }

	var args = getQueryStringArgs()
	var bookId = args['bookId']
	var path = 'http://101.200.188.139:8080/ief/app/get_book_detail?bookId=' + bookId
	$.get(path, function(dataset) {

		var data = JSON.parse(dataset)
		$('.recommend span:eq(0)').html(data.data.username)
		$('.name').html('《' + data.data.bookName + '》')
		$('.main-detail p').html(data.data.comment)
		$('.book_logo').css('background','url(http://101.200.188.139:8080/imgServer/' + data.data.userHeadImg +')')
		$('.main-img').css('background','url(http://101.200.188.139:8080/imgServer/' + data.data.bookCoverImg +')')
		
	})

	
})
