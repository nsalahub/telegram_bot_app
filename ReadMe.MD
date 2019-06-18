Telegram bot application
==================
Bot name: NsalahubBot

Token: 727913534:AAFB3MpnpWO2Dl-R9q7Gn15WTvAenEywSSs
_____________
The application uses MySQL database

To start, you must create a database called "telegram". 
Then you need to start the application and liquibase will create the table you need to work.


If you enter a command  "/show" bot 
will give you a list of all available cities.
If the list is empty. 
You can add a city and its description using REST APT

Valid json: 
=====
Add city:
 (POST) /api/city

    {
        "title": "city",
        "description": "description",
        "deleted": false
         }
    
Update city:
    (POST) /api/city/update
 
    {
           "id": 1,
           "title": "city2",
           "description": "description",
           "deleted": false
       }
      
 Delete city: (DELETE) "/api/city/1" ({id})