(function ($) {

    //省市区选择对象
    var ChooseCity = {
        controls: {
            container: null,
            navs: [],
            cons: []
        },
        isInit: false,
        target: null,
        clickArea: [],
        isClick:false  //add by wumz
    };

    var _Config = window.Config || {
        geo: {
            provinces: [],
            cities: [],
            districts: []
        }
    };

    //坐标信息
    ChooseCity.geo = {
        provinces: _Config.geo.provinces,
        cities: _Config.geo.cities,
        districts: _Config.geo.districts,
        filter: function (array, fn) {
            var r = [];
            for (var i in array) {
                if (fn.call(array[i])) {
                    r.push(array[i]);
                }
            }
            return r;
        }
    };

    //地理位置信息对象
    ChooseCity.GeoEntity = function () {
        if (arguments.length > 0 && ChooseCity.checkEntity(arguments[0])) {
            for (var i in arguments[0]) {
                this[i] = (typeof (arguments[0][i]) === "undefined" || arguments[0][i] == "undefined") ? "" : arguments[0][i];
            }
        } else {
            this.provinceID = null;
            this.provinceName = null;
            this.cityID = null;
            this.cityName = null;
            this.districtID = null;
            this.districtName = null;
            this.streetID = -1;
            this.streetName = "";
        }
        this.toString = function () {
            return ChooseCity.format("{0}{1}{2}",
                        this.provinceName,
                        this.cityName ? (" - " + this.cityName) : "",
                        this.districtName ? (" - " + this.districtName) : "");
        }
    }

    ChooseCity.checkEntity = function (entity) {
        return entity != null && typeof (entity) == "object" && typeof (entity.districtID) != "undefined";
    }

    ChooseCity.getTargetGeo = function () {
        return !!ChooseCity.target ? ChooseCity.target.data("geo") : new ChooseCity.GeoEntity();
    }

    ChooseCity.setTargetGeo = function (geo) {
        if (!!ChooseCity.target) {
            ChooseCity.target.data("geo", geo);
        }
    }

    //加载常用城市列表
    ChooseCity.getMostsObjectList = function () {
        var list = ChooseCity.geo.filter(ChooseCity.geo.cities, function () { return this.isGeneral; }),
            r = [];
        for (var i in list) {
            //构造元素
            var temp = $(ChooseCity.format("<a href=\"javascript:void(0);\" title=\"{0}\">{0}</a>", list[i]["name"], list[i]["id"]));
            //绑定数据
            temp.data("entity", list[i]);
            r.push(temp);
        }
        return r;
    }

    ChooseCity.loadMosts = function () {
        if (!ChooseCity.isInit) return;
        //清空
        var mostsCon = ChooseCity.controls.cons.filter("#mostsList");
        mostsCon.empty();
        $.each(ChooseCity.getMostsObjectList(), function () {
            //绑定单击事件
            this.bind("click", function () {
                if (!ChooseCity.target) return;

                //处理对象缓存数据=========
                var bindEntity = $(this).data("entity");
                var objectGeo = ChooseCity.getTargetGeo();
                var pEntity = ChooseCity.geo.filter(ChooseCity.geo.provinces, function () { return this.id == bindEntity["provinceId"]; });
                if (pEntity.length > 0) {
                    objectGeo.provinceID = pEntity[0]["id"];
                    objectGeo.provinceName = pEntity[0]["name"];
                }
                objectGeo.cityID = bindEntity["id"];
                objectGeo.cityName = bindEntity["name"];
                objectGeo.districtID = -1;
                objectGeo.districtName = "";
                ChooseCity.setTargetGeo(objectGeo);
                //=========================

                var options = ChooseCity.target.data("options");
                if (options.mode.toLowerCase() === "citymode") {
                    ChooseCity.setTargetGeo(objectGeo);
                    ChooseCity.onComplete();
                }else if(options.mode.toLowerCase() === "districtcheckbox"){ //add by wumz 20150826
                	ChooseCity.isClick = true;
                	ChooseCity.controls.cons.filter("#districtList").empty();
                	ChooseCity.onForthwithDisplay2();
                    ChooseCity.showDistricts2();
                   
                }else {
                    ChooseCity.onForthwithDisplay();
                    ChooseCity.showDistricts();
                }
            });
            this.appendTo(mostsCon);
        });
    }

    ChooseCity.showMosts = function () {
        ChooseCity.controls.navs.filter("#most_head").addClass("active list_title_active").siblings().removeClass("active list_title_active");
        ChooseCity.controls.cons.filter("#mostsList").show().siblings().hide();
    }

    //加载省份列表
    ChooseCity.getProvincesObjectList = function () {
        var r = [];
        for (var i in ChooseCity.geo.provinces) {
            var temp = $(ChooseCity.format("<a href=\"javascript:void(0);\" title=\"{0}\" onclick=\"ChooseCity.loadCities({1});\">{0}</a>", ChooseCity.geo.provinces[i]["name"], ChooseCity.geo.provinces[i]["id"]));
            //绑定数据
            temp.data("entity", ChooseCity.geo.provinces[i]);
            r.push(temp);
        }
        return r;
    }

    ChooseCity.loadProvinces = function () {
        if (!ChooseCity.isInit) return;
        //清空
        var provincesCon = ChooseCity.controls.cons.filter("#provinceList");
        provincesCon.empty();
        $.each(ChooseCity.getProvincesObjectList(), function () {
            //绑定单击事件
            this.bind("click", function () {
                if (!ChooseCity.target) return;

                //处理对象缓存数据=========
                var bindEntity = $(this).data("entity");
                var objectGeo = ChooseCity.getTargetGeo();
                objectGeo.provinceID = bindEntity["id"];
                objectGeo.provinceName = bindEntity["name"];
                objectGeo.cityID = null;
                objectGeo.cityName = "";
                objectGeo.districtID = -1;
                objectGeo.districtName = "";
                ChooseCity.setTargetGeo(objectGeo);
                //=========================

                if (bindEntity["isMunicipality"]) {
                    var cityData = ChooseCity.geo.filter(ChooseCity.geo.cities, function () { return this.provinceId == bindEntity["id"]; });
                    if (cityData.length > 0) {
                        objectGeo.cityID = cityData[0]["id"];
                        objectGeo.cityName = cityData[0]["name"];
                        ChooseCity.setTargetGeo(objectGeo);
                    }
                    ChooseCity.onForthwithDisplay();
                    ChooseCity.showDistricts();
                } else {
                    ChooseCity.showCities();
                }
            });
            this.appendTo(provincesCon);
        });
    }

    ChooseCity.showProvinces = function () {
        ChooseCity.controls.navs.filter("#provinceList_head").addClass("active list_title_active").siblings().removeClass("active list_title_active");
        ChooseCity.controls.cons.filter("#provinceList").show().siblings().hide();
    }

    //加载城市列表
    ChooseCity.getCitiesObjectList = function (pid) {
        var list = ChooseCity.geo.filter(ChooseCity.geo.cities, function () { return this.provinceId == pid; }),
                r = [];
        for (var i in list) {
            var temp = $(ChooseCity.format("<a href=\"javascript:void(0);\" title=\"{0}\">{0}</a>", list[i]["name"], list[i]["id"]));
            //绑定数据
            temp.data("entity", list[i]);
            r.push(temp);
        }
        return r;
    }

    ChooseCity.loadCities = function (pid) {
        if (!ChooseCity.isInit) return;
        //清空
        var citiesCon = ChooseCity.controls.cons.filter("#cityList");
        citiesCon.empty();
        $.each(ChooseCity.getCitiesObjectList(pid), function () {
            //绑定单击事件
            this.bind("click", function () {
                if (!ChooseCity.target) return;


                //处理对象缓存数据=========
                var bindEntity = $(this).data("entity");
                var objectGeo = ChooseCity.getTargetGeo();
                objectGeo.cityID = bindEntity["id"];
                objectGeo.cityName = bindEntity["name"];
                objectGeo.districtID = -1;
                objectGeo.districtName = "";
                ChooseCity.setTargetGeo(objectGeo);
                //=========================

                var options = ChooseCity.target.data("options");
                if (options.mode.toLowerCase() === "citymode") {
                    ChooseCity.setTargetGeo(objectGeo);
                    ChooseCity.onComplete();
                }else if(options.mode.toLowerCase() === "districtcheckbox"){ //add by wumz 20150826
                	ChooseCity.controls.cons.filter("#districtList").empty();
                	ChooseCity.isClick=true;
                	ChooseCity.onForthwithDisplay2();
                    ChooseCity.showDistricts2();
                    //ChooseCity.onComplete();
                }else {
                    ChooseCity.onForthwithDisplay();
                    ChooseCity.showDistricts();
                }
                
                
            });
            this.appendTo(citiesCon);
        });
    }

    ChooseCity.showCities = function () {
        var geo = ChooseCity.getTargetGeo();
     
        if (geo.provinceID != null) {
            ChooseCity.loadCities(geo.provinceID);
        } else {
            ChooseCity.controls.cons.filter("#cityList").empty();
        }
        ChooseCity.controls.navs.filter("#cityList_head").addClass("active list_title_active").siblings().removeClass("active list_title_active");
        ChooseCity.controls.cons.filter("#cityList").show().siblings().hide();
    }

    //加载区域列表
    ChooseCity.getDistrictsObjectList = function (cid) {
        var list = ChooseCity.geo.filter(ChooseCity.geo.districts, function () { return this.cityId == cid; }),
                r = [];
        for (var i in list) {
            var temp = $(ChooseCity.format("<a href=\"javascript:void(0);\" title=\"{0}\">{0}</a>", list[i]["name"]));
            //绑定数据
            temp.data("entity", list[i]);
            r.push(temp);
        }
        return r;
    }

    ChooseCity.loadDistricts = function (cid) {
        if (!ChooseCity.isInit) return;
        //清空
        var districtsCon = ChooseCity.controls.cons.filter("#districtList");
        districtsCon.empty();
        $.each(ChooseCity.getDistrictsObjectList(cid), function () {
            //绑定单击事件
            this.bind("click", function () {
                if (!ChooseCity.target) return;

                //处理对象缓存数据=========
                var bindEntity = $(this).data("entity");
                var objectGeo = ChooseCity.getTargetGeo();
                objectGeo.districtID = bindEntity["id"];
                objectGeo.districtName = bindEntity["name"];
                ChooseCity.setTargetGeo(objectGeo);
                //=========================

                ChooseCity.onComplete();

                //                ChooseCity.controls.container.hide();

                //                var options = ChooseCity.target.data("options");

                //                ChooseCity.target.val(objectGeo.toString()).removeClass(options.tipsClass).addClass(options.valueClass);

                //                if (typeof options.callback == "function") {
                //                    options.callback(objectGeo);
                //                }
            });
            this.appendTo(districtsCon);
        });
    }

    ChooseCity.showDistricts = function () {
        var geo = ChooseCity.getTargetGeo();
        if (geo.cityID != null) {
            ChooseCity.loadDistricts(geo.cityID);
        } else {
            ChooseCity.controls.cons.filter("#districtList").empty();
        }
        ChooseCity.controls.navs.filter("#districtList_head").addClass("active list_title_active").siblings().removeClass("active list_title_active");
        ChooseCity.controls.cons.filter("#districtList").show().siblings().hide();
    }

    /*
     * 增加区域多选框列表   开始  add by wumz 20150826
     * 
     *   增加一下参数
     *   $("#id").chooseCity({ ..., mode:'districtcheckbox', name:"",areaid:'',... })
	 *    name:"",多选框的名字
	 *    areaid:地区id,显示在文本框的地址全名
     **/
    
    ChooseCity.onForthwithDisplay2 = function(){
      
    	 if( !ChooseCity.isClick || !ChooseCity.target) return false;
    	 var options = ChooseCity.target.data("options");
    
    	 if( options.mode=='districtcheckbox' ){
    		 
    		
    		var objectGeo = ChooseCity.getTargetGeo();
        	var dctnames = '';
         	var checkdct = [];
         	dctnames += '(';
         	ChooseCity.controls.cons.filter("#districtList").find('input[name="'+ChooseCity.getName()+'"]').each(function(index,item){
         		item = $(item);
         		if(item.attr("checked")){
         			
         			dctnames += item.attr("text")+"/";
         			checkdct.push({'districtID':item.val(),'districtName':item.attr("text")})
         		}
         	});
         	dctnames = dctnames.substr(0,dctnames.length-1);
         	if( dctnames != '' )dctnames += ")";
            if (typeof options.callback == "function" ) {
	        	
            	 if( objectGeo.provinceName != null &&  objectGeo.cityName != null ){
		             checkdct.push({'cityID':objectGeo.cityID,'cityName':objectGeo.cityName})
		             checkdct.push({'provinceID':objectGeo.provinceID,'provinceName':objectGeo.provinceName})	
	                 options.callback( checkdct );
	                 ChooseCity.target.val( objectGeo.provinceName+"-"+objectGeo.cityName+dctnames );
            	 }
                 ChooseCity.isClick = false;
                
            }
          
            
         }
    	
    }
    
    
    
    ChooseCity.getName = function(){
    	var options
    	if (ChooseCity.target){
    		options= ChooseCity.target.data("options");
    	}
        var name = "districts";
        if( typeof options.name != "undefined" ){
      	  name =  options.name;
        }
    	return name;
    }
    
    ChooseCity.getDistrictsObjectList2 = function (cid) {
        var list = ChooseCity.geo.filter(ChooseCity.geo.districts, function () { return this.cityId == cid; });
        r = [];
        for (var i in list) {
            var temp = $("<a href=\"javascript:void(0);\" title=\""+list[i]["name"]+"\"><input style=\"margin:0px 2px 0px 5px;cursor:pointer;\" type=\"checkbox\" name=\""+ChooseCity.getName()+"\" text="+list[i]["name"]+" id=\"ckx"+i+"\" value="+list[i]["id"]+"><label style=\"cursor:pointer;\" for=\"ckx"+i+"\">"+list[i]["name"]+"</label></a>");
          
            //绑定数据
            temp.data("entity", list[i]);
            r.push(temp);
        }
        return r;
    }
    
    ChooseCity.loadDistricts2 = function (cid) {
        if (!ChooseCity.isInit) return;
        //清空
        var districtsCon = ChooseCity.controls.cons.filter("#districtList");
        districtsCon.empty();
        $.each(ChooseCity.getDistrictsObjectList2(cid), function () {
        	 this.bind("click", function () {
                 if (!ChooseCity.target) return;
        	     ChooseCity.isClick = true;
        	 });
            this.appendTo(districtsCon);
        });
    }
    
    ChooseCity.showDistricts2 = function () {
    	
    	//ChooseCity.controls.cons.filter("#districtList").empty();
    	var geo = ChooseCity.getTargetGeo();

        if (geo.cityID != null) {
            ChooseCity.loadDistricts2(geo.cityID);
        } 
         
        ChooseCity.controls.navs.filter("#districtList_head").addClass("active list_title_active").siblings().removeClass("active list_title_active");
        ChooseCity.controls.cons.filter("#districtList").show().siblings().hide();
        ChooseCity.isClick = false;
    }  
    
    ChooseCity.getAddressFullName = function(obj,options){
    	
    	if (!options.areaid) return false;
    	var fullnName= [],city=[],provinces=[],cityId='';
    	var areaid= options.areaid;
    	var district = ChooseCity.geo.filter(ChooseCity.geo.districts, function () { 
    		return this.id == areaid; 
    	});
    	if( typeof district[0] == 'undefined' ){
    		cityId = areaid;
    	}else{
    		cityId = district[0].cityId;
    	}
    	if( cityId != '' ){
	        city = ChooseCity.geo.filter(ChooseCity.geo.cities, function () { 
	    		return this.id == cityId; 
	    	});
    	}
    	if( typeof city[0] != 'undefined' ){
	    	provinces = ChooseCity.geo.filter(ChooseCity.geo.provinces, function () { 
	    		return this.id == city[0].provinceId; 
	    	});
    	}
    	
        if( typeof city[0] != 'undefined' && typeof provinces[0] != 'undefined' ){
        	  var fullname= provinces[0].name+"-"+city[0].name;
        	
        	  var objectGeo = ChooseCity.getTargetGeo();
        	  if (typeof options.callback == "function") {
        		  var addressList = [];
        		  if(  typeof district[0] != 'undefined'  ){
        			 addressList.push({'districtID':district[0].id,'districtName':district[0].name});
        			 fullname += "("+district[0].name+")";
        			  objectGeo.districtID = district[0].id;
                      objectGeo.districtName = district[0].name;
        		  }else{
        			  objectGeo.districtID = -1;
                      objectGeo.districtName = "";
        		  }
        		  obj.val(fullname);
        		  addressList.push({'cityID':city[0].id,'cityName':city[0].name})
		          addressList.push({'provinceID':provinces[0].id,'provinceName':provinces[0].name})	
                  options.callback(addressList);
        		  
        		
                  objectGeo.provinceID = provinces[0].id;
                  objectGeo.provinceName = provinces[0].name;
                  objectGeo.cityID = city[0].id;
                  objectGeo.cityName = city[0].name;
                
                  ChooseCity.setTargetGeo(objectGeo);
        		
              }
        }    	
    }
    
    /*增加区域多选框列表   结束*/
    
    
    ChooseCity.onComplete = function () {
        //隐藏浮窗
        ChooseCity.controls.container.hide();

        ChooseCity.onForthwithDisplay();
    }

    ChooseCity.onForthwithDisplay = function () {

        var options = ChooseCity.target.data("options");
        var objectGeo = ChooseCity.getTargetGeo();

        ChooseCity.target.val(objectGeo.toString()).removeClass(options.tipsClass).addClass(options.valueClass);

        switch (options.mode.toLowerCase()) {
            case "citymode":
                $(options.txtControl).text(objectGeo.cityName);
                break;
            default:
                $(options.txtControl).text(objectGeo.toString());
        }


        if (typeof options.callback == "function") {
            options.callback(objectGeo);
        }

    }

    ChooseCity.format = function () {
        if (arguments.length <= 0) return;
        if (arguments.length == 1) return arguments[0];
        var temp = arguments[0];
        for (var i = 0; i < arguments.length - 1; i++) {
            var r = new RegExp("\\{" + i + "\\}", "ig");
            temp = temp.replace(r, arguments[i + 1]);
        }
        return temp;
    }

    //构造城市选择浮窗
    ChooseCity.init = function (options) {
        //是否已初始化
        if (!ChooseCity.isInit) {

            var style = "", line = "\n";
            style += "<style type=\"text/css\">" + line;
            style += "  .black { color:#000; }" + line;
            style += "  #ChooseCityControl { font-size: 14px; line-height: 30px; position: absolute; z-index: 99999; }" + line;
            style += "  #ChooseCityControl .con a { overflow: hidden; white-space: nowrap; text-overflow: ellipsis; }" + line;
            style += "</style>";
            style = $(style);
            $("head").append(style);

            var temp = "";
            temp += "<div class=\"comlist select_more_list\" id=\"ChooseCityControl\">";
            temp += "    <div class=\"bt list_title\" id=\"ChooseCityNav\">";
            temp += "       <a href=\"javascript:void(0);\" class=\"active list_title_active\" id=\"most_head\">常用</a>";
            temp += "       <a href=\"javascript:void(0);\" id=\"provinceList_head\">省份</a>";
            temp += "       <a href=\"javascript:void(0);\" id=\"cityList_head\">城市</a>";
            temp += "       <a href=\"javascript:void(0);\" id=\"districtList_head\">县区</a>";
            temp += "       <div class=\"clear\" id=\"clear\"></div>";
            temp += "    </div>";
            temp += "    <div id=\"ChooseCityContainer\">";
            temp += "       <div class=\"con list_con\" id=\"mostsList\"></div>";
            temp += "       <div class=\"con list_con\" style=\"display: none\" id=\"provinceList\"></div>";
            temp += "       <div class=\"con list_con\" style=\"display: none\" id=\"cityList\"></div>";
            temp += "       <div class=\"con list_con\" style=\"display: none\" id=\"districtList\"></div>";
            temp += "    </div>";
            temp += "</div>";

            ChooseCity.controls.container = $(temp).hide();
            ChooseCity.controls.navs = $("#ChooseCityNav a", ChooseCity.controls.container);
            ChooseCity.controls.cons = $("#ChooseCityContainer>div", ChooseCity.controls.container);

            ChooseCity.controls.navs.filter("#most_head").bind("click", ChooseCity.showMosts);
            ChooseCity.controls.navs.filter("#provinceList_head").bind("click", ChooseCity.showProvinces);
            ChooseCity.controls.navs.filter("#cityList_head").bind("click", ChooseCity.showCities);
            ChooseCity.controls.navs.filter("#districtList_head").bind("click", (options.mode == "districtcheckbox" ? ChooseCity.showDistricts2:ChooseCity.showDistricts));//alter wumz

            $("body").append(ChooseCity.controls.container);

            //对象缓存
            ChooseCity.clickArea.push("#ChooseCityControl");

            $(document).bind("click", function (e) {
            	//jquery v1.5.1
            	/* var a = $(e.target).closest(ChooseCity.clickArea);
                if (a.length == 0) {
                    ChooseCity.controls.container.hide();
                    
                    ChooseCity.onForthwithDisplay2(); //add by wumz
                }*/
                
            	//jquery v3.2.1
	      		  var _con = $(ChooseCity.clickArea.join(','));   // 设置目标区域
	    		  if(!_con.is(e.target) && _con.has(e.target).length === 0){
	    			  ChooseCity.controls.container.hide();
	    			  ChooseCity.onForthwithDisplay2();
	    		  }
            });

            ChooseCity.isInit = true;
            ChooseCity.loadMosts();
            ChooseCity.showMosts();
            ChooseCity.loadProvinces();
        }
    }

    ChooseCity.setGeo = function (target, geo) {
        if (!geo){
        	$(target).val($(target).data("options").tips);
        	return;
        } 
        if (typeof (target) === "string") {
            target = $(target);
        }
        var _data_op = target.data("options");

        var g = new ChooseCity.GeoEntity();
        g.provinceID = geo.provinceID;
        g.provinceName = geo.provinceName;
        g.cityID = geo.cityID;
        g.cityName = geo.cityName;
        g.districtID = geo.districtID;
        g.districtName = geo.districtName;
        g.streetID = -geo.streetID;
        g.streetName = geo.streetName;

        $(target).data("geo", g).val(g.toString()).addClass(_data_op.valueClass);
    }

    ChooseCity.wrapper = function ($obj, options) {

        switch (options.mode) {
            case "cityMode":

                //构造样式、属性、数据、包裹
                $obj.attr({
                    "title": options.tips
                });
                //if (ChooseCity.checkEntity(options.initGeo)) {
                $(options.txtControl).text(options.initGeo.cityName);
                //}
                $obj.data("geo", new ChooseCity.GeoEntity(options.initGeo));
                $obj.data("options", options);
                break;
            case "districtcheckbox": // alter by wumz 
            	 $obj.attr({
                       "readonly": true
                 });
            	 var _isSet = ChooseCity.checkEntity(options.initGeo);
                 var _val = _isSet ? new ChooseCity.GeoEntity(options.initGeo).toString() : options.tips;
                 var _class = _isSet ? options.valueClass : options.tipsClass;
                 $obj.val(_val);
                 $obj.addClass(_class);
                 $obj.data("geo", new ChooseCity.GeoEntity(options.initGeo));
                 $obj.data("options", options);
                break;
            default:
                //图标对象
                var ojbIcon = $("<a href=\"javascript:void(0);\" class=\"icon_list\"></a>"),
                    objWraper = $("<div class=\"sel\"></div>");


                ojbIcon.bind(options.event, function () {
                    $obj.trigger(options.event);
                });

                //构造样式、属性、数据、包裹
                $obj.addClass("inp_regis inp_grey tipInput")
                $obj.attr({
                    "readonly": true,
                    "tipTextObjectList": options.tips
                });

                var _isSet = ChooseCity.checkEntity(options.initGeo);
                var _val = _isSet ? new ChooseCity.GeoEntity(options.initGeo).toString() : options.tips;
                var _class = _isSet ? options.valueClass : options.tipsClass;
                $obj.val(_val);
                $obj.addClass(_class);
                $obj.data("geo", new ChooseCity.GeoEntity(options.initGeo));
                $obj.data("options", options);
                $obj.wrap(objWraper).before(ojbIcon);
        }
    }

    //创建控件全局变量
    window.ChooseCity = ChooseCity;

    //插件开始
    $.fn.chooseCity = function (options) {

        //对象缓存
    	//ChooseCity.clickArea.push(this.selector);
        ChooseCity.clickArea.push('#'+$(this).attr('id'));

        //初始化浮窗
        ChooseCity.init(options);

        //插件默认设置
        var defaults = {
            event: "click", //响应事件
            tips: "请选择地区", //默认提示
            tipsClass: "",  //提示样式
            valueClass: "", //值样式
            callback: function () { },
            initGeo: null,
            mode: "default",
            txtControl: null,
            offset: {
            	top: 0,
            	left: 0
            }
        };
        //扩展插件设置
        options = $.extend(defaults, options);
       
        return this.each(function (index, currentObj) {

            currentObj = $(currentObj);

            //输入框绑定事件
            currentObj.bind(options.event, function () {

                //选择浮窗的指向对象设置到当前元素
                ChooseCity.target = $(this);

                //浮窗定位
                var lt = currentObj.offset().left,
                    tt = currentObj.offset().top,
                    //h = currentObj.height();
                    h = currentObj.closest(".sel").height();

                ChooseCity.controls.container.css({ left: lt + options.offset.left, top: tt + h + options.offset.top}).show();

                ChooseCity.controls.navs.filter("#districtList_head").css({ "display": (options.mode.toLowerCase() === "citymode" ) ? "none" : "block" }); 

                //默认常用
                ChooseCity.showMosts();
                ChooseCity.isClick = true;
            });
          
            ChooseCity.wrapper(currentObj, options);
            if( options.mode.toLowerCase() === "districtcheckbox" ){
            	ChooseCity.target = currentObj;
            	ChooseCity.getAddressFullName(currentObj,options);
            }
        });

    }

})(jQuery);