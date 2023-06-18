$(document).ready(function(){
    $(".btn-deleteUser").click(function (){
        let userId = $(this).attr("userId");
        let functionDeleteUser = $(this);
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/admin_pixel/user/delete?user_id=" + userId,
        }).done(function (result) {
            functionDeleteUser.closest("tr").remove();
            console.log("Data" + result);
        })
    })
})