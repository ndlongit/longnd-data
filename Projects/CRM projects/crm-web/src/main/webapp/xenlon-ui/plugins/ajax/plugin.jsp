<%@ page import="jp.co.tis.xenlon.web.constants.XenlonWebConstants" %>

<c:set var="HTTP_REQUEST_HEADER_AJAX_PROCESS" value="<%= XenlonWebConstants.HTTP_REQUEST_HEADER_AJAX_PROCESS %>" />
<c:set var="HTTP_STATUS_CODE_AJAX_BUSINESS_ERROR" value="<%= XenlonWebConstants.HTTP_STATUS_CODE_AJAX_BUSINESS_ERROR %>" />
<c:set var="HTTP_STATUS_CODE_AJAX_SYSTEM_ERROR" value="<%= XenlonWebConstants.HTTP_STATUS_CODE_AJAX_SYSTEM_ERROR %>" />

<style>
<%-- ダイアログ本体 --%>
#x_ajax_error_dialog {
    display: none;
}
<%-- エラーメッセージ部分 --%>
#x_ajax_error_dialog .x_ajax_error_message {
    padding: 15px;
    line-height: 150%
}
<%-- タイトル部分 --%>
.x_dialog .ui-dialog-title {
    font-size: 14px;
}
<%-- タイトルバー --%>
.x_dialog .ui-dialog-titlebar {
    padding: 10px;
}
<%-- フッターボタン部分 --%>
.x_dialog .ui-dialog-buttonpane {
    margin: 0px;
}
<%-- 業務エラー用ダイアログのヘッダー --%>
.x_dialog_biz_error .ui-widget-header {
    background: #FC2;
    color: #951;
}
<%-- 業務エラー用ダイアログのボディ --%>
.x_dialog_biz_error .ui-dialog-content,
.x_dialog_biz_error .ui-widget-content {
    background-color: #FFD;
}
<%-- システムエラー用ダイアログのヘッダー --%>
.x_dialog_sys_error .ui-widget-header {
    background: #F55;
    color: #FFF;
}
<%-- システムエラー用ダイアログのボディ --%>
.x_dialog_sys_error .ui-dialog-content,
.x_dialog_sys_error .ui-widget-content {
    background-color: #FEE;
}
</style>

<script>
$(function() {
    var dialogDom = $("#x_ajax_error_dialog").html();

    $.ajaxSetup({
        beforeSend : function(xhr) {
            xhr.setRequestHeader("${HTTP_REQUEST_HEADER_AJAX_PROCESS}", "true");
        },
        error : function(xhr, txtStatus, errorThrown) {
            var httpStatusCode = xhr.status;
            var message = "";
            var debugInfo = "";
            var dialogSettings = {
                modal: true,
                draggable: false,
                resizable: false,
                show: "fade",
                hide: "fade",
                position: ["center", 25],
                width: (xj_core.getWindowInnerWidth() / 3 * 1),
                height: (xj_core.getWindowInnerHeight() / 3 * 1),
                buttons: {
                    "<bean:message key='xui.x_ajaxDialogClose' />": function(event) {
                        $(this).dialog("close");
                    }
                },
                close: function() {
                    $("#x_ajax_error_dialog").html(dialogDom);
                    $("#x_ajax_error_dialog").dialog("destroy");
                }
            };

            <%-- Xenlonのエラーハンドリングが正常終了している場合 --%>
            if (httpStatusCode == ${HTTP_STATUS_CODE_AJAX_BUSINESS_ERROR}
                || httpStatusCode == ${HTTP_STATUS_CODE_AJAX_SYSTEM_ERROR}) {

                var jsonResponse = JSON.parse(xhr.responseText);

                <%-- エラータイトル --%>
                dialogSettings.title = jsonResponse.title;

                <%-- エラーメッセージ --%>
                if (jsonResponse.message != null && jsonResponse.message.length > 0) {
                    message = jsonResponse.message;
                }

                <%-- 遷移先指定がある場合 --%>
                if (!xj_core.isNone(jsonResponse.actionPath)) {
                    dialogSettings.close = function() {
                        location.href = jsonResponse.actionPath;
                    };
                }

                <%-- 業務エラー --%>
                if (httpStatusCode == ${HTTP_STATUS_CODE_AJAX_BUSINESS_ERROR}) {
                    dialogSettings.dialogClass = "x_dialog x_dialog_biz_error";
                }
                <%-- システムエラー --%>
                else if (httpStatusCode == ${HTTP_STATUS_CODE_AJAX_SYSTEM_ERROR}) {
                    dialogSettings.dialogClass = "x_dialog x_dialog_sys_error";
                    <%-- デバッグ情報表示欄を追加 --%>
                    if (jsonResponse.debugInfo != null && jsonResponse.debugInfo.length > 0) {
                        $("#x_ajax_error_dialog .x_ajax_error_debug_info").append(jsonResponse.debugInfo);
                        dialogSettings.width = (xj_core.getWindowInnerWidth() / 4 * 3);
                        dialogSettings.height = (xj_core.getWindowInnerHeight() / 4 * 3) ;
                    }
                }
            }
            <%-- Xenlonのエラーハンドリングが完遂できない場合 --%>
            <%-- ★ プロジェクトの特性に応じて適切にハンドリングしてください ★ --%>
            else {
                <%-- サーバー側でのエラーでなければ握りつぶす --%>
                if (httpStatusCode == 0) {
                    return;
                }
                <%-- 想定外の異常終了 --%>
                else {
                    dialogSettings.dialogClass = "x_dialog x_dialog_sys_error";
                    dialogSettings.title = "<bean:message key='xui.x_ajaxSystemError' />";
                    message = "<div><bean:message key='errors.handleUnexpected' /></div>";
                    debugInfo = "<div style='padding-left:25px;'><div>HTTP Status Code : " + httpStatusCode + "</div>"
                            + "<div>Status : " + txtStatus + "</div>"
                            + "<div>Error Thrown : " + errorThrown + "</div></div>";
                }
            }

            $("#x_ajax_error_dialog .x_ajax_error_message").append(message);
            <%-- ★ デバッグ情報を出力したい場合は以下のコメントアウトを解除してください ★ --%>
            // $("#x_ajax_error_dialog .x_ajax_error_debug_info").append(debugInfo);

            $("#x_ajax_error_dialog").dialog(dialogSettings);
        }
    });
});
</script>

<%-- エラーダイアログ用DOM --%>
<div id="x_ajax_error_dialog">
    <div class="x_ajax_error_message">
    </div>
    <div class="x_ajax_error_debug_info">
    </div>
</div>
