$(document).ready(function () {
    $(".btn-deleteUser").click(function () {
        let userId = $(this).attr("userId");
        let fileName = $(this).attr("fileName");
        let functionDeleteUser = $(this);
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/admin_pixel/user/delete?user_id=" + userId + "&imageFile=" + fileName,
            success: function (result) {
                showSuccessAlert();
            },
            error: function () {
                showErrorAlert();
            }
        }).done(function (result) {
            functionDeleteUser.closest("tr").remove();
        });
    })
})

function showSuccessAlert() {
    swal("success", "user has been deleted!", "success");
}

function showErrorAlert() {
    swal("error", "user delete is failed!", "error");
}