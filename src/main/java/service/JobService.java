package service;

import entity.JobModel;
import repository.JobRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class JobService {
    private final JobRepository jobRepository = new JobRepository();

    public void showAllJob(HttpServletRequest req, HttpServletResponse resp){
        jobRepository.jobLists = jobRepository.findAllModels("jobs", new String[]{"id","name","start_date","end_date"}, JobModel.class);
        List<JobModel> jobModelList = jobRepository.jobLists;
        try{
            if (jobModelList.size() > 0) {
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (JobModel job : jobModelList) {
                    String start_date = outputFormat.format(job.getStart_date());
                    String end_date = outputFormat.format(job.getEnd_date());

                    job.setStart_date_string(start_date);
                    job.setEnd_date_string(end_date);
                }
                req.setAttribute("jobModelList", jobModelList);
            }
        }catch (Exception e){
            System.out.println("Error showAllJob " + e);
        }
    }

    public boolean showDetailJob(int job_id , HttpServletResponse resp, HttpServletRequest req){
        JobModel job = jobRepository.getJobById(job_id);
        boolean isHaveJob = false;
        try{
            if(job != null){
                isHaveJob = true;
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String start_date = outputFormat.format(job.getStart_date());
                String end_date = outputFormat.format(job.getEnd_date());

                job.setStart_date_string(start_date);
                job.setEnd_date_string(end_date);
                req.setAttribute("jobDetailModel", job);
            }
        }catch (Exception e){
            System.out.println("Error showAllJob " + e);
        }
        return isHaveJob;
    }

    public void jobAdd(HttpServletRequest req, HttpServletResponse resp , String name, Date start_date, Date end_date) throws ServletException, IOException {
        boolean isSuccess = jobRepository.insertIntoJob(name, (java.sql.Date) start_date,(java.sql.Date) end_date);
        if (isSuccess) {
            jobRepository.jobLists = jobRepository.findAllModels("jobs",new String[]{"id", "name", "start_date", "end_date"}, JobModel.class);
        }
    }

    public void deleteJobSerivce(int job_id) {
        boolean isSuccess = jobRepository.deleteJob(job_id);
        if (isSuccess) {
            jobRepository.jobLists = jobRepository.findAllModels("jobs",new String[]{"id", "name", "start_date", "end_date"}, JobModel.class);
        }
    }

    public void jobUpdate(Object job_id, JobModel jobModel){
        jobRepository.updateJob(job_id, jobModel);
        jobRepository.jobLists = jobRepository.findAllModels("jobs",new String[]{"id", "name", "start_date", "end_date"}, JobModel.class);
    }
}
