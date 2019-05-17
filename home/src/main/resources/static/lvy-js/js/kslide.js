 function isNumber(num) {
 	return Object.prototype.toString.call(num) === "[object Number]";
 }

 $.fn.kSlide = function(options) {
 	var opts = $.fn.extend({
 		name: 'kSlide',
 		timeOut: 3000,
 		type: 'slide'
 	}, options);
 	return this.each(function() {
 		var that = this,
 			ul = $(that).children('ul'),
 			li = ul.children('li'),
 			len = li.length,
 			width,
 			height,
 			timeout,
 			icons = '',
 			span,
 			init = 0,
 			setinterval,
 			lock = true;
 		if (len <= 1) {
 			return;
 		}
 		for (var i = li.length - 1; i >= 0; i--) {
 			icons += '<span></span>';
 		}
 		$(that).append('<div class="' + opts.name + '-icons" data-main="icons">' + icons + '</div>');
 		span = $(that).children('div[data-main=icons]').children('span');
 		span.eq(0).addClass('current').end().click(function() {
 			if (!lock) {
 				return;
 			}
 			var index = $(this).index();

 			span.removeClass('current');
 			$(this).addClass('current');
 			init = index;
 			_run();
 		});

 		function _run() {
 			clearInterval(setinterval);
 			run();
 			setinterval = setInterval(run, opts.timeOut);
 		}

 		function run() {
 			if (init >= len) {
 				init = 0;
 			} else if (init < 0) {
 				init = len;
 			}
 			lock = false;
 			span.removeClass('current').eq(init).addClass('current');
 			if (opts.type == 'fade') {
 				// li.css({
 				// 	zIndex: 0
 				// }).eq(init).css({zIndex:1});
 				// li.eq(init).fadeOut(1000,function(){
 				// 	lock = true;
 				// 	li.css({zIndex:0}).eq(init+1).css({zIndex:1});
 				// });
 				li.fadeOut().eq(init).fadeIn(1000, function() {
 					lock = true;
 				});
 			} else {
 				ul.animate({
 					left: -init * $(that).outerWidth()
 				}, function() {
 					lock = true;
 				});
 			}
 			init++;
 		}
 		setinterval = setInterval(run, opts.timeOut);

 		function calc() {
 			width = isNumber(opts.width) ? opts.width : $(window).width();
 			height = isNumber(opts.height) ? opts.height : $(that).parent().outerHeight();
 			$(that).css({
 				position: 'relative',
 				width: width,
 				height: height,
 				overflow: 'hidden'
 			});
 			if (opts.type == 'fade') {
 				ul.css({
 					width: width,
 					left: 0,
 					top: 0
 				});
 				li.css({
 					width: width,
 					height: height,
 					position: 'absolute',
 					top: 0,
 					left: 0
 				});
 			} else {
 				ul.css({
 					width: width * len,
 					left: 0,
 					top: 0
 				});
 				li.css({
 					width: width,
 					height: height,
 					'float': 'left'
 				});
 			}

 		}
 		calc();
 		$(window).resize(function() {
 			clearTimeout(timeout);
 			timeout = setTimeout(calc, 10);
 		});
 	});
 };