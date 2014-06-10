<html>
<body>
<form name="form1" method="post" action="/startgame"/>
    <table>
        <tr>
            <td><h2>Agent location: </h2></td>
            <td><input type="text" name="location"/></td>
        </tr>
        <tr>
            <td><h2>Board size: </h2></td>
            <td><input type="text" name="board" /></td>
        </tr>
        <tr>
            <td><h2>Epsilon: </h2></td>
            <td><input type="text" name="epsilon" /></td>
        </tr>
        <tr>
            <td><h2>Alpha decay power: </h2></td>
            <td><input type="text" name="alpha"/></td>
        </tr>
        <tr>
            <td><h2>Maximum iteration number: </h2></td>
            <td><input type="text" name="maxIter" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="submit" value="Submit"/>
        </td>
        </tr>
    </table>
</body>
</html>