package pl.put.poznan.buildinginfo.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildinginfo.model.*;


/**
 * Class responsible for calculating surface
 */
@Component
public class SurfaceService {

    private static final Logger logger = LoggerFactory.getLogger(SurfaceService.class);

    private LocalizationFinder localizationFinder;

    @Autowired
    public void setLocalizationFinder(LocalizationFinder localizationFinder) {
        this.localizationFinder = localizationFinder;
    }

    /**
     * method responsible for calculating surface area of specific Building, Floor or Room based on type and id
     *
     * @param building object containing floors
     * @param id       id of localization
     * @param type     type of localization
     * @return surface area of a building, a floor or a room
     */
    public SurfaceInformation calculateSurface(Building building, String id, LocalizationType type) {

        logger.debug("Calculating surface of the localization: " + id);

        SurfaceInformation inf = new SurfaceInformation();

        Localization localization = localizationFinder.findLocalizationInBuilding(building, type, id);

        inf.setName(localization.getName());
        inf.setType(type);
        inf.setId(id);
        inf.setSurface(localization.calculateSurface());

        return inf;
    }

}
