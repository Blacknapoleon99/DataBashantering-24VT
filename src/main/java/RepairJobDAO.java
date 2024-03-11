import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;

public class RepairJobDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseConfig.getUrl(),
                DatabaseConfig.getUser(),
                DatabaseConfig.getPassword());
    }

    public boolean insertRepairJob(RepairJob repairJob) {
        String sql = "INSERT INTO repair_jobs (device_id, problem_description, repair_status, estimated_completion_date, completion_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, repairJob.getDeviceId());
            stmt.setString(2, repairJob.getProblemDescription());
            stmt.setString(3, repairJob.getRepairStatus());
            stmt.setTimestamp(4, repairJob.getEstimatedCompletionDate());
            stmt.setTimestamp(5, repairJob.getCompletionDate());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RepairJob getRepairJobById(int jobId) {
        String sql = "SELECT * FROM repair_jobs WHERE job_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RepairJob(
                        rs.getInt("job_id"),
                        rs.getInt("device_id"),
                        rs.getString("problem_description"),
                        rs.getString("repair_status"),
                        rs.getTimestamp("estimated_completion_date"),
                        rs.getTimestamp("completion_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RepairJob> getAllRepairJobs() {
        List<RepairJob> repairJobs = new ArrayList<>();
        String sql = "SELECT * FROM repair_jobs";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                repairJobs.add(new RepairJob(
                        rs.getInt("job_id"),
                        rs.getInt("device_id"),
                        rs.getString("problem_description"),
                        rs.getString("repair_status"),
                        rs.getTimestamp("estimated_completion_date"),
                        rs.getTimestamp("completion_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repairJobs;
    }

    public boolean updateRepairJob(RepairJob repairJob) {
        String sql = "UPDATE repair_jobs SET device_id = ?, problem_description = ?, repair_status = ?, estimated_completion_date = ?, completion_date = ? WHERE job_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, repairJob.getDeviceId());
            stmt.setString(2, repairJob.getProblemDescription());
            stmt.setString(3, repairJob.getRepairStatus());
            stmt.setTimestamp(4, repairJob.getEstimatedCompletionDate());
            stmt.setTimestamp(5, repairJob.getCompletionDate());
            stmt.setInt(6, repairJob.getJobId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRepairJob(int jobId) {
        String sql = "DELETE FROM repair_jobs WHERE job_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jobId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
