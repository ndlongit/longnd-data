/*
 * Xenlon-UIのウィンドウ操作サポート機能。
 *
 *     名前空間は『xj_window』です。
 */
var xj_window = {
    /** ブロック処理が内部的に使用する制御用フラグです。 */
    blockCanceled : false,

    /** サブウィンドウ制御用に内部的に使用される変数です。 */
    modalSubwindow : null,

    /**
     * ブロックUIの基本設定を行います。
     */
    setUpBlockUI : function() {
        // ブロックレイヤー（暗幕）のCSS設定
        $.extend($.blockUI.defaults.overlayCSS, {
            backgroundColor: xj_conf.blockUI.backGroundColor,
            cursor: xj_conf.blockUI.cursor,
            opacity: xj_conf.blockUI.opacity
        });
    },

    /**
     * リクエスト時に画面のブロック処理を行います。<br/>
     * JavaScript変数「xj_conf.blockUI.effectTransition」が true である場合にこの機能が働きます。<br/>
     * 「input type="submit"」、「input type="image」、およびアンカータグに対して働きます。<br/>
     * これらのタグのclass属性に、個別に「x_noblock」が指定されている場合は、ブロック処理を行いません。<br/>
     * また、これらのタグ以外でも、タグのclass属性に「x_block」が指定されている場合は、必ずブロック処理を行います。<br/>
     */
    bindBlock : function() {
        // 画面遷移時のブロックがfalseになっている場合は無効化
        if (!xj_conf.blockUI.effectTransition) {
            return;
        }

        // サブミットボタン、イメージボタン、リンクの場合のみがブロック対象
        var targetComponent = $("input[type='submit'],input[type='image'],a,.x_block");
        targetComponent.bind("click", function() {

            // class="x_noblock" が指定されているものは対象外
            if ($(this).hasClass("x_noblock")) {
                return;
            }

            // 一度非ブロックフラグが立っていれば対象外
            if (xj_window.blockCanceled) {
                return;
            }

            // HTML5のvalidateメッセージ対応
            var parentForm = $(this).prop("form");
            if (parentForm && parentForm.checkValidity) {
                if (!parentForm.checkValidity()) {
                    return;
                }
            }

            // <a>タグリンク対応
            if ($(this).get(0).tagName.match(/a/i) && !$(this).is(".x_block,.x_tmpBlock")) {
                var href = $(this).attr("href");
                if (href != null) {
                    // ページ内リンクや電話発信用リンク、メール作成用リンク、JavaScript発動用リンクである場合は対象外
                    if (href.match(/.*#.*|^tel:|^mailto:|^javascript:/i)) {
                        return;
                    }
    
                    // 他のウィンドウに画面表示する場合は対象外
                    if ($(this).attr("target") != null) {
                        return;
                    }
                }
            }

            // class="x_tmpBlock" が指定されている場合は一定時間だけブロック
            var blockTimeout = 0;
            if ($(this).hasClass("x_tmpBlock")) {
                blockTimeout = xj_conf.blockUI.tmpBlockTime;
            }

            // ブロック実行
            xj_window.invokeBlockUI(
                    xj_conf.blockUI.image,
                    xj_conf.blockUI.transitionMessage,
                    blockTimeout);

            // 時間指定ブロックでなければ、非ブロックフラグを立てておく
            if (!$(this).hasClass("x_tmpBlock")) {
                xj_window.blockCanceled = true;
            }
        });

        // 画面がアンロードされる際には、ブロック状態をクリアしておく（ブラウザの戻るボタン対策）
        $(window).unload(function() {
            xj_window.unblock();
        });
    },

    /**
     * ネイティブなjQuery.BlockUIを呼び出す関数です。
     */
    invokeBlockUI : function(blockImage, blockMessage, blockTimeout) {
        blockImage = (blockImage == null || blockImage.length == 0) ? null : blockImage;
        blockMessage =(blockMessage == null || blockMessage.length == 0) ? null : blockMessage;
        blockTimeout =(blockTimeout == null) ? 0 : blockTimeout;

        // 画像もメッセージも指定がない場合はブロックするのみ
        if (blockImage == null && blockMessage == null) {
            $.blockUI({
                message: null,
                timeout: blockTimeout
            });
        }
        else {
            blockImage = (blockImage == null) ? "" : "<img src='" + blockImage + "' />";
            blockMessage = (blockMessage == null) ? "" : "<p style='font-size:" + xj_conf.blockUI.fontSize + "; font-weight:bold;'>" + blockMessage + "</p>";
            $.blockUI({
                timeout: blockTimeout,
                message : blockImage + "<br/><br/>" + blockMessage,
                css: {
                    border: "none",
                    background: "none",
                    color: xj_conf.blockUI.fontColor,
                    cursor: xj_conf.blockUI.cursor
                }
            });
        }
    },

    /**
     * 画面のブロックを通常的に解除する場合に使用します。
     */
    unblock : function() {
        // ブロックUIを解除する
        $.unblockUI();

        // 非ブロックフラグをクリアする
        xj_window.blockCanceled = false;
    },

    /**
     * 画面のブロックを明示的に解除する場合に使用します。<br/>
     */
    unblockQuickly : function() {
        // ブロックUIを即時解除する
        $.unblockUI({fadeOut: 0});

        // 非ブロックフラグをクリアする
        xj_window.blockCanceled = false;
    },

    /**
     * モーダルサブウィンドウがクローズ状態であるかどうかを調べます。
     * 
     * @return true : モーダルサブウィンドウがクローズしている。false : モーダルサブウィンドウはオープン中である。
     */
    isModalSubwindowClosed : function() {
        try {
            if (xj_window.modalSubwindow == null || xj_window.modalSubwindow.closed) {
                return true;
            }
            return false;
        }
        catch (e) {
            return true;
        }
    },

    /**
     * モーダルサブウィンドウにフォーカスを移します。
     */
    focusModalSubwindow : function() {
        if (!xj_window.isModalSubwindowClosed()) {
            $(xj_window.modalSubwindow).focus();
        }
    },

    /**
     * モーダルサブウィンドウを開きます。
     * 
     * @param url サブウィンドウ上に展開するアクションパス。
     * @param windowName サブウィンドウに付与するウィンドウ名。《nullの場合は"_blank"が設定されます。》
     * @param width サブウィンドウの幅。《nullの場合は親ウィンドウの半分の幅が設定されます。》
     * @param height サブウィンドウの高さ。《nullの場合は親ウィンドウの半分の高さが設定されます。》
     * @param posX サブウィンドウの出現位置（X軸）。《nullの場合は親ウィンドウの中心にサブウィンドウが表示されるように設定されます。》
     * @param posY サブウィンドウの出現位置（Y軸）。《nullの場合は親ウィンドウの中心にサブウィンドウが表示されるように設定されます。》
     * @param resizable サブウィンドウのリサイズの可否。（true : 可、false : 否）《nullの場合は可が設定されます。》
     * @param scrollBar サブウィンドウのスクロールバーの有無。（true : 有、false : 無）《nullの場合は有が設定されます。》
     * @param menuBar サブウィンドウのメニューバーの有無。（true : 有、false : 無）《nullの場合は無が設定されます。》
     * @param addressBar サブウィンドウのアドレス（URL）バーの有無。（true : 有、false : 無）《nullの場合は無が設定されます。》
     * @param toolBar サブウィンドウのツールバーの有無。（true : 有、false : 無）《nullの場合は無が設定されます。》
     * @param statusBar サブウィンドウのステータスバーの有無。（true : 有、false : 無）《nullの場合は有が設定されます。》
     * @param modalImage モーダル中（暗転中）の親ウィンドウ上に表示する画像。《nullの場合は何も表示されません。》
     * @param modalMessage モーダル中（暗転中）の親ウィンドウ上に表示するメッセージ。《nullの場合は何も表示されません。》
     */
    openModalSubWindow : function(
            url,
            windowName,
            width,
            height,
            posX,
            posY,
            resizable,
            scrollBar,
            menuBar,
            addressBar,
            toolBar,
            statusBar,
            modalImage,
            modalMessage) {

        // 指定がない場合はデフォルト値を設定
        if (windowName == null) windowName = "_blank";
        if (width == null) width = (xj_core.getWindowInnerWidth() / 2);
        if (height == null) height = (xj_core.getWindowInnerHeight() / 2);
        if (posX == null) {
            // 親ウィンドウの真中(X軸) = 親ウィンドウの左端 + (親ウィンドウの幅 / 2)
            // サブウィンドウの左端 = 親ウィンドウの真中(X軸) - (サブウィンドウの幅 / 2)
            var windowPosX = xj_core.getWindowPositionX();
            if (xj_core.isOpera()) windowPosX = 0; // Operaの場合は正しく動作しない
            var centerX = (windowPosX + (xj_core.getWindowInnerWidth() / 2));
            posX = centerX - (width / 2);
        }
        if (posY == null) {
            // 親ウィンドウの真中(Y軸) = 親ウィンドウの上端 + (親ウィンドウの高さ / 2)
            // サブウィンドウの上端 = 親ウィンドウの真中(Y軸) - (サブウィンドウの高さ / 2)
            var windowPosY = xj_core.getWindowPositionY();
            if (xj_core.isOpera()) windowPosY = 0; // Operaの場合は正しく動作しない
            var centerY = (windowPosY + (xj_core.getWindowInnerHeight() / 2));
            posY = centerY - (height / 2);
        }
        if (resizable == null) resizable = true;
        if (scrollBar == null) scrollBar = true;
        if (menuBar == null) menuBar = false;
        if (addressBar == null) addressBar = false;
        if (toolBar == null) toolBar = false;
        if (statusBar == null) statusBar = true;
        if (modalImage == null) {
            modalImage = (xj_conf.blockUI.image == null || xj_conf.blockUI.image.length == 0) ? null : xj_conf.blockUI.image;
        }
        if (modalMessage == null) {
            modalMessage = (xj_conf.blockUI.modalSubWindowMessage == null || xj_conf.blockUI.modalSubWindowMessage.length == 0) ? null : xj_conf.blockUI.modalSubWindowMessage;
        }

        // 編集処理
        resizable = (resizable == true) ? "yes" : "no";
        scrollBar = (scrollBar == true) ? "yes" : "no";
        menuBar = (menuBar == true) ? "yes" : "no";
        addressBar = (addressBar == true) ? "yes" : "no";
        toolBar = (toolBar == true) ? "yes" : "no";
        statusBar = (statusBar == true) ? "yes" : "no";

        // サブウィンドウのオープン
        var option = "width=" + width + ", height=" + height + ", left=" + posX + ", top=" + posY +
                            ", resizable=" + resizable + ", scrollbars=" + scrollBar + ", menubar=" + menuBar +
                            ", location=" + addressBar + ", toolbar=" + toolBar + ", status=" + statusBar;
        xj_window.modalSubwindow = window.open(url, windowName, option);

        // サブウィンドウへフォーカスを移す
        xj_window.focusModalSubwindow();

        // サブウィンドウオープン時のブロックを行わない場合は以降の処理を行わない
        if (!xj_conf.blockUI.effectModalSubwindow) {
            return;
        }

        // 親ウィンドウのモーダル状態を設定（ブロックUIを適用）
        xj_window.invokeBlockUI(modalImage, modalMessage, 0);

        // サブウィンドウのクローズ状態をチェックする監視スレッドをスタートさせる（0.1秒おきに巡回）
        $.timer(100, function(timer) {
            timer.reset(100);
            // サブウィンドウが閉じられているのを検知したら、監視スレッドを止め、自分のモーダル状態を解除、フォーカスを自分に戻す
            if (xj_window.isModalSubwindowClosed()) {
                timer.stop();
                xj_window.unblock();
                $(window).focus();
            }
        });
    },

    /**
    * ウィンドウを閉じます。
    * 親ウィンドウの場合は、閉じられることを確認する確認ダイアログを表示します。
    */
    closeWindow : function() {
        if (!window.dialogArguments && window.opener == null) {
            // 自ウィンドウが親ウィンドウの場合は確認メッセージを表示
            if (!confirm(xj_conf.closeWindowConfirmMessage)) {
                return false;
            }
        }

        top.name = "_x_closeWindow";
        var windowId = window.open("", "_x_closeWindow");
        windowId.close();
    },

    /**
     * （サブウィンドウから見て）親ウィンドウのコンポーネントに、値を設定します。<br/>
     * 
     * @param id 親ウィンドウに存在する項目ID。（対象のタグには属性「id」が必須です。）
     * @param value 設定したい値。
     */
    setValueForParentWindow : function(id, value) {
        if (window.opener == null) {
            return;
        }

        var parentWindow = window.opener;
        var $target = parentWindow.$("#" + xj_core.escapeJQSelector(id));
        var tagName = $target.get(0).tagName;

        // input タグ
        if (tagName.match(/input/i)) {
            var type = $target.attr("type");
            // テキストボックス、隠しフィールド
            if (type.match(/text|hidden/i)) {
                $target.val(value);
            }
            /*
             * 対応したい input が他にもある場合は、ここに分岐を追加していってください。
             */
            else {
                // no-op
            }
        }
        /*
         * 対応したいHTML部品が他にもある場合は、ここに分岐を追加していってください。
         */
        // それ以外の種類のコンポーネントの場合はテキスト部分（タグのボディ部）を書き換える。
        else {
            $target.text(value);
        }
        // 変更破棄確認ダイアログ対応
        $target.change();
    }
};

$(function() {
    // ブロックUIの設定
    xj_window.setUpBlockUI();

    // 画面遷移時のブロック処理をバインド
    xj_window.bindBlock();
});
