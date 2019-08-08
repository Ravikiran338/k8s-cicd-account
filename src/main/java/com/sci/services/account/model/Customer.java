package com.sci.services.account.model;

import org.davidmoten.rx.jdbc.annotations.Column;
import org.davidmoten.rx.jdbc.annotations.Query;

@Query("SELECT * FROM customer_details_tbl")
	public interface Customer {

	    @Column("customer_id")
	    int getCustId();

	   /*// @Column("active_flag")
	    String getActiveFlag();*/
	    
	    @Column("customer_id_num")
	    int getCustIdNum();

	    @Column("user_id")
	    String getUserId();
	}



