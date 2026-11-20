package com.revature.entities;

import java.util.Base64;

public class Moon {
    private int moonId;

    private String moonName;

    private int ownerId;
    private byte[] imageData;

    public Moon() {}

    public Moon(int id, String name, int planetId) {
        this.moonId = id;
        this.moonName = name;
        this.ownerId = planetId;
    }

    public Moon(int id, String name, int planetId, byte[] imageData) {
        this.moonId = id;
        this.moonName = name;
        this.ownerId = planetId;
        this.imageData = imageData;
    }

    public int getMoonId() {
        return moonId;
    }

    public void setMoonId(int moonId) {
        this.moonId = moonId;
    }

    public String getMoonName() {
        return moonName;
    }

    public void setMoonName(String moonName) {
        this.moonName = moonName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getImageData() {
        return this.imageData != null ? Base64.getEncoder().encodeToString(this.imageData) : null;
    }

    public void setImageData(String base64ImageData) {
        if (base64ImageData != null) {
            this.imageData = Base64.getDecoder().decode(base64ImageData);
        }
    }

    public byte[] imageDataAsByteArray() {
        return this.imageData != null ? this.imageData : null;
    }

    public String toString() {
        return String.format("Moon ID: %d, Name: %s, Owner ID: %d", this.moonId, this.moonName, this.ownerId);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = 31 * result + this.moonId;
        result = 31 * result + (this.moonName == null ? 0 : this.moonName.hashCode());
        result = 31 * result + this.ownerId;

        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Moon other = (Moon) obj;

            if (this.moonId != other.moonId) {
                return false;
            } else {
                if (this.moonName == null) {
                    if (other.moonName != null) {
                        return false;
                    }
                } else if (!this.moonName.equals(other.moonName)) {
                    return false;
                }

                return this.ownerId == other.ownerId;
            }
        }
    }
}
