package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Lock {

	private Long idLock;
	private String owner;
	private String spectacleName;
	private String place;
	@XmlJavaTypeAdapter(MyInstantAdapter.class)
	private Instant creationDate;
	@XmlJavaTypeAdapter(MyInstantAdapter.class)
	private Instant expirationDate;
	
	
	private Long signatureLock;

	private static Long compteur = 0L;
	private static int expirationDuration = 1800;

	public Lock() {

	}

	public Lock(String owner, String spectacleName, String place) {
		this.idLock = nextId();
		this.owner = owner;
		this.spectacleName = spectacleName;
		this.place = place;
		this.creationDate = Instant.now();
		this.expirationDate = creationDate.plusSeconds(expirationDuration);
	}

	private synchronized static Long nextId() {
		return compteur++;
	}

	public Long getIdLock() {
		return idLock;
	}

	public String getOwner() {
		return owner;
	}

	public String getSpectacleName() {
		return spectacleName;
	}

	public void setSpectacleName(String spectacleName) {
		this.spectacleName = spectacleName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public void sign(){
		// «SPECTACLE_!_PLACE_!_OWNER_!_AAAAMMJJHHmmss.mse »
		ZonedDateTime creationDt = ZonedDateTime.ofInstant(this.creationDate,ZoneOffset.UTC);
		String dataToSign = "" + this.spectacleName+ "_!_"+ this.place +"_!_" + this.owner+"_!_" + DateTimeFormatter.ofPattern("YYYYMMddHHmmss").format(creationDt);

		this.signatureLock = SignatureHelper.signatureOf(dataToSign);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLock == null) ? 0 : idLock.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((spectacleName == null) ? 0 : spectacleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lock other = (Lock) obj;
		if (idLock == null) {
			if (other.idLock != null)
				return false;
		} else if (!idLock.equals(other.idLock))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (spectacleName == null) {
			if (other.spectacleName != null)
				return false;
		} else if (!spectacleName.equals(other.spectacleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lock [idLock=" + idLock + ", owner=" + owner + ", spectacleName=" + spectacleName + ", place=" + place + "]";
	}

	public Instant getExpirationDate() {
		return expirationDate;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	void setDateCreation(Instant creationDate) {
		this.creationDate = creationDate;

	}

	void setDateExpiration(Instant expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Long getSignature() {
		return this.signatureLock;
	}

}
