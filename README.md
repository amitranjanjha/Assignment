# User Listing App -Sample App
![architecture ](https://github.com/user-attachments/assets/f51d23ed-780e-41c0-a101-61f39541cd48)
## App Screen
<img width="600" height="1200" alt="Screenshot_20251027_093659" src="https://github.com/user-attachments/assets/9e3e81a2-f49c-4234-9628-1247cee1688f" /> 

## App Architecture: Key Components and Data Flow

Description below outlines the key components and data flow within this application's architecture, emphasizing a clear separation of concerns for improved maintainability and testability.

## Key Components and Their Roles
### Presentation Layer (View):

    Compose Screens (e.g., SearchActivity)
    Role: Responsible for displaying data to the user and capturing all user interactions.
ViewModel Layer:

XXXViewModel (e.e.g SearchViewModel)
Role: Manages UI-related data and state, processes user actions, and communicates with the Repository Layer.
Repository Layer:

SearchRepository
Role: Serves as the single source of truth for data. It decides when and how to fetch data from the network.
Network Layer:

NetworkService
Role: Handles all API calls to the external news service.
External API:

The actual news service API that provides the data consumed by the application.

