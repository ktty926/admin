var m = {
	usingCity : false,//只显示已开通的城市
	provinces : [{id:28061,name:'广东'},{id:23841,name:'湖北'}],
	cities : [{id:28373,name:'深圳',provinceId:28061},{id:28062,name:'广州',provinceId:28061},{id:23842,name:'武汉',provinceId:23841}],
	companyStatus : false,
	fillCompnay : function (obj){
		$('#companyName').val($(obj).text());
		$('#companyName').attr('inputcid',$(obj).attr('cid'));
		$('#companySelect').hide();
	},
	searchCompany : function(){
		var companyId = $('#companyName').attr('inputcid');
		if( companyId > 0 ){
			window.open(basePath+'company/'+companyId+'/index.html');
		}else{
			$('#companySelect').show();
			alert('对不起！你查找的公司不存在或没有选择列表中的公司。');
		}
	},
	changeColor : function(obj, index){
		if(index == 1){
			$(obj).addClass("tangram-suggestion-current");
		}else{
			$(obj).removeClass("tangram-suggestion-current");
		}
	}
};

$(function() {

	//搜索商圈
	$('select[name=provinces]').append(function(){
		if( m.usingCity === false ){
			m.provinces = ChooseCity.geo.provinces;
			m.cities = ChooseCity.geo.cities;
		}
		var pHtml = '';
		$.each(m.provinces, function(k, v){
			pHtml += '<option value="'+v.id+'">'+v.name+'</option>';
		});
		$(this).change(function(){
			var pid = $(this).val();
			$('select[name=city]').html(function(){
				var cHtml = '<option value="">城市</option>';
				var aCity = ChooseCity.geo.filter(m.cities, function () { return this.provinceId == pid; });
				$.each(aCity, function(ks, vs){
					cHtml += '<option value="'+vs.id+'">'+vs.name+'</option>';
				});
				$(this).change(function(){
					var cityID = $(this).val();
					var parkHtml = '<option value="">商圈</option>';
					$.ajax({
						  type: "POST", 
						  url: basePath+"company/companyAjax.php",
						  dataType : "json",
						  data: { action : 'parkSearch', cityID : cityID },
						  success: function( data ) {
							  if( data && data.flag ){
									$.each(data.data, function(kc, vc){
										parkHtml += '<option value="'+vc.circle_id+'">'+vc.name+'</option>';
									});
									$('select[name=park]').html(parkHtml);
							  }else{
								 alert(data.msg);
							  }
						  }
					});
				});
				return cHtml;
			});
		});
		return pHtml;
	});
	
	//搜索承运商
	$('#companyName').keyup(function(){
		var kw = $(this).val();
		var companyHtml = '';
		$.ajax({
			  type: "POST", 
			  url: basePath+"company/companyAjax.php",
			  dataType : "json",
			  data: { action : 'companySearch', companyKw : kw },
			  success: function( data ) {
				  if( data && data.flag ){
						$.each(data.data, function(k, v){
							companyHtml += '<tr><td class="" onmouseover="m.changeColor(this,1);" onmouseout="m.changeColor(this,2);" onclick="m.fillCompnay(this);" cid="'+v.userid+'"><i class="route-icon">'+v.company+'</i></td></tr>';
						});
						$('#companySelect tbody').html(companyHtml);
				  }else{
					 //alert(data.msg);
				  }
			  }
		});
		$('#companySelect').show();

	});
	$('#companySelect').mouseenter(function(){
		m.companyStatus = false;
	});
	$('#companySelect').mouseleave( function() {
		m.companyStatus = true;
	});
	$('#companyName').blur(function(){
		if( m.companyStatus == true ){
			$('#companySelect').hide();
		}
	});
	
	//Tab切换
	$('.block-wrapper').each(function() {
		var title = $(this).find('ul.tab-title');
		var content = $(this).find('.block-body');
		$(title).tabs(content, {});
	});

    //公路物流-始发城市
    $("#road_leave_city").chooseCity({
        tips: "请选择发货地",
        tipsClass: "",
        valueClass: "black",
        offset:{
        	top: 47,
        	left: 0
        },
        callback: function (data) {
            road_leave_geo = data;
            $('[name="jsonLeaveCityParams"]').val(data);
        }
    });

    //公路物流-到达城市
    $("#road_arrive_city").chooseCity({
        tips: "请选择收货地",
        tipsClass: "",
        valueClass: "black",
        offset:{
        	top: 47,
        	left: 0
        },
        callback: function (data) {
            road_arrive_geo = data;
            $('[name="jsonArriveCityParams"]').val(data);
        }
    });

	$('#kSlide').kSlide({
		type: 'slide',
		timeOut: 5000
	});
	//Search
	$('#searchTitle').tabs('#searchContent');
	$('#areaTitle1').tabs('#areaContent1');
	$('#areaTitle2').tabs('#areaContent2');
	$('#tabTitle1').tabs('#tabContent1');
	$('.placeholder-box').placeholder();

	// 选择地区
	var $faHuoSelect = $('#faHuoSelect');
	var $shouHuoSelect = $('#shouHuoSelect');
	var $faHuo = $('input.fahuo');
	var $shouHuo = $('input.shouhuo');
	var states = true;
	var areatStates;
	var $selectArea = $('#selectArea');
	var $top_area = $('.top-area');
	var $tel_app = $('.tel-app');
	var $dropdown = $('.dropdown');
	var $selectCitye = $('#selectCitye');
	var $enterCity = $('#enterCity');

	function hideSelectCity() {
		if (!selectCloseState) {
			return;
		}
		$selectCitye.hide();
		$selectCitye.find('select').val('');
	}

	function joinCity() {
		var str = '';
		$selectCitye.find('select').each(function() {
			str += $(this).val();
		});
		return str;
	}

	var selectCloseState = true;
	var selectState = 0;
/*	$selectCitye
		.on('mouseenter', function() {
			selectCloseState = false;
		})
		.on('mouseleave', function() {
			selectCloseState = true;
		});*/
	$('body')
		.delegate('.states', 'click', function() {
			var text;
			$(this).toggleClass('show');
			if (!$(this).data('s')) {
				text = '隐藏非必填项';
				$(this).data('s', true);
				$('.feibixu').show();
			} else {
				text = '填写更多信息';
				$(this).data('s', false);
				$(this).data('s', false);
				$('.feibixu').hide();
			}
			$(this).find('em').eq(0).text(text);
		})
		.delegate('.sqfahuo,.sqshouhuo', 'click', function() {
			var pos = $(this).offset();
			var size = {
				width: $(this).outerWidth(),
				height: $(this).outerHeight()
			};
			selectState = $(this).data('type');
			$selectCitye.show();
		})
		.delegate('#enterCity', 'click', function() {
			var circleID = $('select[name=park]').val();
			if( circleID == '' ){
				alert('请选择商圈！');
			}else{
				window.location.href = basePath+'road/wuliuyuan/'+circleID+'.html';
			}
		})
		.delegate('.sqfahuo,.sqshouhuo', 'blur', function() {
			//hideSelectCity();
		})
		.delegate('.diqu li', 'click', function() {
			$top_area.removeClass('current');
		})
		.delegate('.diqu li', 'click', function() {
			var text = $(this).text();
			$selectArea.text(text);
			window.location.href = basePath+'?cityid='+$(this).attr('cityid')+'&cityName='+$(this).text();
		})
		.delegate('.area-tag', 'click', function() {
			var $fh = $('.fahuo:visible'),
				$sh = $('.shouhuo:visible');
			var val = $(this).text();

			if (areatStates === 1) {
				$fh.val(val).blur();
				$faHuoSelect.hide();
				$faHuoSelect.find('.area-tag').removeClass('current');

			} else {
				$sh.val(val).blur();
				$shouHuoSelect.hide();
				$shouHuoSelect.find('.area-tag').removeClass('current');

			}
			$(this).addClass('current');
		});

	$dropdown
		.mouseover(function() {
			$(this).addClass('current');
		})
		.mouseout(function() {
			$(this).removeClass('current');

		});

	$faHuoSelect
		.mouseenter(function() {
			states = false;
		})
		.mouseleave(function() {
			states = true;
			$faHuoSelect.hide();
			$faHuoSelect.blur();
		});
	$shouHuoSelect
		.mouseenter(function() {
			states = false;
		})
		.mouseleave(function() {
			states = true;
			$shouHuoSelect.hide();
			$shouHuoSelect.blur();
		});
/*	$faHuo
		.focus(function() {
			var pos = $(this).parent().offset();
			var size = {
				width: $(this).outerWidth(),
				height: $(this).outerHeight()
			};
			areatStates = 1;
			$faHuoSelect
				.css({
					left: pos.left,
					top: pos.top + size.height
				})
				.show();
		})
		.blur(function() {
			if (states) {
				$faHuoSelect.hide();
			}

		});
	$shouHuo
		.focus(function() {
			var pos = $(this).parent().offset();
			var size = {
				width: $(this).outerWidth(),
				height: $(this).outerHeight()
			};
			areatStates = 2;
			$shouHuoSelect
				.css({
					left: pos.left,
					top: pos.top + size.height
				})
				.show();
		})
		.blur(function() {
			if (states) {
				$faHuoSelect.hide();
			}

		});*/

	$('.icons-position-1').click(function() {
		$(this).parent().find('input').focus();
	});

	$('#moreCitySelect').click(function() {
		var pos = $(this).offset();
		var size = {
			width: $(this).outerWidth(),
			height: $(this).outerHeight()
		};
		$('#moreCity').show().css({
			width: size.width,
			left: pos.left,
			top: pos.top + size.height
		});
	});
	$('#moreCity').click(function() {
		$(this).hide();
	});

	//发送信息
	function sendMessage(options) {
		var obj = options || {};
		this.ele = obj.ele || document.getElementById(obj.ele);
		this.defaultText = obj.ele.innerHTML || this.defaultText;
		this.timeOut = obj.timeOut || this.timeOut;
		this.validation = obj.validation || this.validation;
		this.error = obj.error || this.error;
		this.sendAfter = obj.sendAfter || this.sendAfter;
		this.sendBefore = obj.sendBefore || this.sendBefore;
		this.setInterval;
		this.num = obj.num || this.defaultNum;
		this.tempNum = this.num;
		this.status = true;
		this.msg = this.msg;
		this.textBefore = obj.textBefore || this.textBefore;
		this.textAfter = obj.textAfter || this.textAfter;
		this.event();
	}
	sendMessage.prototype = {
		timeOut: 100,
		defaultText: '获取',
		textBefore: '',
		msg: '出错',
		textAfter: '秒',
		num: 60,
		event: function() {
			var that = this;
			this.ele.onclick = function() {
				that.send();
			}
		},
		validation: function() {},
		success: function() {},
		send: function() {
			var that = this;
			if (!that.status) {
				//that.error.call(that, '请在' + that.num + '秒后重新获取');
				return;
			}
			if (this.validation.call(this)) {

				that.status = false;
				that.sendBefore.call(that.ele);
				this.setInterval = setInterval(function() {
					that.num--;
					that.ele.innerHTML = that.textBefore + that.num + that.textAfter;
					if (that.num < 0) {
						clearInterval(that.setInterval);
						that.ele.innerHTML = that.defaultText;
						that.status = true;
						that.num = that.tempNum;
						that.sendAfter.call(that.ele);
					}
				}, 1000);
			} else {
				this.error(this.errorMsg);
			}
		},
		error: function() {}
	};

	function isTel(str) {
		if (!str) {
			return false;
		}
		var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
		return telReg;
	}
	//发送短信
	var sendBtn = document.getElementById('sendMessage');
	if (sendBtn) {
		var sendmessage = new sendMessage({
			ele: sendBtn,
			num: 60,
			textBefore: '',
			textAfter: '秒后重新获取',
			validation: function() {

				this.errorMsg = '请输入正确的手机号码';
				$('#tel').focus();
				return isTel($('#tel').val());
			},
			sendBefore: function() {
				$(this).addClass('disabled');
				//alert('正在发送，事件写在这里');
			},
			sendAfter: function() {
				$(this).removeClass('disabled');
				//发送完毕
			},
			error: function(msg) {
				alert(msg);
			}
		});
	}


	//登录
	$('#loginBtn').click(function(ev) {
		if (!isTel($('#tel').val())) {
			alert('请输入正确的手机号码');
			ev.preventDefault();
			return;
		}
		if ($('#YZM').val().length !== 6) {
			alert('请输入正确的六位验证码');
			ev.preventDefault();
			return;
		}

	});



	function backToTop() {
		var backToTop = $('.back-to-top'),
			timeout;
		if (backToTop.length === 0) {
			$('body').append('<div class="back-to-top icons-back-to-top"></div>');
			backToTop = $('.back-to-top');
		}
		backToTop.click(function(e) {
			$('html,body').animate({
				scrollTop: 0
			});
			e.preventDefault();
			e.stopPropagation();
		});
		$(window).scroll(function() {
			var top = $('html').scrollTop() || $('body').scrollTop();
			clearTimeout(timeout);
			setTimeout(function() {
				if (top <= 20) {
					backToTop.fadeOut();
				} else {
					backToTop.fadeIn();

				}
			}, 10);
		});
	}
	backToTop();
});

