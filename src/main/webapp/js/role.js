$(document).ready(function(){
    $(".btn-deleteRole").click(function (){
        let role_id = $(this).attr("roleId");
        let functionDeleteRole = $(this);
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/admin_pixel/role/delete?role_id=" + role_id,
            success: function(result) {
                showSuccessAlert();
            },
            error: function() {
                showErrorAlert();
            }
        }).done(function (result) {
            functionDeleteRole.closest("tr").remove();
        })
    })
})


function showSuccessAlert() {
    swal("Success", "", "success");
}

function showErrorAlert() {
    swal("error", "", "error");
}
