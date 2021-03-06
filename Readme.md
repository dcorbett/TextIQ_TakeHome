# Take home task

Create a webapp that allows you to search through articles by keywords

![Document Viewer](demo.png?raw=true "Take Home Assignment")

## Archtecure
I chose to use 2 tiers to complete this project. A react frontend run by a node server. I chose to use java dropwizard for the REST midtier. I chose MySQL for the database. Lastly I chose docker for my deployment container so that it can run without issue.

## Endpoints
I have created 4 endpoints which the application will use. The first 3 are used to display the data. 

    - /articles : GET
    - /articles/id : GET
    - /search?q=<query>&page=<int> : GET
    - /processDocuments

The article endpoint will display either a paginated list of articles or a the full context of a specific article. The search will return a paginated list of all articles which contain a specific keyword. The processDocuments endpoint parses all of the docs in a specific folder and inserts them in the DB if they are not present. Running processDocuments is required to populate the database

## Setup
This setup requires docker https://docs.docker.com/engine/installation/

Once you are in the takeHome folder run the follwing to start and stop the project

To restart the project
    docker-compose down
    docker-compose up fullstack

to verify its running the webapp runs on http://localhost:18000

This can also be installed locally. It uses MySQL Database and Dropwizard / Gradel for the DB engine. Locally the Frontend and packed end run on ports 3000 and 8000 respectivly

The database schmea is stored in the DB directory under init.sql
