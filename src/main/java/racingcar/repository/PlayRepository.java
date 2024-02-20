package racingcar.repository;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            DBUtils.close(dataSource, conn, pstmt, rs);
        }
    }



    public List<PlayResult> getPlays() {
        List<PlayResult> plays = new ArrayList<>();

        String sql = "select * from play_result";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                plays.add(new PlayResult(rs.getInt("id"), rs.getString("winners")));
            }
            return plays;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            DBUtils.close(dataSource, conn, pstmt, rs);
        }
    }
}
