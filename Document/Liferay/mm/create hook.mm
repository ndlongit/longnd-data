<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1291707029826" ID="Freemind_Link_228562328" MODIFIED="1291707033654" TEXT="create hook">
<node CREATED="1291707067592" ID="_" MODIFIED="1291707084764" POSITION="right" TEXT="overriding a JSP">
<node CREATED="1291707110654" ID="Freemind_Link_618569533" MODIFIED="1291707112264" TEXT="create  the   directory    hooks/example-hook/docroot/META-INF/custom_jsps ."/>
<node CREATED="1291707166435" ID="Freemind_Link_1904035463" MODIFIED="1291707168389" TEXT=" The directory structure inside this folder must mirror the one  within  liferay-portal-[version]/tomcat-6.0.26/webapps/ROOT"/>
<node CREATED="1291707223639" ID="Freemind_Link_935606093" MODIFIED="1291707232780" TEXT="To   over- ride the Terms of Use, copy  liferay-portal-[version]/tomcat-6.0.26/we- bapps/ROOT/html/portal/terms_of_use.jsp        to                hooks/example- hook/docroot/META-INF/custom_jsps/html/portal/terms_of_use.jsp . Then modify that file"/>
<node CREATED="1291707126545" ID="Freemind_Link_608902235" MODIFIED="1291707137607" TEXT="edit   hooks/exam- ple-hook/docroot/WEB-INF/liferay-hook.xml: &lt;custom-jsp-dir&gt;/META-INF/custom_jsps&lt;/custom-jsp-dir&gt;"/>
<node CREATED="1291707294390" ID="Freemind_Link_848691862" MODIFIED="1291707295546" TEXT="If     you     look     inside     the       liferay-portal-[version]/tomcat- 6.0.26/webapps/ROOT/html/portal  directory you will see that there are now  two terms of use files, one called   terms_of_use.jsp   and another called  terms_of_use.portal.jsp .   terms_of_use.jsp   is the version from your hook,  while   terms_of_use.portal.jsp   is the original."/>
</node>
<node CREATED="1291711562663" ID="Freemind_Link_1151824095" MODIFIED="1291711565100" POSITION="right" TEXT="Overriding a Portal Service">
<node CREATED="1291711639040" ID="Freemind_Link_1055870345" MODIFIED="1291711675400" TEXT="way to modify the functionality of  UserLocalService  from our hook">
<node CREATED="1291711676493" ID="Freemind_Link_1112273149" MODIFIED="1292232790941" TEXT="create a class that extends from  UserLocalServiceWrapper , override some of its methods"/>
<node CREATED="1291711695025" ID="Freemind_Link_975455990" MODIFIED="1291711714916" TEXT="instruct Liferay to inject  our class into  UserLocalServiceUtil ."/>
</node>
<node CREATED="1291711724401" ID="Freemind_Link_1060053462" MODIFIED="1291711736338" TEXT="steps">
<node CREATED="1291711759479" ID="Freemind_Link_973396024" MODIFIED="1292232787082" TEXT=" inside   example-hook/docroot/WEB-INF/src/com/sample/hook   create   a  new file called  MyUserLocalServiceImpl.java which extend class UserLocalServiceWrapper"/>
<node CREATED="1291711794449" ID="Freemind_Link_1427657229" MODIFIED="1291711809465" TEXT="edit  liferay-hook.xml  inside  example-hook/docroot/WEB-INF  and add  the following:&#xa;&lt;service&gt;&#xa;&lt;service-type&gt;com.liferay.portal.service.UserLocalService&lt;/service-type&gt;&#xa;&lt;service-impl&gt;com.sample.hook.MyUserLocalServiceImpl&lt;/service-impl&gt;&#xa;&lt;/service&gt;"/>
</node>
</node>
<node CREATED="1291713156225" ID="Freemind_Link_1587029210" MODIFIED="1291713158131" POSITION="right" TEXT="Overriding a Language.properties File">
<node CREATED="1291713180803" ID="Freemind_Link_1149181499" MODIFIED="1291713183304" TEXT="The process is extremely similar to any of the ones we have  just   described."/>
</node>
<node CREATED="1291707307906" ID="Freemind_Link_1218158097" MODIFIED="1291707309468" POSITION="left" TEXT="Performing a Custom Action">
<node CREATED="1291707661755" ID="Freemind_Link_1624638763" MODIFIED="1291707666255" TEXT="way">
<node CREATED="1291707668896" ID="Freemind_Link_910897994" MODIFIED="1291707681380" TEXT="in order to create a custom action we  will also need to extend portal.properties file"/>
<node CREATED="1291707756319" ID="Freemind_Link_1707603885" MODIFIED="1291707759148" TEXT="There are several other events that you can define custom actions  for using hooks. Some of these actions must extend from  com.liferay.- portal.kernel.events.Action ,   while   others   must   extend   com.liferay.por- tal.struts.SimpleAction . "/>
</node>
<node CREATED="1291707690302" ID="Freemind_Link_1242701776" MODIFIED="1291712505025" TEXT="steps" VSHIFT="34">
<node CREATED="1291707347500" ID="Freemind_Link_1375474790" MODIFIED="1291707350312" TEXT="create        the       directory         example-hook/docroot/WEB- INF/src/com/sample/hook "/>
<node CREATED="1291707360250" ID="Freemind_Link_385009582" MODIFIED="1291707361469" TEXT="create the file  LoginAction.java  inside it"/>
<node CREATED="1291707379188" ID="Freemind_Link_711980748" MODIFIED="1291707408829" TEXT="create the file   portal.properties   inside   example-hook/docroot/WE- B-INF/src  with the following content:&#xa;login.events.pre=com.sample.hook.LoginAction"/>
<node CREATED="1291707424048" ID="Freemind_Link_632515664" MODIFIED="1291707459189" TEXT=" edit   liferay-hook.xml   inside   example-hook/docroot/WEB-INF F   and  add the following line above  &lt;custom-jsp-dir&gt; :&#xa;&lt;portal-properties&gt;portal.properties&lt;/portal-properties&gt;"/>
</node>
</node>
<node CREATED="1291711244404" ID="Freemind_Link_388461171" MODIFIED="1291712498962" POSITION="left" TEXT=" Extending and Overriding portal.properties" VSHIFT="59">
<node CREATED="1291711357783" ID="Freemind_Link_1758471593" MODIFIED="1292232845551" TEXT="It is safe to modify portal properties (accept many values) from multiple hooks, and they will not interfere with one another. "/>
<node CREATED="1291711400971" ID="Freemind_Link_1272028096" MODIFIED="1291711422222" TEXT="Some portal  properties only accept a single value.  You should only modify  these properties from one hook, otherwise Liferay will not know which  value to use. "/>
<node CREATED="1291711453348" ID="Freemind_Link_1109480705" MODIFIED="1291711455473" TEXT="You can determine which type a particular property is by  looking in  portal.properties ."/>
<node CREATED="1291711475411" ID="Freemind_Link_932252570" MODIFIED="1292232877364" TEXT="Not all portal properties can be overridden in a hook. A complete  list   of   the   available   properties   can   be   found   in   the   DTD   for   liferay-hook.xml  in the  definitions  folder of the Liferay source code"/>
<node CREATED="1291711477551" ID="Freemind_Link_1897294297" MODIFIED="1292232916552" TEXT="In addition to defining custom actions, hooks can also override portal properties to define model listeners, validators, generators, and content sanitizers."/>
</node>
</node>
</map>
