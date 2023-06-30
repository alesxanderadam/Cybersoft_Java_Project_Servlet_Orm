$(document).ready(function(){
    $(".btn-deleteJob").click(function (){
        let job_id = $(this).attr("jobId");
        let functionDeleteJob = $(this);
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/admin_pixel/groupwork/delete?job_id=" + job_id,
        }).done(function (result) {
            functionDeleteJob.closest("tr").remove();
            console.log("Data" + result);
        })
    })
})