<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1292472873156" ID="Freemind_Link_1488110386" MODIFIED="1292582912556" TEXT="social-networking-portlets">
<node CREATED="1292472916950" ID="_" MODIFIED="1292480251132" POSITION="right" TEXT="social-networking-portlets">
<node CREATED="1292472939816" ID="Freemind_Link_1810924182" MODIFIED="1292472943954" TEXT="Wall">
<node CREATED="1292472984719" ID="Freemind_Link_1681802214" MODIFIED="1292472989748" TEXT="View">
<node CREATED="1292473103752" ID="Freemind_Link_1477673875" MODIFIED="1292473375889" TEXT="wall/view_wall.jspf"/>
<node CREATED="1292473108562" ID="Freemind_Link_881686062" MODIFIED="1292582336353" TEXT="WallPortlet : Action"/>
</node>
<node CREATED="1292472991575" ID="Freemind_Link_1876902231" MODIFIED="1292472994090" TEXT="Model">
<node CREATED="1292473293837" ID="Freemind_Link_1928653330" MODIFIED="1292582576400" TEXT="WallEntry (WallEntryModelImpl)"/>
<node CREATED="1292582484368" ID="Freemind_Link_1484448447" MODIFIED="1292582497353" TEXT="DB table name: SN_WallEntry"/>
<node CREATED="1292582632587" ID="Freemind_Link_506346548" MODIFIED="1292813115663" TEXT="&lt;!-- WEB-INF\service.xml --&gt;&#xa;..........................................................&#xa;&lt;namespace&gt;SNW&lt;/namespace&gt;&#xa;...............................................................&#xa;&lt;entity name=&quot;WallEntry&quot; local-service=&quot;true&quot; remote-service=&quot;false&quot;&gt;&#xa;&#x9;&#x9;&lt;!-- PK fields --&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;wallEntryId&quot; type=&quot;long&quot; primary=&quot;true&quot; /&gt;&#xa;&#xa;&#x9;&#x9;&lt;!-- Group instance --&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;groupId&quot; type=&quot;long&quot; /&gt;&#xa;&#xa;&#x9;&#x9;&lt;!-- Audit fields --&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;companyId&quot; type=&quot;long&quot; /&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;userId&quot; type=&quot;long&quot; /&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;userName&quot; type=&quot;String&quot; /&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;createDate&quot; type=&quot;Date&quot; /&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;modifiedDate&quot; type=&quot;Date&quot; /&gt;&#xa;&#xa;&#x9;&#x9;&lt;!-- Other fields --&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;comments&quot; type=&quot;String&quot; /&gt;&#xa;&#x9;&#x9;&lt;column name=&quot;postFromIpAddress&quot; type=&quot;String&quot; /&gt;&#xa;&#xa;&#x9;&#x9;&lt;!-- Order --&gt;&#xa;&#x9;&#x9;&lt;order by=&quot;desc&quot;&gt;&#xa;&#x9;&#x9;&#x9;&lt;order-column name=&quot;createDate&quot; /&gt;&#xa;&#x9;&#x9;&lt;/order&gt;&#xa;&#xa;&#x9;&#x9;&lt;!-- Finder methods --&gt;&#xa;&#x9;&#x9;&lt;finder name=&quot;GroupId&quot; return-type=&quot;Collection&quot;&gt;&#xa;&#x9;&#x9;&#x9;&lt;finder-column name=&quot;groupId&quot; /&gt;&#xa;&#x9;&#x9;&lt;/finder&gt;&#xa;&#x9;&#x9;&lt;finder name=&quot;UserId&quot; return-type=&quot;Collection&quot;&gt;&#xa;&#x9;&#x9;&#x9;&lt;finder-column name=&quot;userId&quot; /&gt;&#xa;&#x9;&#x9;&lt;/finder&gt;&#xa;&#x9;&#x9;&lt;finder name=&quot;G_U&quot; return-type=&quot;Collection&quot;&gt;&#xa;&#x9;&#x9;&#x9;&lt;finder-column name=&quot;groupId&quot; /&gt;&#xa;&#x9;&#x9;&#x9;&lt;finder-column name=&quot;userId&quot; /&gt;&#xa;&#x9;&#x9;&lt;/finder&gt;&#xa;&#x9;&lt;/entity&gt;"/>
<node CREATED="1292812971794" ID="Freemind_Link_1433134312" MODIFIED="1292813232630" TEXT="&lt;!-- META-INF\portlet-hbm.xml --&gt;&#xa;.......................................................&#xa;&lt;class name=&quot;com.liferay.socialnetworking.model.impl.WallEntryImpl&quot; table=&quot;SNW_WallEntry&quot;&gt;&#xa;&#x9;&#x9;&lt;cache usage=&quot;read-write&quot; /&gt;&#xa;&#x9;&#x9;&lt;id name=&quot;wallEntryId&quot; type=&quot;long&quot;&gt;&#xa;&#x9;&#x9;&#x9;&lt;generator class=&quot;assigned&quot; /&gt;&#xa;&#x9;&#x9;&lt;/id&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;groupId&quot; type=&quot;com.liferay.portal.dao.orm.hibernate.LongType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;companyId&quot; type=&quot;com.liferay.portal.dao.orm.hibernate.LongType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;userId&quot; type=&quot;com.liferay.portal.dao.orm.hibernate.LongType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;userName&quot; type=&quot;com.liferay.portal.dao.orm.hibernate.StringType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;createDate&quot; type=&quot;org.hibernate.type.TimestampType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;modifiedDate&quot; type=&quot;org.hibernate.type.TimestampType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;comments&quot; type=&quot;com.liferay.portal.dao.orm.hibernate.StringType&quot; /&gt;&#xa;&#x9;&#x9;&lt;property name=&quot;postFromIpAddress&quot; type=&quot;com.liferay.portal.dao.orm.hibernate.StringType&quot; /&gt;&#xa;&#x9;&lt;/class&gt;"/>
</node>
<node CREATED="1292472994683" ID="Freemind_Link_1448769295" MODIFIED="1292472998807" TEXT="Service">
<node CREATED="1292473505675" ID="Freemind_Link_619096616" MODIFIED="1292825832021" TEXT="WallEntryLocalServiceUtil.addWallEntry: Add a new Wall entry">
<node CREATED="1292780362159" ID="ID_1474135258" MODIFIED="1292780472880" TEXT="getService() --&gt; WallEntryLocalService"/>
<node CREATED="1292780474667" ID="ID_1031821322" MODIFIED="1292900197163" TEXT="WallEntryLocalService.addWallEntry(groupId, userId, comments, themeDisplay)"/>
</node>
<node CREATED="1292473522272" ID="Freemind_Link_201940287" MODIFIED="1292825836961" TEXT="WallEntryLocalServiceUtil.deleteWallEntry(wallEntryId): Delete a Wall entry">
<node CREATED="1292780362159" ID="Freemind_Link_1983602626" MODIFIED="1292780472880" TEXT="getService() --&gt; WallEntryLocalService"/>
<node CREATED="1292900287741" ID="Freemind_Link_101029825" MODIFIED="1292900301866" TEXT="WallEntryLocalService.deleteWallEntry(wallEntryId)">
<node CREATED="1292899960835" ID="Freemind_Link_1193858205" MODIFIED="1292899986023" TEXT="WallEntryPersistence.findByPrimaryKey(wallEntryId)"/>
</node>
</node>
<node CREATED="1292899773805" ID="Freemind_Link_504344748" MODIFIED="1292899812570" TEXT="WallEntryLocalServiceUtil.updateWallEntry(wallEntryId, comments) : Update a Wall entry">
<node CREATED="1292780362159" ID="Freemind_Link_443507378" MODIFIED="1292780472880" TEXT="getService() --&gt; WallEntryLocalService"/>
<node CREATED="1292899884648" ID="Freemind_Link_1120375134" MODIFIED="1292900188570" TEXT="WallEntryLocalService.updateWallEntry(wallEntryId, comments)">
<node CREATED="1292899960835" ID="Freemind_Link_1120758419" MODIFIED="1292899986023" TEXT="WallEntryPersistence.findByPrimaryKey(wallEntryId)"/>
<node CREATED="1292900004288" ID="Freemind_Link_1919302327" MODIFIED="1292900005585" TEXT="wallEntry.setModifiedDate(new Date());"/>
<node CREATED="1292900006226" ID="Freemind_Link_1622555125" MODIFIED="1292900018710" TEXT="wallEntry.setComments(comments);"/>
<node CREATED="1292900029695" ID="Freemind_Link_1057717413" MODIFIED="1292900031054" TEXT="wallEntryPersistence.update(wallEntry, false);"/>
</node>
</node>
</node>
<node CREATED="1292473000259" ID="Freemind_Link_1851219312" MODIFIED="1292780646520" TEXT="Dao : WallEntryPersistence"/>
<node CREATED="1292927323132" ID="ID_1962158633" MODIFIED="1292927331174" TEXT="Custom SQL"/>
<node CREATED="1292927461971" ID="ID_1109749245" MODIFIED="1292927469591" TEXT="Join SQL"/>
</node>
</node>
<node CREATED="1292582912540" ID="Freemind_Link_1883627029" MODIFIED="1292582912540" POSITION="left" TEXT="">
<node CREATED="1292582894728" ID="Freemind_Link_1383485354" MODIFIED="1292582940384" TEXT="Friend">
<node CREATED="1292582959181" ID="Freemind_Link_638791950" MODIFIED="1292582962368" TEXT="View">
<node CREATED="1292813994842" ID="Freemind_Link_35395465" MODIFIED="1292814021389" TEXT="friends/view_friends.jspf"/>
<node CREATED="1292814207619" ID="Freemind_Link_1387681421" MODIFIED="1292814213075" TEXT="com.liferay.util.bridges.mvc.MVCPortlet : action"/>
</node>
<node CREATED="1292582921118" ID="Freemind_Link_779882017" MODIFIED="1292582924665" TEXT="Model">
<node CREATED="1292815745925" ID="Freemind_Link_1704473889" MODIFIED="1292815753357" TEXT="User(UserModelImpl)"/>
<node CREATED="1292815831395" ID="Freemind_Link_816210010" MODIFIED="1292815836881" TEXT="Table name : User_"/>
</node>
<node CREATED="1292582925181" ID="Freemind_Link_1396338461" MODIFIED="1292582928556" TEXT="Service">
<node CREATED="1292816816978" ID="Freemind_Link_450665314" MODIFIED="1292816825772" TEXT="UserLocalServiceUtil"/>
<node CREATED="1292816826241" ID="Freemind_Link_1079141409" MODIFIED="1292816834878" TEXT="UserLocalServiceImpl"/>
</node>
<node CREATED="1292582929165" ID="Freemind_Link_402921515" MODIFIED="1292816860953" TEXT="Dao : UserPersistence"/>
</node>
</node>
</node>
</map>
