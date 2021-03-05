package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;
    private String name;
    private Long id;

    public CrewMemberCriteria(CrewMemberCriteriaBuilder crewMemberCriteriaBuilder) {
        this.role = crewMemberCriteriaBuilder.role;
        this.rank = crewMemberCriteriaBuilder.rank;
        this.name = crewMemberCriteriaBuilder.name;
        this.id = crewMemberCriteriaBuilder.id;
        this.isReadyForNextMissions = crewMemberCriteriaBuilder.isReadyForNextMissions;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class CrewMemberCriteriaBuilder {
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;
        private String name;
        private Long id;

        public CrewMemberCriteriaBuilder setRole(final Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder setRank(final Rank rank) {
            this.rank = rank;
            return this;
        }

        public CrewMemberCriteriaBuilder setIsReadyForNextMissions(final Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteriaBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public CrewMemberCriteriaBuilder setId(final Long id) {
            this.id = id;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this);
        }

    }
}
