# Anthology-Maker
CIT5940 Spring 2023 Final Project
Team members: Milan Filo, Vasco Silveira, Qingmiao Zhong , Rui Zhou.

Generate a personalized poetry anthology based on user search specifications (author, title, content, theme, form). 
Interaction with the console determines what poems are written out into the poem_anthology.txt file.

Features:
- Author-based searches
- Title-based searches
- Word-based searches
- Theme-based searches (love, death, happiness, etc.)
- Form-based search (sonnet, free verse, etc.)

Data Structures & Software Design
- Linear data structures used to generate lists of poem suggestions
- Hashing used to create mappings and associations between poems and characteristics
- Indexing used to store data efficiently
- Developing internal algorithms to determine poem structure and form

Web Scraping Framework: implementing a web crawler to scrape data from website.
- Data source is famouspoetsandpoems.com
- Scrape author, poem title, and poem text
- External libraries used: Selenium, WebDriver (ChromeDriver)
- Write out scraped data into a CSV file
- each row in a CSV File represents a Poem Object
- read by a CSV File Reader Class to generate our poetry “database”

Classes in this framework:
- WebCrawler Class

Database Framework:
CSVFileReader() class reads each row of the CSV file and instantiates a Poem object using the poem’s title, author, and text. Each Poem object is stored in a List.
Poem() Class constructor figures out the Poem’s theme, form, etc. using the poem text variable
External libraries used: Open CSV, Apache Commons Lang 3
DataBaseManage() class is responsible for creating the data structures using hashing (i.e. HashMaps) based off Poem List on which user searches will be performed 

Classes in this framework:
- Poem Class
- Database Manage Class
- CSVFileReader Class 


User Console Framework: user interacts with the Console to search for poems, view them and decide to add them to their anthology. These poems are written out to poem_anthology.txt
Classes in this framework:
- Console Class 

