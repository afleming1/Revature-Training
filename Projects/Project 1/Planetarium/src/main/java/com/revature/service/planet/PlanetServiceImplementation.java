package com.revature.service.planet;

import com.revature.entities.Planet;
import com.revature.exceptions.PlanetFail;
import com.revature.repository.planet.PlanetDao;

import java.sql.SQLException;
import java.util.List;

public class PlanetServiceImplementation implements PlanetService {
    private static final byte[] pngMagicNumber = new byte[] {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final byte[] jpegMagicNumber = new byte[] {-1, -40, -1};

    private PlanetDao planetDao;

    public PlanetServiceImplementation(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }

    public boolean createPlanet(int userId, Planet planet) {
        try {
            if (!planet.getPlanetName().isEmpty() && planet.getPlanetName().length() <= 30) {
                if (!planet.getPlanetName().matches("^[a-zA-Z0-9-_ ]*$")) {
                    throw new PlanetFail("Invalid planet name");
                } else if (userId != planet.getOwnerId()) {
                    throw new PlanetFail("Invalid owner id");
                } else {
                    Planet existingPlanet = this.planetDao.readPlanet(planet.getPlanetName());

                    if (existingPlanet != null) {
                        throw new PlanetFail("Invalid planet name");
                    } else {
                        if (planet.imageDataAsByteArray() != null) {
                            boolean isPng = this.checkMagicNumber(true, planet.imageDataAsByteArray());
                            boolean isJpeg = this.checkMagicNumber(false, planet.imageDataAsByteArray());

                            if (!isPng && !isJpeg) {
                                throw new PlanetFail("Invalid file type");
                            }
                        }

                        boolean createdPlanet = this.planetDao.createPlanet(planet);

                        if (createdPlanet) {
                            return true;
                        } else {
                            throw new PlanetFail("Failed to create planet");
                        }
                    }
                }
            } else {
                throw new PlanetFail("Invalid planet name");
            }
        } catch (SQLException exception) {
            if (exception.getMessage().contains("name_length_check")) {
                throw new PlanetFail("Character length fail");
            } else if (exception.getMessage().contains("name_character_check")) {
                throw new PlanetFail("Invalid character fail");
            } else {
                throw new PlanetFail("Could not create new planet");
            }
        }
    }

    public List<Planet> selectByOwner(int ownerId) {
        try {
            return this.planetDao.readPlanetsByOwner(ownerId);
        } catch (SQLException exception) {
            throw new PlanetFail("There was an error trying to find your planets");
        }
    }

    public boolean deletePlanet(int userId, String planetName) {
        try {
            if (this.planetDao.readPlanetsByOwner(userId).isEmpty()) {
                throw new PlanetFail("Invalid planet name");
            } else {
                boolean deleted = false;
                boolean unownedPlanet = true;

                for (Planet planet : this.planetDao.readPlanetsByOwner(userId)) {
                    if (planet.getPlanetName().equals(planetName) && planet.getOwnerId() == userId) {
                        unownedPlanet = false;

                        deleted = this.planetDao.deletePlanet(planetName);

                        break;
                    }

                    if (planet.getPlanetName().equals(planetName) && planet.getOwnerId() != userId) {
                        break;
                    }
                }

                if (unownedPlanet) {
                    throw new PlanetFail("Invalid planet name");
                } else if (deleted) {
                    return true;
                } else {
                    throw new PlanetFail("Could not delete the planet");
                }
            }
        } catch (SQLException exception) {
            throw new PlanetFail("Planet delete failed, please try again");
        }
    }

    public boolean checkMagicNumber(boolean checkingPNG, byte[] fileData) {
        if (checkingPNG) {
            for (int i = 0; i < pngMagicNumber.length; ++i) {
                if (pngMagicNumber[i] != fileData[i]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < jpegMagicNumber.length; ++i) {
                if (jpegMagicNumber[i] != fileData[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
