# Talk2Me
The Android-based instant message application uses the client/server architecture. The client can add a registered user to be his/her friend and send or receive a text message while the friend is online. Currently, the communication in this application is using TCP. Google Firebase database is used as a backbone to store the user information.


### System Requirement:
1.	Android version 8 or above
2.	Active internet connection on your device


### Set Up on Android Studio
Add all the dependencies in the gradle file.

    implementation 'com.android.support:appcompat-v7:28.0.0-rc01'

    implementation 'com.android.support:design:28.0.0-rc01'

    implementation 'com.android.support:support-v4:28.0.0-rc01'

    implementation 'com.android.support.constraint:constraint-layout:1.1.2'

    implementation 'com.google.firebase:firebase-auth:11.8.0'

    implementation 'com.google.firebase:firebase-database:11.8.0'

    implementation 'com.google.firebase:firebase-messaging:11.8.0'

    implementation 'com.google.firebase:firebase-storage:11.8.0'

    implementation 'com.firebaseui:firebase-ui-database:3.2.2'

    implementation 'com.android.support:support-v4:28.0.0-rc01'

    implementation 'de.hdodenhof:circleimageview:2.2.0'
 
    implementation 'com.theartofdev.edmodo:android-image-cropper:2.7.0'
    
    implementation 'com.squareup.picasso:picasso:2.71828'
    
 2. extract all the files
 3. Build the gradle and copy all the files from "src" and"gradle".
    
 4. Compile and run.
    
### Features of TALK 2 ME:
#### 1. User Registration:
  The User needs to type his/her phone number in order to finish the first verification process. This is one of the simplest form of verification for users.

#### 2.User Login:
  After first registration user can generate an ID and password through a proper phone no. and E-mail verification which makes the user comfortable with compatibility feature so that if they want to work it on any other device later on then it will be easy for them to switch through their login details.


#### 3.User Profile:
A Profile is the most important feature of an application which can be used to display the characteristics of a person. In the field of socializing with the world, a user profile definitely plays a major role in expressing the person.

#### 4.Peer to peer message:
It is a system of communication where particularly communicating users can read the messages. However, End to end encryption always plays an important role in the textual conversation so its usage has become a vital important feature to be implemented whenever possible.

#### 5.Group formation:
It gives the power to the user to form a list of people and create a localized group of his/her own choice in order to share a common message to the participants of the group.

#### 6.Broadcast message:
This feature enables user to create a group of selected contacts and then frequently send the message to a number of people. All these texts will start appearing to recipients as normal non-group messages.

##### 7.Find friends:
It needs at least two persons to start any type of conversation with unless one is obsessed with talking to himself/herself as a SOLILOQUY (talking to yourself).
Thus the name TALK 2 ME provide you the feature to search your friends in a certain list.
There may be a lot of friends in your friend list or circle whom you want to text, talk or share experiences with, and for this you can use the modern format of texting through a messaging app.

#### 8.Cloud Synchronization: 
This is a process of keeping files like images, docs, audios, and videos stored in different places. TALK 2 ME provides storing of chat histories and files in the cloud, and this can be accessed anywhere and anytime whenever an Internet Connection is provided.

##### 9.Push Notifications:
Instant messaging apps cannot be completed without push notifications that enable users to check the new messages. It informs the users with the new available messages.


### Future Scope: - 
Even though the majority of functionalities were implemented, the application is still a proof-of-concept and there is still room for modifications and optimization.
If the limitations are resolved, then the applications will be more effective. In future we will try to develop our project and add certain features like scheduler, geolocation and so on.
With this growing world of digitalization and further development of our conceptual project we will be soon able to bring a remarkable change in the field of messaging and emerge as one of the valuable project possible.

## ScreenShots
### Login Page

<img src = screenshot/login.jpg width = "320" height = "480">

### SignUp Page

<img src = screenshot/createaccount.jpg width = "320" height = "480">
### Chat Fragment Page

<img src = screenshot/chatfragment.jpg width = "320" height = "480">
### Profile Update Page

<img src = screenshot/profileupdate.jpg width = "320" height = "480">
