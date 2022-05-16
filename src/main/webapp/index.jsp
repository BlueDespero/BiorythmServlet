<html>
<body>
<h2>
    Hello World!
</h2>



<form id="formoid" action="servlet" title="" method="get">
        <label for="birthday">Birthday: </label><input type="date" id="birthday" name="birthday" value=<%=getServletConfig().getInitParameter("default_date") %>>
        <input type="submit">
</form>

</body>
</html>