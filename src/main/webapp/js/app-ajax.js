$(document).ready(function() {
    $('#userName').blur(function() {
        $.ajax({
            url : 'servlet',
            data : {
                userName : $('#userName').val()
            },
            success : function(responseText) {
                $('#ajaxGetUserServletResponse').text(responseText);
            }
        });
    });
});