package app.manager.changedShift;

import java.util.List;

public interface ChangedShiftService {

	List <ChangedShift> findAll();
	
	ChangedShift save(ChangedShift changedShift);
	
	ChangedShift findOne(Long id);
}