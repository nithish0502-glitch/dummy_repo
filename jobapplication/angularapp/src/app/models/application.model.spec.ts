import { Application } from "./application.model";

describe('Application Model', () => {

  fit('Frontend_should_create_an_instance_Application_with_defined_properties', () => {
    // Create a sample Application object
    const application: Application = {
      status: 'Applied',
      yearsOfExperience: 5,
      skills: 'JavaScript, Angular',
      applicationDate: new Date('2024-12-28'),
      locationPreference: 'New York'
    };

    expect(application).toBeTruthy();
    expect(application.status).toBeDefined();
    expect(application.yearsOfExperience).toBeDefined();
    expect(application.skills).toBeDefined();
    expect(application.applicationDate).toBeDefined();
    expect(application.locationPreference).toBeDefined();

    expect(application.status).toBe('Applied');
    expect(application.yearsOfExperience).toBe(5);
    expect(application.skills).toBe('JavaScript, Angular');
    expect(application.applicationDate).toEqual(new Date('2024-12-28'));
    expect(application.locationPreference).toBe('New York');
  });

});
