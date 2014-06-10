<%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 17.05.14
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results page</title>

    <%
        int[][] tailsTable = (int[][]) request.getAttribute("tailsTable");
        int criticalX = (Integer) request.getAttribute("criticalX");
        int criticalY = (Integer) request.getAttribute("criticalY");
    %>
</head>
<body>

    <table border="3">
        <%
            for (int i=0; i<tailsTable.length; i++) {
                %>
                <tr>
                    <%
                        for (int j = 0; j < tailsTable[i].length; j++) {
                            if (i == criticalX && j == criticalY) {
                    %>
                    <td bgcolor="red" width="20px" height="20px"><%= tailsTable[i][j]%>
                    </td>
                    <%
                            }
                            else if (tailsTable[i][j] > 0) {
                    %>
                    <td bgcolor="blue" width="20px" height="20px"><%= tailsTable[i][j]%>
                    </td>
                    <%
                    } else {
                    %>
                    <td bgcolor="#d3d3d3" width="20px" height="20px">&nbsp;</td>
                    <%
                            }
                        }
                    %>

                </tr>
            <%}
        %>
    </table>
</body>
</html>
