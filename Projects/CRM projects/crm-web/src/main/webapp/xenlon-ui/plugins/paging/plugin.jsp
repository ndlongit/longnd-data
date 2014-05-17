<style>
<%-- ページングコントロールエリア全体 --%>
div.x_paging_area {
    margin: 1px 0px;
    overflow: hidden;
    background-color: #DDF;
    color: #000;
    width: 100%;
    height: 40px;
}

<%-- ページ情報表示部（検索件数・ページ情報） --%>
div.x_paging_area p {
    padding: 5px 15px;
}

<%-- 検索件数表示部 --%>
div.x_paging_area p.x_paging_count {
    float: left;
    text-align: left;
    font-size: 9px;
}

<%-- ページ情報（現在位置・ページ数）表示部 --%>
div.x_paging_area p.x_paging_position {
    float: right;
    text-align: right;
    font-size: 9px;
}

<%-- ページ移動リンク表示部 --%>
div.x_paging_area ul {
    clear: both;
    float: left;
    padding: 0px;
    position: relative;
    top: -16px;
    left: 50%;
}

<%-- ページ移動リンク --%>
div.x_paging_area ul li {
    float: left;
    margin: 2px;
    position: relative;
    left: -50%;
}

<%-- ページ移動リンク・標準 --%>
div.x_paging_area ul li a {
    height: 1.50em;
    width: 2.25em;
    padding-top: 5px;
    font-weight: normal;
    color: #333;
    text-decoration: none;
    display: block;
    text-align: center;
    border: solid 1px #EEE;
    border-bottom: solid 1px #CCC;
    border-right: solid 1px #CCC;
    background: #FFF url(/xenlon-ui/plugins/paging/img/bg-navi.gif) repeat-x left bottom;
    border-top-right-radius: 2px;
    border-bottom-left-radius: 2px;
}

<%-- ページ移動リンク・マウスオーバー --%>
div.x_paging_area ul li a:hover,
div.x_paging_area ul li a:focus,
div.x_paging_area ul li a:active {
    background: #8AF;
    color: #FFF;
    font-weight: bold;
    border: solid 1px #FFF;
    border-right: solid 1px #7AF;
    border-bottom: solid 1px #69F;
}

<%-- ページ移動リンク・アクティブ（現在位置） --%>
div.x_paging_area ul li a.x_paging_active,
div.x_paging_area ul li a.x_paging_active:hover,
div.x_paging_area ul li a.x_paging_active:focus,
div.x_paging_area ul li a.x_paging_active:active {
    background: #55F;
    color: #FFF;
    font-weight: bold;
    border: solid 1px #FFF;
    border-right: solid 1px #33F;
    border-bottom: solid 1px #00F;
    cursor: text;
}

<%-- ページ移動リンク・非活性（移動不可） --%>
div.x_paging_area ul li a.x_paging_disable,
div.x_paging_area ul li a.x_paging_disable:hover,
div.x_paging_area ul li a.x_paging_disable:focus,
div.x_paging_area ul li a.x_paging_disable:active {
    color: #AAA;
    font-weight: normal;
    background: #FFF url(/xenlon-ui/plugins/paging/img/bg-navi.gif) repeat-x left bottom !important;
    border: solid 1px #EEE;
    border-right: solid 1px #CCC;
    border-bottom: solid 1px #CCC;
    cursor: text;
}

<%-- ページ移動リンク【最初・前・次・最後】 --%>
div.x_paging_area ul li.x_paging_first,
div.x_paging_area ul li.x_paging_prev,
div.x_paging_area ul li.x_paging_next,
div.x_paging_area ul li.x_paging_last {
    margin: 2px;
    background: #FFF url(/xenlon-ui/plugins/paging/img/bg-navi.gif) repeat-x left bottom;
}

<%-- ページ移動リンク【最初・最後】 --%>
div.x_paging_area ul li.x_paging_first a,
div.x_paging_area ul li.x_paging_last a {
    width: 70px;
}

<%-- ページ移動リンク【前・次】 --%>
div.x_paging_area ul li.x_paging_prev a,
div.x_paging_area ul li.x_paging_next a {
    width: 60px;
}

