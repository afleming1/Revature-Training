package com.revature.service.moon;

import com.revature.entities.Moon;
import com.revature.exceptions.MoonFail;
import com.revature.repository.moon.MoonDao;

import java.sql.SQLException;
import java.util.List;

public class MoonServiceImplementation implements MoonService {
    private static final byte[] pngMagicNumber = new byte[] {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final byte[] jpegMagicNumber = new byte[] {-1, -40, -1};

    private MoonDao moonDao;

    public MoonServiceImplementation(MoonDao moonDao) {
        this.moonDao = moonDao;
    }

    public boolean createMoon(Moon moon) {
        try {
            if (!moon.getMoonName().isEmpty() && moon.getMoonName().length() <= 30) {
                if (!moon.getMoonName().matches("^[a-zA-Z0-9-_ ]*$")) {
                    throw new MoonFail("Invalid moon name");
                } else {
                    if (moon.imageDataAsByteArray() != null) {
                        boolean isPng = this.checkMagicNumber(true, moon.imageDataAsByteArray());
                        boolean isJpeg = this.checkMagicNumber(false, moon.imageDataAsByteArray());

                        if (!isPng && !isJpeg) {
                            throw new MoonFail("Invalid file type");
                        }
                    }

                    if (this.moonDao.readMoon(moon.getMoonName()) != null) {
                        throw new MoonFail("Invalid moon name");
                    } else {
                        boolean newMoon = this.moonDao.createMoon(moon);

                        if (newMoon) {
                            return true;
                        } else {
                            throw new MoonFail("Failed to create moon");
                        }
                    }
                }
            } else {
                throw new MoonFail("Invalid moon name");
            }
        } catch (SQLException exception) {
            if (exception.getMessage().contains("name_length_check")) {
                throw new MoonFail("Character length fail");
            } else if (exception.getMessage().contains("name_character_check")) {
                throw new MoonFail("Invalid character fail");
            } else {
                throw new MoonFail("Could not create new moon");
            }
        }
    }

    public List<Moon> selectByPlanetId(int planetId) {
        try {
            return this.moonDao.readMoonsByPlanet(planetId);
        } catch (SQLException exception) {
            throw new MoonFail(exception.getMessage());
        }
    }

    public boolean deleteMoon(String moonName) {
        boolean deleted = false;

        try {
            deleted = this.moonDao.deleteMoon(moonName);

            if (deleted) {
                return true;
            } else {
                throw new MoonFail("Could not delete the moon");
            }
        } catch (SQLException exception) {
            throw new MoonFail("Moon delete failed, please try again");
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
