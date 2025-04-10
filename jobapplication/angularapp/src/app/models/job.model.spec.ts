import { Job } from "./job.model";

describe('Job Model', () => {

  fit('Frontend_should_create_an_instance_Job_with_defined_properties', () => {
    // Create a sample Job object
    const job: Job = {
      title: 'Software Engineer',
      description: 'Responsible for developing software solutions.',
      company: 'TechCorp',
      location: 'San Francisco'
    };

    expect(job).toBeTruthy();
    expect(job.title).toBeDefined();
    expect(job.description).toBeDefined();
    expect(job.company).toBeDefined();
    expect(job.location).toBeDefined();

    // Additional checks for values
    expect(job.title).toBe('Software Engineer');
    expect(job.description).toBe('Responsible for developing software solutions.');
    expect(job.company).toBe('TechCorp');
    expect(job.location).toBe('San Francisco');
  });

});
