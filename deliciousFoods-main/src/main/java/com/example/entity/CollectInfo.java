package com.example.entity;

import javax.persistence.*;

@Table(name = "collect_info")
public class CollectInfo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "time")
	private String time;
	@Column(name = "foodsId")
	private Long foodsId;
	@Column(name = "notesId")
	private Long notesId;
	@Column(name = "userId")
	private Long userId;
	private Integer level;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getFoodsId() {
		return foodsId;
	}
	public void setFoodsId(Long foodsId) {
		this.foodsId = foodsId;
	}


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

	public Long getNotesId() {
		return notesId;
	}

	public void setNotesId(Long notesId) {
		this.notesId = notesId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
