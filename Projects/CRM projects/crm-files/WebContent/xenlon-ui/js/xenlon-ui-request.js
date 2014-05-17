/*
 * リクエスト送信周りの処理を支援する機能。
 * 
 *     名前空間は『xj_request』です。
 */
var xj_request = {
    /** リクエスト関連の処理で内部的に使用する制御用フラグです。 */
    cancelFlg : false,

    /**
     * <a>タグによるGETリクエスト発動や、<input type="submit">によるやPOSTリクエスト発動を監視し、
     * リクエスト発動中止フラグが挙がっていた場合は、そこで中断します。
     */
    bindControllRequest : function() {
        $(":submit,a").click(function(event) {
            if (xj_request.cancelFlg) {
                event.preventDefault();
                event.stopImmediatePropagation();
                xj_request.cancelFlg = false;
            }
        });
    },

    /**
     * リンクサブミット機能（<a>タグでのフォームサブミット）をバインドします。
     * 
     *     ※ マルチパートリクエストをリンクサブミットで送信する場合は、
     *         index()メソッドを呼び出すために「/xxx/xxx/」というアクションパス省略形式を使用しないでください。
     *         この形式のアクションパスでリクエストを行った場合には、アクションフォームがセッションからクリアされません。
     */
    bindLinkSubmit : function() {
        $(document).on("click", "a.x_linkSubmit", function(event) {
            // <a>タグのデフォルト動作（HTTP-GETリクエスト発動）をストップさせる
            event.preventDefault();

            // 自分が<form>タグ内部に属していない場合は発動させない
            var parentForm = $(this).closest("form");
            if (parentForm.val() == undefined) {
                event.stopImmediatePropagation();
                return;
            }

            // action属性をhref属性に改ざんし、サブミットする
            var href = $(this).attr("href");
            parentForm.attr("action", href);
            parentForm.attr("method", "post");
            parentForm.submit();    
        });
    },

    /**
     * 入力項目の変更有無監視をバインドします。
     */
    bindEditMonitoring : function() {
        // 指定された項目で内容変更が発生した場合に変更有無フラグをonにする
        $(document).on("change", xj_conf.editMonitoring.$targetOnWatch.selector, function() {
            xj_conf.editMonitoring._edit = true;
        });

        // 入力変更状態を次画面に引き継ぐ
        $(document).on("submit", "form", function() {
            if (xj_conf.editMonitoring._edit) {
                xj_core.addHidden($(this), "x_valueChanged", "true");
            }
        });

        // 編集内容破棄確認ダイアログの表示
        $(document).on("click", xj_conf.editMonitoring.$targetOnConfirm.selector, function(event) { 
            if (xj_conf.editMonitoring._edit) {
                if (!confirm(xj_conf.revokeInputConfirmMessage)) {
                    // ブロックを解除して、サブミットを中断
                    xj_window.unblockQuickly();
                    event.preventDefault();
                    event.stopImmediatePropagation();
                }
            }
        });
    },

    /**
     * マルチパートリクエスト時のサポート処理をバインドします。
     */
    bindMultiPartSubmitSupport : function() {
        /*
         * マルチパートリクエストの場合、index()メソッド呼び出しにおけるXAFのアクションフォームのクリア処理の時点では
         * マルチパートリクエストの内容解析がまだ行われていないため、画面から指定されたアクションメソッドが判定できません。
         * このためサブミットボタンに「name="index"」が指定された場合のみ、<form>タグのaction属性の末尾に「/index/」を付加し、
         * マルチパートリクエストの内容解析が未実行の段階でも、アクションパスからindex()メソッドの明示的な呼び出しであることを検知可能にし、
         * アクションフォームのクリア処理を行わせます。
         */
        $("form[enctype='multipart/form-data'] :submit,:image").click(function() {
            if ($(this).attr("name") == "index") {
                var multiPartForm = $(this).closest("form");
                multiPartForm.attr("action", multiPartForm.attr("action") + "index/");
            }
        });
    },

    /**
     * <a>タグによるGETリクエスト発動や、<input type="submit">によるやPOSTリクエスト発動を中止します。
     */
    cancelRequest : function() {
        xj_request.cancelFlg = true;
    }
};

$(function() {
    // <a>タグやサブミットボタンなどによるリクエスト発動制御をバインドします
    xj_request.bindControllRequest();

    // リンクサブミットをバインドします
    xj_request.bindLinkSubmit();

    // 入力項目の変更監視をバインドします
    xj_request.bindEditMonitoring();

    // マルチパートリクエスト時のサポート処理をバインドします
    xj_request.bindMultiPartSubmitSupport();
});
