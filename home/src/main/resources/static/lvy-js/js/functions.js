
/* 
 * ===================================
 * 封装JQuery的函数库
 * date:2015-04-06 Aaron整理
 * ===================================
 */

(function($){
	//文字上下循环滚动
    $.fn.FontScroll = function(options){
        var d = { time: 4000, num: 1, ul:'ul', li:'li'}
        var o = $.extend(d,options);
        
		var scrtime;
		var $ul = $(this).children(o.ul);
		var ulHeight = $ul.height();
		var liHeight = $ul.find(o.li+":last").height();
		var i = 0;
		this.hover(function(){
			clearInterval(scrtime);
		},function(){
			scrtime = setInterval(function(){
				if(i < 1){
					$ul.animate({maxHeight:ulHeight-liHeight});
				}
				$ul.animate({maxHeight:ulHeight-liHeight,marginTop : liHeight * o.num + "px"},1000,function(){
					$ul.find(o.li+":last").prependTo($ul)
					$ul.find(o.li+":first").hide();
					$ul.css({marginTop:0,maxHeight:ulHeight});
					$ul.find(o.li+":first").fadeIn(1000);
				});
				i++;
			},o.time);
		}).trigger("mouseleave");
	};
    
    //超出浏览器浮动在顶部
	$.fn.TopFloat = function(){
		var currObj = $(this);
		var _defautlTop = currObj.offset().top;
	    var _defautlLeft = currObj.offset().left;
	    var _position = currObj.css('position');
	    var _top = currObj.css('top');
	    var _left = currObj.css('left');
	    var _zIndex = currObj.css('z-index');
	    //鼠标滚动事件
	    $(window).scroll(function(){
	        if($(this).scrollTop() > _defautlTop){
	            //IE6不认识position:fixed，单独用position:absolute模拟
	            if($.browser.msie && $.browser.version=="6.0"){
	                $("#top").css({'position':'absolute','top':eval(document.documentElement.scrollTop),'left':_defautlLeft,'z-index':99999});
	                //防止出现抖动
	                $("html,body").css({'background-image':'url(about:blank)','background-attachment':'fixed'});
	            }else{
	                currObj.css({'position':'fixed','top':0,'left':_defautlLeft,'z-index':99999});
	            }
	        }else{
	            currObj.css({'position':_position,'top':_top,'left':_left,'z-index':_zIndex});
	        }
	    });
	}
})(jQuery);