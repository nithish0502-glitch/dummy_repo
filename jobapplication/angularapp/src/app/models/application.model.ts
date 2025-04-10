export interface Application {
    applicationId?: number;
    status: string; 
    userId?:number;
    jobId?:number;
    yearsOfExperience: number;
    skills: string; 
    applicationDate: Date; 
    locationPreference: string; 
  }