# Anthology-Maker
**CIT5940 Spring 2023 Final Project
**Team members: Milan Filo, Vasco Silveira, Qingmiao Zhong , Rui Zhou.


**Project Description:
**
Generate a personalized poetry anthology based on user search specifications (author, title, content, theme, form). 
Interaction with the console determines what poems are written out into the poem_anthology.txt file.**


**Background:
**

The group came about this idea as we were talking about Spotify’s music recommendation software. It had occurred to us that it could be interesting to mimic certain aspects of a music playlist generation algorithm that is based on specified parameters and user preferences.
We realized  that it would be more enjoyable and original to focus on a domain that is given less attention these days – poetry.
Rather than designing software that generates a playlist tailored to an individual’s preferences, we want to develop a program that creates a collection of poems (an anthology) specific to an individual’s choice of theme, epoch, structure, and/or form

Additionally, we would like to develop our own internal algorithms to determine the structure and form of our poems to define them accordingly. For instance, we could develop methods that would compute the number of syllables and stanzas in a poem which would let us know if a poem is a sonnet for example.


**Features:
**

- Author-based searches
- Title-based searches
- Word-based searches
- Theme-based searches (love, death, happiness, etc.)
- Form-based search (sonnet, free verse, etc.)

**Data Structures & Software Design
**

- Linear data structures used to generate lists of poem suggestions
- Hashing used to create mappings and associations between poems and characteristics
- Indexing used to store data efficiently
- Developing internal algorithms to determine poem structure and form

Web Scraping Framework: implementing a web crawler to scrape data from website.
- Data source is famouspoetsandpoems.com
- Scrape author, poem title, and poem text
- External libraries used: Selenium, WebDriver (ChromeDriver), SL4J
- Write out scraped data into a CSV file (poem_data.csv)
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

