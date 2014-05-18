<%@ page import="pl.edu.agh.game.domain.Board" %>
<%@ page import="pl.edu.agh.game.domain.Location" %>
<%@ page import="pl.edu.agh.game.util.GameUtil" %>
<%@ page import="java.util.List" %>
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
        List<Location> trace1 = (List<Location>) request.getAttribute("trace1");
        List<Location> trace2 = (List<Location>) request.getAttribute("trace2");
        Board board = (Board) request.getAttribute("board");
        Integer winnerId = (Integer) request.getAttribute("winner");
    %>
</head>
<body>

    <h1>Winner: <b>Player<%=winnerId%></b></h1>

    <table>
        <tr>
            <td bgcolor="blue">Player0</td>
            <td bgcolor="red">Player1</td>
        </tr>
    </table>

    <table border="3">
        <%
            for (int i=0; i<board.getxSize(); i++) {
                %>
                <tr>
                    <%
                        for (int j = 0; j < board.getySize(); j++) {
                            int location1 = GameUtil.getLocation(trace1, i, j);
                            int location2 = GameUtil.getLocation(trace2, i, j);
                            if (location1 >= 0) {
                    %>
                    <td bgcolor="blue" width="20px" height="20px"><%=location1%></td>
                    <%
                            } else if (location2 >= 0) {
                    %>
                    <td bgcolor="red" width="20px" height="20px"><%=location2%></td>
                    <%
                            } else{
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
