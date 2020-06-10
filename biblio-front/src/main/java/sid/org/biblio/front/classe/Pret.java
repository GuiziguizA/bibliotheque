package sid.org.biblio.front.classe;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;








public class Pret {

Long id;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}




}
