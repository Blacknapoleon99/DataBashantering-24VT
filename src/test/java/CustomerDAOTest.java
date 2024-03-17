import com.napoleon.dao.CustomerDAO;

import com.napoleon.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private CustomerDAO customerDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerDAO = new CustomerDAO() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };
    }

    @Test
    public void insertCustomerTest() throws SQLException {
        Customer customer = new Customer(1, "Test Name", "test@example.com", "123456789", "Test Address");
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1); // Simulera genererat kund-ID

        int customerId = customerDAO.insertCustomer(customer);

        assertEquals(1, customerId);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void getCustomerByIdTest() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("customer_id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Name");
        when(mockResultSet.getString("email")).thenReturn("test@example.com");
        when(mockResultSet.getString("phone")).thenReturn("123456789");
        when(mockResultSet.getString("address")).thenReturn("Test Address");

        Customer customer = customerDAO.getCustomerById(1);

        assertNotNull(customer);
        assertEquals("Test Name", customer.getName());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    public void updateCustomerTest() throws SQLException {
        Customer customer = new Customer(1, "Updated Name", "updated@example.com", "987654321", "Updated Address");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Anta att uppdateringen lyckades

        boolean result = customerDAO.updateCustomer(customer);

        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void deleteCustomerTest() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Anta att raderingen lyckades

        boolean result = customerDAO.deleteCustomer(1);

        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
