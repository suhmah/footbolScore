# Football Score App

This is a React Native application that displays football scores and updates them in real-time through notifications. The app supports both Android and iOS platforms.

## Features

1. **Display Current Score**: Shows the current score of the football match.
2. **Add Goals**: Allows users to add goals to either team.
3. **Update Notification**: Updates the score and other details through a native notification.
4. **Persistent Score**: Saves the current state of the game and loads it when the app restarts.

## Implementation Details

### React Native Components

1. **App.tsx**:
   - This is the main component of the application.
   - It manages the state for the home and away scores and the scorers.
   - It uses `useEffect` to load the saved score when the app starts.
   - It provides buttons to add goals to each team and to update the score.

2. **TeamScoreBoard**:
   - Displays the name and logo of the team.

3. **ScoreButtons**:
   - Provides buttons to add goals to the teams.

### Native Module (Android)

1. **FootballScoreModule.kt**:
   - This module handles the creation of notifications and saves the state of the game.
   - It uses `SharedPreferences` to save and retrieve the game state.
   - It uses `Glide` to load images into the notifications.
   - It provides methods to update the score and retrieve the saved score.

### JavaScript Services

1. **getSavedScore.ts**:
   - A service to retrieve the saved score from the native module.

## Getting Started

### Prerequisites

- Node.js
- npm or yarn
- Android Studio (for Android development)
- Xcode (for iOS development)

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/suhmah/footbolScore.git
   cd football-score-app


2. Install dependencies:
   ```sh yarn install
   yarn install

### Running the App

1. Install dependencies:
   ```sh yarn install
   yarn install
   
2. Run the app on an Androiddevice/emulator:
   ```sh yarn android
   yarn android

### Updating the Score

1. Open the app.

2. Use the buttons to add goals to either team.

### Screen record


https://github.com/suhmah/footbolScore/assets/38407958/2020551c-df7e-4f0c-b10c-69b7031edd4c




4. Additional Notes
The app saves the current state (scores and scorers) and restores it when reopened.
The notifications are customized using native Android code.
