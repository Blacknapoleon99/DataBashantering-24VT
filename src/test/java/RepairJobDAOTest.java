import com.napoleon.dao.RepairJobDAO;

import com.napoleon.model.RepairJob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



// Test av RepairJobDAO
public class RepairJobDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private RepairJobDAO repairJobDAO;


    // setup och Färberedelse av test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repairJobDAO = new RepairJobDAO() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };
    }

    // Test att sätta alla reparationer
    @Test
    public void insertRepairJobTest() throws SQLException {
        RepairJob repairJob = new RepairJob(0, 1, "Screen cracked", "Pending", null, null, "No notes yet");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Antag att insättningen lyckades

        boolean result = repairJobDAO.insertRepairJob(repairJob);

        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }


    // Test att hämta alla reparationer
    @Test
    public void getAllRepairJobsTest() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulerar att en rad finns, sedan ingen mer

        // Konfigurera mockResultSet med lämpliga värden
        when(mockResultSet.getInt("job_id")).thenReturn(1);
        when(mockResultSet.getInt("device_id")).thenReturn(1);
        when(mockResultSet.getString("problem_description")).thenReturn("Screen cracked");
        when(mockResultSet.getString("repair_status")).thenReturn("Pending");
        when(mockResultSet.getTimestamp("estimated_completion_date")).thenReturn(null);
        when(mockResultSet.getTimestamp("completion_date")).thenReturn(null);
        when(mockResultSet.getString("notes")).thenReturn("No notes yet");

        List<RepairJob> result = repairJobDAO.getAllRepairJobs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Screen cracked", result.get(0).getProblemDescription());
    }


    // Test att hämta en reparation med visst ID
    @Test
    public void getRepairJobByIdTest() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulerar att en rad finns

        // Konfigurera mockResultSet
        when(mockResultSet.getInt("job_id")).thenReturn(1);
        // Konfigurera övriga fält liknande getAllRepairJobsTest

        RepairJob result = repairJobDAO.getRepairJobById(1);

        assertNotNull(result);
        assertEquals(1, result.getJobId());
    }


    // Test att uppdatera en reparation
    @Test
    public void updateRepairJobTest() throws SQLException {
        RepairJob repairJob = new RepairJob(1, 1, "Screen fixed", "Completed", null, new Timestamp(System.currentTimeMillis()), "Repaired successfully");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Antag att uppdateringen lyckades

        boolean result = repairJobDAO.updateRepairJob(repairJob);

        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }


    // Test att radera en reparation
    @Test
    public void deleteRepairJobTest() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Antag att raderingen lyckades

        boolean result = repairJobDAO.deleteRepairJob(1);

        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
