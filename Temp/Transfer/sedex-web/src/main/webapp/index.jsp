<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="default.tiles">
	<tiles:putAttribute name="body">
		<form action="p1" method="post">
			<input name="id" value="01"> <input name="name"
				value="Long Nguyen"> <input type="submit" name="Submit"
				value=" Submit Form">
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>
