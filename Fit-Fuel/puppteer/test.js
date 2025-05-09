const puppeteer = require('puppeteer');

const BASE_URL = 'https://8081-fddbbedbb327214235bfdebbddcaecone.premiumproject.examly.io';

(async () => {
  const browser = await puppeteer.launch({
    headless: false,
    args: ['--headless', '--disable-gpu', '--remote-debugging-port=9222', '--no-sandbox', '--disable-setuid-sandbox'],
  });
  const page = await browser.newPage()
  try {
    // Go to the add cricket player form
    await page.goto(`${BASE_URL}/add-player`, { waitUntil: 'networkidle2' });
    await page.waitForSelector('form');
    await page.type('input[formControlName="name"]', 'Virat Kohli');
    await page.type('input[formControlName="age"]', '35');
    await page.type('input[formControlName="team"]', 'India');
    await page.select('select[formControlName="position"]', 'Batsman');
    await page.type('input[formControlName="battingStyle"]', 'Right-hand bat');
    await page.type('input[formControlName="bowlingStyle"]', 'Right-arm medium');

    // Optional fields
    await page.type('input[formControlName="totalRuns"]', '12000');
    await page.type('input[formControlName="totalWickets"]', '5');
    await page.type('input[formControlName="totalMatches"]', '275');

    // Submit the form
    await Promise.all([
      page.click('button[type="submit"]'),
      page.waitForNavigation({ waitUntil: 'networkidle2' })
    ]);

    // Check if redirected to /players page
    const url = page.url();
    if (url.includes('/players')) {
      console.log('TESTCASE:add_cricket_player_form_submission:success');
    } else {
      console.log('TESTCASE:add_cricket_player_form_submission:failure');
    }
  } catch (error) {
    console.log('TESTCASE:add_cricket_player_form_submission:failure');
    console.error(error);
  }

  // **2. Verify Add Job Form Exists with All Required Fields**
  const page2 = await browser.newPage()  
  try {
    await page2.goto(`${BASE_URL}/add-player`,{timeout:3000}); // adjust route if it's different
    const formExists = await page2.evaluate(() => {
      const form = document.querySelector('form');
      const inputFields = [
        'name',
        'age',
        'team',
        'position',
        'battingStyle',
        'bowlingStyle',
        'totalRuns',
        'totalWickets',
        'totalMatches'
      ];
      return !!form && inputFields.every(field => !!form.querySelector(`[formControlName="${field}"]`));
    });

    if (formExists) {
      console.log('TESTCASE:addCricketPlayer_form_exists_and_input_fields_present:success');
    } else {
      console.log('TESTCASE:addCricketPlayer_form_exists_and_input_fields_present:failure');
    }
  } catch (e) {
    console.log('TESTCASE:addCricketPlayer_form_exists_and_input_fields_present:failure');
  } 

  // **3. Verify Search Functionality (Partial Match)**
  const page3 = await browser.newPage();

try {
  await page3.goto(`${BASE_URL}/players`, { timeout: 10000, waitUntil: 'domcontentloaded' });
  await page3.waitForSelector('table', { timeout: 3000 });

  // Extract the Total Runs column before sorting
  const runsBeforeSort = await page3.evaluate(() => {
    const rows = Array.from(document.querySelectorAll('tbody tr'));
    return rows.map(row => parseInt(row.children[7].textContent.trim(), 10));
  });

  // Click the sort button
  await page3.click('button'); // Adjust if there are multiple buttons
  await page3.waitForTimeout(1000); // Wait for DOM to update

  // Extract the Total Runs column after sorting
  const runsAfterSort = await page3.evaluate(() => {
    const rows = Array.from(document.querySelectorAll('tbody tr'));
    return rows.map(row => parseInt(row.children[7].textContent.trim(), 10));
  });

  const isSorted = runsAfterSort.every((val, i, arr) => i === 0 || arr[i - 1] <= val);

  if (isSorted) {
    console.log('TESTCASE:sortByRuns_button_sorts_table_correctly:success');
  } else {
    console.log('TESTCASE:sortByRuns_button_sorts_table_correctly:failure');
    console.log('Before Sort:', runsBeforeSort);
    console.log('After Sort:', runsAfterSort);
  }
} catch (e) {
  console.log('TESTCASE:sortByRuns_button_sorts_table_correctly:failure');
  console.error('Error:', e);
}

  // **4. Verify Required Field Validation on Add Job Listing**
 

  describe('Cricket Player Form Validation', () => {
    let browser, page;
  
    beforeAll(async () => {
      browser = await puppeteer.launch({ headless: true });
      page = await browser.newPage();
      await page.goto(BASE-URL); // Adjust if needed
    });
  
    afterAll(async () => {
      await browser.close();
    });
  
    it('should show alert with required field messages when submitting empty form', async () => {
      // Listen for alert popup
      page.on('dialog', async dialog => {
        const message = dialog.message();
  
        expect(message).toContain('Name is required.');
        expect(message).toContain('Age is required.');
        expect(message).toContain('Team is required.');
        expect(message).toContain('Position is required.');
        expect(message).toContain('Batting style is required.');
        expect(message).toContain('Bowling style is required.');
  
        await dialog.dismiss(); // Close the alert
      });
  
      // Click submit without filling any field
      await page.click('button[type="submit"]');
  
      // Wait briefly to let alert be triggered
      await page.waitForTimeout(500);
    });
  });
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
}) ();
