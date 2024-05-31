import {NativeModules} from 'react-native';

interface FootballScoreModuleInterface {
  updateScore(
    homeTeam: string,
    awayTeam: string,
    homeScore: number,
    awayScore: number,
    homeLogoPath: string,
    awayLogoPath: string,
    homeScorersString: string,
    awayScorersString: string,
    timestamp: string,
  ): void;
}

const {FootballScoreModule} = NativeModules;

export default FootballScoreModule as FootballScoreModuleInterface;
