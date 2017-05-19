package com.helha.entities;

public class APIStatus {
	public String error = "OK";
	public String description = "/";
	
	public APIStatus(String error, String description)
	{
		this.description = description;
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "APIStatus [error=" + error + ", description=" + description + "]";
	}
}
