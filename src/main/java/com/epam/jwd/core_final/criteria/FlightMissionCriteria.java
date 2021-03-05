package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMissionCriteria(FlightMissionCriteriaBuilder flightMissionCriteriaBuilder) {
        this.name = flightMissionCriteriaBuilder.name;
        this.startDate = flightMissionCriteriaBuilder.startDate;
        this.endDate = flightMissionCriteriaBuilder.endDate;
        this.distance = flightMissionCriteriaBuilder.distance;
        this.assignedSpaceShift = flightMissionCriteriaBuilder.assignedSpaceShift;
        this.assignedCrew = flightMissionCriteriaBuilder.assignedCrew;
        this.missionResult = flightMissionCriteriaBuilder.missionResult;
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public static class FlightMissionCriteriaBuilder {
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Long distance;
        private Spaceship assignedSpaceShift;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;

        public FlightMissionCriteriaBuilder setMissionName(final String name) {
            this.name = name;
            return this;
        }

        public FlightMissionCriteriaBuilder setStartDate(final LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public FlightMissionCriteriaBuilder setEndDate(final LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public FlightMissionCriteriaBuilder setDistance(final Long distance) {
            this.distance = distance;
            return this;
        }

        public FlightMissionCriteriaBuilder setAssignedSpaceShift(final Spaceship assignedSpaceShift) {
            this.assignedSpaceShift = assignedSpaceShift;
            return this;
        }

        public FlightMissionCriteriaBuilder setAssignedCrew(final List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public FlightMissionCriteriaBuilder setMissionResult(final MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteriaBuilder setId(final Long id) {
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(this);
        }
    }
}
