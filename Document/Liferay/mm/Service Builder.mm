<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1292484576211" ID="Freemind_Link_1642075366" MODIFIED="1292576426262" TEXT="Service Builder">
<node CREATED="1292484657504" ID="_" MODIFIED="1292576418137" POSITION="right" TEXT="Create a Liferay Plugin project">
<node CREATED="1292484719871" ID="Freemind_Link_1415936089" MODIFIED="1292576637893" TEXT="create  a file named service.xml&#xa;in %project_name%\docroot\WEB-INF">
<icon BUILTIN="full-1"/>
<node CREATED="1292484902292" ID="Freemind_Link_593562899" MODIFIED="1292486167080" TEXT="&lt;service-builder package-path=&quot;com.sample.portlet.library&quot;&gt; : the package path that the class will generate to"/>
<node CREATED="1292486148980" ID="Freemind_Link_1583090899" MODIFIED="1292486191000" TEXT="&lt;namespace&gt;Library&lt;/namespace&gt; : namespace element must be a unique namespace for this component. Table names will be prepended with this namepace"/>
</node>
<node CREATED="1292486276351" ID="Freemind_Link_1893798971" MODIFIED="1292576434402" TEXT="Generate the Service">
<icon BUILTIN="full-2"/>
<node CREATED="1292486326205" ID="Freemind_Link_423168254" MODIFIED="1292857769858" TEXT="go to %project_name% directory"/>
<node CREATED="1292486344757" ID="Freemind_Link_643473763" MODIFIED="1293465540276">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      call: ant build-service
    </p>
    <p>
      (or ant build-service -Dservice.file=path/to/service.xml)
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1292572988150" ID="Freemind_Link_1620970714" MODIFIED="1292576534585" TEXT="LocalService : interface for the local service"/>
<node CREATED="1292573039226" ID="Freemind_Link_49757924" MODIFIED="1292574956890" TEXT="LocalServiceBaseImpl">
<node CREATED="1292574961015" ID="Freemind_Link_1444285893" MODIFIED="1292576465370" TEXT="Contains a few automatically generated methods &#xa;providing common functionality"/>
<node CREATED="1292574997014" ID="Freemind_Link_1223857753" MODIFIED="1292576490369" TEXT="Should never modify it, or our changes will be &#xa;overwritten the next time we run Service Builder"/>
</node>
<node CREATED="1292574450476" ID="Freemind_Link_1648779048" MODIFIED="1292574737463" TEXT="LocalServiceImpl">
<node CREATED="1292574738619" ID="Freemind_Link_1958253325" MODIFIED="1292574754071" TEXT="Add custom code here."/>
<node CREATED="1292574755071" ID="Freemind_Link_992693136" MODIFIED="1292576498009" TEXT="call: ant build-service to generate methods &#xa;for LocalService interface"/>
</node>
</node>
</node>
</node>
</node>
</map>
