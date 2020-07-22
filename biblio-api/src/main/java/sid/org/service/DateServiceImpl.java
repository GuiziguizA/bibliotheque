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
	 * @param mois
	 * @return une date modifier en fonction de parametre mois
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public Date modifierDate(Date date,int mois) throws ResultNotFoundException {
		if (mois>12) {
			throw new ResultNotFoundException("le chifre du mois dois etre strictement inferieur a 12 ");
		}
		
		Date date1 = new Date();
		if (date.getMonth()+mois>12) {
			date1=new Date(date.getYear()+1,date.getMonth()+mois-12,date.getDay());

		}
		else if (date.getMonth()+mois<=12) {
			 date1=new Date(date.getYear(),date.getMonth()+mois,date.getDay());
		
	}
		return date1;

	}
	

	
	
}
