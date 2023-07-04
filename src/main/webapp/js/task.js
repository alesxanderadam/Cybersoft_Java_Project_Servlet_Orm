$(document).ready(function(){
    $(".btn-deleteTask").click(function (){
        let task_id = $(this).attr("taskId");
        let functionDeleteTask = $(this);
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/admin_pixel/task/delete?task_id=" + task_id,
            success: function(result) {
                showSuccessAlert();
            },
            error: function() {
                showErrorAlert();
            }
        }).done(function (result) {
            functionDeleteTask.closest("tr").remove();
        })
    })
})


function showSuccessAlert() {
    swal("Success", "", "success");
}

function showErrorAlert() {
    swal("error", "", "error");
}
