var isJSON = false;
var dataDomin = "http://192.168.40.61:8090/qtz_sm/";
// var dataDomin = "http://localhost:8080/qtz_sm_web_app/";

//var urlDomin = "http://192.168.40.84:8888/ppsh_cs/";
var urlDomin = "http://localhost:8080/qtz_sm_web_app/ppsh_cs/";

function queryString(a) {
	return (document.location.search.match(new RegExp("(?:^\\?|&)" + a + "=(.*?)(?=&|$)")) || ["", null])[1]
};

function addDateText(a) {
	switch (a) {
		case 1:
			$("#add").html('<a class="loading" href="">');
			break;
		case 2:
			$("#add").html('<a class="loading" href=""><span>努力加载中...</span></a>');
			break;
		case 3:
			$("#add").html('<a class="error" href=""><span>点我刷新试试?</span></a>');
			break;
		case 4:
			$("#add").html('<center>没有更多了</center>');
			break;
		default:
			return
	}
}
//超市添加到购物车
function addToShoppingCart(data) {
	console.log(data)
	window.location.href = "PPSH://SUPERMARKET//addToShoppingCart?requestParams==" + JSON.stringify(data);
}
//超市移除购物车的商品
function delToShoppingCart(data) {
	console.log(data)
	window.location.href = "PPSH://SUPERMARKET//delToShoppingCart?requestParams==" + JSON.stringify(data);
}
//超市系统回调获取商品信息与数量准备放到购物车
window.getGoodsInfoNumberToShopCartCS = function() {

	}
	//超市系统回调获取商品信息与数量准备立即购买
window.getGoodsInfoNumberToBuyCS = function() {

	}
	//跳转新页面
function goToNewPage(url) {
	console.log("跳转新页面:" + url);
	window.location.href = "PPSH://SUPERMARKET//goToNewPage?url==" + url;
}

//进入便利店
function goToConvenienceStore(data) {
	console.log("进入便利店:" + JSON.stringify(data));
	window.location.href = "PPSH://SUPERMARKET//goToConvenienceStore?requestParams==" + JSON.stringify(data);
}
//便利店系统回调获取商品信息与数量准备放到购物车
window.getGoodsInfoNumberToShopCartBLD = function() {

	}
	//便利店系统回调获取商品信息与数量准备立即购买
window.getGoodsInfoNumberToBuyBLD = function() {

}
 