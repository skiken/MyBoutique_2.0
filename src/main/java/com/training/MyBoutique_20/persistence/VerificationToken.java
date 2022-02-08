package com.training.MyBoutique_20.persistence;


import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import com.training.MyBoutique_20.persistence.abstracts.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@EqualsAndHashCode(callSuper = false)
@Entity
public class VerificationToken extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private static final int EXPIRATION = 4;
	
    private String token;
    
    private Date expireDate;

    @OneToOne(cascade=CascadeType.ALL)
    private Customer customer;

	
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }


	/**
	 * @param token
	 * @param expireDate
	 * @param customer
	 */
	public VerificationToken(String token,Customer customer) {
		super();
		this.token = token;
		this.expireDate = calculateExpiryDate(EXPIRATION);
		this.customer = customer;
	}
    

    
    
 

}
