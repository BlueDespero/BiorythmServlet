<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Customer Information Center</title>
    <script src="plotly/js/plotly-latest.min.js" type="text/javascript">
    </script>

    <script type="text/javascript">
        function plotChart(elementId, data, layout) {
            Plotly.newPlot(document.getElementById(elementId),
                data, layout, {displayModeBar: false});
        }
    </script>
</head>

<body>
<h2>
    Hello World!
</h2>



<form id="formoid" action="servlet" title="" method="get">
        <label for="birthday">Birthday: </label><input type="date" id="birthday" name="birthday" value=<%=getServletConfig().getInitParameter("default_date") %>>
        <input type="submit">
</form>

<c:if test="${not empty biorythmsList}">

    <h2>Age and Sales Count Chart</h2>

    <div id="BiorythmsChart" ></div>

    <script>

        var physicalValues = {
            name: 'physical',
            type: 'lines+markers',
            line: { width: 6},
            marker: { size: 8}
        };
        var emotionallValues = {
            name: 'emotional',
            type: 'lines+markers',
            line: { width: 6},
            marker: { size: 8}
        };
        var intellectualValues = {
            name: 'intellectual',
            type: 'lines+markers',
            line: { width: 3},
            marker: { size: 4}
        };

        var days_X = [];
        var physical_Y = [];
        var emotional_Y = [];
        var intellectual_Y = [];

        <c:forEach items="${biorythmsList}" var="biorythm" varStatus="i">
        days_X[${i.index}] = "${biorythm.day_date}";
        physical_Y[${i.index}] = "${biorythm.physical}";
        emotional_Y[${i.index}] = "${biorythm.emotional}";
        intellectual_Y[${i.index}] = "${biorythm.intellectual}";
        </c:forEach>

        physicalValues.x = days_X;
        physicalValues.y = physical_Y;

        emotionallValues.x = days_X;
        emotionallValues.y = emotional_Y;

        intellectualValues.x = days_X;
        intellectualValues.y = intellectual_Y;

        var data = [physicalValues, emotionallValues, intellectualValues];
        var layout = {
            xaxis: {
                title: 'dates',
                showgrid: true,
                zeroline: true,
            },
            yaxis: {
                showgrid: true,
                showline: true,
                zeroline: true,
            }
        };

        plotChart("BiorythmsChart", data, layout);
    </script>
</c:if>

</body>
</html>