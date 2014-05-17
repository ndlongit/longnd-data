<%--
    Xenlon-UI が依存する外部ファイルと初期化処理を記述するJSPです。

    【動作環境・前提条件】

        ＜CSSファイル＞
            ・reset.css が必要です。
            ・jquery.ui.css が必要です。
            ・jquery.treeview.css が必要です。
            ・jquery.qtip.css が必要です。
            ・xenlon-ui.css が必要です。

        ＜JSファイル＞
            ・json2.js が必要です。
            ・jquery.js が必要です。
            ・jquery.ui.js が必要です。
            ・jquery.timer.js が必要です。
            ・jquery.cookie.js が必要です。
            ・jquery.treeview.js が必要です。
            ・jquery.blockUI.js が必要です。
            ・jquery.MultiFile.js が必要です。
            ・jquery.qtip.js が必要です。
            ・xenlon-ui-core.js が必要です。
            ・xenlon-ui-input.js が必要です。
            ・xenlon-ui-request.js が必要です。
            ・xenlon-ui-ui.js が必要です。
            ・xenlon-ui-window.js が必要です。

        ＜ライブラリー・リソース情報＞
            ・jsonパーサー（古いIE用）
                ・json2.js
            ・jQuery本体 : v1.7.2 (2012.03.21)
                ・jquery.js
            ・jQuery UI : v1.8.10 (2011.02.24) [jQuery 1.3.2+]
                ・jquery.ui.js
                ・jquery.ui.css
                ・/css/images/ui
            ・jQuery Timer : v0.1.0 (2007.09.21) [不明]
                ・jquery.timer.js
            ・jQuery Treeview : v1.4.1 (2010.10.23) [jQuery 1.2+]
                ・jquery.treeview.js
                ・jquery.cookie.js
                ・jquery.treeview.css
                ・/css/images/treeview
            ・jQuery BlockUI : v2.42 (2012.05.10) [jQuery 1.2.3+]
                ・jquery.blockUI.js
            ・jQuery MultiFile : v1.47 (2012.02.08) [jQuery 1.7+]
                ・jquery.MultiFile.js
            ・qTip2 : v2.0.0 pre (2012.11.14) [jQuery 1.4.4+]
                ・jquery.qtip.js
                ・jquery.qtip.css
--%>
<link type="text/css" rel="stylesheet" href="/xenlon-ui/css/reset.css" />
<link type="text/css" rel="stylesheet" href="/xenlon-ui/css/jquery.ui.css" />
<link type="text/css" rel="stylesheet" href="/xenlon-ui/css/jquery.treeview.css" />
<link type="text/css" rel="stylesheet" href="/xenlon-ui/css/jquery.qtip.css" />
<link type="text/css" rel="stylesheet" href="/xenlon-ui/css/xenlon-ui.css" />
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/json2.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.ui.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.timer.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.cookie.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.treeview.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.blockUI.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.MultiFile.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/jquery.qtip.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/xenlon-ui-core.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/xenlon-ui-input.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/xenlon-ui-request.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/xenlon-ui-ui.js"></script>
<script type="text/javascript" charset="UTF-8" src="/xenlon-ui/js/xenlon-ui-window.js"></script>
<script type="text/javascript">
<!--
<%--
    Xenlon-UIの実行設定情報。
 
        名前空間は『xj_conf』です。
