package subway.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * create table station (
 *     id bigint generated by default as identity,
 *     name varchar(255) not null,
 *     primary key (id)
 * )
 */
@Entity
@Table(name = "station")
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "line_id")
	private Line line;

	protected Station() {
	}

	public Station(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Line getLine() {
		return line;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void setLine(Line line) {
		// 연관관계 편의 메소드는 쌍으로 구현해야 한다
		// 무한루프를 방지할 수 있는 방어로직을 구현해야 한다
		if (Objects.isNull(line)) {
			this.line = null;
			return;
		}

		if (this.line == line) {
			return;
		}

		this.line = line;
		line.addStation(this);
	}
}