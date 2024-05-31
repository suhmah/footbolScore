import {StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
  },
  row: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  logo: {
    width: 50,
    height: 50,
  },
  teamName: {
    fontSize: 18,
    marginLeft: 8,
  },
  spacer: {
    width: 100,
  },
  scoreText: {
    fontSize: 24,
    marginVertical: 20,
  },
  spacing: {
    height: 20,
  },
});

export default styles;
