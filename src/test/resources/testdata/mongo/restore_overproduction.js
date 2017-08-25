/**
 * Script for testing User Story ISA-2408 (Overproduction).
 * This script will restore the "METER_COUNTER_SET" : "Win.Msc.McoXxx.SetStt" in the machineData of dumped FRD database with timestamp 2016-05-08T23:35:35.994+0000
 * 
 * How to run this script manually:
 * > mongo <ip>:<port>/<DB name> <scriptname>.js
 * Example:  mongo 10.50.1.70:27017/frd isa-2408_restore_meterCounterSet.js
 * 
 * @author Manfred Novotny (novotny@software-novotny.de)
 * @since 01.02.2017
 */


//=========================================== START ===========================================

restoreMeterCounterSet();
quit(0); // 0 ::= success

//============================================ END ============================================



/**
 * Restores the last METER_COUNTER_SET of 1741/Win.Msc.McoXxx.SetStt to 469000.
 */
function restoreMeterCounterSet() {
	
	print();
    print("--------------------------------------------------------------------");
    print("--------- restoreMeterCounterSet -----------------------------------");
    print("--------------------------------------------------------------------");
	
	// Find the bucket with the correspondign ID and update the field:
	db.getCollection('machineData').update( //
		{
			_id : "1741/Win.Msc.McoXxx.SetStt/2016/05/08/23/35/24/152" 
		},{
			$set : { "values.2.v" : 46900.0 } 
		});
		
	print("Finished successfully");
};

