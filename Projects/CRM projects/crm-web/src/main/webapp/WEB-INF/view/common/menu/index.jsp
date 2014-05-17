<style>
/* -------------------- スライドメニュー -------------------- */
#slide_panel {
    position: absolute;
    top: 110px;
    left: 0px;
    margin: 0px;
    padding: 0px;
    width: 270px;
    height: hidden;
    overflow: hidden;
    visibility : hidden;
    cursor: pointer;
}
#slide_panel .wrap {
    width: 270px;
    overflow: hidden;
}
#slide_panel .wrap .panel_main {
    margin-left: 20px;
    background-color: #EEF;
}
div.menu_off {
    background-image: url(/images/sidemenu_btn.jpg);
    background-repeat: no-repeat;
    background-position: -20px 0px;
}
div.active {
    background-position: 0px 0px;
}

/* -------------------- オリジナルのツリービュー定義 -------------------- */
.treeview-custom ul {
    margin: 0px;
    padding: 0px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
}
.treeview-custom li {
    list-style: none;
    display: block;
    background: none;
    margin: 5px 4px 0px 4px;
}
.treeview-custom li a {
    display: block;
    margin: 0px;
    padding: 8px 3px 8px 3px;
    color: #33B;
    border-top: 1px solid #EEE;
    border-bottom: 1px solid #555;
    border-left: 2px solid #888;
    border-right: 1px solid #EEE;
    text-decoration: none;
    border-radius: 3px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
}
.treeview-custom li a:hover {
    color: #D22;
    background-color: #FDD;
}
.treeview-custom .hitarea,
.treeview-custom li.lastCollapsable,
.treeview-custom li.lastExpandable {
    background: none;
}
.disabledItem {
    color: #DDD;
}
</style>

<script>
    $(function() {
        var panelWidth = $("#slide_panel").width();
        var buttonWidth = 20;

        // 初期状態ではパネルの横幅をボタンの横幅だけにしておく
        $("#slide_panel").width(buttonWidth);

        // 初期表示時にメニューツリーが一瞬だけフラッシュしないための対策
        $("#slide_panel").css("visibility", "visible");
        $("#slide_panel").hide();
        $("#slide_panel").show(500);

        $("#slide_panel").hover(
            // マウスカーソルが乗った時
            function() {
                $("#slide_panel").stop().animate({width: panelWidth}, 150, function() {
                    $('.menu_off').addClass("active");
                });
            },
            // マウスカーソルが外れた時
            function() {
                $("#slide_panel").stop().animate({width: buttonWidth}, 150, function() {
                    $('.menu_off').removeClass("active");
                });
            }
        );

        $("#menuTree").treeview({collapsed: true, animated: 150});
    });
</script>

<div id="slide_panel">
    <div class="wrap menu_off">
        <div class="panel_main">
            <div class="title-nav" id="menu">
                <span>メニュー</span>
            </div>
            <ul id="menuTree" class="treeview-custom">
                <c:forEach var="menuList" varStatus="s" items="${menuDto.children}">
                    <li>
                        <span id="menu-id-${menuList.menuId}">${f:h(menuList.menuNm)}</span>
                        <ul>
                            <c:forEach var="children1" varStatus="s" items="${menuList.children}">
                                <li>
                                    <span id="menu-id-${children1.menuId}">${f:h(children1.menuNm)}</span>
                                    <ul>
                                        <c:forEach var="children2" varStatus="s" items="${children1.children}">
                                            <c:choose>
                                                <c:when test="${children2.actionPath != null}">
                                                    <c:choose>
                                                        <c:when test="${children2.accessible}">
                                                            <li><a id="menu-id-${children2.menuId}" href="${f:url('/common/menu/fwAction')}?forwardActionPath=${f:h(children2.actionPath)}">${f:h(children2.menuNm)}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li class="disabledItem">${f:h(children2.menuNm)}</li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${f:h(children2.menuNm)}</span>
                                                    <ul>
                                                        <c:forEach var="children3" varStatus="s" items="${children2.children}">
                                                            <c:choose>
                                                                <c:when test="${children3.accessible}">
                                                                    <li><a id="menu-id-${children3.menuId}" href="${f:url('/common/menu/fwAction')}?forwardActionPath=${f:h(children3.actionPath)}">${f:h(children3.menuNm)}</a></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <li class="disabledItem">${f:h(children3.menuNm)}</li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </ul>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