<%-- ページ移動リンク【最初】・標準 --%>
div.x_paging_area ul li.x_paging_first a {
    text-indent: 1em;
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-first-normal.png) no-repeat -5px center;
}

<%-- ページ移動リンク【最初】・アクティブ（現在位置） --%>
div.x_paging_area ul li.x_paging_first a:hover,
div.x_paging_area ul li.x_paging_first a:focus,
div.x_paging_area ul li.x_paging_first a:active {
    background: url(/xenlon-ui/plugins/paging/img/nav-first-focus.png) no-repeat -5px center #8AF;
}

<%-- ページ移動リンク【最初】・非活性（移動不可） --%>
div.x_paging_area ul li.x_paging_first a.x_paging_disable,
div.x_paging_area ul li.x_paging_first a.x_paging_disable:hover,
div.x_paging_area ul li.x_paging_first a.x_paging_disable:focus,
div.x_paging_area ul li.x_paging_first a.x_paging_disable:active {
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-first-disable.png) no-repeat -5px center !important;
}

<%-- ページ移動リンク【前】・標準 --%>
div.x_paging_area ul li.x_paging_prev a {
    text-indent: 1em;
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-prev-normal.png) no-repeat -5px center;
}

<%-- ページ移動リンク【前】・アクティブ（現在位置） --%>
div.x_paging_area ul li.x_paging_prev a:hover,
div.x_paging_area ul li.x_paging_prev a:focus,
div.x_paging_area ul li.x_paging_prev a:active {
    background: url(/xenlon-ui/plugins/paging/img/nav-prev-focus.png) no-repeat -5px center #8AF;
}

<%-- ページ移動リンク【前】・非活性（移動不可） --%>
div.x_paging_area ul li.x_paging_prev a.x_paging_disable,
div.x_paging_area ul li.x_paging_prev a.x_paging_disable:hover,
div.x_paging_area ul li.x_paging_prev a.x_paging_disable:focus,
div.x_paging_area ul li.x_paging_prev a.x_paging_disable:active {
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-prev-disable.png) no-repeat -5px center !important;
}

<%-- ページ移動リンク【次】・標準 --%>
div.x_paging_area ul li.x_paging_next a {
    text-indent: -1.5em;
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-next-normal.png) no-repeat 30px center;
}

<%-- ページ移動リンク【次】・アクティブ（現在位置） --%>
div.x_paging_area ul li.x_paging_next a:hover,
div.x_paging_area ul li.x_paging_next a:focus,
div.x_paging_area ul li.x_paging_next a:active {
    background: url(/xenlon-ui/plugins/paging/img/nav-next-focus.png) no-repeat 30px center #8AF;
}

<%-- ページ移動リンク【次】・非活性（移動不可） --%>
div.x_paging_area ul li.x_paging_next a.x_paging_disable,
div.x_paging_area ul li.x_paging_next a.x_paging_disable:hover,
div.x_paging_area ul li.x_paging_next a.x_paging_disable:focus,
div.x_paging_area ul li.x_paging_next a.x_paging_disable:active {
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-next-disable.png) no-repeat 30px center !important;
}

<%-- ページ移動リンク【最後】・標準 --%>
div.x_paging_area ul li.x_paging_last a {
    text-indent: -1.5em;
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-last-normal.png) no-repeat 40px center;
}

<%-- ページ移動リンク【最後】・アクティブ（現在位置） --%>
div.x_paging_area ul li.x_paging_last a:hover,
div.x_paging_area ul li.x_paging_last a:focus,
div.x_paging_area ul li.x_paging_last a:active {
    background: url(/xenlon-ui/plugins/paging/img/nav-last-focus.png) no-repeat 40px center #8AF;
}

<%-- ページ移動リンク【最後】・非活性（移動不可） --%>
div.x_paging_area ul li.x_paging_last a.x_paging_disable,
div.x_paging_area ul li.x_paging_last a.x_paging_disable:hover,
div.x_paging_area ul li.x_paging_last a.x_paging_disable:focus,
div.x_paging_area ul li.x_paging_last a.x_paging_disable:active {
    background: transparent url(/xenlon-ui/plugins/paging/img/nav-last-disable.png) no-repeat 40px center !important;
}
</style>
