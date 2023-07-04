$(document).ready(function() {
    $(".btn-updateTask").click(function() {
        let user_id = $(this).attr("user_id");
        let status_id = $(this).closest("tr").find("select").val();
        let task_id = $(this).attr("task_id");

        showConfirmationAlert(user_id, status_id, task_id);
    });

    function showConfirmationAlert(user_id, status_id, task_id) {
        swal({
            icon: 'warning',
            title: 'Confirm',
            text: 'Are you sure you want to update this task status?',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No'
        }, function(result) {
            if (result) {
                $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/admin_pixel/profile?status_id=" + status_id + "&task_id="+ task_id + "&user_id=" + user_id,
                    success: function(result) {
                        showSuccessAlert();
                        window.location.reload();
                    },
                    error: function() {
                        showErrorAlert();
                    }
                });
            } else {
            }
        });
    }

    function showSuccessAlert() {
        swal("success", "", "success");
    }

    function showErrorAlert() {
        swal("error","", "error");
    }
});
