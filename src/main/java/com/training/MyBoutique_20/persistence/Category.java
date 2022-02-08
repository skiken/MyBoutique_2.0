package com.training.MyBoutique_20.persistence;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.training.MyBoutique_20.persistence.abstracts.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name="category")
public class Category extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name="name")
	private String name;
	@NotNull
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private Set<Product> setProduct= new HashSet<>();

}
