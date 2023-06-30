package controller;

import model.JobModel;
import model.UserModel;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "jobController", urlPatterns = {"/groupwork", "/groupwork/add", "/groupwork/update", "/groupwork/delete","/groupwork/detail"})
public class JobController extends HttpServlet {
    private final JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/groupwork":
                findAllJob(req, resp);
                break;
            case "/groupwork/add":
                req.getRequestDispatcher("/views/jobs/groupwork-add.jsp").forward(req, resp);
                break;
            case "/groupwork/update":
                findDetailJob(req, resp);
                break;
            case "/groupwork/detail":
                req.getRequestDispatcher("/views/jobs/groupwork-detail.jsp").forward(req, resp);
                break;
            case "/groupwork/delete":
                deleteJobController(Integer.parseInt(req.getParameter("job_id")), req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/groupwork":
                findAllJob(req, resp);
                break;
            case "/groupwork/add":
                addJob(req, resp);
                break;
            case "/groupwork/update":
                updateJob(req, resp);
                break;
            default:
                break;
        }
    }

    private void findAllJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jobService.showAllJob(req, resp);
        req.getRequestDispatcher("/views/jobs/groupwork.jsp").forward(req, resp);
    }

    private void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String name = req.getParameter("name");
        try {
            Date start_date = inputFormat.parse(req.getParameter("start_date"));
            Date end_date = inputFormat.parse(req.getParameter("end_date"));

            String formattedStartDate = outputFormat.format(start_date);
            String formattedEndDate = outputFormat.format(end_date);

            java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
            java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);

            jobService.jobAdd(req, resp, name, sql_start_date, sql_end_date);
            req.getRequestDispatcher("/groupwork").forward(req, resp);
        } catch (ParseException e) {
            System.out.println("Error date " + e);
            e.printStackTrace();
        }
    }

    private void deleteJobController(int job_id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jobService.deleteJobSerivce(job_id);
        req.getRequestDispatcher("/views/jobs/groupwork.jsp").forward(req, resp);
    }

    private void findDetailJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int job_id = Integer.parseInt(req.getParameter("job_id"));
        boolean isHaveUser = jobService.showDetailJob(job_id, resp, req);
        if (isHaveUser) {
            req.getRequestDispatcher("/views/jobs/groupwork-update.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/notfound").forward(req, resp);
        }
    }

    private void updateJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobModel jobModel = new JobModel();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String job_id = req.getParameter("id");
            String name = req.getParameter("name");
            Date start_date = inputFormat.parse(req.getParameter("start_date"));
            Date end_date = inputFormat.parse(req.getParameter("end_date"));

            String formattedStartDate = outputFormat.format(start_date);
            String formattedEndDate = outputFormat.format(end_date);

            java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
            java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);

            jobModel.setName(name);
            jobModel.setStart_date(sql_start_date);
            jobModel.setEnd_date(sql_end_date);

            jobService.jobUpdate(job_id, jobModel);
        } catch (ParseException e) {
            System.out.println("Error date " + e);
            e.printStackTrace();
        }
        req.getRequestDispatcher("/groupwork").forward(req, resp);
    }
}
