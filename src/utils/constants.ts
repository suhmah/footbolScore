export const TEAM_LOGOS = {
  barca: 'https://a.espncdn.com/i/teamlogos/soccer/500/83.png',
  city: 'https://upload.wikimedia.org/wikipedia/pt/0/02/Manchester_City_Football_Club.png',
};

export const TEAMS = {
  home: {
    name: 'FC Barcelona',
    players: [
      'Ter Stegen',
      'Koundé',
      'Araújo',
      'Christensen',
      'Alejandro Balde',
      'Gavi',
      'Busquets',
      'De Jong',
      'Dembélé',
      'Lewandowski',
      'Pedri',
    ],
    logo: TEAM_LOGOS.barca,
  },
  away: {
    name: 'Manchester City',
    players: [
      'Ederson',
      'Walker',
      'Stones',
      'Dias',
      'Gvardiol',
      'Rodri',
      'Kovacic',
      'Foden',
      'De Bruyne',
      'Doku',
      'Alvarez',
    ],
    logo: TEAM_LOGOS.city,
  },
};
