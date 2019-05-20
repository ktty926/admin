; (function () {
    var _config = {};

    $.ajax({
        url:"../findArea",
        type:"post",
        async:false,
        datatype:"json",
        success:function(data){
            _config.geo = data;
        }
    });

    window.Config = _config;

})(window);