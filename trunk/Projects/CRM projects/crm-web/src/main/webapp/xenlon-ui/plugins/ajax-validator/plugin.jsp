<%@ page import="jp.co.tis.xenlon.web.constants.XenlonWebConstants" %>

<c:set var="HTTP_REQUEST_HEADER_AJAX_VALIDATION_PROCESS" value="<%= XenlonWebConstants.HTTP_REQUEST_HEADER_AJAX_VALIDATION_PROCESS %>" />

<style>
<%-- バリデーション結果がNGの通知ポップアップのスタイル --%>
.x_ajax_validate_tip {
    padding: 8px;
    line-height: 170%;

    background: -moz-linear-gradient(top, #FEE, #FBB);
    background: top left, -o-linear-gradient(#FEE, #FBB);
    background: -webkit-gradient(linear, left top, left bottom, from(#FEE), to(#FBB));
    background-color: #FBB;
    border-color: #D88;

    border-radius: 8px;
    -webkit-border-radius: 8px;
    -moz-border-radius: 8px;

    box-shadow: 3px 5px 5px 3px rgba(0, 0, 0, 0.15);
    -webkit-box-shadow: 3px 5px 5px 3px rgba(0, 0, 0, 0.15);
    -moz-box-shadow: 3px 5px 5px 3px rgba(0, 0, 0, 0.15);
}
</style>

<script>
$(function() {
    <%-- 入力エラー項目に適用するCSSクラス --%>
    var inputErrorClass = "input-error";

    $.fn.qtip.zindex = 0;

    $(document).on("change", ".x_ajaxvalid:input:not(:file)", function() {
        var $validateTarget = $(this);
        var requestUrl = $(this).closest("form").attr("action");
        var targetName = $(this).attr("name");
        var targetValue = $(this).val();
        var requestParam = {};
        requestParam["targetName"] = targetName;
        if (targetValue != null && targetValue.length > 0) {
            requestParam["targetValue"] = targetValue;
        }

        $.ajax({
            url: requestUrl,
            type: "post",
            data: requestParam,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("${HTTP_REQUEST_HEADER_AJAX_VALIDATION_PROCESS}", "true");
            },
            dataType: "json",
            success: function(json, dataType) {
                var validationResult = json.validationResult;
                var message = "";
                $.each(json.messages, function() {
                    message += "<div>" + this + "</div>";
                });
                var qtipOption = {
                    content: {
                        text: message
                    },
                    position: {
                        at: "top right",
                        my: "bottom left",
                        adjust: {x: -10, y: 5}
                    },
                    style: {
                        classes: "x_ajax_validate_tip"
                    },
                    show: {
                        event: "focus",
                        effect: function(offset) {
                            $(this).fadeIn(250);
                        }
                    },
                    hide: {
                        event: false,
                        fixed: true,
                        effect: function(offset) {
                            $(this).fadeOut(1000);
                        }
                    },
                    events: {
                        render: function(event, api) {
                            $(this).click(function() {
                                $(this).hide(250);
                            });
                        },
                        show: function(event, api) {
                            if (!$validateTarget.data("qtip").options.x_errored) {
                                event.preventDefault();
                            }
                        },
                        hidden: function(event, api) {
                            if (!$validateTarget.data("qtip").options.x_errored) {
                                api.destroy();
                                xj_ui.bindTooltip();
                            }
                        }
                    },
                    x_errored: true
                };
                var $qtip = $validateTarget.data("qtip");
                var qtipRendered = (!xj_core.isNone($qtip) && $qtip.rendered);
                if (validationResult) {
                    if (qtipRendered) {
                        $qtip.options.x_errored = false;
                        $validateTarget.removeClass(inputErrorClass);
                        $validateTarget.qtip("hide");
                    }
                }
                else {
                    if (qtipRendered) {
                        $qtip.destroy();
                    }
                    $validateTarget.addClass(inputErrorClass);
                    $validateTarget.qtip(qtipOption).qtip("show");
                }
            }
        });
    });
});
</script>