--%>
var xj_conf = {
    <%--
        CSS（StyleSheet）ファイルを配置するトップディレクトリを指定してください。
        画面に応じてディレクトリへのパスが異なるため、＄{f:url()}を使用して、パスを相対的に取得して下さい。
    --%>
    cssDirectory : "/css/",

    <%--
        JS（JavaScript）ファイルを配置するトップディレクトリを指定してください。
        画面に応じてディレクトリへのパスが異なるため、＄{f:url()}を使用して、パスを相対的に取得して下さい。
    --%>
    jsDirectory : "/js/",

    <%--
        現在のアクションパスルートです。
    --%>
    currentActionPathRoot : "${f:url('')}",

    <%--
        現在のロケールです。
    --%>
    currentLocale : "<bean:write name='org.apache.struts.action.LOCALE' ignore='true' />",

    <%--
        カレンダーUI
            名前空間は『calendarUI』です。

            optionForCalendar :
                標準カレンダーUIのオプションをマップで指定します。
                ※ オプションの詳細はjQuery UI Datepickerのリファレンスを参照してください。
            optionForCalendarImg :
                ボタン付きカレンダーUIのオプションをマップで指定します。
                ※ オプションの詳細はjQuery UI Datepickerのリファレンスを参照してください。
            localeMapping :
                「サーバーロケール」と「jQuery UI Datepicker i18N」とのマッピングを指定します。
                ※ 多言語リソースの提供状況の詳細はjQuery UI Datepickerのリファレンスを参照してください。
    --%>
    calendarUI : {
        optionForCalendar : {
            dateFormat : "yy/mm/dd",
            showButtonPanel : true
        },
        optionForCalendarImg : {
            dateFormat : "yy/mm/dd",
            showOn : "button",
            buttonImage : "/xenlon-ui/img/calendar.png",
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

    <%--
        ユーザー操作無効化
            名前空間は『disableOperation』です。

            eventHandlingMap :
                ［イベント : ハンドリング関数］をマップで定義します。
                ハンドリング関数の引数には、JavaScriptのイベントオブジェクトが設定されます。
                イベントを無効化する場合はハンドリング関数でtrueを、そうでない場合はfalseを戻り値で返却してください。
                ※ 定義可能なイベントについてはjQueryのリファレンスを参照してください。
    --%>
    disableOperation : {
        eventHandlingMap : {
            keydown : function(event) {
                switch (event.keyCode) {
                    case 8: // [Backspace]
                        var type = event.target.type;
                        return (xj_core.isNone(type) || type.match(/textarea|text|password|number|time|tel|url|email|search/i) != type || (event.target.readOnly));
                    case 13: // [Enter]
                        var type = event.target.type;
                        return (xj_core.isNone(type) || type.match(/textarea/i) != type || (event.target.readOnly));
                    default:
                        return false;
                }
            }
        }
    },

    <%--
        入力破棄確認ダイアログに表示するメッセージを指定します。
        多言語対応する場合は、JSP内で<bean：message />を使用して、メッセージリソース内から文字列を取得して下さい。
    --%>
    revokeInputConfirmMessage : "<bean:message key='xui.x_revokeInputMessage' />",

    <%--
        ウィンドウの閉じる確認ダイアログに表示するメッセージを指定します。
        多言語対応する場合は、JSP内で<bean：message />を使用して、メッセージリソース内から文字列を取得して下さい。
    --%>
    closeWindowConfirmMessage : "<bean:message key='xui.x_closeWindowMessage' />",

    <%--
        ブロックUI設定
            名前空間は『blockUI』です。

            effectTransition :
                画面遷移時にブロックUIを使用するかどうかを指定します。
                「true : 使用する、false : 使用しない」のいずれかで指定して下さい。
            effectModalSubwindow :
                サブウィンドウ起動時に、親ウィンドウのブロックを行うかどうかを指定します。
                「true : 使用する、false : 使用しない」のいずれかで指定して下さい。
            transitionMessage :
                画面遷移時のブロック中に表示するメッセージを指定します。
                多言語対応する場合は、JSP内で<bean：message />を使用して、メッセージリソース内から文字列を取得して下さい。
            modalSubWindowMessage :
                サブウィンドウ起動時に、親ウィンドウのブロック中に表示するメッセージを指定します。
                多言語対応する場合は、JSP内で<bean：message />を使用して、メッセージリソース内から文字列を取得して下さい。
            image :
                ブロック中に表示したいエフェクト画像を指定します。
                参照したい画像ファイルパスへの、各画面からの相対パスを指定してください。
                画面に応じて画像ファイルへのパスが異なるため、JSP内で＄{f:url()}を使用して、パスを相対的に取得して下さい。
            backGroundColor :
                画面にかぶせるブロックレイヤーのベース色を指定します。
                RGBの16進数、または色名（英語）で指定して下さい。
            opacity :
                画面にかぶせるブロックレイヤーの透明度を指定します。
                0.0（完全に透明）-> 1.0（完全に不透明）の範囲で指定して下さい。
            cursor :
                ブロック中のマウスカーソルの種類を指定します。
                "default" or "wait" で指定して下さい。
            fontColor :
                ブロック中に表示するメッセージの色を指定します。
                RGBの16進数、または色名（英語）で指定して下さい。
            fontSize :
                ブロック中に表示するメッセージのフォントサイズを指定します。
            tmpBlockTime :
                時間指定ブロックを使用する場合のブロック時間を指定します。
                ミリ秒（msec）で指定して下さい。
    --%>
    blockUI : {
        effectTransition : false,
        effectModalSubwindow : true,
        transitionMessage : "<bean:message key='xui.x_blockMessage' />",
        modalSubWindowMessage : "<bean:message key='xui.x_subWindowBlockMessage' />",
        image : "/xenlon-ui/img/progress.gif",
        backGroundColor : "#555",
        opacity : "0.7",
        cursor : "default",
        fontColor : "#FFF",
        fontSize : "16px",
        tmpBlockTime : 3000
    },

    <%--
        入力内容変更監視設定
            名前空間は『editMonitoring』です。

            _edit :
                入力値の変更有無を保持します（※内部変数のため操作禁止）。
            $targetOnWatch :
                入力内容の変更監視対象をjQueryオブジェクトで指定します。
                    [デフォルト] : 『x_nowatch』がマーキングされていない非disabledのテキストボックス、テキストエリア、
                        プルダウン、ラジオボタン、チェックボックス、パスワード、ファイルが監視対象。
                        また『x_watch』が明示的にマーキングされた入力項目も監視対象。
            $targetOnConfirm :
                編集内容の破棄確認を行う対象をjQueryオブジェクトで指定します。
                    [デフォルト] : nameが「fwBack」のサブミットボタンのみ。
    --%>
    editMonitoring : {
        _edit : ("${x_valueChanged}" == "true") ? true : false,
        $targetOnWatch : $(":input:enabled:not(:hidden,:button,:submit,:image,:reset,.x_nowatch),:input[class*=x_watch]"),
        $targetOnConfirm : $(":submit[name='fwBack']")
    },

    <%--
        ツールチップ設定
            名前空間は『tooltip』です。

            settings :
                ［jQueryセレクタ : qTip2オプション］をマップで定義します。
                任意のDOM要素とqTip設定をバインドします。
                ※ qTip2オプション詳細はqTip2のリファレンスを参照してください。
    --%>
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

    <%--
        User-Agent（ブラウザ種別とバージョンを識別するためにブラウザ自身が持つ文字列）を判定するための正規表現と、それに対応するベース名を列挙定義します。
        先に定義されているものから順にマッチングを行っていき、マッチした場合にはマッチング検査はそこで止まります。
        このため、特にクロスブラウザ対応で正確な判定を行いたい場合は、マッチング条件がゆるいものほど後方に宣言するようにしてください。
        
        ※ 正規表現のマッチング文字列は、ダブルクォーテーション等で囲まないように注意してください。
    --%>
    baseNameByUA : [
        // IE6 [/^.*compatible;.*MSIE 6.*$/i, "common-ie6"],
        // IE
        [/^.*compatible;.*MSIE .*$/i, "common-ie"]
        // FireFox [/^.*Firefox.*$/i, "common-ff"],
        // Opera [/^.*Opera.*$/i, "common-op"],
        // Chrome [/^.*Chrome.*$/i, "common-ch"],
        // Safari [/^((?!Chrome).)*Safari.*$/i, "common-sf"]
    ],

    <%--
        マルチファイルアップロード設定
            名前空間は『mfup』です。

            max :
                選択出来るファイルの数を指定します。
            accept :
                選択可能なファイルの拡張子を指定します。
            file :
                選択したファイルをコンテナに表示させる際のレイアウトをHTMLで指定します。
            remove : 
                選択したファイルを取り消す際の表示レイアウトをHTMLで指定してください。
                指定しない場合は、「×」リンクがデフォルトで表示されます。
            denied :
                accept属性で指定した拡張子以外のファイルを選択した際に表示されるエラーメッセージを指定します。
            selected :
                選択されたファイルにマウスカーソルが乗った際に表示されるツールチップの文字列を指定します。
            duplicate :
                同一ファイル（ファイル名で判断）を選択した際に表示されるエラーメッセージを指定します。
                本属性を設定すると同一ファイル名を選択することが出来ません。
                異なるディレクトリに格納されていてもファイル名が同一の場合、エラーとみなされるため注意してください。
                このチェックを無効化したい場合は「''」を設定して下さい。
            onFileRemove :
                選択したファイルを取り消した際に実行されるコールバック関数を定義します。
            afterFileRemove :
                選択したファイルを取り消した後に実行されるコールバック関数を定義します。
            onFileAppend :
                選択したファイルがコンテナに追記される時に実行されるコールバック関数を定義します。
            afterFileAppend :
                選択したファイルがコンテナに追記される後に実行されるコールバック関数を定義します。
            onFileSelect :
                ファイルを選択した時に実行されるコールバック関数を定義します。
            afterFileSelect :
                ファイルを選択した後に実行されるコールバック関数を定義します。
    --%>
    mfup : {
        max : 0,
        accept : '',
        file : '$file',
        remove : '<img src="/xenlon-ui/img/bin.gif" height="16" width="16" alt="削除"/>',
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

    <%--
        オートコンプリートのスタイル定義を行います。
        JavaScriptの連想配列キーに「-」は使用できないため、キャメルタイプで属性定義を行います。
            例 : 「max-height」→「maxHeight」
    --%>
    autoCompleteCSS : {
        maxHeight : "200px",
        overflowY : "auto",
        overflowX : "hidden",
        paddingRight : "20px"
    }
};

$(function() {
    // 画像をあらかじめ読み込んでおく処理
    if (xj_conf.calendarImage != null) {
        $("<img/>").attr("src", xj_conf.calendarImage);
    }
    if (xj_conf.blockUI.image != null) {
        $("<img/>").attr("src", xj_conf.blockUI.image);
    }
});
//-->
</script>
