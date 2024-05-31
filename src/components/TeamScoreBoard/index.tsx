import React from 'react';
import {View, Image, Text} from 'react-native';
import {TEAMS} from '../../utils/constants';
import styles from '../styles';

type TeamScoreBoardProps = {
  team: 'home' | 'away';
};

const TeamScoreBoard: React.FC<TeamScoreBoardProps> = ({team}) => (
  <View style={styles.row}>
    <Image source={{uri: TEAMS[team].logo}} style={styles.logo} />
    <Text style={styles.teamName}>{TEAMS[team].name}</Text>
  </View>
);

export default TeamScoreBoard;
