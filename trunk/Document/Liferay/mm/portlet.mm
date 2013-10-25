<map version="0.8.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1291174662557" ID="Freemind_Link_1464311215" MODIFIED="1291360133962" TEXT="portlet">
<icon BUILTIN="bookmark"/>
<node CREATED="1291175059313" ID="_" MODIFIED="1291175061126" POSITION="right" TEXT="are pluggable user interface software components that are managed and displayed in a web portal"/>
<node CREATED="1291200158582" ID="Freemind_Link_1710699382" MODIFIED="1291200160051" POSITION="right" TEXT="A portlet is a web component that processes requests and generates  dynamic content. "/>
<node CREATED="1291200172020" ID="Freemind_Link_697411381" MODIFIED="1291200174270" POSITION="right" TEXT="The content generated by a portlet is also called a fragment (e.g.  HTML, XHTML, WML) and can be aggregated with other fragments to  form a complete document. "/>
<node CREATED="1291277268930" ID="Freemind_Link_1723008355" MODIFIED="1291277279415" POSITION="right" TEXT="is an application that provides a specific  piece of content to be included as part of a portal  page"/>
<node CREATED="1291277303759" ID="Freemind_Link_1869333496" MODIFIED="1291277305119" POSITION="right" TEXT="It is managed by a portlet container that  processes requests and generates dynamic content."/>
<node CREATED="1291277325010" ID="Freemind_Link_35764122" MODIFIED="1291277326838" POSITION="right" TEXT=" the portlets are used  by portals as pluggable user interface components."/>
<node CREATED="1291277418871" ID="Freemind_Link_1346648537" MODIFIED="1291277420293" POSITION="right" TEXT="The content generated by a portlet is also called a fragment. A fragment is a piece  of markup (for example, HTML, XHTML, WML, and so on) adhering to certain  rules and can be aggregated with other fragments to form a complete document. "/>
<node CREATED="1291277564061" ID="Freemind_Link_24573421" MODIFIED="1291277565358" POSITION="right" TEXT=" The life  cycle of a portlet is managed by the portlet container."/>
<node CREATED="1291277737096" ID="Freemind_Link_1210216066" MODIFIED="1291621411994" POSITION="right" TEXT="The content generated by a portlet may vary from one user to another depending  on the user configuration for the portlet"/>
<node CREATED="1291277835379" ID="Freemind_Link_476014263" MODIFIED="1291360147869" POSITION="right" TEXT="what is portlet container?">
<icon BUILTIN="bookmark"/>
<node CREATED="1291277960491" ID="Freemind_Link_707678848" MODIFIED="1291277962209" TEXT="runs portlets, provides them with the required runtime  environment, and manages their life cycles. "/>
<node CREATED="1291277979694" ID="Freemind_Link_1915972369" MODIFIED="1291277982132" TEXT=" also provides persistent storage for  portlet preferences."/>
<node CREATED="1291278033117" ID="Freemind_Link_1543373605" MODIFIED="1291278034867" TEXT="receives requests from the portal to execute  requests on the portlets hosted by it."/>
<node CREATED="1291278079493" ID="Freemind_Link_1926889995" MODIFIED="1291278106321" TEXT="is not responsible for aggregating the content produced  by the portlets. It is the responsibility of the portal to handle the aggregation"/>
<node CREATED="1291278135885" ID="Freemind_Link_960258999" MODIFIED="1291278137369" TEXT="A  portal and a portlet container can be built together as a single component of an  application suite or as two separate components of a portal application."/>
<node CREATED="1291278339638" ID="Freemind_Link_1704692813" MODIFIED="1291278345482" TEXT=" typical sequence of events, initiated when you access the portal  page">
<node CREATED="1291278346576" ID="Freemind_Link_1121269599" MODIFIED="1291278368389" TEXT="A client makes an  HTTP request to the portal"/>
<node CREATED="1291278378467" ID="Freemind_Link_482566865" MODIFIED="1291278379686" TEXT="The request is received by the portal"/>
<node CREATED="1291278391671" ID="Freemind_Link_840275903" MODIFIED="1291278402140" TEXT="The portal determines if the request contains an action targeted at the portlets"/>
<node CREATED="1291278412530" ID="Freemind_Link_1462227742" MODIFIED="1291278421327" TEXT="If there is an action targeted at a portlet, the portal  requests the portlet container to invoke the portlet to process the action"/>
<node CREATED="1291278439250" ID="Freemind_Link_738763369" MODIFIED="1291278460813" TEXT="A portal can invokes some portlets through the portlet container"/>
<node CREATED="1291278472188" ID="Freemind_Link_1756160019" MODIFIED="1291278473500" TEXT="The portal aggregates the output of the portlets in the portal page to the  client"/>
</node>
</node>
<node CREATED="1291278560205" ID="Freemind_Link_609239398" MODIFIED="1291278562565" POSITION="right" TEXT="Liferay portal has its own portlet container&#x2014;a logical component for handling the  life cycle and modes of the portlets. "/>
<node CREATED="1291278598487" ID="Freemind_Link_1331590529" MODIFIED="1291278600378" POSITION="right" TEXT="Interestingly, Liferay portal can also integrate  with other portlet containers by replacing its own portlet container, for example,  OpenPortal Portlet Container."/>
<node CREATED="1291359968771" ID="Freemind_Link_289707" MODIFIED="1291622806201" POSITION="right" TEXT="portlet 2.0 is JSR-286 compliant"/>
<node CREATED="1291360107805" ID="Freemind_Link_1240694761" MODIFIED="1291360288840" POSITION="right" TEXT="portlet cycle">
<icon BUILTIN="password"/>
<node CREATED="1291360182025" ID="Freemind_Link_388264048" MODIFIED="1291360185135" TEXT="Loading and Instantiation">
<node CREATED="1291360214682" ID="Freemind_Link_1349076385" MODIFIED="1291360216557" TEXT="portlet container is responsible for  loading and instantiating the portlets."/>
<node CREATED="1291360351607" ID="Freemind_Link_1483477627" MODIFIED="1291360367092" TEXT="this can occur when the portlet container starts the portlet application, or is delayed  until the portlet container determines whether the portlet is needed to service  a request."/>
</node>
<node CREATED="1291360392545" ID="Freemind_Link_1978342730" MODIFIED="1291363672333" TEXT="Initialization">
<node CREATED="1291360424280" ID="Freemind_Link_1403922022" MODIFIED="1291360437859" TEXT="correspond with method init()"/>
<node CREATED="1291360461093" ID="Freemind_Link_1202099033" MODIFIED="1291360463093" TEXT="after the portlet object is instantiated,  the portlet container must initialize the portlet before invoking it to handle  requests."/>
<node CREATED="1291360541486" ID="Freemind_Link_909218800" MODIFIED="1291621800144" TEXT="at this time, portlets can initialize costly resources  (for example, backend connections), and perform other one-time activities."/>
</node>
<node CREATED="1291360617909" ID="Freemind_Link_1688898429" MODIFIED="1291360619846" TEXT="Request Handling">
<node CREATED="1291360658316" ID="Freemind_Link_845028020" MODIFIED="1291363322904" TEXT="correspond with method processAction() or render()"/>
<node CREATED="1291360679301" ID="Freemind_Link_1754115688" MODIFIED="1291360680738" TEXT="after a portlet object is properly initialized,  the portlet container may invoke the portlet to handle client requests."/>
</node>
<node CREATED="1291363531377" ID="Freemind_Link_656315704" MODIFIED="1291363536439" TEXT="end of service">
<node CREATED="1291363554846" ID="Freemind_Link_370759913" MODIFIED="1291363561143" TEXT=" destroy() method"/>
<node CREATED="1291363641301" ID="Freemind_Link_445816367" MODIFIED="1291363680052" TEXT=" allow the portlet to release any resources it is using  and save any persistent state."/>
</node>
</node>
<node CREATED="1291365272817" ID="Freemind_Link_151697936" MODIFIED="1291365284739" POSITION="right" TEXT="portlet modes">
<node CREATED="1291365286677" ID="Freemind_Link_1721124273" MODIFIED="1291365405149" TEXT="default, 3 modes: VIEW, EDIT, and HELP"/>
<node CREATED="1291365339397" ID="Freemind_Link_212170400" MODIFIED="1291365340913" TEXT="A portlet mode advises the portlet what task it should perform and what content it  should generate."/>
<node CREATED="1291365366992" ID="Freemind_Link_1598798425" MODIFIED="1291365368258" TEXT="When invoking a portlet, the portlet  container will provide the current portlet mode to the portlet."/>
<node CREATED="1291365386055" ID="Freemind_Link_111964189" MODIFIED="1291365387602" TEXT=" Logically, portlets   can change their portlet mode programmatically when processing an action request,   that is, the processAction method."/>
<node CREATED="1291365428947" ID="Freemind_Link_1393384598" MODIFIED="1291365430244" TEXT="the availability of the portlet modes may be restricted to specific user  roles by Liferay portal."/>
<node CREATED="1291365452604" ID="Freemind_Link_550384531" MODIFIED="1291365453823" TEXT=" you can have custom portlet modes. "/>
<node CREATED="1291365560982" ID="Freemind_Link_914631181" MODIFIED="1291365599421" TEXT="default, the portal will manage these  custom modes.  However, you may define additional modes for your customized  portlets that don&apos;t need to be managed by the portal. In this case, you need to  provide implementation to manage these modes."/>
</node>
<node CREATED="1291366269076" ID="Freemind_Link_638199170" MODIFIED="1291366284310" POSITION="right" TEXT="window states">
<node CREATED="1291366285420" ID="Freemind_Link_757677933" MODIFIED="1291366287467" TEXT="Window states indicate the amount of portal page space that will be assigned to a  portlet. "/>
<node CREATED="1291368686888" ID="Freemind_Link_1784202687" MODIFIED="1291368688263" TEXT="When invoking  a portlet, the portlet container provides the current window state to the portlet. "/>
<node CREATED="1291369234898" ID="Freemind_Link_988589789" MODIFIED="1291369236289" TEXT="In addition, you can have custom window states in Liferay portal. Note that the  portlets can use only window states that are defined by the Liferay portal."/>
</node>
<node CREATED="1291369292790" ID="Freemind_Link_481157901" MODIFIED="1291369303056" POSITION="right" TEXT="portlet vs. servlet"/>
<node CREATED="1291371244184" ID="Freemind_Link_762228129" MODIFIED="1291371292669" POSITION="right" TEXT="portlet configuration, context, request, response, and preferences">
<node CREATED="1291372437488" ID="Freemind_Link_1535867152" MODIFIED="1291372449973" TEXT="portlet configuration">
<node CREATED="1291372465145" ID="Freemind_Link_1674523917" MODIFIED="1291372467223" TEXT="configuration holds information about the portlet that is valid for all of the  users retrieved from the portlet definition in the deployment descripto"/>
<node CREATED="1291372485036" ID="Freemind_Link_1975083335" MODIFIED="1291372486364" TEXT=" Portlet  configuration is provided by the PortletConfig interface and the portlet can read  only the configuration data"/>
</node>
<node CREATED="1291372498130" ID="Freemind_Link_660971709" MODIFIED="1291372502318" TEXT="portlet context">
<node CREATED="1291372809464" ID="Freemind_Link_486452149" MODIFIED="1291372810980" TEXT="Using the context, a portlet can access the portlet log, and obtain URL references  to resources."/>
<node CREATED="1291372812371" ID="Freemind_Link_1704153438" MODIFIED="1291372828371" TEXT="The PortletContext interface defines a portlet view of the portlet  container. "/>
<node CREATED="1291372829590" ID="Freemind_Link_1079784095" MODIFIED="1291372839184" TEXT="The PortletContext also makes resources available to the portlet. "/>
<node CREATED="1291372863465" ID="Freemind_Link_1910796387" MODIFIED="1291372871028" TEXT="A portlet obtains a PortalContext object from the request  object using getPortalContext method."/>
</node>
<node CREATED="1291372885981" ID="Freemind_Link_1790971134" MODIFIED="1291372891653" TEXT="portlet request">
<node CREATED="1291372978046" ID="Freemind_Link_143869272" MODIFIED="1291372979796" TEXT="The PortletRequest defines the base interface to provide client request information  to a portlet"/>
<node CREATED="1291372997546" ID="Freemind_Link_952712804" MODIFIED="1291373055922" TEXT="The portlet container uses two specialized versions of this interface  when invoking a portlet&#x2014; ActionRequest and RenderRequest (both extend  PortletRequest). The portlet container creates these objects, and further passes &#xa;them as arguments to the portlet, and the processAction and render methods"/>
<node CREATED="1291373251395" ID="Freemind_Link_1857609546" MODIFIED="1291373252910" TEXT="the request objects encapsulate all the information  about the client request, parameters, request content data, portlet mode, window  state, and so on."/>
<node CREATED="1291373253332" ID="Freemind_Link_1228348208" MODIFIED="1291373261832" TEXT=" Request object is passed to the processAction, processEvent,  serveResource, and render methods of the portlet"/>
</node>
<node CREATED="1291373612978" ID="Freemind_Link_868338388" MODIFIED="1291373618244" TEXT="portlet response">
<node CREATED="1291373619697" ID="Freemind_Link_1571879066" MODIFIED="1291373641652" TEXT="The PortletResponse defines the base interface to assist a portlet in creating and  sending a response to the client."/>
<node CREATED="1291373686313" ID="Freemind_Link_1018713884" MODIFIED="1291373714346" TEXT="The portlet container creates these response objects. It then passes them as arguments to the processAction, processEvent,  serveResource, and the render methods of the portlet. "/>
<node CREATED="1291373907808" ID="Freemind_Link_1082894543" MODIFIED="1291373908980" TEXT=" response objects encapsulate all the information to be  returned from the portlet to the portlet container during a request&#x2014;a redirection,  a portlet mode change, title, content, and so on."/>
<node CREATED="1291373964217" ID="Freemind_Link_445826107" MODIFIED="1291373965701" TEXT="The Liferay portal or the portlet  container will use this information to construct the response to be returned to  the client. "/>
</node>
<node CREATED="1291373974717" ID="Freemind_Link_600875552" MODIFIED="1291373995031" TEXT="portlet preference">
<node CREATED="1291374013000" ID="Freemind_Link_128614657" MODIFIED="1291374014485" TEXT="are intended to store basic configuration data for portlets"/>
<node CREATED="1291374032798" ID="Freemind_Link_999476046" MODIFIED="1291374034064" TEXT="The PortletPreferences interface allows the portlet to store configuration data, not  to replace the general-purpose databases."/>
<node CREATED="1291374034970" ID="Freemind_Link_1351871109" MODIFIED="1291374273132" TEXT=" Note that the store method can be invoked only within the scope of a  processAction call."/>
<node CREATED="1291374290133" ID="Freemind_Link_252554677" MODIFIED="1291374317899" TEXT="Normally, there are two different  types of preferences: modifiable and ready-only. By default, preference is modifiable"/>
<node CREATED="1291374328649" ID="Freemind_Link_124995297" MODIFIED="1291374330118" TEXT="Preferences would be read-only if they are defined in  the deployment descriptor with read-only set to true, or if the portlet container  restricts write access."/>
</node>
</node>
<node CREATED="1291276037485" ID="Freemind_Link_1237807118" MODIFIED="1291276275786" POSITION="left" TEXT="portlet standards/specifications">
<node CREATED="1291276278083" ID="Freemind_Link_720270197" MODIFIED="1291276288412" TEXT="define the lifecycle of a portlet as well as its  characteristics/look and feel"/>
<node CREATED="1291276304506" ID="Freemind_Link_1090766492" MODIFIED="1291276307006" TEXT=" standardize the way portlets are developed"/>
<node CREATED="1291278836650" ID="Freemind_Link_1332858719" MODIFIED="1291278839822" TEXT="JSR-286">
<node CREATED="1291278870308" ID="Freemind_Link_328177589" MODIFIED="1291278873605" TEXT=" major features">
<node CREATED="1291278931278" ID="Freemind_Link_688693319" MODIFIED="1291278933825" TEXT="Coordination"/>
<node CREATED="1291278947310" ID="Freemind_Link_375686072" MODIFIED="1291278949560" TEXT="WSRP (Web Services for Remote Portlets) 2.0 alignment"/>
<node CREATED="1291278984405" ID="Freemind_Link_6900870" MODIFIED="1291278985717" TEXT="Better support for Web Frameworks (for example, JSF, Struts, Spring,   Wicket, and so on)"/>
<node CREATED="1291279013499" ID="Freemind_Link_1051420714" MODIFIED="1291279015046" TEXT="Serving dynamically-generated resources directly through portlets"/>
<node CREATED="1291279033625" ID="Freemind_Link_1934107550" MODIFIED="1291279034891" TEXT="Serving AJAX or JSON data directly through portlets (while JSR-168   serves the AJAX data using an additional servlet)"/>
</node>
</node>
<node CREATED="1291279717718" ID="Freemind_Link_294054578" MODIFIED="1291279719155" TEXT="Liferay portal is designed to deploy portlets that adhere to the Portlet API  (JSR-286). Many useful portlets are bundled with the portal"/>
</node>
<node CREATED="1291277111724" ID="Freemind_Link_638078080" MODIFIED="1291277163585" POSITION="left" TEXT="What is Portal?" VSHIFT="114">
<node CREATED="1291277134772" ID="Freemind_Link_135253808" MODIFIED="1291277136428" TEXT="a portal (otherwise known as a web portal) is a web-based application  that, typically, provides personalization, authentication, and content aggregation  from different sources, and hosts the presentation layer of information systems"/>
<node CREATED="1291277147209" ID="Freemind_Link_682131480" MODIFIED="1291277148631" TEXT="A portal may have sophisticated personalization features to provide  customized content to user"/>
<node CREATED="1291277155272" ID="Freemind_Link_28076911" MODIFIED="1291277157147" TEXT="Portal pages may have a different set of portlets  creating content for different users."/>
<node CREATED="1291277190429" ID="Freemind_Link_57364170" MODIFIED="1291277193241" TEXT="a portal page is made up of a set of portlets"/>
</node>
</node>
</map>