<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1291789610873" ID="Freemind_Link_537194582" MODIFIED="1291789623996" TEXT="create Ext plugin">
<node CREATED="1291789788139" ID="_" MODIFIED="1291789794591" POSITION="right" TEXT="Ext plugins provide the most powerful methods of extending Liferay. "/>
<node CREATED="1291789823899" ID="Freemind_Link_781246712" MODIFIED="1291790740737" POSITION="right" TEXT="Ext plugins  are designed to be used only in special scenarios in which all other  plugin types cannot meet the needs of the project. It is recommended to use another plugin type &#xa;to accomplish your goals if it is at all possible."/>
<node CREATED="1291789903494" ID="Freemind_Link_1703353026" MODIFIED="1291789904729" POSITION="right" TEXT="Ext plugins  are designed to be used only in special scenarios in which all other  plugin types cannot meet the needs of the project. "/>
<node CREATED="1291790007711" ID="Freemind_Link_1266197061" MODIFIED="1291790050235" POSITION="right" TEXT=" Because Ext plugins allow using internal APIs or  even overwriting files provided in the Liferay core, it is a problem when updating to a new version of Liferay"/>
<node CREATED="1291790095852" ID="Freemind_Link_1776411270" MODIFIED="1291790098429" POSITION="right" TEXT="unlike the  other types of plugins, Ext plugins require the server to be rebooted af- ter deployment, as well as requiring additional steps  when deploying  or redeploying to production systems."/>
<node CREATED="1291790527105" ID="Freemind_Link_635578060" MODIFIED="1291790572988" POSITION="right" TEXT="Developing an Ext plugin is slightly different than working with oth- er plugin types: &#xa; an Ext plugin when deployed will make changes to the Liferay web application itself, instead of staying as a separate component that can be removed at any time."/>
<node CREATED="1291790598988" ID="Freemind_Link_470564214" MODIFIED="1291790600801" POSITION="right" TEXT="once an Ext plugin has  been deployed, some of its files are copied inside the Liferay installa- tion, so the only way to remove its changes is to redeploy an unmodi- fied Liferay application again."/>
<node CREATED="1291790677123" ID="Freemind_Link_1179072041" MODIFIED="1291790691455" POSITION="right" TEXT="The Plugins SDK contains several Ant targets. r to do this it requires a  .zip  file of a Tomcat 6 based Liferay bun- dle. The Ant targets will unzip and clean up this installation whenever  needed to guarantee that any change done to the Ext plugin during  development is properly applied and previous changes that have been  removed are not left behind"/>
<node CREATED="1291790129815" ID="Freemind_Link_90874417" MODIFIED="1291790139954" POSITION="left" TEXT="use Ext Plugin when">
<node CREATED="1291790140953" ID="Freemind_Link_11113122" MODIFIED="1291790150124" TEXT="Customizing   portal.properties   that   are   not   supported   by  Hook Plugins"/>
<node CREATED="1291790159169" ID="Freemind_Link_107465396" MODIFIED="1291790161856" TEXT="Customizing Struts Actions "/>
<node CREATED="1291790241311" ID="Freemind_Link_520936868" MODIFIED="1291790242373" TEXT=" Providing  custom implementations for any of the Liferay  beans declared in Liferay&apos;s Spring files (use service wrap- pers from a hook instead if possible) "/>
<node CREATED="1291790258339" ID="Freemind_Link_1074673513" MODIFIED="1291790259433" TEXT="Adding JSPs that are referenced from portal properties that  can only be changed from an ext plugin (use hook plugin if  possible) "/>
<node CREATED="1291790269166" ID="Freemind_Link_153223012" MODIFIED="1291790270181" TEXT="Direct overwriting of a class (not recommended unless it&apos;s  strictly necessary) "/>
</node>
<node CREATED="1291790958537" ID="Freemind_Link_192640024" MODIFIED="1291790978319" POSITION="left" TEXT="set up before deploying Ext">
<node CREATED="1291790980522" ID="Freemind_Link_472018115" MODIFIED="1291791155147" TEXT=" edit the  file   build.{username}.properties   in the root folder of the Plugins SDK:&#xa;app.server.dir=  C:\ext-work\liferay-portal-${lp.version}\tom-cat-6.0.18&#xa;app.server.zip.name=C:\files\liferay-portal-tomcat-6.0-${lp.version}.zip&#xa;ext.work.dir=C:\ext-work">
<icon BUILTIN="bookmark"/>
</node>
</node>
<node CREATED="1291791482435" ID="Freemind_Link_1424141594" MODIFIED="1291791541966" POSITION="left" TEXT="Note: cannot redeploy in Liferay IDE, must use command line with Ant">
<icon BUILTIN="bookmark"/>
</node>
<node CREATED="1291790332420" ID="Freemind_Link_1159582787" MODIFIED="1291793840470" POSITION="left" TEXT="steps" VSHIFT="33">
<node CREATED="1291790338458" ID="Freemind_Link_1989195371" MODIFIED="1291790371325" TEXT="New / Liferay plugin project / Ext"/>
<node CREATED="1291790418319" ID="Freemind_Link_624675235" MODIFIED="1291794633442" TEXT="after creating an Ext plugin, remove all of the files added by de- fault that are not necessary for the extension"/>
<node CREATED="1291791192194" ID="Freemind_Link_1558395157" MODIFIED="1291791207505" TEXT="add code for customizing"/>
<node CREATED="1291791238767" ID="Freemind_Link_1273373741" MODIFIED="1291794640539" TEXT="Redeployment">
<icon BUILTIN="bookmark"/>
<node CREATED="1291791241392" ID="Freemind_Link_318663088" MODIFIED="1291791256656" TEXT="stop application server (stop tomcat)"/>
<node CREATED="1291791263015" ID="Freemind_Link_410798807" MODIFIED="1291791264265" TEXT="ant clean-app-server direct-deploy"/>
<node CREATED="1291791271483" ID="Freemind_Link_545055025" MODIFIED="1291791287575" TEXT="start applications server"/>
</node>
</node>
<node CREATED="1291791295917" ID="Freemind_Link_1747317974" MODIFIED="1291793838423" POSITION="left" TEXT="sample 1" VSHIFT="56">
<node CREATED="1291791333476" ID="Freemind_Link_701222138" MODIFIED="1291791344729" TEXT="goal: customize the  sections of a user profile"/>
<node CREATED="1291791352090" ID="Freemind_Link_1170886997" MODIFIED="1291791359295" TEXT="steps">
<node CREATED="1291791381926" HGAP="24" ID="Freemind_Link_1773523024" MODIFIED="1291793830096" TEXT="edit file docroot/WEB-INF/ext-impl/src/portal-ext.properties: &#xa;users.form.update.main=details,password,organizations,communities,roles" VSHIFT="-22"/>
<node CREATED="1291793768274" ID="Freemind_Link_521338144" MODIFIED="1291793823018" TEXT="because it is the first time update Liferay so no need to redeploy server, just: ant deploy"/>
<node CREATED="1291793873873" ID="Freemind_Link_1807970580" MODIFIED="1291793886840" TEXT="See result: go to  Control Panel -&gt; Users. Edit an existing user and verify that the right  navigation  menu only shows the five sections that were referenced  from the  users.form.update.main  property"/>
</node>
</node>
<node CREATED="1291793899433" ID="Freemind_Link_137486605" MODIFIED="1291804316858" POSITION="left" TEXT="sample 2">
<node CREATED="1291793916103" ID="Freemind_Link_924614491" MODIFIED="1291793920181" TEXT="goal: customize the details view of the user profile."/>
<node CREATED="1291793969051" HGAP="24" ID="Freemind_Link_605734901" MODIFIED="1291794371278" TEXT="steps" VSHIFT="20">
<node CREATED="1291793983831" ID="Freemind_Link_920595695" MODIFIED="1291794362076" TEXT="we remove the section  details and added a new custom one called basic by modify the property users.form.update.main:&#xa;users.form.update.main=basic,password,organizations,communities,roles"/>
<node CREATED="1291794109178" ID="Freemind_Link_992514353" MODIFIED="1291794151940" TEXT="create file ext-web/docroot/html/portlet/enterprise_admin/user/basic.jsp with content you want"/>
<node CREATED="1291794133848" ID="Freemind_Link_1706727151" MODIFIED="1291794602241" TEXT="In our case, we don&apos;t need to add a new key to  Language-ext.proper- ties , because &#x201c;basic&#x201d; is already included in Liferay&apos;s language bundle. "/>
<node CREATED="1291794614856" ID="Freemind_Link_863144267" MODIFIED="1291794616091" TEXT="Redeployment"/>
</node>
<node CREATED="1291794428228" ID="Freemind_Link_22880016" MODIFIED="1291804313671" TEXT="Liferay looks for the implementation of each section based on the follow- ing conventions:">
<node CREATED="1291794451332" ID="Freemind_Link_427077065" MODIFIED="1291794469730" TEXT="In our case, that section should be implemented in a JSP inside the di- rectory: html/portlet/enterprise_admin/user"/>
<node CREATED="1291794503667" ID="Freemind_Link_1286110515" MODIFIED="1291794512561" TEXT="If the section name has a dash sign (&#x201c;-&#x201d;), it will be converted to an  underscore sign (&#x201c;_&#x201d;). For example, if the section is called  my-info, the JSP should be named   my_info.jsp ."/>
<node CREATED="1291794565850" ID="Freemind_Link_625995582" MODIFIED="1291794567554" TEXT="  The name of the section that will be shown to the user will  be   looked   for   in   the   language   bundles.   When   using   a  key/value that is not already among the ones included with  Liferay, you should add it to the  Language-ext.properties  and  each of the language variants for which we want to provide  a translation. Within the Ext plugin these files should be  placed within  ext-impl/src ."/>
</node>
</node>
</node>
</map>
