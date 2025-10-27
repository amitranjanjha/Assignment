# User Listing App -Sample App 
## MVVM clean Architecture
<img width="600" height="1200" alt="Screenshot_20251027_104921" src="https://github.com/user-attachments/assets/f51d23ed-780e-41c0-a101-61f39541cd48" />

## App Screen
<img width="270" height="600" alt="Screenshot_20251027_105935" src="https://github.com/user-attachments/assets/0925f46b-3f78-4eca-967c-aa52510bd994" />

<img width="270" height="600" alt="Screenshot_20251027_105051" src="https://github.com/user-attachments/assets/fa927024-2ec7-424e-a71e-b5dc226566e9" />
<img width="270" height="600" alt="Screenshot_20251027_104921" src="https://github.com/user-attachments/assets/39fcc882-0c81-4483-ac60-c83d3496b1d0" />
## Screen recording
[![Watch the demo](https://img.youtube.com/vi/j2u67nBoRto/0.jpg)](https://www.youtube.com/watch?v=j2u67nBoRto)


## App Architecture: Key Components and Data Flow

Description below outlines the key components and data flow within this application's architecture, emphasizing a clear separation of concerns for improved maintainability and testability.

## Key Components and Their Roles
### Presentation Layer (View):

    - Compose Screens (e.g., UserListScreen)
      Role: Responsible for displaying data to the user and capturing all user interactions.
    - ViewModel
     Role: Manages UI-related data and state, processes user actions, and communicates with the domain Layer.
### Domain Layer:
    - model
    - repository
    - usecase
    
### Data Layer :
    NetworkService
    Role: Handles all API calls to the external news service.
        
### Util:
    General Item
    
## Data Flow
Understanding the data flow illustrates how different components interact to deliver information to the user:

    1. User : User open the screen
    2. UI Notification: The UI notifies the ViewModel for data.
    3. ViewModel Request: The ViewModel requests data from the Repository thru usecase.
    4. Repository Fetch: The Repository fetches data from the NetworkService.
    5. NetworkService API Call: The NetworkService makes an API call to the External API.
    6. Data Flow Back: Data flows back up the chain, potentially undergoing transformations at each layer to fit the needs of the higher layers.
    7. ViewModel Update: The ViewModel updates its state with the received data.
    8. UI Update: The UI observes the ViewModel's state changes and updates accordingly to display the new information to the user.
## Folder Structure   
<img width="580" height="641" alt="Screenshot 2025-10-27 at 12 09 08 PM" src="https://github.com/user-attachments/assets/56c219c0-1bcd-4dbe-a58b-4d38a01a7361" />


## TODO
Add more test cases especially Android instrumentation tests.  
Add Room Database to support offline first app.

