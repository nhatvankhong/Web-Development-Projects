package com.edu.backend.utils;

import java.util.ArrayList;
import java.util.List;
import com.edu.backend.model.Job;
import com.edu.backend.model.dto.JobDTO;

public class JobDTOUtil {

	public static List<JobDTO> getJobDTOs(List<Job> jobs) {
		List<JobDTO> result = new ArrayList<>();
		
		for (Job job :jobs) {
			result.add(new JobDTO(job));
		}
		return result;
	}
}