function RoadQuery() {
    var $leave_city = $("#road_leave_city");
    var data_leave_city = $leave_city.data("geo");
    
    if (typeof (data_leave_city) != "object" || data_leave_city.provinceID == null) {
        setTimeout(function () {
            $leave_city.trigger("click");
        }, 10);
        return;
    }
    
    var $arrive_city = $("#road_arrive_city");
    var data_arrive_city = $arrive_city.data("geo");
    if (typeof (data_arrive_city) != "object" || data_arrive_city.provinceID == null) {
        setTimeout(function () {
            $arrive_city.trigger("click");
        }, 10);
        return;
    }
	
    var temp_leave = road_leave_geo;
    var temp_arrive = road_arrive_geo;

    for (var p in temp_leave) {
        if (temp_leave[p] == -1 || temp_leave[p] === undefined)
            temp_leave[p] = 0;
    }
    for (var p in temp_arrive) {
        if (temp_arrive[p] == -1 || temp_arrive[p] === undefined)
            temp_arrive[p] = 0;
    }
 	var leave_str = temp_leave["cityID"] + "-" + temp_arrive["cityID"] ;
//    var leave_str = temp_leave["provinceID"] + "_" + temp_leave["cityID"] + "_" + temp_leave["districtID"];
//    var arrive_str = temp_arrive["provinceID"] + "_" + temp_arrive["cityID"] + "_" + temp_arrive["districtID"];
//	  var url = "search.php?leave=" + leave_str + "&arrive=" + arrive_str;
	if(temp_leave["districtID"]<1 && temp_arrive["districtID"]<1){
		var url = "road/"+leave_str+"/index.html";
	}else{
		var url = "road/"+leave_str+"/index-"+temp_leave["districtID"]+"-"+temp_arrive["districtID"]+".html";
	}
	
    window.location.href = url;
}

function seaportQuery(){
	var startPort = $('#GPStartPort').attr('vid');
	var endPort = $('#GPEndPort').attr('vid');
	if(startPort > 0 && endPort >0){
		window.location.href = '/seaport/'+startPort+'-'+endPort+'/index.html';
	}
	return;
}