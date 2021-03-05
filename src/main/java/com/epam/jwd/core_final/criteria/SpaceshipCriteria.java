package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;


    public SpaceshipCriteria(SpaceshipCriteriaBuilder spaceshipCriteriaBuilder) {
        this.crew = spaceshipCriteriaBuilder.crew;
        this.flightDistance = spaceshipCriteriaBuilder.flightDistance;
        this.isReadyForNextMissions = spaceshipCriteriaBuilder.isReadyForNextMissions;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getIsReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class SpaceshipCriteriaBuilder {
        private Map<Role, Short> crew;
        private Long flightDistance;
        private Boolean isReadyForNextMissions;

        public SpaceshipCriteriaBuilder setCrew(final Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        public SpaceshipCriteriaBuilder setFlightDistance(final Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public SpaceshipCriteriaBuilder setIsReadyForNextMissions(final Boolean readyForNextMissions) {
            isReadyForNextMissions = readyForNextMissions;
            return this;
        }

        public SpaceshipCriteriaBuilder setName(final String name) {
            return this;
        }

        public SpaceshipCriteriaBuilder setId(final Long id) {
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }
    }

}
