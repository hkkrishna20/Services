const fs = require('fs');
const axeBuilder = require('axe-webdriverjs');

var webdriver = require('selenium-webdriver'),
        By = webdriver.By, until = webdriver.until;
        var webdriver = require('selenium-webdriver');

var chrome = require('selenium-webdriver/chrome');

var driver = new webdriver.Builder()
    .forBrowser('chrome')
    .build();

// create a PhantomJS WebDriver instance
// const driver = new webDriver.Builder()
//   .forBrowser('phantomjs')
//   .build();

// run the tests and output the results in the console
driver
  .get('http://www.google.com')
  .then(() => {
    axeBuilder(driver)
      .analyze((results) => {
        console.log(results);
        // write to a new file named 2pac.txt
fs.writeFile('2pac.txt', JSON.stringify(results), (err) => {  
  // throws an error, you could also catch it here
  if (err) throw err;

  // success case, the file was saved
  console.log('Lyric saved!');
});
      });
  });
