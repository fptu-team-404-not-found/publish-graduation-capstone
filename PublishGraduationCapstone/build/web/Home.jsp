<%-- 
    Document   : Home
    Created on : Jan 12, 2022, 7:11:28 PM
    Author     : jike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <c:set var="ID" value="${sessionScope.id}"/>
        <c:if test="${empty ID}">
            <a href="https://accounts.google.com/o/oauth2/auth?scope=openid profile email&redirect_uri=http://localhost:8084/PublishGraduationCapstone/LoginProcess&response_type=code
               &client_id=905648126821-fs0vnje6r097kc3u2nar0d2p3rnrlh4l.apps.googleusercontent.com&approval_prompt=force">
                Login With Google
            </a>
        </c:if>
        <c:if test="${not empty ID}">
            <h2>User Information</h2>
            <div>
                <img src="${sessionScope.picture}" alt="Avatar"> <br/>
                Name: ${sessionScope.name} <br/>
                ID:  ${sessionScope.id} <br/>
                Email: ${sessionScope.email}
            </div>
        </c:if>
    </body>
</html>
