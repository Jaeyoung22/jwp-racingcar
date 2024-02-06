package racingcar.repository;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.PlayerResult;

import java.sql.*;

@Repository
@RequiredArgsConstructor
public class PlayRepository {
    private final DataSource dataSource;

    public int savePlay(PlayResult playResult) {
        String sql = "insert into play_result(winners) values(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, playResult.getWinners());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("id 조회 실패");
            }
            return rs.getInt("id");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public void savePlayer(int racingId, PlayerResult playerResult) {
        String sql = "insert into player_result(id, name, position) values(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, racingId);
            pstmt.setString(2, playerResult.getName());
            pstmt.setInt(3, playerResult.getPosition());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
