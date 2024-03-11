import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MobileDeviceDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseConfig.getUrl(),
                DatabaseConfig.getUser(),
                DatabaseConfig.getPassword());
    }

    public boolean insertMobileDevice(MobileDevice device) {
        String sql = "INSERT INTO mobile_devices (customer_id, brand, model, serial_number, submission_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, device.getCustomerId());
            stmt.setString(2, device.getBrand());
            stmt.setString(3, device.getModel());
            stmt.setString(4, device.getSerialNumber());
            stmt.setTimestamp(5, new Timestamp(device.getSubmissionDate().getTime()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MobileDevice getMobileDeviceById(int deviceId) {
        String sql = "SELECT * FROM mobile_devices WHERE device_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviceId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new MobileDevice(
                        rs.getInt("device_id"),
                        rs.getInt("customer_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("serial_number"),
                        rs.getTimestamp("submission_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMobileDevice(MobileDevice device) {
        String sql = "UPDATE mobile_devices SET customer_id = ?, brand = ?, model = ?, serial_number = ?, submission_date = ? WHERE device_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, device.getCustomerId());
            stmt.setString(2, device.getBrand());
            stmt.setString(3, device.getModel());
            stmt.setString(4, device.getSerialNumber());
            stmt.setTimestamp(5, new Timestamp(device.getSubmissionDate().getTime()));
            stmt.setInt(6, device.getDeviceId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMobileDevice(int deviceId) {
        String sql = "DELETE FROM mobile_devices WHERE device_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviceId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MobileDevice> getAllMobileDevices() {
        List<MobileDevice> devices = new ArrayList<>();
        String sql = "SELECT * FROM mobile_devices";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                devices.add(new MobileDevice(
                        rs.getInt("device_id"),
                        rs.getInt("customer_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("serial_number"),
                        rs.getTimestamp("submission_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }
}
