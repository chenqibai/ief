$(document).ready(function() {
	//var path = '/data/index.json'

	// function getQueryStringArgs() {
	// 	var qs = (location.search.length > 0 ? location.search.substring(1) : "")

	// 	var args = {}

	// 	var items = qs.length ? qs.split("&") : []
	// 	var item = null, name = null,value = null 

	// 	for (i=0 ; i<itens.length ; i++) {
	// 		item = items[i].split("=")
	// 		name = item[0]
	// 		value = items[1]

	// 		if (name.length) {
	// 			args[name] = value
	// 		}
	// 	}

	// 	return args
	// }

	// var args = getQueryStringArgs()
	// var bookId = args[bookId]
	var path = 'http://101.200.188.139:8080/ief-1.0.0-SNAPSHOT/app/get_book_detail?bookId=' + 128
	$.get(path, function(data) {
		$('.recommend span:eq(0)').html(data.data.username)
		$('.name').html('《' + data.data.bookName + '》')
		$('.main-detail p').html(data.data.comment)
		$('.book_logo').css('background','url(http://101.200.188.139:8080/ief-1.0.0-SNAPSHOT/' + data.data.userHeadImg +')')
		$('.main-img').css('background','url(http://101.200.188.139:8080/ief-1.0.0-SNAPSHOT/' + data.data.bookCoverImg +')')
	})

	
})
