import {NativeModules} from 'react-native';

const {FootballScoreModule} = NativeModules;

const getSavedScore = async () => {
  try {
    const score = await FootballScoreModule.getSavedScore();
    return score;
  } catch (error) {
    console.error('Failed to get saved score:', error);
    return null;
  }
};

export default getSavedScore;
