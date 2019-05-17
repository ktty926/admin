//校验
function CheckJs() {
}

// 校验密码字符串
CheckJs.checkPassStr = function(strPass) {
	re = new RegExp(" ", "g");
	if (strPass == null || strPass == "") {
		return false;
	} else if (strPass.replace(re, "").length == 0) {
		return false;
	} else if (strPass.replace(re, "").length < 6) {
		return false;
	} else {
		return true;
	}
}

// 校验密码按下键盘
CheckJs.checkPassWord = function(obj) {
	var value = $(obj).val();
	var hideId = $(obj).parent().parent().next().attr("id");
	var re = /^[a-zA-Z0-9_]{6,18}$/;
	if (hideId == "passTrId3") {
		if (!re.test(value)) {
			if (value == "") {
				jQuery('#' + hideId).find("span").text("请再次输入确认密码");
			} else {
				jQuery('#' + hideId).find("span").text("两次输入密码不一致");
			}
			jQuery('#' + hideId).show();
		} else {
			jQuery('#' + hideId).hide();
		}
	} else {
		if (!re.test(value)) {
			jQuery('#' + hideId).show();
		} else {
			jQuery('#' + hideId).hide();
		}
	}
}

// 校验修改手机或邮箱按下键盘
CheckJs.checkPhoneEmail = function(obj) {
	var value = $(obj).val();
	var hideId = $(obj).parent().parent().next().attr("id");
	var re;
	if (hideId.indexOf("phone") != -1) {
		re = /^1(?:[38]\d|4[57]|5[012356789])\d{8}$/;
	} else {
		re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	}
	if (!re.test(value)) {
		jQuery('#' + hideId).show();
	} else {
		jQuery('#' + hideId).hide();
	}

}

// 字符串处理
function StringUtil() {
}

// 判断字符串为空
StringUtil.isNull = function(str) {
	var ret = false;
	if (str == null || str == "" || str == "undefined"
			|| typeof (str) == "undefined") {
		return true;
	} else {
		var re = /^[　\s]*$/;
		return re.test(str);
	}
}

function imageJcrop() {
	var jcrop_api, boundx, boundy, 
	    $preview = $('#preview-pane'), 
	    $pcnt = $('#preview-pane .preview-container'), 
	    $pimg = $('#preview-pane .preview-container img'), 
	    xsize = $pcnt.width(), 
	    ysize = $pcnt.height();
	// console.log('init', [ xsize, ysize ]);
	$('#target').Jcrop({
		bgFade : true,
		bgOpacity : .2,
		setSelect : [ 0, 0, 50, 50 ],
		//aspectRatio : 1,
		onChange : updatePreview,
		onSelect : updatePreview,
		aspectRatio : xsize / ysize
	}, function() {
		// Use the API to get the real image size
		var bounds = this.getBounds();
		boundx = bounds[0];
		boundy = bounds[1];
		// Store the API in the jcrop_api variable
		jcrop_api = this;
		// Move the preview into the jcrop container for css positioning
		$preview.appendTo(jcrop_api.ui.holder);
	});

	function updatePreview(c) {
		if (parseInt(c.w) > 0) {
			var rx = xsize / c.w;
			var ry = ysize / c.h;
			$pimg.css({
				width : Math.round(rx * boundx) + 'px',
				height : Math.round(ry * boundy) + 'px',
				marginLeft : '-' + Math.round(rx * c.x) + 'px',
				marginTop : '-' + Math.round(ry * c.y) + 'px'
			});
			$('#x').val(c.x);
			$('#y').val(c.y);
			$('#w').val(c.w);
			$('#h').val(c.h);
		} else {
			$pimg.css({
				width : Math.round(boundx) + 'px',
				height : Math.round(boundy) + 'px',
				marginLeft : '-' + Math.round(0) + 'px',
				marginTop : '-' + Math.round(0) + 'px'
			});
		}

	};}


String.prototype.Trim = function() {

    var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);

    return (m == null) ? "" : m[1];

    }

    String.prototype.isMobile = function() {

    return (/^1(?:[38]\d|4[57]|5[012356789])\d{8}$/.test(this.Trim()));

    }

    String.prototype.isTel = function()

    {

    //"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"

    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));

    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));

    }


    function logout(){
		$.ajax({
			   type: "post",
			   url: basePath+"/logistics/logout",
			   dataType:"json",
			   success: function(msg){
		  		if(msg != null){
		  			 window.location.href=lebp_logout;
		  		 }
			  }
		});	 
	}  
  /**
   * 如果同时开通了诚信通和定证金的话就显示修改旗舰店的菜单
   */
   $(function(){
	   if(window.location.href.indexOf("/road/s-")==-1){
		   $.cookie('openPutAway', '0', { expires: 1, path: '/' });
	   }
   });

   function saveClickPhone(lineId,carrierId,type){
		var jsonParams = {
				"line_id" : lineId,
				"carrier_id" : carrierId,
				"type" : type
			};
		jQuery.ajax({
			type : "post",
			url : basePath+"/logistics/click/phone",
			data : jsonParams,
			success : function(data) {
				 //alert(data);
			}
		});
	}

