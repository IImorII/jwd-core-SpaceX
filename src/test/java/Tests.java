import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.util.CrewReaderUtil;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.epam.jwd.core_final.util.SpaceshipsReaderUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    @Before
    public void loadProperties() {
        PropertyReaderUtil.loadProperties();
    }
    @Test
    public void testCrewReaderUtil() {
        List<CrewMember> crewMembers = new ArrayList<>();
        CrewReaderUtil.loadCrew(crewMembers);
        CrewMember expectedStartMember = CrewMemberFactory.getInstance().create("Davey Bentley", Role.resolveRoleById(4), Rank.resolveRankById(2));
        CrewMember expectedEndMember = CrewMemberFactory.getInstance().create("Shayan Mclellan", Role.resolveRoleById(4), Rank.resolveRankById(1));
        Assert.assertEquals(expectedStartMember.getName(), crewMembers.get(0).getName());
        Assert.assertEquals(expectedStartMember.getRank(), crewMembers.get(0).getRank());
        Assert.assertEquals(expectedStartMember.getRole(), crewMembers.get(0).getRole());
        Assert.assertEquals(expectedEndMember.getName(), crewMembers.get(crewMembers.size() - 1).getName());
        Assert.assertEquals(expectedEndMember.getRank(), crewMembers.get(crewMembers.size() - 1).getRank());
        Assert.assertEquals(expectedEndMember.getRole(), crewMembers.get(crewMembers.size() - 1).getRole());
    }
}
