<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h1>calculations</h1>
    <h2>${msg}</h2>
    <c:choose>
    	<c:when test="${not empty logs}">
    	   <span>${logs}</span>
    	</c:when>
    	<c:when test="${not empty nodeEmpty}">
    	   <form:form method="post" action="destroySession.do">
		      <p>${nodeEmpty}</p>
		      <input type="submit" value="Continue"/>	   
    	   </form:form>
    	</c:when>
        <c:otherwise>
        	<p>${info}</p>
        </c:otherwise>
    </c:choose>
</body>
</html>