<%-- 
    Document   : account
    Created on : 10-Dec-2012, 6:20:19 PM
    Author     : Samual
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head> <title>Online Video Store System</title> </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <!--Used to capture the exceptions -->
        <c:if test="${exception ne null}">
            <div class="error">
                ${exception.message}
            </div>
        </c:if>
        <form action="<c:url value="/registerAccountView.htm"/>" method="post">
            <h3>Please fill in account information.</h3> 
            <fieldset>
                <legend>Account Registration</legend>
                <table>
                    <tr>
                        <td>
                            Username :
                        </td>
                        <td>
                            <input type="text" id="username" name="username" placeholder="Username"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password :
                        </td>
                        <td>
                            <input type="password" id="password" name="password" placeholder="Password"/>
                        </td>
                    </tr>
                </table>
                <input type="submit" name="Submit" value="Register">
            </fieldset>
        </form>
    </body>
</html>