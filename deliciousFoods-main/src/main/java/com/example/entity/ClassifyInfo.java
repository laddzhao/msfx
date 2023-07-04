package com.example.entity;

import javax.persistence.*;

@Table(name = "classify_info")
public class ClassifyInfo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "descroiption")
	private String descroiption;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescroiption() {
		return descroiption;
	}
	public void setDescroiption(String descroiption) {
		this.descroiption = descroiption;
	}


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

}
