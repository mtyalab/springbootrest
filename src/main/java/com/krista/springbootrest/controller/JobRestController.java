package com.krista.springbootrest.controller;

import com.krista.springbootrest.model.JobPost;
import com.krista.springbootrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    private final JobService service;

    @Autowired
    public JobRestController(JobService service) {
        this.service = service;
    }

    // @GetMapping("jobPosts")
    @GetMapping(path="jobPosts", produces = {"application/json"})
    public List<JobPost> getAllJobs(){
      return service.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        return service.getJob(postId);
    }

    // @PostMapping("jobPost")
    @PostMapping(path="jobPost", consumes = {"application/json"})
    public JobPost addJob(@RequestBody JobPost jobPost) {
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId) {
        service.deleteJob(postId);
        return "Deleted";
    }
}
