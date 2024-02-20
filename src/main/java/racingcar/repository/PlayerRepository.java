package racingcar.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayerResult;
import racingcar.utils.DBUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayerRepository {
    private final DataSource dataSource;

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
            DBUtils.close(dataSource, conn, pstmt, null);
        }
    }

    public List<PlayerResult> getPlayers(int id) {
        List<PlayerResult> players = new ArrayList<>();

        String sql = "select * from player_result where id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                players.add(new PlayerResult(rs.getString("name"), rs.getInt("position")));
            }
            return players;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            DBUtils.close(dataSource, conn, pstmt, rs);
        }
    }
}
