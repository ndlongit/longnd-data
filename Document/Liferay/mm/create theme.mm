<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1291695189410" ID="Freemind_Link_1698542719" MODIFIED="1291695353838" TEXT="create theme">
<node CREATED="1291695248803" ID="_" MODIFIED="1291695275819" POSITION="right" TEXT="New / Liferay plug-in project / Theme">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1292223069408" ID="Freemind_Link_1688927647" MODIFIED="1292223108769" POSITION="right" TEXT="Deploy: use WTP of Eclipse">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1291695302758" ID="Freemind_Link_372551511" MODIFIED="1292223115160" POSITION="right" TEXT="use theme: hover over Manage at the top of the page, and click on Page. Di- rectly underneath the words Manage Pages select the Look and Feel  tab. Simply click on your theme to activate it.">
<icon BUILTIN="full-3"/>
</node>
<node CREATED="1291695468044" ID="Freemind_Link_820013152" MODIFIED="1292223126582" POSITION="left" TEXT="Anatomy of a Theme">
<node CREATED="1291695473841" ID="Freemind_Link_480342230" MODIFIED="1291695483372" TEXT="theme.parent"/>
<node CREATED="1291695484232" ID="Freemind_Link_231047652" MODIFIED="1291695530436" TEXT="_diffs  folder">
<node CREATED="1291695562671" ID="Freemind_Link_1645404590" MODIFIED="1291695564327" TEXT="to customize the parts of your theme that will differ from the  parent theme"/>
<node CREATED="1291695565327" ID="Freemind_Link_1379178540" MODIFIED="1292223229695" TEXT="To do this, you mirror the directory structure of the parent theme inside of the  _diffs  folder, placing only the folders and files  you need to customize there."/>
<node CREATED="1292225428633" ID="Freemind_Link_1220583896" MODIFIED="1292225430274" TEXT="For example, to customize the naviga- tion,       you       would       copy        navigation.vm        from        deep-blue- theme/docroot/templates/navigation.vm   into  deep-blue- theme/docroot/_diffs/templates  folder"/>
</node>
</node>
<node CREATED="1291695640439" ID="Freemind_Link_791947490" MODIFIED="1291695647236" POSITION="left" TEXT="thumbnails"/>
<node CREATED="1291695673877" ID="Freemind_Link_1138407769" MODIFIED="1291695675158" POSITION="left" TEXT=" JavaScript">
<node CREATED="1291695717706" ID="Freemind_Link_1101317059" MODIFIED="1292223295603" TEXT="Inside of the  main.js  file, definitions for three JavaScript callbacks"/>
<node CREATED="1292226884213" ID="Freemind_Link_1990264372" MODIFIED="1292226885838" TEXT="AUI().ready(fn); This callback is executed as soon as the HTML in the page has fin- ished loading (minus any portlets loaded via ajax)."/>
<node CREATED="1292226906279" ID="Freemind_Link_1770143766" MODIFIED="1292226907873" TEXT="Liferay.Portlet.ready(fn); Executed after each portlet on the page has loaded. The callback  receives two parameters: portletId and node. portletId is the id of the  portlet that was just loaded. node is the Alloy Node object of the same  portlet."/>
<node CREATED="1292226915484" ID="Freemind_Link_843551406" MODIFIED="1292226916828" TEXT=" Liferay.on(&apos;allPortletsReady&apos;, fn); Executed   after   everything&#x2014;including   AJAX   portlets&#x2014;has   finished  loading. "/>
</node>
<node CREATED="1291695749941" ID="Freemind_Link_23288556" MODIFIED="1291695751144" POSITION="left" TEXT="Settings">
<node CREATED="1292226957412" ID="Freemind_Link_1342204659" MODIFIED="1292226959506" TEXT="Each theme can define settings to make it configurable"/>
<node CREATED="1292226992480" ID="Freemind_Link_709527497" MODIFIED="1292227180898" TEXT="These settings are defined in a file named  liferay-look-and-feel.xml  inside  WEB-INF . "/>
<node CREATED="1291695783895" ID="Freemind_Link_1335777849" MODIFIED="1291695785301" TEXT="&lt;setting key=&quot;my-setting&quot; value=&quot;my-value&quot; /&gt;"/>
<node CREATED="1292227182570" ID="Freemind_Link_1244353827" MODIFIED="1292227210697" TEXT="We will use these settings in files .vm"/>
<node CREATED="1291695790238" ID="Freemind_Link_1257572279" MODIFIED="1291695791473" TEXT="$theme.getSetting(&quot;my-setting&quot;)"/>
<node CREATED="1291695804582" ID="Freemind_Link_810137029" MODIFIED="1291695809004" TEXT="sample">
<node CREATED="1291695809926" ID="Freemind_Link_95276149" MODIFIED="1291695811083" TEXT="&lt;setting key=&quot;header-type&quot; value=&quot;detailed&quot; /&gt;"/>
<node CREATED="1291695831677" ID="Freemind_Link_1035212378" MODIFIED="1291695833161" TEXT="&lt;setting key=&quot;header-type&quot; value=&quot;brief&quot; /&gt;"/>
<node CREATED="1291695833802" ID="Freemind_Link_1562896753" MODIFIED="1291695864865" TEXT="#if ($theme.getSetting(&quot;header-type&quot;) == &quot;detailed&quot;) &#xa;   #parse (&quot;$full_templates_path/header_detailed.vm&quot;) &#xa;#else &#xa;   #parse (&quot;$full_templates_path/header_brief.vm&quot;) #end"/>
</node>
</node>
<node CREATED="1291695872646" ID="Freemind_Link_1898286807" MODIFIED="1292227371848" POSITION="left" TEXT="Color Schemes" VSHIFT="20">
<node CREATED="1292227348128" ID="Freemind_Link_341963541" MODIFIED="1292227349847" TEXT="are specified using a CSS class name"/>
<node CREATED="1292227366801" ID="Freemind_Link_438946147" MODIFIED="1292227368317" TEXT="you can not only change colors, but also choose different background  images, different border colors, and so on."/>
</node>
<node CREATED="1291696081088" ID="Freemind_Link_1715808682" MODIFIED="1292227375067" POSITION="left" TEXT="Portal Predefined Settings" VSHIFT="29">
<node CREATED="1292227526716" ID="Freemind_Link_1476136633" MODIFIED="1292227528637" TEXT="These settings can be modified  from  liferay-look-and-feel.xml ."/>
<node CREATED="1291696113011" ID="Freemind_Link_150975109" MODIFIED="1291696114308" TEXT="portlet-setup-show-borders-default">
<node CREATED="1292227495558" ID="Freemind_Link_1534762441" MODIFIED="1292227630735" TEXT="If set to false, the portal will turn off borders by default for ALL the  portlets. The default is true"/>
<node CREATED="1292227540653" ID="Freemind_Link_511476722" MODIFIED="1292227541810" TEXT="&lt;settings&gt; &lt;setting key=&quot;portlet-setup-show-borders-default&quot; value=&quot;false&quot; /&gt; &lt;/settings&gt;"/>
<node CREATED="1292227655376" ID="Freemind_Link_1738797036" MODIFIED="1292227680002" TEXT="This default behavior can be overridden for individual portlets using: liferay-portlet.xml or Portlet CSS popup setting"/>
</node>
<node CREATED="1291696121386" ID="Freemind_Link_588969731" MODIFIED="1291707024201" TEXT="bullet-style-options">
<node CREATED="1292227735425" ID="Freemind_Link_1308136397" MODIFIED="1292227736660" TEXT="This setting is used by the Navigation portlet to determine the CSS  class name of the list of pages."/>
<node CREATED="1292227813146" ID="Freemind_Link_1528438414" MODIFIED="1292227818849" TEXT="The value must be a comma separated  list of valid bullet styles to be used."/>
</node>
</node>
</node>
</map>
