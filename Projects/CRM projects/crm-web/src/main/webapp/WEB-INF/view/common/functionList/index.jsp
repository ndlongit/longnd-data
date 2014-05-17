<tiles:insert template="/WEB-INF/view/common/layout.jsp">
<tiles:put name="title"  value="機能一覧画面"/>
<tiles:put name="content" type="string">

<s:form>
    <div class="title-nav">
        <span>Xenlonへようこそ！</span>
    </div>
    <div style="margin-top: 10px; margin-left:50px;">
        <h2>画面の呼び出しに必要なパラメータを入力し、機能一覧から表示したい機能のリンクを選択してください。</h2>
    </div>
    <div style="margin-top: 30px; margin-left:100px;">
        <div>画面の呼び出しに必要なパラメーター（任意指定）</div>
        <input id="requiredParameters" type="text" size="50" class="input-text" />
        <br/><br/>
        <h4 class="font-group">機能一覧</h4>
        <div style="width: 90%">
            <table width="100%" class="list-table x_color_row_table_target">
                <thead>
                    <tr class="list-header">
                        <td style="width:25%;">サブシステム名 (ユースケースID)</td>
                        <td style="width:75%;">機能（ユースケース名）</td>
                    </tr>
                </thead>
                <tbody class="list-back">
                     <c:forEach var="function" varStatus="s" items="${functions}">
                        <tr>
                            <td>${f:h(function.subsystemNmJa)}</td>
                            <td><s:link href="${f:h(function.functionUrl)}" title="${f:url(function.functionUrl)}" onclick="moveFunction(this);">${f:h(function.useCaseNm)}</s:link></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <br/>
    <div class="title-nav">
        <div class="button-nav"></div>
    </div>
</s:form>

</tiles:put>
</tiles:insert>

<script type="text/javascript">
<!--
    /**
     * コンファームダイアログで確認してから画面遷移を行います。
     * @param target : リンクタグエレメント。
     */
    function moveFunction(target) {
        var path = $(target).attr("href") + $("#requiredParameters").val();
        if (confirm("次のURLを呼び出します。\n" + path + "\nよろしいですか？")) {
        	$(target).attr("href", path);
        } else {
            xj_request.cancelRequest();
        }
    }
// -->
</script>
