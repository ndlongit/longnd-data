var xj_conf = {
    cssDirectory : "stylesheets/",
    jsDirectory : "javascripts/",
    currentActionPathRoot : null,
    currentLocale : "ja_JP",

    calendarUI : {
        optionForCalendar : {
            dateFormat : "yy/mm/dd",
            showButtonPanel : true
        },
        optionForCalendarImg : {
            dateFormat : "yy/mm/dd",
            showOn : "button",
            buttonImage : "xenlon-ui/img/calendar.png",
            buttonText : null,
            buttonImageOnly : true,
            showButtonPanel : true
        },
        localeMapping: {
            en_US : "en-GB",
            ja_JP : "ja",
            zh_CN : "zh-CN",
            zh_TW : "zh-TW",
            zh_HK : "zh-HK"
        }
    },

    disableOperation : {
        eventHandlingMap : {
            keydown : function(event) {
                switch (event.keyCode) {
                    case 8: // [Backspace]
                        var type = event.target.type;
                        return (xj_core.isNone(type) || !type.match(/text|textarea|password/i) || (event.target.readOnly));
                    case 13: // [Enter]
                        var type = event.target.type;
                        return (xj_core.isNone(type) || !type.match(/textarea/i) || (event.target.readOnly));
                    default:
                        return false;
                }
            }
        }
    },

    revokeInputConfirmMessage : null,
    closeWindowConfirmMessage : null,

    blockUI : {
        effectTransition : false,
        effectModalSubwindow : true,
        image : "xenlon-ui/img/progress.gif",
        transitionMessage : "処理中です・・・",
        modalSubWindowMessage : "サブウィンドウがオープン中です・・・",
        backGroundColor : "#555",
        opacity : "0.7",
        cursor : "default",
        fontColor : "#FFF",
        fontSize : "16px",
        tmpBlockTime : 3000
    },

    editMonitoring : {
        _edit : true,
        $targetOnWatch : $(":input:enabled:not(:hidden,:button,:submit,:image,:reset,.x_nowatch),:input[class*=x_watch]"),
        $targetOnConfirm : $(":submit[name='fwBack']")
    },

    tooltip : {
        settings : {
            ".tooltip-bottom-left[title]" : {
                position: {
                    target: "mouse",
                    at: "bottom left",
                    my: "top right",
                    adjust: {x: -5, y: 15}
                }
            },
            "[title]" : {
                position: {
                    target: "mouse",
                    at: "top left",
                    my: "bottom left",
                    adjust: {x: 0, y: -10}
                }
            }
        }
    },

    baseNameByUA : [
        // IE6 [/^.*compatible;.*MSIE 6.*$/i, "common-ie6"],
        // IE
        [/^.*compatible;.*MSIE .*$/i, "common-ie"]
        // FireFox [/^.*Firefox.*$/i, "common-ff"],
        // Opera [/^.*Opera.*$/i, "common-op"],
        // Chrome [/^.*Chrome.*$/i, "common-ch"],
        // Safari [/^((?!Chrome).)*Safari.*$/i, "common-sf"]
    ],

    mfup : {
        max : 0,
        accept : '',
        file : '$file',
        remove : '<img src="xenlon-ui/img/bin.gif" height="16" width="16" alt="削除"/>',
        denied : '拡張子が「$ext」のファイルは選択出来ません。',
        selected : '選択ファイル: $file',
        duplicate : '',
        onFileRemove : function(element, value, master_element) {},
        afterFileRemove : function(element, value, master_element) {},
        onFileAppend : function(element, value, master_element) {},
        afterFileAppend : function(element, value, master_element) {},
        onFileSelect : function(element, value, master_element) {},
        afterFileSelect : function(element, value, master_element) {}
    },

    autoCompleteCSS : {
        maxHeight : "200px",
        overflowY : "auto",
        overflowX : "hidden",
        paddingRight : "20px"
    }
};
