package controller;

import model.JobModel;
import model.TaskModel;
import service.JobService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task/add", "/task/update", "/task/delete"})
public class TaskController extends HttpServlet {
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletPath();
        switch (path) {
            case "/task":
                findAllTask(req, resp);
                break;
            case "/task/add":
                userService.showAllUser(req, resp);
                userService.showAllStatus(req, resp);
                jobService.showAllJob(req, resp);
                req.getRequestDispatcher("/views/tasks/task-add.jsp").forward(req, resp);
                break;
            case "/task/update":
                findDetailTask(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/task":
                findAllTask(req, resp);
                break;
            case "/task/add":
                addTask(req, resp);
                break;
            case "/task/update":
                updateTask(req, resp);
                break;
            default:
                break;
        }
    }

    private void findAllTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        taskService.showAllTask(req, resp);
        req.getRequestDispatcher("/views/tasks/task.jsp").forward(req, resp);
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String name = req.getParameter("name");
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int job_id = Integer.parseInt(req.getParameter("job_id"));
        int status_id = Integer.parseInt(req.getParameter("status_id"));
        try {
            Date start_date = inputFormat.parse(req.getParameter("start_date"));
            Date end_date = inputFormat.parse(req.getParameter("end_date"));

            String formattedStartDate = outputFormat.format(start_date);
            String formattedEndDate = outputFormat.format(end_date);

            java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
            java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);

            taskService.addTask(name, sql_start_date, sql_end_date, user_id, job_id, status_id);
        } catch (ParseException e) {
            System.out.println("Error date " + e);
            e.printStackTrace();
        }
        req.getRequestDispatcher("/tasks").forward(req, resp);
    }

    private void findDetailTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int task_id = Integer.parseInt(req.getParameter("task_id"));
        userService.showAllUser(req, resp);
        userService.showAllStatus(req, resp);
        jobService.showAllJob(req, resp);
        boolean isHaveTask = taskService.showDetailTask(task_id, resp, req);
        if (isHaveTask) {
            req.getRequestDispatcher("/views/tasks/task-update.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/notfound").forward(req, resp);
        }
    }

    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskModel taskModel = new TaskModel();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String task_id = req.getParameter("id");
            int job_id = Integer.parseInt(req.getParameter("job_id"));
            String name = req.getParameter("name");
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            Date start_date = inputFormat.parse(req.getParameter("start_date"));
            Date end_date = inputFormat.parse(req.getParameter("end_date"));
            int status_id = Integer.parseInt(req.getParameter("status_id"));

            String formattedStartDate = outputFormat.format(start_date);
            String formattedEndDate = outputFormat.format(end_date);

            java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
            java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);

            taskModel.setUser_id(user_id);
            taskModel.setJob_id(job_id);
            taskModel.setName(name);
            taskModel.setStart_date(sql_start_date);
            taskModel.setEnd_date(sql_end_date);
            taskModel.setStatus_id(status_id);

            taskService.taskUpdate(task_id, taskModel);
        } catch (ParseException e) {
            System.out.println("Error date " + e);
            e.printStackTrace();
        }
        req.getRequestDispatcher("/task").forward(req, resp);
    }
}