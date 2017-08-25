// Set default clientConfig.config data
db.getCollection('clientConfig').find({_id:"ILM"}).forEach(function(clientConfigDocument) {
	clientConfigDocument.config={
        "shortDateFormat" : "dd.MM.yy",
        "longDateFormat" : "dd.MM.yyyy",
        "shortTimeFormat" : "HH:mm",
        "longTimeFormat" : "HH:mm:ss",
        "shortDateFormatList" : [ 
            "dd.MM.yy", 
            "MM/dd/yy", 
            "yy-MM-dd"
        ],
        "longDateFormatList" : [ 
            "dd.MM.yyyy", 
            "MM/dd/yyyy", 
            "yyyy-MM-dd"
        ],
        "shortTimeFormatList" : [ 
            "HH:mm"
        ],
        "longTimeFormatList" : [ 
            "HH:mm:ss"
        ],
        "materialTableColumnSettings" : [
            {
                "lineNumber" : 1741,
                "tableColumnSettings" : [ 
                    {
                        "columnName" : "MATERIAL_DOSING",
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
                        "columnName" : "MATERIAL_QUALITY",
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
                    }, 
                    {
                        "columnName" : "MATERIAL_VALIDITY",
                        "order" : 12,
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
                        "columnName" : "ROLLS_ID",
                        "order" : 1,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_INITIAL_GRADE",
                        "order" : 2,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_LABORATORY_GRADE",
                        "order" : 3,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_TREATMENT_INSIDE",
                        "order" : 4,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_TREATMENT_OUTSIDE",
                        "order" : 5,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_PRODUCT",
                        "order" : 6,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_THICKNESS_SET",
                        "order" : 7,
                        "visible" : true,
                        "type" : "NUMBER"
                    }, 
                    {
                        "columnName" : "ROLLS_RECIPE",
                        "order" : 8,
                        "visible" : true,
                        "type" : "STRING"
                    }, 
                    {
                        "columnName" : "ROLLS_LENGTH",
                        "order" : 9,
                        "visible" : true,
                        "type" : "NUMBER"
                    }, 
                    {
                        "columnName" : "ROLLS_WEIGHT",
                        "order" : 10,
                        "visible" : true,
                        "type" : "NUMBER"
                    }, 
                    {
                        "columnName" : "ROLLS_DENSITY",
                        "order" : 11,
                        "visible" : true,
                        "type" : "NUMBER"
                    }, 
                    {
                        "columnName" : "ROLLS_STARTTIMESTAMP",
                        "order" : 12,
                        "visible" : true,
                        "type" : "DATE"
                    }, 
                    {
                        "columnName" : "ROLLS_ENDTIMESTAMP",
                        "order" : 13,
                        "visible" : true,
                        "type" : "DATE"
                    },
                    {
                        "columnName" : "ROLLS_RATING",
                        "order" : 14,
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
                        "order" : 15,
                        "visible" : true,
                        "type" : "STRING"
                    },
                    {
                        "columnName" : "ROLLS_THICKNESS_AVERAGE",
                        "order" : 16,
                        "visible" : true,
                        "type" : "NUMBER"
                    },
                    {
                        "columnName" : "ROLLS_TWOSIGMA_PERCENT",
                        "order" : 17,
                        "visible" : true,
                        "type" : "NUMBER"
                    }
                ]
            }
        ],
        "rollIdType" : "BMS_ID",
        "rollIdTypeList" : [ 
            "BMS_ID", 
            "CUSTOMER_ID", 
            "TECHNICAL_ID"
        ],
        "materialDetailsMaxLength" : 7,
        "currentPeriodMaxDays" : 2,
        "thicknessTrendMaxHours" : 48,
        "productionTrendMaxDays" : 2,
        "rollsMaxDays" : 3,
        "campaignManagerSettings" : {
            "editToolSettings" : {
                "timelineSettings" : {
                    "arrowNavigationStep" : 10.0,
                    "laneSettings" : {
                        "periods" : [ 
                            {
                                "name" : "< 2h",
                                "periodId" : 1.0,
                                "milliSeconds" : 0.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
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
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "1_p0_state_roll",
                                                "name" : "State/Roll",
                                                "visibility" : true,
                                                "dependencyId" : ""
                                            }
                                        ]
                                    }
                                ]
                            }, 
                            {
                                "name" : "2h - 24h",
                                "periodId" : 2.0,
                                "milliSeconds" : 7200000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
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
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "2_p0_state_roll",
                                                "name" : "State/Roll",
                                                "visibility" : true,
                                                "dependencyId" : ""
                                            }
                                        ]
                                    }
                                ]
                            }, 
                            {
                                "name" : "24h - 3d",
                                "periodId" : 3.0,
                                "milliSeconds" : 86400000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "3_p2_campaign",
                                                "name" : "Campaign",
                                                "visibility" : true,
                                                "dependencyId" : "3_p0_campaign"
                                            }
                                        ]
                                    }, 
                                    {
                                        "name" : "Lane 2",
                                        "position" : 2.0,
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
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "3_p0_prod_nonprod",
                                                "name" : "Prod./Non-prod",
                                                "visibility" : true,
                                                "dependencyId" : "3_p2_cw"
                                            }
                                        ]
                                    }
                                ]
                            }, 
                            {
                                "name" : "3d - 1w",
                                "periodId" : 4.0,
                                "milliSeconds" : 259200000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "4_p2_campaign",
                                                "name" : "Campaign",
                                                "visibility" : true,
                                                "dependencyId" : "4_p0_campaign"
                                            }
                                        ]
                                    }, 
                                    {
                                        "name" : "Lane 2",
                                        "position" : 2.0,
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
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "4_p0_prod_nonprod",
                                                "name" : "Prod./Non-prod",
                                                "visibility" : true,
                                                "dependencyId" : "4_p2_cw"
                                            }
                                        ]
                                    }
                                ]
                            }, 
                            {
                                "name" : "1w - 1m",
                                "periodId" : 5.0,
                                "milliSeconds" : 604800000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "5_p1_cw",
                                                "name" : "Calendar week",
                                                "visibility" : true,
                                                "dependencyId" : "5_p0_prod_nonprod"
                                            }
                                        ]
                                    }, 
                                    {
                                        "name" : "Lane 1",
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "5_p0_campaign",
                                                "name" : "Campaign",
                                                "visibility" : true,
                                                "dependencyId" : "5_p1_campaign"
                                            }
                                        ]
                                    }
                                ]
                            }, 
                            {
                                "name" : "1month-3months",
                                "periodId" : 6.0,
                                "milliSeconds" : 2678400000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
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
                                        "position" : 1.0,
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
                                "name" : "3months-1year",
                                "periodId" : 7.0,
                                "milliSeconds" : 7776000000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
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
                                        "position" : 1.0,
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
                                "name" : "1year-1.5 years",
                                "periodId" : 8.0,
                                "milliSeconds" : 31536000000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
                                        "active" : false,
                                        "laneTypes" : []
                                    }, 
                                    {
                                        "name" : "Lane 1",
                                        "position" : 1.0,
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
                                "name" : "1.5 years-",
                                "periodId" : 9.0,
                                "milliSeconds" : 47343600000.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
                                        "active" : false,
                                        "laneTypes" : []
                                    }, 
                                    {
                                        "name" : "Lane 1",
                                        "position" : 1.0,
                                        "active" : false,
                                        "laneTypes" : []
                                    }
                                ]
                            }
                        ]
                    }
                }
            },
            "shiftToolSettings" : {
                "timelineSettings" : {
                    "arrowNavigationStep" : 10.0,
                    "laneSettings" : {
                        "periods" : [ 
                            {
                                "name" : "preload period",
                                "periodId" : 1.0,
                                "milliSeconds" : 0.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
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
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "1_p0_state_roll",
                                                "name" : "State/Roll",
                                                "visibility" : true,
                                                "dependencyId" : ""
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                },
                "maxPeriodLoad" : 7.0
            },
            "cutToolSettings" : {
                "timelineSettings" : {
                    "arrowNavigationStep" : 10.0,
                    "laneSettings" : {
                        "periods" : [ 
                            {
                                "name" : "preload period",
                                "periodId" : 1.0,
                                "milliSeconds" : 0.0,
                                "lanes" : [ 
                                    {
                                        "name" : "Lane 3",
                                        "position" : 3.0,
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
                                        "position" : 2.0,
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
                                        "position" : 1.0,
                                        "active" : true,
                                        "laneTypes" : [ 
                                            {
                                                "laneTypeId" : "1_p0_state_roll",
                                                "name" : "State/Roll",
                                                "visibility" : true,
                                                "dependencyId" : ""
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                },
                "maxPeriodLoad" : 7.0
            }
        }    
    };
	
	db.getCollection('clientConfig').save(clientConfigDocument);
});

// Set default lineConfig data
db.getCollection('lineConfig').find({}).forEach(function(lineConfigDoc) {
	lineConfigDoc={
	    "_id" : 1741,
	    "weightSource" : "MILLROLL_SCALE",
	    "availableWeightSources" : [ 
	        "TCE", 
	        "DOSING", 
	        "MILLROLL_SCALE"
	    ],
	    "qualityGradeSource" : "LABORATORY",
	    "datapointMap" : {
	        "SPEED" : "StrTdoOut.DrvLef.SpdAct",
	        "THROUGHPUT" : "TceSysPur.WinOup",
	        "THICKNESS" : "TceProRolPur.Rol.Avg",
	        "THICKNESS_SET" : "TceProRolPur.Rol.ThkSet",
	        "METER_COUNTER_SET" : "Win.Msc.McoXxx.SetStt",
	        "METER_COUNTER_ACT" : "Win.Msc.Mco.Act",
	        "TCE_SCANNING" : "TceGagSttPur.Bit003",
	        "DOSING_CONSUMPTION" : null,
	        "DOSING_PROCESS" : {
	            "_class" : "de.brueckner.ilm.dm.dal.model.config.DosingDataPointMapping",
	            "mappings" : [ 
	                {
	                    "consumption" : "SilCox001.Dos001.CmpTotAct",
	                    "material" : "SilCox001.Dos001.MatNam",
	                    "runStatus" : "SilCox001.Dos001.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox001.Dos002.CmpTotAct",
	                    "material" : "SilCox001.Dos002.MatNam",
	                    "runStatus" : "SilCox001.Dos002.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox001.Dos003.CmpTotAct",
	                    "material" : "SilCox001.Dos003.MatNam",
	                    "runStatus" : "SilCox001.Dos003.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox001.Dos004.CmpTotAct",
	                    "material" : "SilCox001.Dos004.MatNam",
	                    "runStatus" : "SilCox001.Dos004.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox002.Dos001.CmpTotAct",
	                    "material" : "SilCox002.Dos001.MatNam",
	                    "runStatus" : "SilCox002.Dos001.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox002.Dos002.CmpTotAct",
	                    "material" : "SilCox002.Dos002.MatNam",
	                    "runStatus" : "SilCox002.Dos002.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox002.Dos003.CmpTotAct",
	                    "material" : "SilCox002.Dos003.MatNam",
	                    "runStatus" : "SilCox002.Dos003.RunStt"
	                }, 
	                {
	                    "consumption" : "SilCox002.Dos004.CmpTotAct",
	                    "material" : "SilCox002.Dos004.MatNam",
	                    "runStatus" : "SilCox002.Dos004.RunStt"
	                }, 
	                {
	                    "consumption" : "SilSto.Dos001.CmpTotAct",
	                    "material" : "SilSto.Sil001.MatNam",
	                    "runStatus" : "SilSto.Dos001.RunStt"
	                }, 
	                {
	                    "consumption" : "SilSto.Dos002.CmpTotAct",
	                    "material" : "SilSto.Sil002.MatNam",
	                    "runStatus" : "SilSto.Dos002.RunStt"
	                }, 
	                {
	                    "consumption" : "SilSto.Dos003.CmpTotAct",
	                    "material" : "SilSto.Sil003.MatNam",
	                    "runStatus" : "SilSto.Dos003.RunStt"
	                }, 
	                {
	                    "consumption" : "SilMne.Dos002.CmpTotAct",
	                    "material" : "SilMne.Dos002.MatNam",
	                    "runStatus" : "SilMne.Dos002.RunStt"
	                }, 
	                {
	                    "consumption" : "SilMne.Dos003.CmpTotAct",
	                    "material" : "SilMne.Dos003.MatNam",
	                    "runStatus" : "SilMne.Dos003.RunStt"
	                }, 
	                {
	                    "consumption" : "SilMne.Dos004.CmpTotAct",
	                    "material" : "SilMne.Dos004.MatNam",
	                    "runStatus" : "SilMne.Dos004.RunStt"
	                }
	            ]
	        }
	    },
	    "dieAdapters" : [ 
	        {
	            "name" : "dieConfig",
	            "active" : true,
	            "chillRollSideUp" : false,
	            "extruders" : null,
	            "extruderSetup" : [ 
	                {
	                    "extruder" : "Cox001",
	                    "layers" : {
	                        "1" : {}
	                    }
	                }, 
	                {
	                    "extruder" : "Mne",
	                    "layers" : {
	                        "2" : {}
	                    }
	                }, 
	                {
	                    "extruder" : "Cox002",
	                    "layers" : {
	                        "3" : {}
	                    }
	                }
	            ]
	        }
	    ],
	    "outputTable" : [ 
	        {
	            "thickness" : 12.0,
	            "speed" : 525.0,
	            "throughput" : 2993.0
	        }, 
	        {
	            "thickness" : 15.0,
	            "speed" : 525.0,
	            "throughput" : 3741.0
	        }, 
	        {
	            "thickness" : 18.0,
	            "speed" : 525.0,
	            "throughput" : 4490.0
	        }, 
	        {
	            "thickness" : 20.0,
	            "speed" : 525.0,
	            "throughput" : 4988.0
	        }, 
	        {
	            "thickness" : 25.0,
	            "speed" : 525.0,
	            "throughput" : 6235.0
	        }, 
	        {
	            "thickness" : 30.0,
	            "speed" : 446.0,
	            "throughput" : 6350.0
	        }, 
	        {
	            "thickness" : 35.0,
	            "speed" : 379.0,
	            "throughput" : 6305.0
	        }, 
	        {
	            "thickness" : 40.0,
	            "speed" : 308.0,
	            "throughput" : 5850.0
	        }, 
	        {
	            "thickness" : 50.0,
	            "speed" : 220.0,
	            "throughput" : 5230.0
	        }, 
	        {
	            "thickness" : 60.0,
	            "speed" : 160.0,
	            "throughput" : 4570.0
	        }
	    ],
	    "extruders" : {
	        "Cox001" : {
	            "name" : "Cox001",
	            "description" : "Coextruder 1",
	            "type" : "COEXTRUDER",
	            "consumptionDpMap" : {
	                "DOSING_PROCESS" : [ 
	                    "SilCox001.Dos001.CmpTotAct", 
	                    "SilCox001.Dos002.CmpTotAct", 
	                    "SilCox001.Dos003.CmpTotAct", 
	                    "SilCox001.Dos004.CmpTotAct"
	                ]
	            },
	            "dpThickness" : "ExtCox001.FlmThk.Act"
	        },
	        "Mne" : {
	            "name" : "Mne",
	            "description" : "Main Extruder",
	            "type" : "MAIN_EXTRUDER",
	            "consumptionDpMap" : {
	                "DOSING_PROCESS" : [ 
	                    "SilMne.Dos002.CmpTotAct", 
	                    "SilMne.Dos003.CmpTotAct", 
	                    "SilMne.Dos004.CmpTotAct", 
	                    "SilSto.Dos001.CmpTotAct", 
	                    "SilSto.Dos002.CmpTotAct", 
	                    "SilSto.Dos003.CmpTotAct"
	                ]
	            }
	        },
	        "Cox002" : {
	            "name" : "Cox002",
	            "description" : "Coextruder 2",
	            "type" : "COEXTRUDER",
	            "consumptionDpMap" : {
	                "DOSING_PROCESS" : [ 
	                    "SilCox002.Dos001.CmpTotAct", 
	                    "SilCox002.Dos002.CmpTotAct", 
	                    "SilCox002.Dos003.CmpTotAct", 
	                    "SilCox002.Dos004.CmpTotAct"
	                ]
	            },
	            "dpThickness" : "ExtCox002.FlmThk.Act"
	        }
	    }
	};
	
	db.getCollection('lineConfig').save(lineConfigDoc);
});