
$(document).ready(function () {
$('#submitbtn').click(function (event) {
alert(toJson());
        $.ajax({
        url: 'http://localhost:8080/SimonBorgstromIn2JavaBackend/api/todo',
                type: 'post',
                headers: {
                'Content-Type': 'application/json'
                },
                dataType: 'JSON',
                // contentType: 'application/json',
                data: toJson(), //$('#form').serialize(),
                success: function (data)
                {
                console.log("test");
                        updateList();
                }
        });
        //event.preventDefault();

});
        });
        function updateList() {
        $("#table tbody").empty();
                $.getJSON("http://localhost:8080/SimonBorgstromIn2JavaBackend/api/todo", function (data) {
                $.each(data, function (i, fromservlet) {
                var connected = '';
                        if (fromservlet.done === true) {
                connected = 'checked';
                }
                $("#table tbody").append("<tr><td>" + fromservlet.description +
                        "</td><td id=\"editdate\">" + fromservlet.duedate + "</td><td id=\"inlineCheckbox\">" +
                        "<input type=\"checkbox\" id=\"inlineCheckbox1\"  \n\
                    name=\"done\"" + connected + " > Klart" + "</td></tr>");
                });
                });
                }

$(document).ready(function () {
$("#table").delegate('#editdate', 'click', function (e) {
//  e.preventDefault();
var text = prompt("Change date?", "new date");
        //  $(this).html(text);
        $.ajax({
        url: 'http://localhost:8080/SimonBorgstromIn2JavaBackend/api/todo/' + ($(this).parent().index() + 1),
                headers: {
                'Content-Type': 'application/json'
                },
                type: 'put',
                dataType: 'json',
                data: {'duedate': text},
                success: function (data)
                {
                updateList();
                }
        });
});
        });
        $(document).ready(function () {
$("#table").delegate('#inlineCheckbox', 'click', function (e) {
var entity = ($(this).parent().index() + 1);
        var description = $(this).parent().find("td:first").html();
        var duedate = $(this).parent().find("td").eq(1).html();
        var done = true;
        alert(description + " " + duedate + " " + done);
        var putData = JSON.stringify({
            "description":description,
            "duedate":duedate,
            "done":done,
            "id":entity
        });

        $.ajax({
        url: 'http://localhost:8080/SimonBorgstromIn2JavaBackend/api/todo/'+entity,
                headers: {
                'Content-Type': 'application/json'
                },
                type: 'put',
                dataType: 'json',
                data: putData,
                success: function (data)
                {
                updateList();
                }
        });
        });
        });
        $(document).ready(function () {
updateList();
        });
        function toJson() {
        return JSON.stringify({
        "description": $('#description').val(),
                "duedate": $('#duedate').val().toString(),
                "done": "false"
        });
                }
