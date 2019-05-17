/*  
 *=============================================*
 *   
 *   创 建 者: jack.zhang
 *   创建时间: 2014/7/14 13:36:01
 *   描    述: 合并js（JS/HJRegularExpression.js,
 *                     JS/HJCommonJs.js,
 *                     JS/HJPostData.js,
 *                     JS/ExpressQueryMGT.js,
 *                     JS/HJPagePack.js,
 *                     JS/HJDataStructure.js）
 *   
 *=============================================*
*/

(function (window) {

    var isDebug = true,

        domain = isDebug ? "http://localhost" : "http://www.lvmae.com",

        HJ = window.HJ || {};

    HJ.constant = {

        defaultPageSize: 10,

        domain: domain,

        userType: {}

    };

    HJ.getResourceUrl = function (version) {
        return domain + ":8081/Skin" + (!version ? "" : ("_" + version)) + "/Green/";
    };

    //特殊处理
    HJ.special = {
        branchVisited: {
            rand: function () {
                if (arguments.length == 0) {
                    return 0;
                } else {
                    var num = arguments[0];
                    if ((num / 10000) > 1) {
                        return Math.floor(2459 / 4) + num % 10000;
                    } else {
                        return Math.floor(num / 2) + (num & 10000);
                    }
                }
            },
            init: function () {
                //正则查找
                var r = /\/(\d+).html/;
                //延时执行
                //setTimeout(function () {
                $(".volume").each(function () {
                    var a = $(this).closest("table").find(".site_name");

                    if (a.length > 0) {
                        r.exec(a[0].href);
                    }
                    console.info(RegExp.$1);

                    $(this).html("浏览量：<span>" + HJ.special.branchVisited.rand(RegExp.$1) + "</span>");
                });
            }
        }
    };

    window.HJ = HJ;

})(window);

/*
 * -------------------
 * 常用正则表达式
 * -------------------
*/
function HJRegularExpression() { }

