# Focura-Record

30/4/2015
Parth
-Create user activity
-Sign in activity
-Custom splah screen
-Initial activity sorting

2/5/2025
Parth
-FeatureSelector
-AuditoryHome
-VisualHome basic activity created
-User account retrival

4/5/2025
Parth
-Home and more button in visual and auditory activity
-Profile bs fragment
-Profile activity
-Settings activity
-Signout functionality

shrishti 
-will discuss with ayush and decide the brand color and typography

5/5/2025
Parth
-Firebase Authentication
-Adding basic padding to all existing fragments and activities 
-Create account using firebase auth (using google acc as ref

6/5/2025
All
-finalised the rough user journey of app
-finalised upon how the auditory sample space (lyrical music) be served to user
-brain stormed upon how to increase difficulty in visual amd auditory modes

7/5/2025
Parth
-Portfolio activity
-Setting activity)
-View Pager 2 implementation in Auditory main,Visual main
-Auditory and Visual Home fragment
-Auditory and Visual Leaderboard fragment
-Instrument Sound activity
-finalising the top dock menu {Streak,xps,life} for both auditory and visual
-Streak activity
-XPS activity

8/5/2025
Parth
-Finalised instruments list
-Curated sample spaces (wav files) of all instruments
-Added back button to all activities

11/5/2025
Parth
-Instrument sounds preview
-Removal of Feature Selector Activity

13/5/2025
Parth
-Forget password (authentication by registered phonenumber verification)


15/05/2025
shirin
-on week 3 of course 2 of DL

Parth
-UI of Login Screen Implemented
-Made a sample app using free music api

16/05/2025
Parth
-UI of Create Account Implemented 

17/05/2025
Parth
-UI implementation of 
    -instrument sounds
    -authentication through phone number
    -custom splash,on boarding
-Icon implementation

19/06/2025
Parth
-Minor improvements

20/05/2025-22/05/2025
Parth
-Searching Free Misic APIs
-Testing API
-Learning API integration

23/05/2025
Parth
-Secessfully tested youtube video to audio converter and downloader api
-Sucessfully implemented YT downloader api in app and made a project on it

24/05/2025
Parth
-Testing of spotify track search, download API
-Worked on the project which integrates the above API

25/05/2025
Soumik
-Joined the project

Parth
-Sucessfully tested spotify track downloader
-Sucessfully implemented the spotify track downloader API and made a project on it

Issues solved & experience gained(*)
*Learned to use "Postman" for API end point testing 
*Make "ApiResponse" as the primary data class if there are more than one branches at the initial data structure tree in JSON
*Learned to use "HttpLoggingInterceptor" for debugging
*Realised the value of logging while debugging


26/05/2025
Parth
-Code review of API integration projects

27/05/2025
Parth
-Decided flow of audio file processing for different difficulty levels
-Onboarding activity creation
-Onboarding activity ui implementation

28/05/2025
Parth
-Implemented music track downloader in the main project
-made the pilot project to determine the coordinates of the touch on image view

29/05/2025
Parth
-Made a pilot project to run a track on predetermined track start and end point annotations fed through a CSV file for single instrument determination

30/05/2025
All
-Revised the auditory section basis
-Undone the unrelated provisions in auditory section in app
-Ui implementation of auditory (everything except recycler view)

31/05/2025
Parh
-Gemini api integration and pilot project completion based on decided parameters and features. 
-Implementation of ui of visual and auditory home screen fragmnet recycler view. 

01/06/2025
Parth
-Leaderboard ui implementation of auditory and visual
-ui improvements and detailing

02/06/2025
Parth
-Worked on auditory readout level planner 
-Implemented top sheet fragment for lives remaining
-profile ui completion
-settings ui completion

Shrishti
-plan the screen of Auditory mode 
profile UI completion
settings UI completion

03/06/2025
Parth
-Made floating card view which apper on clicking any level
-finalised and created the basic structure of Tasks view holder (Activity) for Auditory Read out section and associated fragments (Blank Fragments)
-Ui improvements in implementation part

04/06/2025
Parth
-making view model for auditory read out
-made view model in common to graphic and auditory
-linked heart,xp count
-linked csv file of auditory readout to the project
-made a object to fetch data from csv
-linked the csv data extractor to view model
-extracted data from the csv to complete prompt for gemini
-fetched respoonse in json format
-sanitised the recived json
-channeled the data of individual q in a structured way and safely isolated each q by storing them in q type specific data class
