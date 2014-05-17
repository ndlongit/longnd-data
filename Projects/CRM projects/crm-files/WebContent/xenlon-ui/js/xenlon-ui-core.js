/*
 * Xenlon-UIのコアAPI。
 * 
 *     名前空間は『xj_core』です。
 */
var xj_core = {
    /**
     * ブラウザがIEかどうかを判定します。
     * 
     * @return true : IE、false : IEではない。
     */
    isIE : function() {
        return (navigator.userAgent.match(/MSIE/i) != null);
    },

    /**
     * ブラウザがChromeかどうかを判定します。
     * 
     * @return true : Chrome、false : Chromeではない。
     */
    isChrome : function() {
        return (navigator.userAgent.match(/Chrome/i) != null);
    },

    /**
     * ブラウザがOperaかどうかを判定します。
     * 
     * @return true : Opera、false : Operaではない。
     */
    isOpera : function() {
        return (navigator.userAgent.match(/Opera/i) != null);
    },

    /**
     * ブラウザがFirefoxかどうかを判定します。
     * 
     * @return true : Firefox、false : Firefoxではない。
     */
    isFirefox : function() {
        return (navigator.userAgent.match(/Firefox/i) != null);
    },

    /**
     * ブラウザがSafariかどうかを判定します。
     * 
     * @return true : Safari、false : Safariではない。
     */
    isSafari : function() {
        return (navigator.userAgent.match(/Safari/i) != null
                && navigator.userAgent.match(/Chrome/i) == null);
    },

    /**
     * 表示中のウィンドウの左上端のX軸位置を取得します。
     * 
     * @return X軸位置（ピクセル）。
     */
    getWindowPositionX : function() {
        if (xj_core.isIE() || xj_core.isChrome() || xj_core.isOpera() || xj_core.isSafari()) {
            return window.screenLeft;
        }
        else if (xj_core.isFirefox()) {
            return window.screenX;
        }
        else {
            return 0;
        }
    },

    /**
     * 表示中のウィンドウの左上端のY軸位置を取得します。
     * 
     * @return Y軸位置（ピクセル）。
     */
    getWindowPositionY : function() {
        if (xj_core.isIE() || xj_core.isChrome() || xj_core.isOpera() || xj_core.isSafari()) {
            return window.screenTop;
        }
        else if (xj_core.isFirefox()) {
            return window.screenY;
        }
        else {
            return 0;
        }
    },

    /**
     * ウィンドウ内の使用可能な領域部分の幅（ピクセル値）を取得します。
     * ブラウザにより定義（考え方）が異なります。
     * 
     * @return ウィンドウ内の使用可能な領域部分の幅（ピクセル値）。
     */
    getWindowInnerWidth : function() {
        if (xj_core.isIE()) {
            if (document.documentElement && document.documentElement.clientWidth != 0) {
                return document.documentElement.clientWidth;
            }
            else if (document.body) {
                return document.body.clientWidth;
            }
            else {
                // ここには来ない
                return 0;
            }
        }
        else if (xj_core.isChrome() || xj_core.isOpera() || xj_core.isFirefox() || xj_core.isSafari()) {
            return window.innerWidth;
        }
        else {
            return 0;
        }
    },

    /**
     * ウィンドウ内の使用可能な領域部分の高さ（ピクセル値）を取得します。
     * ブラウザにより定義（考え方）が異なります。
     * 
     * @return ウィンドウ内の使用可能な領域部分の高さ（ピクセル値）。
     */
    getWindowInnerHeight : function() {
        if (xj_core.isIE()) {
            if (document.documentElement && document.documentElement.clientHeight != 0) {
                return document.documentElement.clientHeight;
            }
            else if (document.body) {
                return document.body.clientHeight;
            }
            else {
                // ここには来ない
                return 0;
            }
        }
        else if (xj_core.isChrome() || xj_core.isOpera() || xj_core.isFirefox() || xj_core.isSafari()) {
            return window.innerHeight;
        }
        else {
            return 0;
        }
    },

    /**
     * ブラウザの持つUser-Agent情報と、ユーザー定義された『xj_conf.baseNameByUA』を用い、
     * マッチング条件からブラウザ識別用のベース名を取得します。
     * 
     * @return ユーザー定義のブラウザ識別名。
     */
    getBaseNameByUA : function() {
        for (var i = 0;  i < xj_conf.baseNameByUA.length ; i++) {
            if (navigator.userAgent.match(xj_conf.baseNameByUA[i][0])) {
                return xj_conf.baseNameByUA[i][1];
            }
        }
        return null;
    },

    /**
     * getBaseNameByUA()により取得したブラウザ識別名をもとにCSSファイルを取得します。
     * 
     * @return [CSS格納ディレクトリ] + ユーザー定義のブラウザ識別名 + ".css"
     */
    getCssFileByUA : function() {
        var baseName = xj_core.getBaseNameByUA();
        if (baseName) {
            return xj_conf.cssDirectory + baseName + ".css";
        }
        return null;
    },

    /**
     * 指定したフォーム内に、hiddenタグ要素を動的に追加します。
     * 
     * @param jqFrm 対象フォーム。（jQueryオブジェクトとして引き渡します。）
     * @param name name属性に設定する値。
     * @param value value属性に設定する値。
     */
    addHidden : function(jqFrm, name, value) {
        jqFrm.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\" />");
    },

    /**
     * JavaScriptがoffでない場合に、本来のメインコンテンツを表示します。
     */
    showMainContents : function() {
        $(".x_mainContents").show();
    },

    /**
     * 指定した名前の関数を取得します。
     * 
     * @param functionName 関数名。
     * @return 関数ポインタ。存在しない場合はnull。
     */
    getFunction : function(functionName) {
        try {
            var func = eval(functionName);
            if (typeof(func) == "function") {
                return func;
            }
        } catch (e) {
        }
        return null;
    },

    /**
     * 指定した値が「値なし」かどうかを判定します。
     * 
     * @param target 任意の値。
     * @return true : 値なし、false : 値あり。
     */
    isNone : function(target) {
        return (target == null || target == undefined || target == "" || target.length == 0);
    },

    /**
     * jQueryのセレクタ文字列をエスケープします。
     * 
     * @param text エスケープしたいセレクタ文字列。
     * @return エスケープされた文字列。
     */
    escapeJQSelector : function(text) {
        return text.replace(/[#;&,\.\+\*~':"!\^\$\[\]\(\)=>|\/\\]/g, '\\$&');
    }
};

$(function() {
    // x_cssByUAがHTML文書内に存在していれば、クロスブラウザ用のCSSファイルインポートを行う
    if ($("#x_cssByUA")) {
        var cssFile = xj_core.getCssFileByUA();
        if (cssFile) {
            $("#x_cssByUA").attr("href", cssFile);
        }
        else {
            // 対応するCSSファイルが存在しない場合は不要な通信が発生するのでlinkタグ自体を削除
            $("#x_cssByUA").remove();
        }
    }

    // メインコンテンツを表示します
    xj_core.showMainContents();
});
