const puppeteer = require('puppeteer');

const BASE_URL = 'https://ide-fddbbedbb327214235bfdebbddcaecone.premiumproject.examly.io/proxy/3001//movies'; 

(async () => {
  const browser = await puppeteer.launch({
    headless: false,
    args: ['--headless', '--disable-gpu', '--remote-debugging-port=9222', '--no-sandbox', '--disable-setuid-sandbox'],
  });

  // **1. Verify Job Listings Table Exists and Has Rows**
  const page1 = await browser.newPage();
  try {
    await page1.goto(`${BASE_URL}/viewJobs`);
    await page1.waitForSelector('table tbody tr', { timeout: 2000 });

    // Fetch all rows in the table
    const rows = await page1.$$eval('table tbody tr', rows =>
      rows.map(row => Array.from(row.querySelectorAll('td')).map(td => td.textContent.trim()))
    );

    if (rows.length > 0) {
      console.log('TESTCASE:job_table_contains_rows:success');
    } else {
      console.log('TESTCASE:job_table_contains_rows:failure');
    }
  } catch (e) {
    console.log('TESTCASE:job_table_contains_rows:failure');
  }

  // **2. Verify Add Job Form Exists with All Required Fields**
  const page2 = await browser.newPage();
  try {
    await page2.goto(`${BASE_URL}/addNewJob`);
    const formExists = await page2.evaluate(() => {
      const form = document.querySelector('form');
      const inputFields = ['title', 'company', 'location', 'salaryPackage', 'description', 'jobType', 'requirements', 'isRemote'];
      return !!form && inputFields.every(field => !!form.querySelector(`[name="${field}"]`));
    });

    if (formExists) {
      console.log('TESTCASE:addJob_form_exists_and_input_fields_present:success');
    } else {
      console.log('TESTCASE:addJob_form_exists_and_input_fields_present:failure');
    }
  } catch (e) {
    console.log('TESTCASE:addJob_form_exists_and_input_fields_present:failure');
  }

  // **3. Verify Search Functionality (Partial Match)**
  const page3 = await browser.newPage();
  try {
    await page3.goto(`${BASE_URL}/viewJobs`);
    await page3.waitForSelector('#searchJob', { timeout: 2000 });
    await page3.type('#searchJob', 'Manager'); // Searching for any job containing 'Manager'

    // Wait for results to update
    await page3.waitForTimeout(1000);
    const searchResults = await page3.evaluate(() => document.body.textContent.includes('Manager'));

    if (searchResults) {
      console.log('TESTCASE:search_functionality_displays_correct_job_listing:success');
    } else {
      console.log('TESTCASE:search_functionality_displays_correct_job_listing:failure');
    }
  } catch (e) {
    console.log('TESTCASE:search_functionality_displays_correct_job_listing:failure');
  }

  // **4. Verify Required Field Validation on Add Job Listing**
  const page4 = await browser.newPage();
  try {
    await page4.goto(`${BASE_URL}/addNewJob`);
    await page4.waitForFunction(() => {
      return Array.from(document.querySelectorAll('button'))
        .some(button => button.textContent.trim() === 'Add Job Listing');
    }, { timeout: 2000 });

    await page4.evaluate(() => {
      const addButton = Array.from(document.querySelectorAll('button'))
        .find(button => button.textContent.trim() === 'Add Job Listing');
      if (addButton) addButton.click();
    });

    const requiredMessages = [
      'Job title is required',
      'Company is required',
      'Location is required',
      'Salary package is required',
      'Description is required',
      'Job type is required',
      'Requirements are required'
    ];
    const bodyText = await page4.evaluate(() => document.body.textContent);

    if (requiredMessages.every(msg => bodyText.includes(msg))) {
      console.log('TESTCASE:verify_required_validation_on_add_job_listing:success');
    } else {
      console.log('TESTCASE:verify_required_validation_on_add_job_listing:failure');
    }
  } catch (error) {
    console.log('TESTCASE:verify_required_validation_on_add_job_listing:failure');
  }

  // **5. Add New Job Listing and Verify in Job List**
  const page5 = await browser.newPage();
  try {
    await page5.goto(`${BASE_URL}/addNewJob`);
    await page5.waitForSelector('form', { timeout: 2000 });

    // Fill in the job details
    const jobDetails = {
      title: 'Business Analyst',
      company: 'Visionary Corp.',
      location: 'Los Angeles, CA',
      salaryPackage: '$90,000 - $110,000 annually',
      description: 'Analyze business processes and suggest improvements.',
      jobType: 'Full-time',
      requirements: '2+ years of experience, strong analytical skills.',
      isRemote: true
    };

    await page5.type('#title', jobDetails.title);
    await page5.type('#company', jobDetails.company);
    await page5.type('#location', jobDetails.location);
    await page5.type('#salaryPackage', jobDetails.salaryPackage);
    await page5.type('#description', jobDetails.description);
    await page5.select('#jobType', jobDetails.jobType);
    await page5.type('#requirements', jobDetails.requirements);
    await page5.evaluate(() => document.querySelector('#isRemote').click()); // Check the 'isRemote' checkbox

    // Click the "Add Job Listing" button
    await page5.evaluate(() => {
      const addButton = Array.from(document.querySelectorAll('button'))
        .find(button => button.textContent.trim() === 'Add Job Listing');
      if (addButton) addButton.click();
    });

    await new Promise((resolve) => setTimeout(resolve, 2000));

    // Verify navigation to viewJobs page
    await page5.waitForSelector('h2', { timeout: 2000 });
    const pageTitle = await page5.$eval('h2', el => el.textContent.trim());
    const urlAfterClick = page5.url();
    const navigationSuccess = pageTitle === 'Job Listings' && urlAfterClick.toLowerCase().includes('/viewjobs');

    // Check if the newly added job listing appears in the table
    await page5.waitForSelector('table tbody tr', { timeout: 2000 });
    const jobListings = await page5.$$eval('table tbody tr', rows =>
      rows.map(row => Array.from(row.querySelectorAll('td')).map(td => td.textContent.trim()))
    );

    const jobDisplayed = jobListings.some(row => row.includes(jobDetails.title) && row.includes('Yes'));  // 'Yes' for remote

    if (navigationSuccess && jobDisplayed) {
      console.log('TESTCASE:add_job_listing_and_verify_in_viewJobs:success');
    } else {
      console.log('TESTCASE:add_job_listing_and_verify_in_viewJobs:failure');
    }
  } catch (e) {
    console.log('TESTCASE:add_job_listing_and_verify_in_viewJobs:failure');
  }

  // **6. Verify Job Table Header Content**
  const page6 = await browser.newPage();
  try {
    await page6.goto(`${BASE_URL}/viewJobs`);
    await page6.waitForSelector('table', { timeout: 2000 });
    const tableHeaders = await page6.evaluate(() => {
      return Array.from(document.querySelectorAll('table th')).map(th => th.textContent.trim());
    });
    const expectedHeaders = ['Job Title', 'Company', 'Location', 'Salary Package', 'Job Type', 'Is Remote', 'Description', 'Requirements', 'Actions'];
    if (expectedHeaders.every(header => tableHeaders.includes(header))) {
      console.log('TESTCASE:job_table_header_content:success');
    } else {
      console.log('TESTCASE:job_table_header_content:failure');
    }
  } catch (e) {
    console.log('TESTCASE:job_table_header_content:failure');
  }

  // **Close all pages and browser**
  finally {
    await page1.close();
    await page2.close();
    await page3.close();
    await page4.close();
    await page5.close();
    await page6.close();
  }

  await browser.close();
})();
