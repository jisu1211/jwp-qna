package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LineRepositoryTest {

    @Autowired
    private LineRepository lines;

    @Autowired
    private StationRepository stations;

    @Test
    void findById() {
        Line line = lines.findByName("3호선");
        assertThat(line.getStations()).hasSize(1);
    }

    @Test
    void save() {
        final Line line = new Line("2호선");
        final Station station = stations.save(new Station("잠실역"));
        line.addStation(station);
        lines.save(line);
        lines.flush();
    }

    @Test
    void name() {
        final Line line = new Line("2호선");
        final Station station = stations.save(new Station("잠실역"));
        station.setLine(line);
        lines.flush();
        assertThat(line.getStations()).isNotEmpty();
    }
}
