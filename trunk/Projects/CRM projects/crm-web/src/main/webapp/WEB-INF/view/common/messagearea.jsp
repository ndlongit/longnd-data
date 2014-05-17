<link rel="stylesheet" type="text/css" href="/css/demo/messagearea.css" media="all" />
<script>
/**
* メッセージ表示エリアの開閉を行います。
*/
function foldingMessageArea() {
    $("#expand-block").slideToggle(200, function() {
        if ($("#expand-controll").hasClass("open-img")) {
            $("#expand-controll").removeClass("open-img").addClass("close-img");
        }
        else {
            $("#expand-controll").removeClass("close-img").addClass("open-img");
        }
    });
}
</script>

<span style="font-weight: bold; color: #0000CD;">
    <fw:errors />
    <fw:messages id="msg" message="true">
        <li><bean:write name="msg" ignore="true" /></li>
    </fw:messages>
    <fw:displayThrowableInfo layout="text"/>
</span>
