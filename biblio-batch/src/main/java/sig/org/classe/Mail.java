package sig.org.classe;

import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@XmlRootElement
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
private Long codeMail;
private String description;
private Date dateEnvoie;



}
