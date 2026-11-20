package com.revature.service.celesstial;

import com.revature.entities.Moon;

import java.util.List;

public interface CelestialService {
    List<Moon> getUserMoons(int userId);
}
