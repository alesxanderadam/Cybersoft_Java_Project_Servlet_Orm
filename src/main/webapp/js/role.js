$(document).ready(function(){
    $(".btn-deleteRole").click(function (){
        let role_id = $(this).attr("jobId");
        let functionDeleteRole = $(this);
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/admin_pixel/role/delete?role_id=" + role_id,
        }).done(function (result) {
            functionDeleteRole.closest("tr").remove();
            console.log("Data" + result);
        })
    })
})