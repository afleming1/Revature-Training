package com.revature.entities;

import java.util.Base64;

public class Planet {
    private int planetId;

    private String planetName;

    private int ownerId;
    private byte[] imageData;

    public Planet() {}

    public Planet(int id, String name, int planetId) {
        this.planetId = id;
        this.planetName = name;
        this.ownerId = planetId;
    }

    public Planet(int id, String name, int planetId, byte[] imageData) {
        this.planetId = id;
        this.planetName = name;
        this.ownerId = planetId;
        this.imageData = imageData;
    }

    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
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
        return String.format("Moon ID: %d, Name: %s, Owner ID: %d", this.planetId, this.planetName, this.ownerId);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = 31 * result + this.planetId;
        result = 31 * result + (this.planetName == null ? 0 : this.planetName.hashCode());
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
            Planet other = (Planet) obj;

            if (this.planetId != other.planetId) {
                return false;
            } else {
                if (this.planetName == null) {
                    if (other.planetName != null) {
                        return false;
                    }
                } else if (!this.planetName.equals(other.planetName)) {
                    return false;
                }

                return this.ownerId == other.ownerId;
            }
        }
    }
}
