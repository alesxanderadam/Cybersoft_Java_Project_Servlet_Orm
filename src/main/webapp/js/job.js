$(document).ready(function () {
    $(".btn-deleteJob").click(function () {
        let job_id = $(this).attr("jobId");
        let functionDeleteJob = $(this);

        showConfirmationAlert(job_id,functionDeleteJob)
    })
})

function showConfirmationAlert(job_id, functionDeleteJob) {
    swal({
        icon: 'warning',
        title: 'Confirm',
        text: 'Are you sure you want delete this project?',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'No'
    }, function (result) {
        if (result) {
            $.ajax({
                method: "GET",
                url: "http://localhost:8080/admin_pixel/groupwork/delete?job_id=" + job_id,
                success: function (result) {
                    showSuccessAlert();
                },
                error: function () {
                    showErrorAlert();
                }
            }).done(function (result){
                functionDeleteJob.closest("tr").remove();
            })
        } else {
        }
    });
}

function showSuccessAlert() {
    swal("Success", "", "success");
}

function showErrorAlert() {
    swal("error", "", "error");
}
