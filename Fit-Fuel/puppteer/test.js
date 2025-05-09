const puppeteer = require('puppeteer');

const BASE_URL = 'https://8081-fddbbedbb327214235bfdebbddcaecone.premiumproject.examly.io';

(async () => {
  const browser = await puppeteer.launch({
    headless: false,
    args: ['--headless', '--disable-gpu', '--remote-debugging-port=9222', '--no-sandbox', '--disable-setuid-sandbox'],
  });
  const page1 = await browser.newPage()
  try {
    // Go to the add cricket player form
    await page1.goto(`${BASE_URL}/add-player`, { waitUntil: 'networkidle2' });
    await page1.waitForSelector('form');
    await page1.type('input[formControlName="name"]', 'Virat Kohli');
    await page1.type('input[formControlName="age"]', '35');
    await page1.type('input[formControlName="team"]', 'India');
    await page1.select('select[formControlName="position"]', 'Batsman');
    await page1.type('input[formControlName="battingStyle"]', 'Right-hand bat');
    await page1.type('input[formControlName="bowlingStyle"]', 'Right-arm medium');

    // Optional fields
    await page1.type('input[formControlName="totalRuns"]', '12000');
    await page1.type('input[formControlName="totalWickets"]', '5');
    await page1.type('input[formControlName="totalMatches"]', '275');

    // Submit the form
    await Promise.all([
      page1.click('button[type="submit"]'),
      page1.waitForNavigation({ waitUntil: 'networkidle2' })
    ]);

    // Check if redirected to /players page
    const url = page1.url();
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

  // **Close all pages and browser**
  finally {
    await page1.close();
    await page2.close();
    await page3.close();
  }

  await browser.close();
}) ();
