exampleData = {
	players: [
		{
			hands: [
				{
					cards: [
						[0, 1],
						[1, 2]
					],
					id: 0
				}
			],
			id: 1,
			name: 'naam1',
			bet: 12
		},
		{
			hands: [
				{
					cards: [
						[2, 3],
						[3, 4],
					],
					id: 0
				},
				{
					cards: [
						[0, 5],
						[1, 6],
						[2, 7]
					],
					id: 1
				}
			],
			id: 2,
			name: 'naam2',
			bet: 1999
		}
	],
	dealer:{
		hand:[
			[3, 4]
		]
	},
	shoe:35
}
parseData(exampleData);