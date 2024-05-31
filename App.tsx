import React, {useState, useEffect} from 'react';
import {View, Button, Text} from 'react-native';
import FootballScoreModule from './src/utils/FootballScoreModule';
import TeamScoreBoard from './src/components/TeamScoreBoard';
import ScoreButtons from './src/components/ScoreButtons';
import {TEAMS} from './src/utils/constants';
import styles from './src/components/styles';
import getSavedScore from './src/services/getSavedScore';

type TeamType = 'home' | 'away';

const App: React.FC = () => {
  const [homeScore, setHomeScore] = useState<number>(0);
  const [awayScore, setAwayScore] = useState<number>(0);
  const [homeScorers, setHomeScorers] = useState<string[]>([]);
  const [awayScorers, setAwayScorers] = useState<string[]>([]);

  useEffect(() => {
    const loadSavedScore = async () => {
      const savedScore = await getSavedScore();
      if (savedScore) {
        setHomeScore(savedScore.homeScore);
        setAwayScore(savedScore.awayScore);
        setHomeScorers(
          savedScore.homeScorers ? savedScore.homeScorers.split('\n') : [],
        );
        setAwayScorers(
          savedScore.awayScorers ? savedScore.awayScorers.split('\n') : [],
        );
      }
    };

    loadSavedScore();
  }, []);

  const addGoal = (team: TeamType) => {
    const scorer =
      TEAMS[team].players[
        Math.floor(Math.random() * TEAMS[team].players.length)
      ];
    if (team === 'home') {
      setHomeScorers([...homeScorers, scorer]);
      setHomeScore(homeScore + 1);
    } else {
      setAwayScorers([...awayScorers, scorer]);
      setAwayScore(awayScore + 1);
    }
  };

  const updateScore = () => {
    const homeScorersString = homeScorers.join('\n');
    const awayScorersString = awayScorers.join('\n');
    const timestamp = '60:22';

    FootballScoreModule.updateScore(
      TEAMS.home.name,
      TEAMS.away.name,
      homeScore,
      awayScore,
      TEAMS.home.logo,
      TEAMS.away.logo,
      homeScorersString,
      awayScorersString,
      timestamp,
    );
  };

  return (
    <View style={styles.container}>
      <TeamScoreBoard team="home" />
      <View style={styles.spacer} />
      <TeamScoreBoard team="away" />
      <Text style={styles.scoreText}>
        {`${TEAMS.home.name} ${homeScore} - ${awayScore} ${TEAMS.away.name}`}
      </Text>
      <ScoreButtons team="home" addGoal={addGoal} />
      <ScoreButtons team="away" addGoal={addGoal} />
      <Button title="Update Score" onPress={updateScore} />
    </View>
  );
};

export default App;
