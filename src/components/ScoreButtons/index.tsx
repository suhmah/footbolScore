import React from 'react';
import {View, Button} from 'react-native';
import {TEAMS} from '../../utils/constants';
import styles from '../styles';

type ScoreButtonsProps = {
  team: 'home' | 'away';
  addGoal: (team: 'home' | 'away') => void;
};

const ScoreButtons: React.FC<ScoreButtonsProps> = ({team, addGoal}) => (
  <>
    <Button
      title={`Add Goal to ${TEAMS[team].name}`}
      onPress={() => addGoal(team)}
    />
    <View style={styles.spacing} />
  </>
);

export default ScoreButtons;
