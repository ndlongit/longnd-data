/*
 * Xenlon-UIの入力補助機能。
 * 
 *     名前空間は『xj_input』です。
 */
var xj_input = {
    /**
     * 数値を3桁カンマ区切りに編集します。
     *
     * @param value 編集対象の数値。
     * @return ３桁カンマ区切りに編集された数値文字列。
     */
    fmtCol3 : function(value) {
        var result = new String(value).replace(/,/g, "");
        while (result != (result = result.replace(/^(-?\d+)(\d{3})/, "$1,$2")));
        return result;
    },

    /**
     * 3桁カンマ区切りに編集された数値を、プレーンな整数に戻します。
     *
     * @param value 編集対象の数値。
     * @return ３桁カンマ区切りが解除された数値文字列。
     */
    clearFmtCol3 : function(value) {
        return value.replace(/,/g, "");
    },

    /**
     * class属性に「x_col3」が指定されたテキストボックスに、3桁カンマ区切りモードを設定します。
     */
    bindCol3 : function() {
        $(":text.x_col3").each(function() {
            // ３桁カンマ区切りにする
            $(this).val(xj_input.fmtCol3($(this).val()));

            // changeイベント、blurイベント発生時には、３桁カンマ区切りを適用
            $(this).bind("change blur", function() {
                $(this).val(xj_input.fmtCol3($(this).val()));
            });

            // focusイベント、submitイベント発生時には、３桁カンマ区切りを除去
            $(this).bind("focus", function() {
                $(this).val(xj_input.clearFmtCol3($(this).val()));
            });
            var item = $(this);
            $(this).closest("form").bind("submit", function() {
                item.val(xj_input.clearFmtCol3(item.val()));
            });
        });
        $("span.x_col3").each(function() {
            $(this).text(xj_input.fmtCol3($(this).text()));
        });
    },

    /**
     * 文字列を日付形式（YYYY/MM/DD）形式に編集します。
     * 以下の変換編集が可能です。
     * <ul>
     * <li>YYYYMMDD（8桁） → YYYY/MM/DD</li>
     * <li>YY/MM/DD（西暦2桁） → YYYY/MM/DD</li>
     * <li>YYYY/M/DD（月1桁） → YYYY/MM/DD</li>
     * <li>YYYY/MM/D（日1桁） → YYYY/MM/DD</li>
     * </ul>
     *
     * @param value 編集対象の文字列。
     * @return 「YYYY/MM/DD」形式に編集された文字列。
     */
    fmtDate : function(value) {
        if (value == null || value.length == 0) {
            return "";
        }

        var yyyy = null;
        var mm = null;
        var dd = null;

        // 数字8桁の場合
        if (value.match(/[0-9]{8}/)) {
            yyyy = value.substring(0, 4);
            mm = value.substring(4, 6);
            dd = value.substring(6);
            return yyyy + "/" + mm + "/" + dd;
        }

        // 既に「yyyy/mm/dd」形式なら何もしない
        if (value.match(/[0-9]{4}\/[0-9]{2}\/[0-9]{2}/)) {
            return value;
        }

        // yyyy/mm/dd で形式でなければ無編集
        if (!value.match(/[0-9]{2,4}\/[0-9]{1,2}\/[0-9]{1,2}/)) {
            return value;
        }

        var array = value.split("/");
        yyyy = array[0];
        mm = array[1];
        dd = array[2];

        // 年が3桁なら何もしない
        if (yyyy.length == 3) {
            return value;
        }
        else if (yyyy.length == 2) {
            var yy = new String(new Date().getYear());
            if (yy.length != 4) {
                yy = new String(new Date().getFullYear());
            }
            yy = yy.substring(0, 2);
            yyyy = yy + yyyy;
        }

        // 月
        if (mm.length == 1) {
            mm = "0" + mm;
        }

        // 日
        if (dd.length == 1) {
            dd = "0" + dd;
        }

        return yyyy + "/" + mm + "/" + dd;
    },

    /**
     * class属性に「x_date」が指定されたテキストボックスに、日付入力支援モードを設定します。
     */
    bindDate : function() {
        $(":text.x_date").each(function() {
            // 日付フォーマットする
            $(this).val(xj_input.fmtDate($(this).val()));

            // changeイベント、blurイベント、submitイベント発生時にフォーマット
            $(this).bind("change blur", function() {
                $(this).val(xj_input.fmtDate($(this).val()));
            });
            var item = $(this);
            $(this).closest("form").bind("submit", function() {
                item.val(xj_input.fmtDate(item.val()));
            });
        });
    },

    /**
     * class属性に「x_calendar」・「x_calendarImg」が指定されたテキストボックスにカレンダーUIを設定します。
     */
    bindCalendar : function() {
        $(":text.x_calendar").datepicker("option", xj_conf.calendarUI.optionForCalendar);
        $(":text.x_calendar").datepicker();

        $(":text.x_calendarImg").datepicker("option", xj_conf.calendarUI.optionForCalendarImg);
        $(":text.x_calendarImg").datepicker();
    },

    /**
     * カレンダーUIをローカライズします。
     */
    localizeCalendar : function() {
        var locale = xj_conf.calendarUI.localeMapping[xj_conf.currentLocale];
        if (xj_core.isNone(locale)) {
            locale = xj_conf.currentLocale;
        }
        $.datepicker.setDefaults($.datepicker.regional[locale]);
    },

    /**
     * class属性に「x_autoComplete」が指定されたテキストボックスに、オートコンプリート機能を設定します。<br/>
     * タグには必ずid属性が必要です。<br/>
     * 
     * デフォルトのアクションパスは「現在アクションパスルート + "do" + [付与したID]」がリクエストされます。<br/>
     * このため、サーバー側のAjax処理を行うメソッドの名前は「do[付与したID]」である必要があります。<br/>
     * 
     * デフォルトのアクションパラメーターは{name属性の値 : 入力値}でリクエストされます。<br/>
     * この場合、必ずname属性が必要です。<br/>
     * 以下の(2)の方式でアクションパラメーターを送信する場合は、name属性の指定は不要です。<br/>
     *
     *     (1) アクションパスのカスタマイズを行いたい場合は、以下の規則の関数を定義してください。<br/>
     *             get[付与したID]ActionPath() : アクションパスを文字列として返す関数。<br/>
     *                 ※自画面とは別のアクションに対象の処理が実装されているケースで使用します。<br/>
     *     (2) アクションパラメーターのカスタマイズを行いたい場合は、以下の規則の関数を定義してください。<br/>
     *             get[付与したID]ActionParam() : アクションパラメーターとなる連想配列を返す関数。<br/>
     *                 ※対象のテキストボックスの入力値以外のパラメーター送信が必要なケースで使用します。<br/>
     */
    bindAutoComplete : function() {
        $(":text.x_autoComplete").each(function() {
            // id属性は必須
            var targetId = $(this).attr("id");
            if (targetId == undefined) {
                return;
            }

            var editTargetId = targetId.substring(0, 1).toUpperCase() + targetId.substring(1);
            var jqTarget = $("#" + targetId);

            // 関数「get{ID属性}ActionPath()」の取得
            var getActionPath = xj_core.getFunction("get" + editTargetId + "ActionPath");

            // アクションパスの作成 or 取得
            var actionPath = null;
            if (getActionPath == null) {
                actionPath = xj_conf.currentActionPathRoot + "do" + editTargetId + "/";
            }
            else {
                actionPath = getActionPath();
            }

            // 関数「get{ID属性}ActionParam()」の取得
            var getActionParam = xj_core.getFunction("get" + editTargetId + "ActionParam");

            // オートコンプリートの設定
            jqTarget.autocomplete({
                source : [],
                create : function(event, ui) {
                // オートコンプリートのCSSスタイル初期設定
                $(".ui-autocomplete").css(xj_conf.autoCompleteCSS);
                    if (xj_core.isIE()) {
                        var maxHeight = $(".ui-autocomplete").css("maxHeight");
                        var paddingRight = $(".ui-autocomplete").css("paddingRight");
                        if (maxHeight != undefined) {
                            $(".ui-autocomplete").css("height", maxHeight);
                        }
                        if (paddingRight != undefined) {
                            $(".ui-autocomplete").css("paddingRight", "0px");
                        }
                    }
                },
                search: function() {
                    // 送信するアクションパラメーター（連想配列）の作成 or 取得
                    var actionParam = {};
                    if (getActionParam == null) {
                        var targetName = jqTarget.attr("name");
                        actionParam[targetName] = jqTarget.val();
                    }
                    else {
                        actionParam = getActionParam();
                    }

                    // Ajax通信を発動し、結果のJSON文字列を受け取る
                    $.getJSON(actionPath, actionParam, function (json) {
                        jqTarget.autocomplete({
                            source : json
                        });
                    });
                }
            });
        });
    },

    /**
     * ユーザー操作によって発生するさまざまなイベントに対する無効化定義をバインドします。<br>
     * xj_conf.disableOperation.eventHandlingMapに「イベント」と「ハンドリング関数」のマッピングを定義してください。<br>
     */
    bindDisableOperation : function() {
        for (handleEvent in xj_conf.disableOperation.eventHandlingMap) {
            var handler = xj_conf.disableOperation.eventHandlingMap[handleEvent];
            $(document).on(handleEvent, function(event) {
                if (handler(event)) {
                    event.preventDefault();
                }
            });
        }
    }
};

$(function() {
    // ３桁カンマ区切りのバインド
    xj_input.bindCol3();

    // 日付文字列のバインド
    xj_input.bindDate();

    // カレンダー入力のバインド
    xj_input.bindCalendar();

    // オートコンプリートのバインド
    xj_input.bindAutoComplete();

    // ユーザー操作無効化のバインド
    xj_input.bindDisableOperation();
});
