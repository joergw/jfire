package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1141320439080401772L;
	Long id;
	MaterialTyp materialTyp;
	Date uebernahme;
	long masterID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATERIAL_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public MaterialTyp getMaterialTyp() {
		return materialTyp;
	}

	public void setMaterialTyp(MaterialTyp materialTyp) {
		this.materialTyp = materialTyp;
	}

	public Date getUebernahme() {
		return uebernahme;
	}

	public void setUebernahme(Date uebernahme) {
		this.uebernahme = uebernahme;
	}

	public long getMasterID() {
		return masterID;
	}

	public void setMasterID(long masterID) {
		this.masterID = masterID;
	}

}
