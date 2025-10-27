# User Listing App -Sample App
![architecture ](https://github.com/user-attachments/assets/f51d23ed-780e-41c0-a101-61f39541cd48)
## App Screen
<img width="600" height="1200" alt="Screenshot_20251027_093659" src="https://github.com/user-attachments/assets/9e3e81a2-f49c-4234-9628-1247cee1688f" /> 

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

## TODO
Add more test cases especially Android instrumentation tests.  
Add Room Database to support offline first app.

