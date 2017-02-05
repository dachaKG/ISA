package app.common;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import app.employed.Employed;
import lombok.Data;


public class ChangedShift {
	
	
	Long id;
	
	// za koji dan
	Date date;
	
	// kome menjas
	Employed employed1;
	
	// sa kim smenu menjas
	Employed employed2;
	

}
