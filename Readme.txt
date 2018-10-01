Pre-requesite to run the application

Java 8 and Maven 3.5 ot later

Instructions To complile and run the application
----------------------------
Go to the Project installed folder (...\CalendarApp) and run the following commands

To compile

mvn clean install

To Run 

mvn spring-boot:run 

Note: The application will start using the default port 8080.  If that port is already used please use alternative port using the following command.

mvn spring-boot:run -Dserver.port=9091
	
Additional Notes
----------------
1) This application doesn't have any security 
2) This applicion Spring batch which will scheduled to run every 2 minutes
	-- This will print alert like "Dear Suresh You have an eventBirthday event on 2018-10-04T10:10:10"
	-- did not check the alert basesd on reminderTime 
3) Have 3 Entity models Calendar and CalendarEvents
4) use in-memory DB (h2database)
5) on startup the application loaded data into DB  ( Multiple events for the users Suresh and Kumar)


Please Use the following CURL commands to test the API  (Assumption the application is running on port 8080)
-------------------------------------------------------


To Verify the Application is Up and Running 

curl -v http://localhost:8080

To List all the Events with User name

curl -v http://localhost:8080/listAll


To List all the Events 

curl -v http://localhost:8080/listEvents


To add an Event (for user Suresh)

curl -v -X POST -H "Content-Type: application/json" -d "{\"name\": \"Birthday4\",\"user\": \"Suresh\",\"calendarEvents\": {\"title\": \"Birthday event 4\",\"eventDateTime\": \"2018-11-07T10:10:10\",\"location\": \"atlanta\",\"attendees\": 2,\"reminderTime\": \"23:59:00\",\"sent\": true}}"  http://localhost:8080/addEvent


To update an Event (for user Suresh)

curl -v -X PUT -H "Content-Type: application/json" -d "{\"name\": \"Birthday4\",\"user\": \"Suresh\",\"calendarEvents\": {\"title\": \"Birthday event 4\",\"eventDateTime\": \"2018-11-07T10:10:10\",\"location\": \"Alpharetta\",\"attendees\": 2,\"reminderTime\": \"23:59:00\",\"sent\": true}}" http://localhost:8080/updateEvent/Suresh/Birthday4


To delete an Event 

curl -v -X DELETE  http://localhost:8080/deleteEvent/suresh/birthday4

To get all the events by date (for user Suresh) -- Need to pass yyy-MM-dd

curl -v http://localhost:8080/listEvents/Suresh/2018-10-04  

To get all the events by Month (for user Suresh) -- ( need to pass the yyyy-MM)
curl -v http://localhost:8080/listEvents/Suresh/2018-10