/**
* 正则表达式json串
**/
var regularExpression = [
    { "regName": "data_float", "value": /^(([1-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/, "mess": "您输入的数据格式不正确，仅能输入数字，请重新输入" },
    { "regName": "data", "value": /^[1-9]\d{12,19}$/, "mess": "" },
    { "regName": "depositCard", "value": /^[1-9]\d{12,19}$/, "mess": "" },
    { "regName": "BankAccount", "value": /^[1-9]\d{1,}$/, "mess": "" },
    { "regName": "Amount", "value": /^\d+(?:\.\d{1,2})?$/, "mess": "您输入的金额不合法！" },
    { "regName": "Count", "value": /^[1-9]*$/, "mess": "您输入的数据格式不正确，请输入数字" },
    { "regName": "ExchangeRate", "value": /^\d+(?:\.\d{1,4})?$/, "mess": "您输入的汇率不合法！" },
    { "regName": "userName", "value": /^[a-zA-Z0-9_]{3,16}$/, "mess": "无效用户名" },
    { "regName": "email", "value": /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, "mess": "您输入的 Email 格式不正确，请重新输入" },
    { "regName": "MpNo", "value": /^1(?:[38]\d|4[57]|5[012356789])\d{8}$/, "mess": "您输入的手机号码格式不正确，请重新输入" },
    { "regName": "mpno", "value": /^1(?:[38]\d|4[57]|5[012356789])\d{8}$/, "mess": "您输入的手机号码格式不正确，请重新输入" },
    { "regName": "principalPhone", "value": /^1(?:[38]\d|4[57]|5[012356789])\d{8}$/, "mess": "" },
    { "regName": "password", "value": /^[a-zA-Z0-9_]{6,18}$/, "mess": "您输入的密码格式不正确，请重新输入" },
    { "regName": "Password", "value": /^[a-zA-Z]\w{5,17}$/, "mess": "您输入的密码格式不正确，请重新输入" },
    { "regName": "StrongPassword", "value": /^([A-Z]+\w)|(\w+[A-Z])$/, "mess": "您输入的密码格式不正确，请重新输入" },
    { "regName": "nickname", "value": /^[\u4E00-\u9FA5A-Za-z0-9_]+$/, "mess": "您输入的呢称格式不正确，请重新输入" },
    { "regName": "buildingname", "value": /^[\u4E00-\u9FA5A-Za-z0-9_]+$/, "mess": "请输入正确的楼宇名称" },
    { "regName": "address", "value": /^[\u4E00-\u9FA5A-Za-z0-9_]+$/, "mess": "请输入正确的详细地址" },
    { "regName": "name", "value": /^[\u4E00-\u9FA5A-Za-z0-9_]+$/, "mess": "请输入正确姓名" },
    { "regName": "telephone", "value": /^\d{7,15}$/, "mess": "您输入的电话号码格式不正确，请重新输入" },
//    { "regName": "telephone", "value": /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/, "mess": "您输入的电话号码格式不正确，请重新输入" },
    {"regName": "originalLoginPassword", "value": /^[a-zA-Z0-9_]{6,18}$/, "mess": "您输入的密码格式不正确，请重新输入" },
    { "regName": "loginPassword", "value": /^[a-zA-Z0-9_]{6,18}$/, "mess": "您输入的密码格式不正确，请重新输入" },
    { "regName": "traderPassword", "value": /^[a-zA-Z0-9_]{6,18}$/, "mess": "您输入的密码格式不正确，请重新输入" },
    { "regName": "DataType_float", "value": /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/, "mess": "您输入的数据格式不正确，仅能输入数字，请重新输入" },
    { "regName": "Order_Weight", "value": /^(([0-9]{1,4}\.[0-9]{1})|([0-9]{1,4}))$/, "mess": "您输入的数据格式不正确" },
    { "regName": "DataType_int", "value": /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/, "mess": "您输入的数据格式不正确，仅能输入数字，请重新输入" },
    { "regName": "DataType_intZero", "value": /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)|(0.0)|0)$/, "mess": "您输入的数据格式不正确，仅能输入数字，请重新输入" },
    { "regName": "IDCardNo", "value": /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/, "mess": "您输入的身份证号码不正确，请重新输入" },
    { "regName": "postalNo", "value": /^[1-9]\d{5}$/, "mess": "请输入正确的邮政编码" }
    
    ];

HJRegularExpression.getRegular = function (regName) {
    if (regName != null) {
        for (var i = 0; i < regularExpression.length; i++) {
            if (regularExpression[i].regName.toLowerCase() == regName.toLowerCase())
                return regularExpression[i];
        }
    }
    return null;
}

HJRegularExpression.getRegularValue = function (regName) {
    if (regName != null) {
        for (var i = 0; i < regularExpression.length; i++) {
            if (regularExpression[i].regName.toLowerCase() == regName.toLowerCase())
                return regularExpression[i].value;
        }
    }
    return null;
}

/*
 * -------------------
 * 公共部分
 * -------------------
*/

$(document).ready(function () {

	// ** start ** 设置输入提示框事件
	var classname = ".tipInput";
    var tipValueLabel = "tipTextString";
    $(classname).css("color", "#b6b6b6");
    $(classname).each(function () {
        if (HJCommonJs.StringIsNullOrEmpty($(this).val())) {
            $(this).val($(this).attr(tipValueLabel));
        }
    });

    $(classname).bind("focus", function () {
        $(this).css("color", "black");
        if ($(this).attr(tipValueLabel) == $(this).val()) {
            $(this).val("");
        }
    });
    $(classname).bind("blur", function () {
        if ($.trim($(this).val()) == "") {
            //$(this).css("color", "lightgray");
            $(this).css("color", "#b6b6b6");
            $(this).val($(this).attr(tipValueLabel));
        } else {
            $(this).css("color", "black");

        }
    });
    // ** end ** 设置输入提示框事件

    // ** start ** 设置验证输入控件事件
    $("*[needVerify='1']").bind("keyup blur", function () {
        //$("#mpno").val(this.id);
        if (VerifyControlValue(this, true)) {
            HJCommonJs.RemoveErrorMess_ThenVerifysucceeded(this);
        }
    });
    // ** end ** 设置验证输入控件事件

    // ** start ** 设置控件 keypress 事件，以响应 enter 按键
    $("input[type='text']").bind("keypress", function (data) {
        HJCommonJs.ControlKeyPressByTypeText(data, this);
    });

    $("textarea").bind("keypress", function (data) {
        HJCommonJs.ControlKeyPressByTypeText(data, this);
    });

    $("input[type='password']").bind("keypress", function (data) {
        HJCommonJs.ControlKeyPressByTypeText(data, this);
    });

    // 防止只读 input 按下 Backspace 时页面回退的情况
    // keypress 事件无法捕获 Backspace 按键
    $("input[readonly='readonly']").bind("keydown", function (data) {
        if (data.keyCode == 8) return false;
    });
    // ** end ** 设置控件 keypress 事件，以响应 enter 按键

    // ** start ** 城市选择控件，快递公司选择控件事件
    HJCommonJs.SettingPopChooseDiv();
    $(document).bind("click", function () {
        $("div[divtype='popmodeldiv']").each(function () {
            var selfID = $(this).attr("selfid");
            var hideObj = $(this).attr("hideobjid");
            var focus = null;
            try {
                focus = $(":focus");    // IE
            }
            catch (ex) {

            }
            if (focus == null || focus.length <= 0) {
                try {
                    if (event != null && event.toElement != null) {
                        focus = event.toElement;    // google, firefox
                    }
                    else if (event != null && event.srcElement != null && !HJCommonJs.StringIsNullOrEmpty(event.srcElement.id)) {
                        focus = $("#" + event.srcElement.id);
                    }
                }
                catch (ex) { }
            }
            var mainID = $(focus).attr("selpparentid");
            if (selfID != mainID) {
                $("#" + hideObj).hide();
            }
        });
    });
    // ** end ** 城市选择控件，快递公司选择控件事件
});

function HJCommonJs() { }

HJCommonJs.SettingPopChooseDiv = function () {
    $("div[divtype='popmodeldiv']").each(function () {
        var selfID = $(this).attr("selfid");
        $(this).attr("selpparentid", selfID);
        $(this).find("*").attr("selpparentid", selfID);
    });
}

HJCommonJs.ControlKeyPressByTypeText = function (data, self) {
    if (data.keyCode == 13) {
        var objID = $(self).attr("responseObject");
        if (!HJCommonJs.StringIsNullOrEmpty(objID)) {
            $("#" + objID).click();
        }
    }
}

HJCommonJs.BindChooseEvent = function (currObj) {
    var groupname = $(currObj).attr("agroupname");
    var objID = $(currObj).attr("objID");
    $("a[atype='chooseitem'][agroupname='" + groupname + "']").attr("class", "");
    $("a[atype='chooseitem'][agroupname='" + groupname + "'][objID='" + objID + "']").attr("class", "active");
    //$(currObj).attr("class", "active");
}

HJCommonJs.VerifyDataFormat = function (areaObj) {
    var errorList = []
    $(areaObj).find("*[needVerify='1']").each(function () {
        if (!VerifyControlValue(this, true)) errorList.push(this);
    });
    return errorList;
}

function VerifyControlValue(self, isShowErrorMess) {
    var value = HJCommonJs.GetObjectValue(self);

    var canNull = $(self).attr("canNull");
    if (canNull != null && canNull.length > 0 && canNull.toLowerCase().substr(0, 1) == "n") {
        if (HJCommonJs.StringIsNullOrEmpty(value)) {
            if (isShowErrorMess == true) HJCommonJs.ShowErrorMess_ThenVerifyFail(self, "必填项!");
            return false;
        }
        else {
            if (isShowErrorMess == true) HJCommonJs.RemoveErrorMess_ThenVerifysucceeded(self);
        }
    }

    var vname = $(self).attr("verifyname");
    var expression = HJCommonJs.GetExpression(vname);
    if (!HJCommonJs.StringIsNullOrEmpty(value) && expression != null && !expression.value.test(value)) {
        if (isShowErrorMess == true) HJCommonJs.ShowErrorMess_ThenVerifyFail(self, expression.mess);
        return false;
    }
    else {
        if (isShowErrorMess == true) HJCommonJs.RemoveErrorMess_ThenVerifysucceeded(self);
    }
    return true;
}

// 当控件验证不成功时，根据情况加载错误信息
HJCommonJs.ShowErrorMess_ThenVerifyFail = function (verifyObj, errorMess) {
    var errorMessControlID = $(verifyObj).attr("errorMessControlID");
    if (!HJCommonJs.StringIsNullOrEmpty(errorMessControlID)) {
        $("#" + errorMessControlID).show();
        return;
    }

    var mess = $(verifyObj).attr("errorMess");
    if (!HJCommonJs.StringIsNullOrEmpty(mess)) {
        errorMess = mess;
    }
    var errorObjID = $(verifyObj).attr("errorObjID");
    if (HJCommonJs.StringIsNullOrEmpty(errorObjID)) {
        errorObjID = new Date().getTime();
    }
    var objdiv = null;
    if ($("body").find("#" + errorObjID).length <= 0) {
        objdiv = document.createElement("div");
        $(verifyObj).after(objdiv);
    }
    else {
        objdiv = $("body").find("#" + errorObjID);
    }
    $(objdiv).attr("id", errorObjID);

    SettingErrorMessStyleByExpressionVerify(verifyObj, objdiv)
    var errorhtml = "<img src=\"" + HJCommonJs.CurrentHostUrl() + "Images/icon_res.jpg\" />" + errorMess;
    $(objdiv).html(errorhtml);
    $(verifyObj).attr("errorObjID", errorObjID);
}

HJCommonJs.RemoveErrorMess_ThenVerifysucceeded = function (verifyObj) {
    var objID = $(verifyObj).attr("errorObjID");
    if (!HJCommonJs.StringIsNullOrEmpty(objID) && $("#" + objID) != null) {
        $("#" + objID).remove();
        $(verifyObj).removeAttr("errorObjID");
    }

    var errorMessControlID = $(verifyObj).attr("errorMessControlID");
    if (!HJCommonJs.StringIsNullOrEmpty(errorMessControlID)) {
        $("#" + errorMessControlID).hide();
    }
}

function SettingErrorMessStyleByExpressionVerify(verifyObj, objdiv) {
    //<div class="regis_prompt" id="userName_errormess" style="display:none;"><img src="<%=HJ.LEBP.Common.Common.GetResourceUrl() %>Images/icon_res.jpg" />用户名不能为空</div>
    var width = $(verifyObj).attr("offsetWidth");
    var left = width + $(verifyObj).offset().left;
    var top = $(verifyObj).offset().top;
    var height = $(verifyObj).attr("offsetHeight");
    $(objdiv).attr("class", "verifyFailMess");
    //$(objdiv).attr("class", "regis_prompt");
    $(objdiv).css("height", height);
    $(verifyObj).css("float", "left");
}

HJCommonJs.GetObjectValue = function (obj) {
    return HJCommonJs.getControlValue(obj);
}

HJCommonJs.GetExpression = function (regName) {
    return HJRegularExpression.getRegular(regName);
}

HJCommonJs.PopUp = function () {
    /// <summary>
    /// 获取鼠标点击位置，设置弹出框的位置
    /// </summary>
    /// <returns>string</returns>
    newX = window.event.x + document.body.scrollLeft
    newY = window.event.y + document.body.scrollTop
    menu = document.all.showBox
    if (menu.style.display == "block") {
        menu.style.display = "none"
    }
    else {
        menu.style.display = "block"
    }
    menu.style.pixelLeft = newX - 250
    menu.style.pixelTop = newY - 100
}

HJCommonJs.GetDataFormAjax = function (setting) {
    // postObj
    // successCallback
    // errorCallback
    //todo 浏览器兼容问题
    $.ajax({
        url: "/AjaxApp", ///AjaxMethodNone.ashx",
        type: "POST",
        data: setting.postObj.toJsonString(),
        cache: false,
        dataType: "json",
        success: function (retObj) {
            if (typeof (setting.successCallback) == "function") {
                setting.successCallback(retObj);
            }
        },
        error: function (retObj) {
            if (typeof (setting.errorCallback) == "function") {
                setting.errorCallback(retObj);
            }
            else HJCommonJs.ShowErrorMessage(retObj.responseText);
        }
    });
}

HJCommonJs.GetAccessDataFormAjax = function (setting) {
    // postObj
    // successCallback
    // errorCallback
    //todo 浏览器兼容问题
    $.ajax({
        url: "/AjaxApp", ///AjaxMethodNone.ashx",
        type: "POST",
        data: setting.postObj.toJsonString(),
        cache: false,
        dataType: "jsonp",
        success: function (retObj) {
            if (typeof (setting.successCallback) == "function") {
                setting.successCallback(retObj);
            }
        },
        error: function (retObj) {
            if (typeof (setting.errorCallback) == "function") {
                setting.errorCallback(retObj);
            }
            else HJCommonJs.ShowErrorMessage(retObj.responseText);
        }
    });
}

HJCommonJs.GetDataAjax = function (setting) {
    // postObj
    // callback
    //todo 浏览器兼容问题

    $.ajax({
        url: "/AjaxApp/AjaxMethodNone.ashx",
        type: "POST",
        data: setting.postObj.toJsonString(),
        cache: false,
        dataType: "json",
        success: function (retObj) {
            if (typeof (setting.callback) == "function") {
                setting.callback(retObj);
            }
        },
        error: function (retObj) {
            HJCommonJs.ShowErrorMessage(retObj.responseText);
        }
    });
}

// 将 json 格式对象转换为 string
HJCommonJs.JsonToString = function (o) {
    var arr = [];
    var fmt = function (s) {
        if (typeof s == "object" && s != null) {
            return HJCommonJs.JsonToString(s);
        }
        //return /^(string|number)$/.test(typeof s) ? "\"" + s + "\"" : s;
        if (typeof s == "string")
            return "\"" + s + "\"";
        else if (typeof s == "number")
            return s;
        else
            return s;
    }
    for (var i in o) {
        arr.push("\"" + i + "\":" + fmt(o[i]));
    }
    //return "{" + arr.join(',') + "}";
    return ("{" + arr.join(',') + "}").replace(eval("/\\n/gi"), "<br>");    // 把回车换行符替换，以免反序列化时出错
}

// 将 json 格式对象转换为 string
HJCommonJs.JsonToString_f2 = function (o) {
    var arr = [];
    var fmt = function (s) {
        if (typeof s == "object" && s != null) {
            return HJCommonJs.JsonToString(s);
        }
        //return /^(string|number)$/.test(typeof s) ? "\"" + s + "\"" : s;
        if (typeof s == "string")
            return "\"" + s + "\"";
        else if (typeof s == "number")
            return s;
        else
            return s;
    }
    for (var i in o) {
        arr.push(fmt(o[i]));
    }
    //return "{" + arr.join(',') + "}";
    return ("{" + arr.join(',') + "}").replace(eval("/\\n/gi"), "<br>");    // 把回车换行符替换，以免反序列化时出错
}

HJCommonJs.JsonToStringNotSerialNumber = function (o) {
    var jsonStr = HJCommonJs.JsonToString_f2(o);
    var startChar = jsonStr.substring(0, 2);
    var endChar = jsonStr.substring(jsonStr.length - 2);
    if (startChar == "{{" && endChar == "}}") {
        jsonStr = "[" + jsonStr.substring(1, jsonStr.length - 1) + "]";
    }
    return jsonStr;
}

HJCommonJs.CurrentHostUrl = function () {
    /// <summary>
    /// 获取当前域名
    /// </summary>
    /// <returns>string</returns>
    //var url = "http://localhost:8081/Skin/Green/";
    //return url;
    if (typeof (HJCommonJsSkinResourceUrl) == "undefined")
        return "";
    else
        return HJCommonJsSkinResourceUrl;
}

HJCommonJs.getBaiduMapAk = function () {
    /// <summary>
    /// 获取百度地址API ak
    /// </summary>
    /// <returns>string</returns>
    var ak = "1009d2d818f76cedb8485b22738590e8";
    return ak;
}

HJCommonJs.getMyCityInfo = function (ip, callback) {
    var hjd = new HJPostData();
    hjd.addParameter("ip", ip);
    hjd.methodName = "GetCurrentUserPostition";
    HJCommonJs.GetDataFormAjax({ postObj: hjd, successCallback: callback });
}

HJCommonJs.getRegular = function (regName) {
    /// <summary>
    /// 获取正则表达式的值
    /// </summary>
    /// <param name="regName">正则名称</param>
    /// <returns>string</returns>
    return HJRegularExpression.getRegularValue(regName);
}

HJCommonJs.getControlValue = function (controlObj) {
    /// <summary>
    /// 获取筛选器为 .tipInput 的控件值
    /// </summary>
    /// <param name="controlObj">控件对象</param>
    /// <returns>string</returns>
    var retValue = $(controlObj).val();
    $(".tipInput").each(function () {
        if ($(this).attr("id") == $(controlObj).attr("id") && $(this).attr("name") == $(controlObj).attr("name") && $(this).attr("class") == $(controlObj).attr("class")) {
            if ($(this).val() == $(this).attr("tipTextString")) {
                retValue = "";
            }
            return false;
        }
    });
    return retValue;
}

HJCommonJs.getLocationParaValue = function (paraName) {
    /// <summary>
    /// 获取当前 URL 的参数值
    /// </summary>
    /// <param name="paraName">参数名称</param>
    /// <returns>string</returns>
    var url = window.location.href;
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    var paraObj = {}
    for (i = 0; j = paraString[i]; i++) {
        paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
    }
    var returnValue = paraObj[paraName.toLowerCase()];
    if (typeof (returnValue) == "undefined") {
        return "";
    }
    else {
        return returnValue;
    }
}

HJCommonJs.getUrlValueByString = function (paraName, strValue) {
    /// <summary>
    /// 获取当前 URL 的参数值
    /// </summary>
    /// <param name="paraName">参数名称</param>
    /// <returns>string</returns>
    var url = strValue;
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    var paraObj = {}
    for (i = 0; j = paraString[i]; i++) {
        paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
    }
    var returnValue = paraObj[paraName.toLowerCase()];
    if (typeof (returnValue) == "undefined") {
        return "";
    }
    else {
        return returnValue;
    }
}

HJCommonJs.StringIsNullOrEmpty = function (str) {
    /// <summary>
    /// 判断字符串是否为空
    /// </summary>
    /// <param name="str">字符串</param>
    /// <returns>bool</returns>

    var ret = false;
    if (str == null || str == "" || str == "undefined" || typeof (str) == "undefined") {
        return true;
    }
    else {
        var re = /^[　\s]*$/;
        return re.test(str);
    }
}

HJCommonJs.Trim = function (str) {
    /// <summary>
    /// 移除字符串前后空格
    /// </summary>
    /// <param name="str">字符串</param>
    /// <returns>string</returns>
    if (!str) return "";
    var reSpace = /^\s*(.*?)\s*$/;
    var str = str.replace(reSpace, "$1");
    //str = str.replace("&nbsp;", "").replace("&nbsp;", "").replace("&nbsp;", "").replace("&nbsp;", "").replace("&nbsp;", "")
    return str;
}

var HJCommonJsErrorMessage = "";
var HJCommonJsOpenWinObj = "";

HJCommonJs.ShowErrorMessage = function (errorMess) {
    //    HJCommonJsErrorMessage = errorMess;
    //    HJCommonJsOpenWinObj = window.open("/form/common/error.aspx");
    //window.open("/form/common/error404.aspx");
    //HJCommonJs.alterForm("/form/common/error404.aspx");
    if (HJCommonJs.getLocationParaValue("isdebug") == "1")
    { }
    else {
        //window.location = "/form/common/error404.aspx";
    }
}

HJCommonJsShowSubError = function () {
    HJCommonJsOpenWinObj.document.getElementById("errorMess").innerHTML = HJCommonJsErrorMessage.replace("\r\n", "<br>").replace("\n", "<br>");
}

HJCommonJs.ShowMessage = function (Mess) {
    alert(Mess);
}

HJCommonJs.ControlValueVadate = function (conObj, re, callFunction, className) {
    /// <summary>
    /// 文本框输入键盘弹起事件
    /// </summary>
    /// <param name="conObj">对象</param>
    /// <param name="re">正则表达式</param>
    /// <param name="callFunction">回调函数</param>
    /// <returns>bool</returns>
    $(conObj).keyup(function () {
        var ret = false;
        if (re.test($(this).val())) {
            ret = true;
        }
        if (typeof (callFunction) == "function") {
            callFunction(ret, className);
        }
    })
}

HJCommonJs.GetStarHtml = function (score) {
    //return "<strong class=\"star\"><b style=\"width:" + (score / 5 * 100) + "%\"></b></strong> " + score + "分";
    return "<strong class=\"star\"><b style=\"width:" + (score / 5 * 100) + "%\"></b></strong>";
    //return "<strong class=\"star\"><b style=\"width:" + (score / 5 * 100) + "%\"></b></strong> " + score + "分";
}

HJCommonJs.GetDefultScore = function (score) {
    if (score <= 0)
        return 5;
    else
        return score;
}

HJCommonJs.GetGeoBriefly = function (geo) {
    var splitStr = "|";
    var pars = "";
    pars += geo.provinceID + splitStr + encodeURIComponent(geo.provinceName) + splitStr;
    pars += geo.cityID + splitStr + encodeURIComponent(geo.cityName) + splitStr;
    pars += geo.districtID + splitStr + encodeURIComponent(geo.districtName) + splitStr;
    pars += geo.streetID + splitStr + encodeURIComponent(geo.streetName);
    return pars;
}

HJCommonJs.GetGeoEntity = function (str) {
    var list = str.split("|");
    var entity = new HJDataStructure().GeoInfoEntity();
    if (!HJCommonJs.StringIsNullOrEmpty(list[0])) entity.provinceID = list[0];
    entity.provinceName = decodeURIComponent(list[1]);
    if (!HJCommonJs.StringIsNullOrEmpty(list[2])) entity.cityID = list[2];
    entity.cityName = decodeURIComponent(list[3]);
    if (!HJCommonJs.StringIsNullOrEmpty(list[4])) entity.districtID = list[4];
    entity.districtName = decodeURIComponent(list[5]);
    if (!HJCommonJs.StringIsNullOrEmpty(list[6])) entity.streetID = list[6];
    entity.streetName = decodeURIComponent(list[7]);
    return entity;
}

HJCommonJs.alterForm = function (url, width, height) {
    //window.open(url);
    // 不使用 window.open 是因为有些浏览器会禁用 window.open
    var newWin = $("#a_newWinEventVessel span");
    if (newWin.length <= 0) {
        $("body").append("<a style=\"display:none;\" id=\"a_newWinEventVessel\" href=\"" + url + "\" target=\"_blank\"><span>window.open</span></a>")
        newWin = $("#a_newWinEventVessel span");
    }
    else {
        $(newWin).parent("a").attr("href", url);
    }
    newWin.click();
}

HJCommonJs.alterFormModalDialog = function (url, width, height) {
    window.showModalDialog(url, null, "dialogHeight=" + height + "px;dialogWidth=" + width + "px;scroll=no;");
}

HJCommonJs.alterWinForm = function (caption, url, imageGroup, width, height, callback) {
    //var avaiHeight = $(window).attr("screen").availHeight;
    //var avaiWidth = $(window).attr("screen").availWidth;
    var avaiHeight = $(document).attr("body").offsetHeight;
    var avaiWidth = $(document).attr("body").offsetWidth
    if (width > avaiWidth) width = avaiWidth - 80;
    if (height > avaiHeight) height = avaiHeight - 80;
    if (parent.frames != null && parent.frames.length > 0 && typeof (parent.f_AlterWinForm) == "function") {
        parent.f_AlterWinForm(caption, url, null, width, height, callback);
    }
    else {
        TB_showWin(caption, url, null, width, height, callback);
    }
}

HJCommonJs.CloseAndRefreshParentWin = function (btnName, retObj, userRet) {
    window.close();
    // 使用 window.open 模式打开的页面
    if (window.opener != null && window.opener.document != null && window.opener.document.getElementById(btnName) != null)
        window.opener.document.getElementById(btnName).click();

    // 使用 thickbox 模式打开的页面
    if (typeof (parent.TB_remove) == "function") parent.TB_remove(retObj, userRet);
}

HJCommonJs.SettingCheckBoxLinkageForGridView = function (mainCbTypeValue) {
    var mainTage = mainCbTypeValue;
    var cbTypeTag = $("span[cbType='" + mainCbTypeValue + "']").attr("cbGroupName");

    $("span[cbType='" + mainTage + "']").find("input[type='checkbox']").bind("change", function () {
        $("span[cbType='" + cbTypeTag + "']").find("input[type='checkbox']").attr("checked", $(this).attr("checked"));
    });

    $("span[cbType='" + cbTypeTag + "']").find("input[type='checkbox']").bind("change", function () {
        if (!$(this).attr("checked"))
            $("span[cbType='" + mainTage + "']").find("input[type='checkbox']").attr("checked", false);

        if ($("span[cbType='" + cbTypeTag + "']").find("input[type='checkbox'][checked=false]").length == 0)
            $("span[cbType='" + mainTage + "']").find("input[type='checkbox']").attr("checked", true);
        else
            $("span[cbType='" + mainTage + "']").find("input[type='checkbox']").attr("checked", false);
    });
}

HJCommonJs.GetFullDateString = function (str) {
    /**
    *str=/Date(1389839904540+0800)/
    * 格式化js日期（yyyy-MM-dd HH:mm:ss）
    **/
    if (str == null) {
        return "";
    }
    var d = eval('new ' + str.substr(1, str.length - 2));
    var ar_date = [d.getFullYear(), d.getMonth() + 1, d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds()];
    for (var i = 0; i < ar_date.length; i++) ar_date[i] = dFormat(ar_date[i]);
    return ar_date.slice(0, 3).join('-') + ' ' + ar_date.slice(3).join(':');

    function dFormat(i) { return i < 10 ? "0" + i.toString() : i; }
}

HJCommonJs.GetDateString = function (str) {
    /**
    *str=/Date(1389839904540+0800)/
    * 格式化js日期（yyyy-MM-dd）
    **/
    if (str == null) {
        return "";
    }
    var d = eval('new ' + str.substr(1, str.length - 2));
    var ar_date = [d.getFullYear(), d.getMonth() + 1, d.getDate()];
    for (var i = 0; i < ar_date.length; i++) ar_date[i] = dFormat(ar_date[i]);
    return ar_date.join('-');

    function dFormat(i) { return i < 10 ? "0" + i.toString() : i; }
}

HJCommonJs.GetSlashDateString = function (str) {
    /**
    *str=/Date(1389839904540+0800)/
    * 格式化js日期（yyyy/MM/dd HH:mm:ss）
    **/
    var d = eval('new ' + str.substr(1, str.length - 2));
    var ar_date = [d.getFullYear(), d.getMonth() + 1, d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds()];
    for (var i = 0; i < ar_date.length; i++) ar_date[i] = dFormat(ar_date[i]);
    return ar_date.slice(0, 3).join('/') + ' ' + ar_date.slice(3).join(':');

    function dFormat(i) { return i < 10 ? "0" + i.toString() : i; }
}

HJCommonJs.FormatCurrency = function (num) {
    /**
    * 将数值四舍五入(保留2位小数)后格式化成金额形式
    *
    * @param num 数值(Number或者String)
    * @return 金额格式的字符串,如'1,234,567.45'
    * @type String
    */
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
    num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

HJCommonJs.FormatStatus = function (status) {
    /**
    * 
    *转义状态
    */
    var statusDes = "";
    switch (status) {
        case 10:
            statusDes = "有效";
            break;
        case 11:
            statusDes = "无效";
            break;
        case 12:
            statusDes = "已禁用";
            break;
        case 13:
            statusDes = "已删除";
            break;
        case 14:
            statusDes = "未激活";
            break;
        case 15:
            statusDes = "未注册";
            break;
        case 16:
            statusDes = "待审核";
            break;
        case 17:
            statusDes = "已通过审核";
            break;
        case 18:
            statusDes = "审核未通过";
            break;
        case 19:
            statusDes = "已受理";
            break;
        case 20:
            statusDes = "已处理";
            break;
        case 91:
            statusDes = "未付款";
            break;
        case 92:
            statusDes = "已付款";
            break;
        case 93:
            statusDes = "交易成功";
            break;
        case 94:
            statusDes = "交易失败";
            break;
        case 95:
            statusDes = "正在处理中";
            break;
        case 96:
            statusDes = "已取消";
            break;
        case 97:
            statusDes = "退款成功";
            break;
        case 112:
            statusDes = "已下单";
            break;
        case 113:
            statusDes = "已确认";
            break;
        case 114:
            statusDes = "已发货";
            break;
        case 115:
            statusDes = "派送中";
            break;
        case 116:
            statusDes = "已签收";
            break;
        case 117:
            statusDes = "已评价";
            break;
    }
    return statusDes;
}

HJCommonJs.FormatBranchAuthenticateStatus = function (status) {
    /**
    * 
    *格式化网点认证状态
    */
    var statusDes = "";
    switch (status) {
        case "":
        case null:
        case 1:
            statusDes = "未认证";
            break;
        case 2:
            statusDes = "待认证";
            break;
        case 3:
            statusDes = "待认证";
            break;
        case 4:
            statusDes = "已认证";
            break;
        case 5:
            statusDes = "拒绝";
            break;
    }
    return statusDes;
}
var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
HJCommonJs.GetRandom = function (n) {
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += chars[id];
    }
    return res;
}

// 获取短字符串
// content 原内容
// 截取长度
HJCommonJs.GetShortContent = function (content, count) {
    if (content == null) return "";
    if (content.length > count)
        return content.substring(0, count) + " ...";
    return content;
}

HJCommonJs.GetTimeSlot = function (startHour, startMinute, endHour, endMinute) {
    ///
    ///startHour 开始小时
    ///startMinute 开始分钟
    ///endHour 结束小时
    ///endMinute 结束分钟
    ///
    if (startMinute == "0") {
        startMinute = "00";
    }
    if (endMinute == "0") {
        endMinute = "00";
    }
    return startHour + ":" + startMinute + " - " + endHour + ":" + endMinute;
}

HJCommonJs.GetTimeValue = function (hour, minute) {
    ///
    /// hour 小时 
    /// minute 分钟
    ///

    if (minute == "0") {
        minute = "00";
    }
    return hour + ":" + minute;
}

HJCommonJs.GetFloatStr = function (num) {
    num += '';
    num = num.replace(/[^0-9|\.]/g, ''); //清除字符串中的非数字非.字符

    if (/^0+/) //清除字符串开头的0
        num = num.replace(/^0+/, '');
    if (!/\./.test(num)) //为整数字符串在末尾添加.00
        num += '.00';
    if (/^\./.test(num)) //字符以.开头时,在开头添加0
        num = '0' + num;
    num += '00';        //在字符串末尾补零
    num = num.match(/\d+\.\d{2}/)[0];
    return num;
};

HJCommonJs.ChangeParentIframeSize = function (parentObj, iframeName) {
    if (parentObj != null && parentObj.document != null && parentObj.document.body != null) {
        $(parentObj.document.body).find("iframe").each(function () {
            var iframe = $(this)[0];
            //if (iframe.contentWindow != null && iframe.contentWindow.testfun != null && typeof (iframe.contentWindow.testfun) == "function") {
            //    var height = $("body").attr("offsetHeight");
            //    $(iframe).css("height", height + 20);
            //}
            var thisname = iframeName;
            if (thisname != null && thisname == iframe.name) {
                var height = $("body").attr("offsetHeight");
                $(iframe).css("height", height + 20);
            }
        });
    }
}

HJCommonJs.GetEncryptionMpno = function (mpno) {
    ///
    ///获取加密手机号
    ///
    mpno = (mpno.substring(0, 3) + "****" + mpno.substring(7, 11));
    return mpno;
}

HJCommonJs.RedirectToPage = function (url) {
    window.location.href = url;
}

HJCommonJs.GetBranchDetailStaticPageUrl = function (branchID, compnayCode) {
    var url = "/kdwd/";
    url += branchID + ".html";
    return url;
}

HJCommonJs.LimitTextArea = function (field, maxlimit) {
    ///
    /// 控制文本输入最大数
    ///
    if (field.value.length > maxlimit) {
        field.value = field.value.substring(0, maxlimit);
    }
}

HJCommonJs.GetFileExtName = function (namestr) {
    var location = namestr;
    var filename = location.match(/[^\/]*$/)[0];
    var point = filename.lastIndexOf(".");
    var type = filename.substr(point);
    //        if (type == ".jpg" || type == ".gif" || type == ".JPG" || type == ".GIF") {

    //        }
    return type;
}

//分享按钮默认值
HJCommonJs.ShareConfig = {
    url: window.location.href,
    summary: "移动在线物流搜索平台",
    title: "禾嘉科技",
    shortUrl: true,
    hideMore: false,
    hide: function () { $("[name='ShareControl']").hide(); },
    show: function () { $("[name='ShareControl']").show(); }
};

//加载进度
HJCommonJs.Loader = (function ($, win) {
    var $loader, $mask;
    var init = function () {
        $(function () {
            if (win == win.top) {
                $mask = $("<div id=\"hj-mask\"></div>").appendTo("body");
                $loader = $("<div id=\"hj-mask-loading\"></div>").appendTo("body");
            } else {
try{
                var $top = $(win.top.document.body);
                $mask = $("#hj-mask", $top);
                $loader = $("#hj-mask-loading", $top);
} catch(ex) {}
            }
        });
    };
    var show = function () {
        $loader.show();
        $mask.show();
        //(typeof (callback) == "function") && callback.call();
    };
    var close = function () {
        $loader.hide();
        $mask.hide();
        //(typeof (callback) == "function") && callback.call();
    };

    init();

    return {
        Show: show,
        Close: close
    };
})(jQuery, window);

HJCommonJs.GetRandom = function () {
    var rand = Math.random();
    rand = String(rand).replace(".", "");
    return rand;
}

/*
 * -------------------
 * ajax请求封装
 * -------------------
*/

function HJPostData() {
    var parameters = "";
    this.addParameter = function (name, value) {
        value = encodeURIComponent(value);
        parameters += "{ \"Name\": \"" + name + "\", \"Value\": \"" + value + "\" },";
    };
    this.clearParameter = function () {
        parameters = "";
    };
    this.toJsonString = function () {
        return "data=" + "{ \"MethodName\": \"" + this.methodName + "\", \"Parameters\": [" + parameters.substr(0, parameters.length - 1) + "]}";
    }
    this.copy = function () {
        return "data=" + "{ \"MethodName\": \"" + this.methodName + "\", \"Parameters\": [" + parameters.substr(0, parameters.length - 1) + "]}";
    }
};
HJPostData.prototype = {
    methodName: ""
}

/*
 * ---------------
 * 解析
 * ---------------
*/

// 快件状态：-1 未知；1 已收件； 2 运输途中；3 正在派件； 4 已签收

function ExpressQueryMGT() {

    // start 实例属性

    var trackRecord = [];

    // end 实例属性

    // start 实例方法

    this.createTrackRecord = function () {
        /// <summary>
        /// 创建一条跟踪记录
        /// </summary>
        /// <returns>返回跟踪记录对象，一个 json 格式对象</returns>
        return { "Time": "", "Text": "" };
    };
    this.addTrackRecord = function (obj) {
        /// <summary>
        /// 将单条跟踪记录加入跟踪记录列表
        /// </summary>
        /// <param name="obj">单条跟踪记录</param>
        /// <returns>void</returns>
        trackRecord.push(obj);
    };
    this.clearTrackRecord = function () {
        /// <summary>
        /// 清除跟踪记录列表
        /// </summary>
        /// <returns>void</returns>
        trackRecord = [];
    };
    this.getJsonObject = function () {
        /// <summary>
        /// 返回跟踪记录列表
        /// </summary>
        /// <returns>void</returns>
        for (var i = 0; i < trackRecord.length; i++) {
            trackRecord[i].time = HJCommonJs.Trim(trackRecord[i].time);
            trackRecord[i].content = HJCommonJs.Trim(trackRecord[i].content);
        }
        return trackRecord;
    }
    this.YunDa = function (text) {
        /// <summary>
        /// 韵达解析程序
        /// </summary>
        /// <param name="text">跟踪信息文本</param>
        /// <returns>string</returns>
        //if (content.indexOf("签收") >= 0) trobj.post = 4;

        var g_ljcs = "";
        var g_ljsj = "";
        var g_ljdd = "";
        var g_ljry = "";
        var g_qsdd = "";
        var g_qsry = "";
        var g_qssj = "";

        var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        var content = decodeURIComponent(text.TraceList[0].content);
        var decodestr = decodeURIComponent(text.TraceList[0].decodestr);
        var a_res = YunDa_Decode_Data(content, keyStr, decodestr);

        var s_res = "";
        for (var i = 0; i < a_res.length; i++) {
            var t_res = a_res[i].split(',')[0];
            var d_res = a_res[i].split(',')[1];
            if (t_res.length == 19) {
                if (d_res != null && d_res.length > 0 && d_res.indexOf('签收') >= 0) {
                    var t = t_res.replace(/[-]/g, "");
                    t = t.replace(/[:]/g, "/");
                    t = t.replace(/[\/]/, "");
                    t = t.replace(/[ ]/, "/");
                    tmp_pic = "<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0' style='margin-top: 10px;'><tr><td align='center'><a href='http://qz.yundasys.com:9900/img/" + t + g_txm + ".jpg' target=_blank>查看照片</a></td></tr></table>"
                    g_qssj = t_res;
                    g_qsry = getWithBegEnd(d_res, "由", "签收");
                    g_qsdd = getWithBegEnd(d_res, ">", "</");
                } else if (d_res != null && d_res.length > 0 && d_res.indexOf('已收件') >= 0) {
                    if (g_ljsj.length == 0) {
                        g_ljsj = t_res;
                        g_ljcs = g_ljdd = getWithBegEnd(d_res, ">", "</");
                    }
                }
                if (d_res.indexOf('sm12=1') < 0) {
                    s_res += "<tr><td class='fxdtl'>" + t_res + "</td><td class='fxdtl'>" + d_res + "</td></tr>";
                } 
            }
        }



        //        var trobj = ret.createTrackRecord();
        //        trobj.Time = timestr;
        //        trobj.Text = content;
        //        ret.addTrackRecord(trobj);
    }

    // end 实例方法
};

ExpressQueryMGT.prototype = {
}

// start 静态方法

ExpressQueryMGT.getExpressTrackRecord = function (content, companyID) {
    var mgt = new ExpressQueryMGT();
    var retObj = null;
    switch (Number(companyID)) {
        case 6: retObj = mgt.YunDa(content); break;
        default:
            {
                alert("无法匹配解析程序");
                break;
            }
    }
    return retObj;
}

ExpressQueryMGT.QueryExpressTrackInfo = function (no) {

    return [{ "a": "1"}];
}

// end 静态方法


/*
* ---------------
* 分页相关
* ---------------
*/

function HJPagePack() { }

HJPagePack.BuildPageSpiltObject = function (setting) {
    //    targetElement     显示页码的元素
    //    pageSize          每页显示数目
    //    totalItem         总记录数
    //    currentPage       当前页数
    //    itemCreate        页码按钮创建时的回调函数
    //    prePageCreate     上一页按钮创建时的回调函数
    //    nextPageCreate    下一页按钮创建时的回调函数
    //    itemClick         页码单击的回调函数
    //    showItemCountChooseClick   显示项数选择区单击的回调函数

    // 计算页数范围
    var pageCount = parseInt(setting.totalItem / setting.pageSize);
    if (setting.totalItem % setting.pageSize > 0)
        pageCount += 1;

    // 获取最大最小页值
    var minPageNumber = getMinPageNumber(setting.currentPage, pageCount);
    var maxPageNumber = getMaxPageNumber(minPageNumber, pageCount);

    // 创建显示区域
    var objdiv = document.createElement("div")
    $(objdiv).addClass("page");

    // 创建显示项数选择区
    //    $(objdiv).append("<span>每页显示</span>");
    //    $(objdiv).append("<a href=\"javascript:void(0);\" class=\"num\" aType=\"chooseArea\">10</a>");
    //    $(objdiv).append("<a href=\"javascript:void(0);\" class=\"num\" aType=\"chooseArea\">20</a>");
    //    $(objdiv).append("<a href=\"javascript:void(0);\" class=\"num\" aType=\"chooseArea\">30</a>");
    //    $(objdiv).append("<span>行</span>");
    //    if (typeof (setting.showItemCountChooseClick) == "function") {
    //        $(objdiv).find("a[aType='chooseArea']").bind("click", function () {
    //            setting.showItemCountChooseClick($(this).text());
    //        });
    //    }

    // 总览区域
    //    var zlDiv = document.createElement("span");
    //    $(zlDiv).text("总数：" + setting.totalItem + "，共 " + pageCount + " 页");
    //    $(objdiv).append(zlDiv);

    // 创建上一页按钮
    var pre = document.createElement("a");
    $(pre).text("上一页").attr("currentPage", setting.currentPage - 1);
    $(pre).attr("href", "javascript:;")
    if (typeof (setting.prePageCreate) == "function") {
        pre = setting.prePageCreate({ element: pre, currentPage: setting.currentPage - 1 });
    }
    $(objdiv).append(pre);


    //创建第一页按钮
    if (setting.currentPage > 3 && pageCount > 7) {
        var first = document.createElement("a");
        $(first).text("1").attr("currentPage", 1);
        $(first).attr("href", "javascript:;")
        if (typeof (setting.prePageCreate) == "function") {
            first = setting.prePageCreate({ element: first, currentPage: 1 });
        }
        $(objdiv).append(first);
    }


    //创建上一页后面的...标示
    if (setting.currentPage >= 5 && pageCount > 7) {
        var preNext = document.createElement("a");
        $(preNext).text("...").attr("currentPage", setting.currentPage - 3);
        $(preNext).attr("href", "javascript:;")
        if (typeof (setting.prePageCreate) == "function") {
            preNext = setting.prePageCreate({ element: preNext, currentPage: setting.currentPage - 3 });
        }
        $(objdiv).append(preNext);
    }


    // 创建页码按钮
    for (var i = minPageNumber; i <= maxPageNumber; i++) {
        var obj_a = document.createElement("a");
        //$(obj_a).attr("href", "javascript:void(0);");
        if (typeof (setting.itemCreate) == "function") {
            obj_a = setting.itemCreate({ element: obj_a, currentPage: i });
        }
        if (i == setting.currentPage) {
            $(obj_a).attr("class", "num active");
            $(obj_a).text(i);
        }
        else {
            $(obj_a).attr("class", "num");
            $(obj_a).text(i);
        }

        // 页码单击后重新进行排列
        $(obj_a).bind("click", function () {
            var cp = Number($(this).text());
            HJPagePack.BuildPageSpiltObject({
                targetElement: setting.targetElement,
                pageSize: setting.pageSize,
                totalItem: setting.totalItem,
                currentPage: cp,
                itemCreate: setting.itemCreate,
                prePageCreate: setting.prePageCreate,
                nextPageCreate: setting.nextPageCreate,
                itemClick: setting.itemClick
            });
            // 为页码绑定单击事件的回调函数
            if (typeof (setting.itemClick) == "function") {
                setting.itemClick({ element: this, currentPage: cp, pageSize: setting.pageSize });
            }
        });
        $(objdiv).append(obj_a);
    }

    //创建下一页前面的...标示
    if (pageCount - setting.currentPage > 3 && pageCount > 7) {
        var nextPre = document.createElement("a");
        $(nextPre).text("...").attr("currentPage", setting.currentPage + 3);
        $(nextPre).attr("href", "javascript:;")
        if (typeof (setting.nextPrePageCreate) == "function") {
            nextPre = setting.nextPageCreate({ element: nextPre, currentPage: setting.currentPage + 3 });
        }
        $(objdiv).append(nextPre);
    }


    //创建最后一页按钮
    if (pageCount > 7 && pageCount - setting.currentPage >= 3) {
        var last = document.createElement("a");
        $(last).text(pageCount).attr("currentPage", pageCount);
        $(last).attr("href", "javascript:;")
        if (typeof (setting.nextPrePageCreate) == "function") {
            last = setting.nextPageCreate({ element: last, currentPage: pageCount });
        }
        $(objdiv).append(last);
    }


    // 创建下一页按钮
    var next = document.createElement("a");
    $(next).text("下一页").attr("currentPage", setting.currentPage + 1);
    $(next).attr("href", "javascript:;")
    if (typeof (setting.nextPageCreate) == "function") {
        next = setting.nextPageCreate({ element: next, currentPage: setting.currentPage + 1 });
    }
    $(objdiv).append(next);

    // 为上下页按钮绑定单击事件的回调函数
    if (typeof (setting.itemClick) == "function") {
        $(pre).bind("click", function () {
            if (Number($(this).attr("currentPage") > 0)) {
                setting.itemClick({ element: this, currentPage: Number($(this).attr("currentPage")), pageSize: setting.pageSize });
            }
        });
        $(next).bind("click", function () {
            if (Number($(this).attr("currentPage") <= pageCount)) {
                setting.itemClick({ element: this, currentPage: Number($(this).attr("currentPage")), pageSize: setting.pageSize });
            }
        });

        $(preNext).bind("click", function () {
            if (Number($(this).attr("currentPage") > 0)) {
                setting.itemClick({ element: this, currentPage: Number($(this).attr("currentPage")) - 3, pageSize: setting.pageSize });
            }
        });
        $(nextPre).bind("click", function () {
            if (Number($(this).attr("currentPage") <= pageCount)) {
                setting.itemClick({ element: this, currentPage: Number($(this).attr("currentPage")) + 3, pageSize: setting.pageSize });
            }
        });
        $(first).bind("click", function () {
            if (Number($(this).attr("currentPage") > 0)) {
                setting.itemClick({ element: this, currentPage: Number($(this).attr("currentPage")), pageSize: setting.pageSize });
            }
        });
        $(last).bind("click", function () {
            if (Number($(this).attr("currentPage") <= pageCount)) {
                setting.itemClick({ element: this, currentPage: Number($(this).attr("currentPage")), pageSize: setting.pageSize });
            }
        });
    }

    // 添加到显示区域
    $(setting.targetElement).html("");
    $(setting.targetElement).append(objdiv);
}

function getMinPageNumber(currentPage, pageCount) {
    if (pageCount <= 7) {
        ret = 1
    } else {
        var ret = currentPage - 2;
        if (ret < 1) {
            ret = 1;
        }
    }
    return ret;
}

function getMaxPageNumber(minPageNumber, pageCount) {
    if (pageCount <= 7) {
        ret = pageCount;
    } else {
        var ret = minPageNumber + 4;
        if (ret > pageCount) {
            ret = pageCount;
        }
    }
    return ret;
}

/*
* ---------------
* 地理位置信息对象
* ---------------
*/

function HJDataStructure() {
    this.GeoInfoEntity = function () {
        return { "provinceID": -1, "provinceName": "", "cityID": -1, "cityName": "", "districtID": -1, "districtName": "", "streetID": -1, "streetName": "" };
    };
    this.ExpComInfoEntity = function () {
        return { "expComID": -1, "expComName": "" };
    }
    this.BranchInfoEntity = function () {
        return { "branchID": -1, "userName": "", "password": "", "expComID": -1, "branchName": "", "provinceID": -1, "cityID": -1, "districtID": -1, "detailAddress": "", "headPhone": "", "telephone": "", "kefuQQ": "" };
    }
    this.ListItem = function () {
        return { "value": "", "text": "" };
    }
    this.AddressCallbakObject = function () {
        return { "AddressID": -1, "Name": "", "MobileNo": "", "DetailAddress": "", "GeoInfo": {} };
    }
    this.SortListItem = function () {
        return { "SortType": -1, "FiledName": "", "ShowText": "" };
    }
    this.CourierTrackTimeEntity = function () {
        return { "ObjID": "", "StartTime": "", "EndTime": "", "BuildingID": "", "BuildingName": "" };
    }
}

//JS操作cookies方法! 
//写cookies 
function setCookie(name,value) 
{ 
var Days = 30; //30天过期
var exp = new Date(); 
exp.setTime(exp.getTime() + Days*24*60*60*1000); 
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 
//读取cookies 
function getCookie(name) 
{ 
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)"); 
if(arr=document.cookie.match(reg)) return unescape(arr[2]); 
else return null; 
} 
//删除cookies 
function delCookie(name) 
{ 
var exp = new Date(); 
exp.setTime(exp.getTime() - 1); 
var cval=getCookie(name); 
if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
}

/*
*点击一级菜单,收叠二级菜单
*/
$(function(){
	//菜单的样式 
	var url=window.location.href.replace(/#/g,"");
	var url_rel=url.substr(url.indexOf("/road"));
	$(".nav_left").find("a[href$='"+url_rel+"']").addClass("active");
	
	$(".header_nav ul li").find("a[href$='"+url.substring(0,url.length-1)+"']").addClass("active");
	
	if($($(".nav_first")[0]).hasClass("active")) delCookie("nav_account");;
	if(getCookie("nav_account")){
		$(".nav_second").hide();
	}
	$(".nav_first").bind("click",function(){
		if($(this).hasClass("nav_account")){
			if(getCookie("nav_account")){
				delCookie("nav_account");
			}else{
				setCookie("nav_account","nav_account");
			}
		}else{
			delCookie("nav_account");
		}
	});	
});

/*
 * 验证的一些统一参数
 */
var validate={
	config:{
		img_tip:"<img src=\""+basePath+"skin/default/image/no.gif\">",
		img_ok:"<img src=\""+basePath+"skin/default/image/know_3.gif\">"
},
	password:{
		reg:/^[a-zA-Z0-9_]{6,18}$/,
		tip:"密码只能由 数字,字母,下划线组成6至18位"
},
	email:{
		reg:/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})$/,
		tip:"请输入正确定的邮箱"
},
	tel:{
		reg:/^((([1][34578][0-9]{9})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)|((\d{3,4})-(\d{3,4})-(\d{3,4}))){0,1}$/,
		tip:"请输入正确的电话号码，固定电话区号、直播号、分机号用-隔开"
},
	phone:{
		reg:/^([1][34578][0-9]{9}){0,1}$/,
		tip:"请输入正确的手机号码"
},
	website:{
		reg:/^(((https|http|ftp|rtsp|mms)?:\/\/)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)){0,1}$/,
		tip:"请输入正确定的网址"
},
	data_int:{
		reg:/^\d*$/,
		tip:"请输入整数"
},
	data_float:{
		reg:/^\d+(\d|(\.[1-9]{1,2}))$/,
		tip:"请输入整数或小数"
}
};

/*
 * 数据格式的精确处理
*/
function parseNumber(data){
	if(parseFloat(data)==0){
		return 0;
	}else{
		return parseFloat(data)||"";
	}
};

HJ.ShowHistoryCount = function (count) {
	debugger;
    if (typeof count == "undefined") {
        $("#headAttentionOrderPromt").hide();
        $.get("http://kd.xk.com/form/common/SaveCookie.aspx?"+new Date().getTime() , function (data) {
            HJExpressSearch.ShowHistoryCount(data);
        });
        count = 0;
    }
    $("#headAttentionOrder").text(count);
    $("#headAttentionOrderCount").text(count);
    if (count > 0) {
        $("#headAttentionOrderPromt").show();
    }
};

HJ.Report = function ( aParam ) {
	if( aParam && aParam.reportId ){
		$.ajax({
			  type: "POST", 
			  url: "/api/ajax/report.php",
			  dataType : "json",
			  data: aParam,
			  success: function( data ) {
				  console.log(data);
			  }
		});
	}
};