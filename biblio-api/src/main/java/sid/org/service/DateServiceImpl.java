package sid.org.service;

import java.util.Date;

import org.springframework.stereotype.Service;
@Service
public class DateServiceImpl implements DateService{

	@SuppressWarnings("deprecation")
	@Override
	public Date modifierDate(Date date,int mois) throws Exception {
		if (mois>12) {
			throw new Exception("le chifre du mois dois etre strictement inferieur a 12 ");
		}
		
		Date date1 = null;
		if (date.getMonth()+mois>12) {
			date1=new Date(date.getYear()+1,date.getMonth()+mois-12,date.getDay());

		}
		else if (date.getMonth()+mois<=12) {
			 date1=new Date(date.getYear(),date.getMonth()+mois,date.getDay());
		
	}
		return date1;

	}
	

	
	
}