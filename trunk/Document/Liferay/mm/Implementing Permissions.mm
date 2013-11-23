<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1292827811973" ID="Freemind_Link_826841977" MODIFIED="1292828506142" TEXT="Implementing Permissions">
<node CREATED="1292828117084" ID="_" MODIFIED="1292831043730" POSITION="right" TEXT="Samples &#xa;(portal-impl/src/resource-actions/blogs.xml)">
<node CREATED="1292828131860" ID="Freemind_Link_1324478292" MODIFIED="1292829725419" TEXT="&lt;?xml version=&quot;1.0&quot;?&gt;&#xa;&lt;resource-action-mapping&gt;&#xa;&lt;portlet-resource&gt;&#xa;&lt;portlet-name&gt;33&lt;/portlet-name&gt;&#xa;&lt;permissions&gt;&#xa;&lt;supports&gt;&#xa;&lt;action-key&gt;ACCESS_IN_CONTROL_PANEL&lt;/action-key&gt;&#xa;&lt;action-key&gt;ADD_TO_PAGE&lt;/action-key&gt;&#xa;&lt;action-key&gt;CONFIGURATION&lt;/action-key&gt;&#xa;&lt;action-key&gt;VIEW&lt;/action-key&gt;&#xa;&lt;/supports&gt;&#xa;&lt;community-defaults&gt;&#xa;&lt;action-key&gt;VIEW&lt;/action-key&gt;&#xa;&lt;/community-defaults&gt;&#xa;&lt;guest-defaults&gt;&#xa;&lt;action-key&gt;VIEW&lt;/action-key&gt;&#xa;&lt;/guest-defaults&gt;&#xa;&lt;guest-unsupported&gt;&#xa;&lt;action-key&gt;ACCESS_IN_CONTROL_PANEL&lt;/action-key&gt;&#xa;&lt;action-key&gt;CONFIGURATION&lt;/action-key&gt;&#xa;&lt;/guest-unsupported&gt;&#xa;&lt;/permissions&gt;&#xa;&lt;/portlet-resource&gt;&#xa;&lt;model-resource&gt;&#xa;&lt;model-name&gt;com.liferay.portlet.blogs&lt;/model-name&gt;&#xa;&lt;portlet-ref&gt;&#xa;&lt;portlet-name&gt;33&lt;/portlet-name&gt;&#xa;&lt;/portlet-ref&gt;&#xa;&lt;permissions&gt;&#xa;&lt;supports&gt;&#xa;&lt;action-key&gt;ADD_ENTRY&lt;/action-key&gt;&#xa;&lt;action-key&gt;PERMISSIONS&lt;/action-key&gt;&#xa;&lt;action-key&gt;SUBSCRIBE&lt;/action-key&gt;&#xa;&lt;/supports&gt;&#xa;&lt;community-defaults /&gt;&#xa;&lt;guest-defaults /&gt;&#xa;&lt;guest-unsupported&gt;&#xa;&lt;action-key&gt;ADD_ENTRY&lt;/action-key&gt;&#xa;&lt;action-key&gt;PERMISSIONS&lt;/action-key&gt;&#xa;&lt;action-key&gt;SUBSCRIBE&lt;/action-key&gt;&#xa;&lt;/guest-unsupported&gt;&#xa;&lt;/permissions&gt;&#xa;&lt;/model-resource&gt;&#xa;&lt;model-resource&gt;&#xa;&lt;model-name&gt;com.liferay.portlet.blogs.model.BlogsEntry&lt;/model-name&gt;&#xa;&lt;portlet-ref&gt;&#xa;&lt;portlet-name&gt;33&lt;/portlet-name&gt;&#xa;&lt;/portlet-ref&gt;&#xa;&lt;permissions&gt;&#xa;&lt;supports&gt;&#xa;&lt;action-key&gt;ADD_DISCUSSION&lt;/action-key&gt;&#xa;&lt;action-key&gt;DELETE&lt;/action-key&gt;&#xa;&lt;action-key&gt;DELETE_DISCUSSION&lt;/action-key&gt;&#xa;&lt;action-key&gt;PERMISSIONS&lt;/action-key&gt;&#xa;&lt;action-key&gt;UPDATE&lt;/action-key&gt;&#xa;&lt;action-key&gt;UPDATE_DISCUSSION&lt;/action-key&gt;&#xa;&lt;action-key&gt;VIEW&lt;/action-key&gt;&#xa;&lt;/supports&gt;&#xa;&lt;community-defaults&gt;&#xa;&lt;action-key&gt;ADD_DISCUSSION&lt;/action-key&gt;&#xa;&lt;action-key&gt;VIEW&lt;/action-key&gt;&#xa;&lt;/community-defaults&gt;&#xa;&lt;guest-defaults&gt;&#xa;&lt;action-key&gt;VIEW&lt;/action-key&gt;&#xa;&lt;/guest-defaults&gt;&#xa;&lt;guest-unsupported&gt;&#xa;&lt;action-key&gt;ADD_DISCUSSION&lt;/action-key&gt;&#xa;&lt;action-key&gt;DELETE&lt;/action-key&gt;&#xa;&lt;action-key&gt;DELETE_DISCUSSION&lt;/action-key&gt;&#xa;&lt;action-key&gt;PERMISSIONS&lt;/action-key&gt;&#xa;&lt;action-key&gt;UPDATE&lt;/action-key&gt;&#xa;&lt;action-key&gt;UPDATE_DISCUSSION&lt;/action-key&gt;&#xa;&lt;/guest-unsupported&gt;&#xa;&lt;/permissions&gt;&#xa;...&#xa;&lt;/model-resource&gt;&#xa;&lt;/resource-action-mapping&gt;">
<node CREATED="1292828813404" ID="Freemind_Link_1265115655" MODIFIED="1292830873406" TEXT="&lt;portlet-resource&gt; : actions and default &#xa;permissions are defined on the portlet itself">
<node CREATED="1292830812028" ID="Freemind_Link_83836348" MODIFIED="1292830815831" TEXT="&lt;supports&gt; : All actions (ACCESS_IN_CONTROL_PANEL, ADD_TO_PAGE, CONFIGURATION, VIEW) are defined here"/>
<node CREATED="1292830827895" ID="Freemind_Link_1041577617" MODIFIED="1292830829469" TEXT="&lt;community-defaults&gt; : The default portlet-level permissions for members of the community are defined here. In this case, members of a community should be able to view any blogs in that community "/>
<node CREATED="1292830839631" ID="Freemind_Link_554661548" MODIFIED="1292830842452" TEXT="&lt;guest-defaults&gt;. &lt;guest-unsupported&gt; : contains permissions that a guest may never be granted, even by an Administrator "/>
</node>
<node CREATED="1292830598734" ID="Freemind_Link_346107221" MODIFIED="1292831009222" TEXT="&lt;model-resource&gt; : The next level of permissions is &#xa;based on the scope of an individual instance of the portlet.">
<node CREATED="1292830675339" ID="Freemind_Link_120812812" MODIFIED="1292830691128" TEXT="&lt;model-name&gt; : is not the name of an actual Java class, but simply of the blogs package"/>
</node>
</node>
</node>
<node CREATED="1292828540213" ID="Freemind_Link_630774056" MODIFIED="1292828549440" POSITION="left" TEXT="Terms">
<node CREATED="1292828572756" ID="Freemind_Link_1767719993" MODIFIED="1292828574814" TEXT="Resource : Examples of resources include portlets (e.g., Message Boards, Calendar, etc.), Java classes (e.g., Message Board Topics, Calendar Events, etc.), and files (e.g., documents, images, etc.)"/>
<node CREATED="1292828601778" ID="Freemind_Link_261586036" MODIFIED="1292828603897" TEXT="Permission : An action acting on a resource. For example, the view in &#x201c;viewing the calendar portlet&#x201d; is defined as a permission in Liferay"/>
</node>
</node>
</map>