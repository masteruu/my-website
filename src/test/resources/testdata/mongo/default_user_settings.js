// Keep only default user data.
db.getCollection('user').remove({ _id : { $ne : "default"} });

db.getCollection('user').find({ _id : "default"}).forEach(function(containerOfUser) {
	containerOfUser.uiSettings = {
		"userId" : "default",
		"timelineSettings" : {
			"arrowNavigationStep" : 10,
			"laneSettings" : {
				"periods" : [ 
					{
						"periodId" : 1,
						"name" : "< 2h",
						"milliSeconds" : NumberLong(0),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "1_p2_campaign",
										"name" : "Campaign",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "1_p1_event",
										"name" : "Event",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "1_p0_state_roll",
										"name" : "State/Roll",
										"visibility" : true,
										"dependencyId" : ""
									}, 
									{
										"laneTypeId" : "1_p0_prod_nonprod",
										"name" : "Prod./Non-prod",
										"visibility" : false,
										"dependencyId" : ""
									}
								]
							}
						]
					}, 
					{
						"periodId" : 2,
						"name" : "2h - 24h",
						"milliSeconds" : NumberLong(7200000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "2_p2_campaign",
										"name" : "Campaign",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "2_p1_event",
										"name" : "Event",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "2_p0_state_roll",
										"name" : "State/Roll",
										"visibility" : true,
										"dependencyId" : ""
									}, 
									{
										"laneTypeId" : "2_p0_prod_nonprod",
										"name" : "Prod./Non-prod",
										"visibility" : false,
										"dependencyId" : ""
									}
								]
							}
						]
					}, 
					{
						"periodId" : 3,
						"name" : "24h - 3d",
						"milliSeconds" : NumberLong(90000000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "3_p2_campaign",
										"name" : "Campaign",
										"visibility" : false,
										"dependencyId" : "3_p0_campaign"
									}, 
									{
										"laneTypeId" : "3_p2_cw",
										"name" : "Calendar week",
										"visibility" : true,
										"dependencyId" : "3_p0_prod_nonprod"
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "3_p1_event",
										"name" : "Event",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "3_p0_campaign",
										"name" : "Campaign",
										"visibility" : true,
										"dependencyId" : "3_p2_campaign"
									}, 
									{
										"laneTypeId" : "3_p0_prod_nonprod",
										"name" : "Prod./Non-prod",
										"visibility" : false,
										"dependencyId" : "3_p2_cw"
									}
								]
							}
						]
					}, 
					{
						"periodId" : 4,
						"name" : "3d - 1w",
						"milliSeconds" : NumberLong(259200000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "4_p2_campaign",
										"name" : "Campaign",
										"visibility" : false,
										"dependencyId" : "4_p0_campaign"
									}, 
									{
										"laneTypeId" : "4_p2_cw",
										"name" : "Calendar week",
										"visibility" : true,
										"dependencyId" : "4_p0_prod_nonprod"
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "4_p1_event",
										"name" : "Event",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "4_p0_campaign",
										"name" : "Campaign",
										"visibility" : true,
										"dependencyId" : "4_p2_campaign"
									}, 
									{
										"laneTypeId" : "4_p0_prod_nonprod",
										"name" : "Prod./Non-prod",
										"visibility" : false,
										"dependencyId" : "4_p2_cw"
									}
								]
							}
						]
					}, 
					{
						"periodId" : 5,
						"name" : "1w - 1m",
						"milliSeconds" : NumberLong(604800000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "5_p2_month",
										"name" : "Month",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "5_p1_cw",
										"name" : "Calendar week",
										"visibility" : true,
										"dependencyId" : "5_p0_prod_nonprod"
									}, 
									{
										"laneTypeId" : "5_p1_campaign",
										"name" : "Campaign",
										"visibility" : false,
										"dependencyId" : "5_p0_campaign"
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "5_p0_campaign",
										"name" : "Campaign",
										"visibility" : true,
										"dependencyId" : "5_p1_campaign"
									}, 
									{
										"laneTypeId" : "5_p0_prod_nonprod",
										"name" : "Prod./Non-prod",
										"visibility" : false,
										"dependencyId" : "5_p1_cw"
									}
								]
							}
						]
					}, 
					{
						"periodId" : 6,
						"name" : "1month-3months",
						"milliSeconds" : NumberLong(2678400000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "6_p2_month",
										"name" : "Month",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "6_p1_cw",
										"name" : "Calendar week",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "6_p0_campaign",
										"name" : "Campaign",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}
						]
					}, 
					{
						"periodId" : 7,
						"name" : "3months-1year",
						"milliSeconds" : NumberLong(7776000000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "7_p2_year",
										"name" : "Year",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "7_p1_month",
										"name" : "Month",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "7_p0_cw",
										"name" : "Calendar week",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}
						]
					}, 
					{
						"periodId" : 8,
						"name" : "1year-1.5 years",
						"milliSeconds" : NumberLong(31536000000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "8_p2_year",
										"name" : "Year",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : false,
								"laneTypes" : []
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "8_p1_month",
										"name" : "Month",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}
						]
					}, 
					{
						"periodId" : 9,
						"name" : "1.5 years-",
						"milliSeconds" : NumberLong(47343600000),
						"lanes" : [ 
							{
								"name" : "Lane 3",
								"position" : 3,
								"active" : true,
								"laneTypes" : [ 
									{
										"laneTypeId" : "9_p2_year",
										"name" : "Year",
										"visibility" : true,
										"dependencyId" : ""
									}
								]
							}, 
							{
								"name" : "Lane 2",
								"position" : 2,
								"active" : false,
								"laneTypes" : []
							}, 
							{
								"name" : "Lane 1",
								"position" : 1,
								"active" : false,
								"laneTypes" : []
							}
						]
					}
				]
			}
		},
		"trendSettings" : {
			"graphStyle" : "LAZY_STEP",
			"trendTypesSettings" : [ 
				{
					"trendType" : "THICKNESS",
					"visible" : true
				}, 
				{
					"trendType" : "SPEED",
					"visible" : true
				}, 
				{
					"trendType" : "THROUGHPUT",
					"visible" : true
				}
			],
			"trendTypeLineSettings" : [ 
				{
					"lineNumber" : 1741,
					"trendTypeConfigurations" : [ 
						{
							"trendType" : "THICKNESS",
							"minValue" : 500.0,
							"maxValue" : 2500.0
						}, 
						{
							"trendType" : "SPEED",
							"minValue" : 450.0,
							"maxValue" : 900.0
						}, 
						{
							"trendType" : "THROUGHPUT",
							"minValue" : 100.0,
							"maxValue" : 2500.0
						}
					]
				}
			],
			"tooltipType" : "SNAP_TO"
		},
		"language" : "en",
		"thicknessSettings" : [ 
			{
				"lineNumber" : 1741,
				"thicknessTrendSettings" : {
					"scaleStartEdge" : 15.0,
					"scaleEndEdge" : 15.0,
					"twoSigmaPercentMin" : 0.0,
					"twoSigmaPercentMax" : 8.0
				},
				"thicknessProfileSettings" : {
					"scaleStartEdge" : 100.0,
					"scaleEndEdge" : 10.0,
					"graphTypeSettings" : [ 
						{
							"graphType" : "AVG",
							"visible" : true,
							"bold" : false
						}, 
						{
							"graphType" : "TWO_SIGMA",
							"visible" : true,
							"bold" : false
						}, 
						{
							"graphType" : "MIN_MAX",
							"visible" : true,
							"bold" : false
						}, 
						{
							"graphType" : "TARGET",
							"visible" : true,
							"bold" : false
						}
					]
				}
			}
		],
		"materialTableColumnSettings" : [ 
			{
				"lineNumber" : 1741,
				"tableColumnSettings" : [ 
					{
						"columnName" : "MATERIAL_QUALITY",
						"order" : 0,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_VALIDITY",
						"order" : 1,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_PRODUCTIVITY",
						"order" : 2,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_DOSING",
						"order" : 3,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_EXTRUDER",
						"order" : 4,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_LABEL_ID",
						"order" : 5,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_MATERIAL",
						"order" : 6,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_QUANTITY",
						"order" : 7,
						"visible" : true,
						"type" : "NUMBER"
					}, 
					{
						"columnName" : "MATERIAL_STATUS",
						"order" : 8,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "MATERIAL_START_TIMESTAMP",
						"order" : 9,
						"visible" : true,
						"type" : "DATE"
					}, 
					{
						"columnName" : "MATERIAL_END_TIMESTAMP",
						"order" : 10,
						"visible" : true,
						"type" : "DATE"
					}, 
					{
						"columnName" : "MATERIAL_DURATION",
						"order" : 11,
						"visible" : true,
						"type" : "STRING"
					}
				]
			}
		],
		"rollsTableColumnSettings" : [ 
			{
				"lineNumber" : 1741,
				"tableColumnSettings" : [ 
					{
						"columnName" : "ROLLS_INITIAL_GRADE",
						"order" : 0,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_LABORATORY_GRADE",
						"order" : 1,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_ID",
						"order" : 2,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_TREATMENT_INSIDE",
						"order" : 3,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_TREATMENT_OUTSIDE",
						"order" : 4,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_RECIPE",
						"order" : 5,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_THICKNESS_SET",
						"order" : 6,
						"visible" : true,
						"type" : "NUMBER"
					}, 
					{
						"columnName" : "ROLLS_ENDTIMESTAMP",
						"order" : 7,
						"visible" : true,
						"type" : "DATE"
					}, 
					{
						"columnName" : "ROLLS_LENGTH",
						"order" : 8,
						"visible" : true,
						"type" : "NUMBER"
					}, 
					{
						"columnName" : "ROLLS_DENSITY",
						"order" : 9,
						"visible" : true,
						"type" : "NUMBER"
					}, 
					{
						"columnName" : "ROLLS_PRODUCT",
						"order" : 10,
						"visible" : true,
						"type" : "STRING"
					}, 
					{
						"columnName" : "ROLLS_STARTTIMESTAMP",
						"order" : 11,
						"visible" : true,
						"type" : "DATE"
					}, 
					{
						"columnName" : "ROLLS_WEIGHT",
						"order" : 12,
						"visible" : true,
						"type" : "NUMBER"
					},
                    {
                        "columnName" : "ROLLS_RATING",
                        "order" : 13,
                        "visible" : true,
                        "type" : "MULTI_OPTIONS",
                        "options" : [
                            "NONE",
                            "FAVORITE",
                            "BENCHMARK"
                        ]
                    },
					{
						"columnName" : "ROLLS_VALIDITY",
						"order" : 14,
						"visible" : true,
						"type" : "STRING"
					},
                    {
                        "columnName" : "ROLLS_THICKNESS_AVERAGE",
                        "order" : 15,
                        "visible" : true,
                        "type" : "NUMBER"
                    },
                    {
                        "columnName" : "ROLLS_TWOSIGMA_PERCENT",
                        "order" : 16,
                        "visible" : true,
                        "type" : "NUMBER"
                    }
				]
			}
		]
	};
		
	db.getCollection('user').save(containerOfUser);	
});
