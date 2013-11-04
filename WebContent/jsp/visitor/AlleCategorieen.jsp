<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
		<s:iterator value="categorieen">
			<p class="meer">- <a href="<s:url action="ToonCategorie?cat=%{naam}" namespace="/visitor" />"><s:property value="naam" /></a></p>
		</s:iterator>