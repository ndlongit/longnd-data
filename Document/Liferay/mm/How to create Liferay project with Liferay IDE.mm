<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1291625744399" ID="Freemind_Link_1581335126" MODIFIED="1291627535008" TEXT="How to create and deploy Liferay project with Liferay IDE">
<node CREATED="1291626027063" ID="_" MODIFIED="1291627562993" POSITION="right" TEXT="install Plugins SDK">
<icon BUILTIN="full-2"/>
<node CREATED="1291626322617" HGAP="27" ID="Freemind_Link_115877603" MODIFIED="1291626339227" TEXT="just extract zip file" VSHIFT="11"/>
<node CREATED="1291626079752" ID="Freemind_Link_1973474396" MODIFIED="1291626126628" TEXT="create a file properties for build: &quot;build.bam.nguyen.properties&quot;"/>
<node CREATED="1291626127815" ID="Freemind_Link_153459531" MODIFIED="1291626180114" TEXT="modify path to point to tomcat of Lifereay:&#xa; app.server.dir=E:/software/development/liferay-portal-tomcat-6.0.5/liferay-portal-6.0.5/tomcat-6.0.26"/>
</node>
<node CREATED="1291627396488" ID="Freemind_Link_1910790977" MODIFIED="1292222120633" POSITION="right" TEXT="in Eclipse, point Liferay/Installed SDK to Plugins SDK installed above">
<icon BUILTIN="full-3"/>
</node>
<node CREATED="1291627439224" ID="Freemind_Link_924920473" MODIFIED="1292222129914" POSITION="right" TEXT="in Eclipse, ponit Server Runtime to tomcat of Liferay Portal">
<icon BUILTIN="full-4"/>
</node>
<node CREATED="1291626298476" ID="Freemind_Link_1931420105" MODIFIED="1291627560711" POSITION="left" TEXT="install Liferay Portal Tomcat bundle">
<icon BUILTIN="full-1"/>
<node CREATED="1291626366806" ID="Freemind_Link_1657998262" MODIFIED="1291626376290" TEXT="just extract zip file"/>
<node CREATED="1291626377009" ID="Freemind_Link_1236721412" MODIFIED="1291626380556" TEXT="install Ant"/>
<node CREATED="1291626430089" ID="Freemind_Link_1759811049" MODIFIED="1291626553170" TEXT="run file startup.bat in folder bin of tomcat to test"/>
</node>
<node CREATED="1291627551242" ID="Freemind_Link_1695178646" MODIFIED="1292222941133" POSITION="left" TEXT="create a Portlet project by New / Liferay Plug-in Project and choose Pprtlet">
<icon BUILTIN="full-5"/>
</node>
<node CREATED="1291627657229" ID="Freemind_Link_1571064350" MODIFIED="1291627676433" POSITION="left" TEXT="deploy is same using WTP">
<icon BUILTIN="full-6"/>
</node>
<node CREATED="1291627686684" ID="Freemind_Link_1086309730" MODIFIED="1291627804095" POSITION="left" TEXT="use porlet: login into Liferay Portal and go to Add / More / Sample..">
<icon BUILTIN="full-7"/>
</node>
<node CREATED="1292223436825" ID="Freemind_Link_1824660077" MODIFIED="1292223445685" POSITION="left" TEXT="anatomy of Portlet">
<node CREATED="1292223457591" ID="Freemind_Link_40555955" MODIFIED="1292223461607" TEXT="soure files">
<node CREATED="1292223581125" ID="Freemind_Link_1388081462" MODIFIED="1292223582438" TEXT="in the docroot/WEB-INF/src folder"/>
</node>
<node CREATED="1292223461998" ID="Freemind_Link_174714475" MODIFIED="1292223465607" TEXT="configuration files">
<node CREATED="1292223597141" ID="Freemind_Link_1369246427" MODIFIED="1292223598766" TEXT=" in the docroot/WEB-INF folder"/>
<node CREATED="1292223921644" ID="Freemind_Link_516928604" MODIFIED="1292223923925" TEXT=" portlet.xml">
<node CREATED="1292223926160" ID="Freemind_Link_670071427" MODIFIED="1292224358772" TEXT="describe general info of all portlets"/>
</node>
<node CREATED="1292223756346" ID="Freemind_Link_1814752615" MODIFIED="1292223765331" TEXT="liferay-display.xml">
<node CREATED="1292223666595" ID="Freemind_Link_1798395370" MODIFIED="1292223775237" TEXT="describes   what   category   the   portlet  should appear under in the Add menu."/>
</node>
<node CREATED="1292223779987" HGAP="22" ID="Freemind_Link_1804698760" MODIFIED="1292223827347" TEXT="liferay-portlet.xml" VSHIFT="20">
<node CREATED="1292223712940" ID="Freemind_Link_819742444" MODIFIED="1292223824097" TEXT="describes some optional Liferay-specific  enhancements for JSR-286 portlets that are installed on a Liferay Portal  server" VSHIFT="-20"/>
<node CREATED="1292223812987" ID="Freemind_Link_807037642" MODIFIED="1292223815268" TEXT=" For example, you can set whether a portlet is instanceable"/>
</node>
</node>
<node CREATED="1292223468467" ID="Freemind_Link_550061124" MODIFIED="1292223483389" TEXT="client-side files (*.jsp, *.css, *.js, graphics, etc.)">
<node CREATED="1292223893503" ID="Freemind_Link_462656147" MODIFIED="1292223896019" TEXT="to implement your portlet&apos;s user interface."/>
<node CREATED="1292223979144" ID="Freemind_Link_1392052922" MODIFIED="1292223980941" TEXT="These files should go  in the docroot folder somewhere"/>
</node>
<node CREATED="1292223550796" ID="Freemind_Link_916880955" MODIFIED="1292223551859" TEXT="New   portlets   are   configured   by   default   to   use   the   MVCPortlet  framework, which uses separate JSPs for each page in the portlet, and  for each of the three portlet modes: view, edit, and help."/>
</node>
<node CREATED="1292224577976" ID="Freemind_Link_14145049" MODIFIED="1292224579336" POSITION="left" TEXT="Adding Friendly URL Mapping  to the Portlet">
<node CREATED="1292224591570" HGAP="26" ID="Freemind_Link_113377188" MODIFIED="1292224671758" TEXT=" edit liferay-portlet.xml:&#xa;&lt;friendly-url-mapper-class&gt;com.liferay.portal.kernel.portlet.Default\&#xa;FriendlyURLMapper&lt;/friendly-url-mapper-class&gt;&#xa;&lt;friendly-url-mapping&gt;my-greeting&lt;/friendly-url-mapping&gt;&#xa;&lt;friendly-url-routes&gt;com/sample/mygreeting/portlet/my-greeting-friendly-url\&#xa;-routes.xml&lt;/friendly-url-routes&gt;" VSHIFT="16"/>
<node CREATED="1292224634383" ID="Freemind_Link_1348285925" MODIFIED="1292224680852" TEXT="/docroot/WEB-INF/src/com/sample/mygreeting/portlet/my\ -greeting-friendly-url-routes.xml with content:&#xa;&lt;?xml version=&quot;1.0&quot;?&gt;&#xa;&lt;!DOCTYPE routes PUBLIC &quot;-//Liferay//DTD Friendly URL Routes 6.0.0//EN&quot; &#xa;&quot;http://www.liferay.com/dtd/liferay-friendly-url-routes_6_0_0.dtd&quot;&gt;&#xa;&lt;routes&gt;&#xa;&lt;route&gt;&#xa;&lt;pattern&gt;/{jspPageName}&lt;/pattern&gt;&#xa;&lt;generated-parameter name=&quot;jspPage&quot;&gt;/{jspPageName}.jsp&lt;/generated-&#xa;parameter&gt;&#xa;&lt;/route&gt;&#xa;&lt;/routes&gt;" VSHIFT="30"/>
</node>
</node>
</map>
