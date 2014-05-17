<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="tiles.default">

	<tiles:putAttribute name="title">
		<s:property value="pageTitle" />
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<script>
			function ready() {

				//Do somethings after page loaded
				//alert('Page loaded');
			}

			function view(url) {
				openPopup(url, 800, 600, 'viewGroup');
			}
		</script>

		<h2>
			<s:property value="headerText" />
		</h2>
		<div class="scroll">
			<table class="alternateRow hover border-all">
				<thead>
					<tr>
						<th width="6%"><s:text name="common.index.NO" /></th>
						<th width="20%"><s:text name="name" /></th>
						<th width="20%"><s:text name="code" /></th>
						<th width="20%"><s:text name="description" /></th>
						<th width="12%"><s:text name="common.action" /></th>
					</tr>
				</thead>
				<s:if test="dataGrid != null && dataGrid.size > 0">
					<s:iterator value="dataGrid" status="status">
						<tr id="row_<s:property value="id"/>">
							<td align="center">${status.index + 1}</td>
							<td><s:url id="view"
									action="%{@org.java.demo.web.action.base.AbstractAction@ACTION_VIEW}">
									<s:param name="id" value="id" />
								</s:url> <s:a href="javascript:view('%{view}')"
									title='%{getText("common.action.viewItem",{"Group"})}'>
									<s:property value="name" />
								</s:a>&nbsp;</td>
							<td><s:property value="code" />&nbsp;</td>
							<td><s:property value="description" />&nbsp;</td>
							<td align="center"><%@ include
									file="/WEB-INF/jspf/action-buttons.jspf"%></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			<s:if test="dataGrid == null || dataGrid.size <= 0">
				<div class="no-data">
					<s:text name="common.text.noData" />
				</div>
			</s:if>
		</div>
		<s:url id="create"
			action="%{@org.java.demo.web.action.base.AbstractAction@ACTION_CREATE}" />

		<s:a href="%{create}">
			<s:property value='getText("common.action.createItem", {"Group"})' />
		</s:a>
	</tiles:putAttribute>

</tiles:insertDefinition>