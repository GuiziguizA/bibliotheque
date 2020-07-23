package sid.org.service;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import sid.org.exception.ResultNotFoundException;
@Component
public class DateServiceImpl implements DateService{

	/*
	 * modification d'une date
	 * @param date
	 * @param jours
	 * @return une date modifier du nombre de jour ajouté
	 */
	
	
	@Override
	public Date modifierDate(Date date,int jours) throws ResultNotFoundException {
		
		
		 long newDate = date.getTime() + (jours*2*24*1000*60*60)+ (jours*2*24*1000*60*60);
		 Date after = new Date(newDate);	
		 
		return after;

	}
	

	
	
}
