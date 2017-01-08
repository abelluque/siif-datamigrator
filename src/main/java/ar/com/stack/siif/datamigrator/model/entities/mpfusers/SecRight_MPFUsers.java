package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sec_rights database table.
 * 
 */
@Entity
@Table(name="sec_rights")
@NamedQuery(name="SecRight_MPFUsers.findAll", query="SELECT s FROM SecRight_MPFUsers s")
public class SecRight_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rig_name")
	private String rigName;

	@Column(name="rig_check")
	private String rigCheck;

	@Column(name="rig_description")
	private String rigDescription;

	//bi-directional many-to-many association to SecGroup
	@ManyToMany
	@JoinTable(
		name="sec_groups_rights"
		, joinColumns={
			@JoinColumn(name="rig_name")
			}
		, inverseJoinColumns={
			@JoinColumn(name="gro_code")
			}
		)
	private List<SecGroup_MPFUsers> secGroups;

	public SecRight_MPFUsers() {
	}

	public String getRigName() {
		return this.rigName;
	}

	public void setRigName(String rigName) {
		this.rigName = rigName;
	}

	public String getRigCheck() {
		return this.rigCheck;
	}

	public void setRigCheck(String rigCheck) {
		this.rigCheck = rigCheck;
	}

	public String getRigDescription() {
		return this.rigDescription;
	}

	public void setRigDescription(String rigDescription) {
		this.rigDescription = rigDescription;
	}

	public List<SecGroup_MPFUsers> getSecGroups() {
		return this.secGroups;
	}

	public void setSecGroups(List<SecGroup_MPFUsers> secGroups) {
		this.secGroups = secGroups;
	}

}