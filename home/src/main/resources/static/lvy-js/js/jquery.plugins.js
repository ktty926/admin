(function() {
    $.fn.tabs = function(ct, obj) {
        var element = $(this);
        var control = $(ct);
        var o = obj || {};
        var current = o.current || 'tab-active';
        var index = 0;
        element.delegate("li", "click", function() {
            var tabName = $(this).attr("data-tab");
            index = $(this).index();
            if (!tabName) {
                return;
            }
            //点击li的时候触发change.tabs自定义事件
            element.trigger("change.tabs", tabName);

        });

        $(window).bind('hashchange', function() { //alert(0);return;
            var tabName = window.location.hash.slice(1);
            var status = false;
            element.find('li').each(function() {
                if ($(this).attr('data-tab') == tabName) {
                    status = true;
                }
            });
            if (!status) {
                return;
            }
            element.trigger("change.tabs", tabName);
        });
        /*
        $(window).bind('hashchange',function(){
            var tabName = window.location.hash.slice(1);
            element.trigger("change.tabs",tabName);
        });*/
        //给element绑定一个change.tabs自定义事件
        element.bind("change.tabs", function(e, tabName) {
            element.find("li").removeClass(current);
            element.find(">[data-tab='" + tabName + "']").addClass(current);
        });
        element.bind("change.tabs", function(e, tabName) {
            control.find(">[data-tab]").removeClass(current);
            var content = control.find(">[data-tab='" + tabName + "']").addClass(current);
            if (typeof o.onComplete === 'function' && content.length !== 0) {

                o.onComplete.call($(this), index, content);
            }
        });

        if (o.hash) {
            if (o.hash === true) {
                var tabName = window.location.hash.slice(1);
                element.trigger("change.tabs", tabName);
            } else {
                element.trigger("change.tabs", o.hash);
            }

            return;
        }
        //激活第一个选项卡
        //var firstName = typeof o.index === 'number' ? (index = o.index, element.find("li:eq(" + o.index + ")").attr("data-tab")) : element.find("li:first").attr("data-tab");
        //element.trigger("change.tabs", firstName);


        return this;
    };

    $.fn.placeholder = function(options) {
        var speed = 400;
        return this.each(function(index, el) {
            var that = this,
                div = $(that).children('div.input-tip,div.placeholder'),
                input = $(that).children('input[placeholder]'),
                inputHeight = input.outerHeight(),
                val = $.trim(input.val()),
                opts = $.fn.extend({
                    timeout: 1000
                }, options);
            // if (!($(that).attr('placeholder') === 'true')) {
            //  return;
            // }
            div.css({
                height: inputHeight,
                lineHeight: inputHeight + 'px',
                left: input.css('paddingLeft')
            });



            function _(val) {
                if (val !== '') {
                    div.fadeOut(speed);
                } else {
                    div.fadeIn(speed);
                    input.val('');
                }
            }

            function _bind() {
                var val = $.trim(input.val());
                _(val);
            }
            input.bind('keydown', _bind)
                .bind('blur', _bind)
                .bind('paste', function() {
                    setTimeout(_bind, 100);
                })
                .bind('keyup', _bind)
                .bind('change', _bind)
                .bind('click', _bind)
                .bind('change', _bind);
            setTimeout(function() {
                var val = $.trim(input.val());
                _(val);
            }, opts.timeout);
            $(window).bind('load', _bind);
        });
    };
})(jQuery)