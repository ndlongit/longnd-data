/*
 * UI周りの拡張機能をもたらす。
 * 
 *     名前空間は『xj_ui』です。
 */
var xj_ui = {
    /**
     * class属性に以下のいずれかが指定されたリストコンテンツに、ツリービュー（jQuery.Treeview）を設定します。
     *     ・「x_treeview_normal」
     *     ・「x_treeview_filesystem」
     *     ・「x_treeview_simple」
     */
    bindTreeview : function() {
        // ノーマルタイプのツリービューを設定
        xj_ui.executeTreeview("x_treeview_normal", "treeview");

        // ファイルシステムタイプのツリービューを設定
        xj_ui.executeTreeview("x_treeview_filesystem", "treeview filetree");

        // シンプルタイプのツリービューを設定
        xj_ui.executeTreeview("x_treeview_simple", "treeview-famfamfam");
    },

    /**
     * bindTreeview()によって内部的に使用される、ネイティブなjQuery.Treeviewを呼び出す関数です。
     * 
     * @param clazz ツリービュー表示する対象のCSSクラス。
     * @param type ツリービューのタイプ。
     */
    executeTreeview : function(clazz, type) {
        $("." + clazz).addClass(type).treeview({ animated: 100, collapsed: true });
    },

    /**
     * class属性に以下のいずれかが指定された<div>コンテンツに、タブビュー（jQuery.ui.tabs）を設定します。
     *     ・「x_tab_fade」
     *     ・「x_tab_slide」
     */
    bindTab : function() {
        $(".x_tab_fade,.x_tab_slide").each(function() {
            // 対象タグのID属性を取得
            var id = $(this).attr("id");

            // ID属性がタグに存在しない場合は、何もしない
            if (id == "undefined") {
                return;
            }

            // タブ部分が押下されても、画面ブロックが起動しないようにする
            $("#" + id + " a").addClass("x_noblock");

            // タイプごとに分岐
            // (1) x_tab_fade : 切替時にフェードイン・フェードアウトするタブ
            if ($(this).hasClass("x_tab_fade")) {
                $("#" + id).tabs({
                    fx:{opacity: "toggle", duration: 200}
                });
            }
            // (2) x_tab_slide : 切替時に上下にスライドアニメーションするタブ
            else if ($(this).hasClass("x_tab_slide")) {
                $("#" + id).tabs({
                    fx:{height: "toggle", duration: 300}
                });
            }
        });
    },

    /**
     * ツールチップUI（qTip）を任意要素に任意設定でバインドします。<br/>
     * xj_conf.tooltip.settings に［jQueryセレクタ : qTip2オプション］のマップで定義された設定を反映します。<br/>
     */
    bindTooltip : function() {
        for (selector in xj_conf.tooltip.settings) {
            var qtipOption = xj_conf.tooltip.settings[selector];
            qtipOption["style"] = {classes: "x_tooltip"};
            $(selector).each(function() {
                var qtipApi = $(this).qtip("api");
                if (xj_core.isNone(qtipApi) || qtipApi.destroyed) {
                    $(this).qtip(qtipOption);
                }
            });
        }
    },

    /**
     * class属性に「x_color_row_table_target(...)」が指定されたテーブルに対し、行の交互着色を設定します。
     */
    bindColorRowTable : function() {
        // <table>タグでclass属性に『x_color_row_table_target』が含んでいるものすべてを処理対象にする（１画面に着色対象のテーブルが複数ある場合）
        $("table[class*='x_color_row_table_target']").each(function() {
            // 『x_color_row_table_target』の文字列後方に、さらに詳細グループ指定があるかどうかを調べて、ある場合はそのID（後方部の文字列）を抜き出す
            var clazz = $(this).attr("class");
            var idxBegin = clazz.indexOf("x_color_row_table_target");
            var idxEnd = clazz.indexOf(" ");
            if (idxEnd < idxBegin) {
                idxEnd = clazz.length;
            }
            var id = clazz.substring(idxBegin + "x_color_row_table_target".length, idxEnd);

            // 詳細グループ指定がある場合は、そのIDを偶数行用CSSクラス、奇数行用CSSクラスの後方に連結する
            var evenClass = "x_color_row_table_even" + id;
            var oddClass = "x_color_row_table_odd" + id;

            // <table>構造内に<tbody>を含んでいるかどうかで、<tbody>以前の<tr>は無視する
            if ($(this).is(":has(tbody)")) {
                $(this).find("tbody > tr:even").addClass(evenClass);
                $(this).find("tbody > tr:odd").addClass(oddClass);
            }
            else {
                $(this).find("tr:even").addClass(evenClass);
                $(this).find("tr:odd").addClass(oddClass);
            }
        });
    },

    /**
     * ページ表示時のエフェクト処理を設定します。
     */
    bindEffectPageLoad : function() {
        $("body").hide().fadeIn(300);
    },

    /**
     * マルチファイルアップロードのオプションを設定します。
     */
    bindMultiFileUpload : function() {
        // チェック
        $.fn.MultiFile.options.max = xj_conf.mfup.max;
        $.fn.MultiFile.options.accept = xj_conf.mfup.accept;

        // メッセージ・レイアウト設定
        $.fn.MultiFile.options.STRING.file = xj_conf.mfup.file;
        $.fn.MultiFile.options.STRING.remove = xj_conf.mfup.remove; 
        $.fn.MultiFile.options.STRING.denied = xj_conf.mfup.denied;
        $.fn.MultiFile.options.STRING.selected = xj_conf.mfup.selected;
        $.fn.MultiFile.options.STRING.duplicate = xj_conf.mfup.duplicate;

        // イベントトリガー
        $.fn.MultiFile.options.onFileRemove = xj_conf.mfup.onFileRemove;
        $.fn.MultiFile.options.afterFileRemove = xj_conf.mfup.afterFileRemove;
        $.fn.MultiFile.options.onFileAppend = xj_conf.mfup.onFileAppend;
        $.fn.MultiFile.options.afterFileAppend = xj_conf.mfup.afterFileAppend;
        $.fn.MultiFile.options.onFileSelect = xj_conf.mfup.onFileSelect;
        $.fn.MultiFile.options.afterFileSelect = xj_conf.mfup.afterFileSelect;
    }
};

$(function() {
    // ページ表示時のエフェクトをバインド
    xj_ui.bindEffectPageLoad();

    // ツールチップをバインド
    xj_ui.bindTooltip();

    // ツリービューをバインド
    xj_ui.bindTreeview();

    // タブビューをバインド
    xj_ui.bindTab();

    // テーブル行の交互着色をバインド
    xj_ui.bindColorRowTable();

    // マルチファイルアップロードのオプションをバインド
    xj_ui.bindMultiFileUpload();
});
