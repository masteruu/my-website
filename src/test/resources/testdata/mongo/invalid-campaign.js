/**
 * Script for testing User Story ISA-2801 (Invalid campaign Repair).
 * This script will get three sequently campaigns end invalides their status.
 * 
 * How to run this script manually:
 * > mongo <ip>:<port>/<DB name> <scriptname>.js
 * Example:  mongo 10.50.1.70:27017/frd isa-2801_invalid-Campaigns.js
 * 
 * @author Christoph Schauer (christoph.schauer@brueckner.de)
 * @since 15.02.2017
 */


//=========================================== START ===========================================

setCampaignsInvalid();
quit(0); // 0 ::= success

//============================================ END ============================================


/**
 * Get campaigns end set the status:
 * 160505_27U_450M_5.7T -> artificial
 * left: 160506_25u_450_5.3t -> Invalid Stop
 * right: 1605009_36u_310m_5.2t -> Invalid Start
 */
function setCampaignsInvalid() {
	
    print();
    print("--------------------------------------------------------------------");
    print("--------- setCampaignsInvalid --------------------------------------");
    print("--------------------------------------------------------------------");
	
	// Find first campaign and set invalid stop
	db.getCollection('campaign').update( //
		{_id : "160506010918039" },
                {$set : { "status" : "INVALID_STOP" } 
	});
                
  // Find second campaign and set invalid start
	db.getCollection('campaign').update(
		{_id : "160508234540232" },
                {$set : { "status" : "INVALID_START" } 
  });
  
  // Find campaign and set artificial
	db.getCollection('campaign').update(
		{_id : "160505083436574" },
                {$set : { "status" : "ARTIFICIAL" } 
  })
		
	print("Finished successfully");
};